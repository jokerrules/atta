package org.assassin.jr.attabot.service.scheduler;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assassin.jr.attabot.config.CurrencySetting;
import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.pojo.exchange.IOrder;
import org.assassin.jr.attabot.pojo.exchange.ITicker;
import org.assassin.jr.attabot.pojo.exchange.OrderCategory;
import org.assassin.jr.attabot.service.exchange.ExchangeMarketService;
import org.assassin.jr.attabot.service.exchange.ExchangeServiceHandler;
import org.assassin.jr.attabot.service.management.OrderStored;
import org.assassin.jr.attabot.service.management.OrdersManagement;
import org.assassin.jr.attabot.service.management.ProfitManagement;
import org.assassin.jr.attabot.service.predictor.ExchangePredictorWrapper;
import org.assassin.jr.attabot.service.predictor.PredictParams;
import org.assassin.jr.attabot.service.predictor.PredictorStandard;
import org.assassin.jr.attabot.utility.AttaConstant;
import org.assassin.jr.attabot.utility.AttaMath;
import org.assassin.jr.attabot.utility.AttaUtility;

public class BidAskHandlerSimple implements BidAskHandler {
	private static Logger logger = LogManager.getLogger(BidAskHandlerSimple.class);

	private OrdersManagement orderManagement;

	public BidAskHandlerSimple() {
		orderManagement = OrdersManagement.getInstance();
	}

	@Override
	public void handleOrder(CurrencySetting currencySetting, ExchangeServiceHandler exchangeService, PredictorStandard predictor) throws Exception {
		if (!currencySetting.isActive()) {
			return;
		}
		
		ExchangePredictorWrapper exchangePredictor = predictor.getPreictorWrapper(currencySetting.getPlayOptions());

		IOrder currentOrder = null;
		PredictParams predictParams;
		OrderStored orderInfo = orderManagement.getOrder(currencySetting.getId());
		String currentOrderUuid = orderInfo == null ? null : orderInfo.getUuid();

		// get order status when order UUID exists in ordered management
		if (currentOrderUuid != null && !"".equals(currentOrderUuid)) {
			currentOrder = exchangeService.getAccountService().getOrder(currencySetting.getMarket(), currentOrderUuid);
		}

		boolean isWillCancelBidOrder = currentOrder != null && !currentOrder.isOrderCompleted() && currentOrder.isBuyOrder() && currencySetting.isCancelBidOrder();
		if (currencySetting.isCancelAllOrders() || isWillCancelBidOrder) {
			cancel(currencySetting, exchangeService, currentOrder);
			currencySetting.setActive(false);
			return;
		}

		// in case buying order status is cancelled or order UUID doesn't exist in order management, we force buy again
		boolean isFirstTimeOrder = currentOrder == null;
		boolean isBuyOrderWasCanceled = currentOrder != null && currentOrder.isOrdercancelled() && currentOrder.isBuyOrder();
		if (isFirstTimeOrder || isBuyOrderWasCanceled) {
			predictParams = createPredictorParams(currencySetting, exchangeService, predictor, currentOrder);
			forceBuyNow(exchangePredictor, currencySetting, exchangeService, predictParams);
			return;
		}

		// in case selling order status is cancelled, we force sell again
		boolean isSellOrderWasCancelled = currentOrder.isOrdercancelled() && currentOrder.isSellOrder();
		if (isSellOrderWasCancelled) {
			sellLimit(exchangeService.getMarketService(), currencySetting, orderInfo.getQuantiy(), orderInfo.getPrice(), currentOrder);
		}

		if (!currentOrder.isOrderCompleted()) {
			return;
		}

		predictParams = createPredictorParams(currencySetting, exchangeService, predictor, currentOrder);

		if (currentOrder.isBuyOrder()) {
			sell(exchangePredictor, currencySetting, exchangeService, predictParams);
			return;
		}

		buy(exchangePredictor, currencySetting, exchangeService, predictParams);
	}

	private PredictParams createPredictorParams(CurrencySetting currencySetting, ExchangeServiceHandler exchangeService, PredictorStandard predictor, IOrder currentOrder) throws ExchangeServiceRequestFailException {
		PredictParams predictParams = predictor.createPredictParam(exchangeService, currencySetting);

		ITicker ticker = exchangeService.getPublicService().getTicker(currencySetting.getMarket());
		predictParams.setTicker(ticker);
		predictParams.setCurrency(currencySetting);
		predictParams.setCurrentOrder(currentOrder);

		return predictParams;
	}

	private void cancel(CurrencySetting currency, ExchangeServiceHandler exchangeService, IOrder currentOrder) throws ExchangeServiceRequestFailException {
		if (currentOrder == null || currentOrder.isOrdercancelled()) {
			return;
		}

		AttaUtility.writeLogInfo(logger, currency, String.format("Begin cancel order : %s", currentOrder));
		exchangeService.getMarketService().cancel(currency.getMarket(), currentOrder.getOrderUuid());
		AttaUtility.writeLogInfo(logger, currency, String.format("End   cancel order : %s", currentOrder));
	}

	private void sell(ExchangePredictorWrapper exchangePredictor, CurrencySetting currency, ExchangeServiceHandler exchangeService, PredictParams predictParams) throws ExchangeServiceRequestFailException {
		IOrder currentOrder = predictParams.getCurrentOrder();

		double askPrice = exchangePredictor.getAskPredictor().predict(predictParams);

		// give up when ask price is invalid or last bid price >= current ask price
		if (askPrice < 0) {
			return;
		}

		if (currentOrder.getLimit() >= askPrice) {
			AttaUtility.writeLogWarn(logger, currency, String.format("[Unexpect] Last buy price >= sell price.[%f >= %f]; %s", currentOrder.getLimit(), askPrice, currentOrder));
			return;
		}

		double quantity = currentOrder.getQuantity();

		if (currency.getTradingFee() != null) {
			double lastTotalSellPrice = currentOrder.getLimit() * quantity;
			quantity = (lastTotalSellPrice - lastTotalSellPrice * currency.getTradingFee()) / currentOrder.getLimit();
		}

		quantity = AttaMath.roundQty(currency, quantity);
		askPrice = AttaMath.roundPrice(currency, askPrice);

		if (quantity * askPrice <= currentOrder.getLimit() * currentOrder.getQuantity()) {
			AttaUtility.writeLogWarn(logger, currency, String.format("[Unexpect] Total sell price < last buy price. askPrice=%.8f askQty=%.8f ; bidPrice=%.8f bidQty=%.8f; %s", askPrice, quantity, currentOrder.getLimit(), currentOrder.getQuantity(), currentOrder));

			askPrice = askPrice * (currentOrder.getQuantity() / quantity);
			askPrice = AttaMath.round(askPrice, currency.getPriceRound() == null ? AttaConstant.MAX_LOT : currency.getPriceRound());

			AttaUtility.writeLogWarn(logger, currency, String.format("[Unexpect] [%s|%s] Sell price become %.8f", currency.getId(), currency.getMarket(), askPrice));
		}

		sellLimit(exchangeService.getMarketService(), currency, quantity, askPrice, currentOrder);
	}

	private void forceBuyNow(ExchangePredictorWrapper exchangePredictor, CurrencySetting currency, ExchangeServiceHandler exchangeService, PredictParams predictParams) throws ExchangeServiceRequestFailException {
		logicBuy(exchangePredictor, currency, exchangeService, predictParams, true);
	}

	private void buy(ExchangePredictorWrapper exchangePredictor, CurrencySetting currency, ExchangeServiceHandler exchangeService, PredictParams predictParams) throws ExchangeServiceRequestFailException {
		logicBuy(exchangePredictor, currency, exchangeService, predictParams, false);
	}

	private void logicBuy(ExchangePredictorWrapper exchangePredictor, CurrencySetting currency, ExchangeServiceHandler exchangeService, PredictParams predictParams, boolean isBuyImmediately) throws ExchangeServiceRequestFailException {
		IOrder currentOrder = predictParams == null ? null : predictParams.getCurrentOrder();

		if (currency.isStopBid()) {
			AttaUtility.writeLogInfo(logger, currency, String.format("Stop bid order : %s", currentOrder));
			currency.setActive(false);
			return;
		}

		// we can't buy if last order is not complete or last order is BUY_LIMIT type
		if (currentOrder != null && currentOrder.isOrderCompleted() && currentOrder.isBuyOrder()) {
			return;
		}

		if (currency.getBidPriceCond() != null && predictParams.getTicker().getBid() < currency.getBidPriceCond()) {
			return;
		}

		if (currency.getBidPriceCondBelow() != null && predictParams.getTicker().getBid() > currency.getBidPriceCondBelow()) {
			return;
		}

		double bidPrice = currency.getDebutBid();

		// force buy now
		if (!isBuyImmediately) {
			bidPrice = exchangePredictor.getBidPredictor().predict(predictParams);
		}

		double quantity = currency.getBudget() / bidPrice;

		quantity = AttaMath.roundQty(currency, quantity);
		bidPrice = AttaMath.roundPrice(currency, bidPrice);
		
		if (bidPrice < 0 ) {
			return;
		}

		if (currentOrder != null && currentOrder.getLimit() * currentOrder.getQuantity() <= bidPrice * quantity) {
			AttaUtility.writeLogWarn(logger, currency, String.format("[Unexpect] Last sell price <= buy price [%f > %f]; %s", currentOrder.getLimit(), bidPrice, currentOrder));
			return;
		}

		if (bidPrice > predictParams.getTicker().getBid()) {
			AttaUtility.writeLogWarn(logger, currency, String.format("[Unexpect] Bid Order will be cancelled. Because Bid price > market price [%f > %f]; %s", bidPrice, predictParams.getTicker().getBid(), currentOrder));
			return;
		}

		buyLimit(exchangeService.getMarketService(), currency, quantity, bidPrice, currentOrder);
	}

	private synchronized String buyLimit(ExchangeMarketService marketService, CurrencySetting currency, double quantity, double bidPrice, IOrder currentOrder) throws ExchangeServiceRequestFailException {
		AttaUtility.writeLogInfo(logger, currency, String.format("Last Order: %s", currentOrder == null ? "NULL" : currentOrder.toString()));
		writeBeginOrder(OrderCategory.BUY.toString(), currency, quantity, bidPrice);

		String uuid = marketService.buyLimit(currency.getMarket(), quantity, bidPrice);
		saveOrdered(currency, quantity, bidPrice, OrderCategory.BUY, uuid);

		writeEndLogOrder(OrderCategory.BUY.toString(), currency, quantity, bidPrice, uuid);
		return uuid;
	}

	private synchronized String sellLimit(ExchangeMarketService marketService, CurrencySetting currency, double quantity, double askPrice, IOrder currentOrder) throws ExchangeServiceRequestFailException {
		AttaUtility.writeLogInfo(logger, currency, String.format("Last Order: %s", currentOrder == null ? "NULL" : currentOrder.toString()));
		writeBeginOrder(OrderCategory.SELL.toString(), currency, quantity, askPrice);

		String uuid = marketService.sellLimit(currency.getMarket(), quantity, askPrice);
		OrderStored orderStored = saveOrdered(currency, quantity, askPrice, OrderCategory.SELL, uuid);
		ProfitManagement.getInstance().addProfit(new Date(), currency.getId(), orderStored.getLastProfit(), currency.getProfitRate());
		ProfitManagement.getInstance().save();

		writeEndLogOrder(OrderCategory.SELL.toString(), currency, quantity, askPrice, uuid);
		return uuid;
	}

	private OrderStored saveOrdered(CurrencySetting currency, double quantity, double price, OrderCategory orderCat, String uuid) {
		OrderStored orderStored = new OrderStored(currency.getMarket(), quantity, price, orderCat, uuid, orderManagement.getOrder(currency.getId()));
		orderManagement.putOrder(currency.getId(), orderStored);
		orderManagement.save();
		return orderStored;
	}

	private void writeBeginOrder(String type, CurrencySetting currency, double quantity, double price) {
		String qtyText = BigDecimal.valueOf(quantity).toPlainString();
		String priceText = BigDecimal.valueOf(price).toPlainString();
		String logContent = String.format("Begin %s Makert:%-10s   Quantity:%-10s   Rate:%-10s", type, currency.getMarket(), qtyText, priceText);
		AttaUtility.writeLogInfo(logger, currency, logContent);
	}

	private void writeEndLogOrder(String type, CurrencySetting currency, double quantity, double price, String uuid) {
		String qtyText = BigDecimal.valueOf(quantity).toPlainString();
		String priceText = BigDecimal.valueOf(price).toPlainString();
		String logContent = String.format("End   %s Makert:%-10s   Quantity:%-10s   Rate:%-10s   UUID:%-36s", type, currency.getMarket(), qtyText, priceText, uuid);
		AttaUtility.writeLogInfo(logger, currency, logContent);
	}
}
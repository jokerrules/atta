package org.assassin.jr.attabot.service.exchange.binance;

import java.math.BigDecimal;
import java.util.List;

import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.pojo.exchange.IOrder;
import org.assassin.jr.attabot.service.exchange.ExchangeMarketService;

import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.NewOrder;
import com.binance.api.client.domain.account.NewOrderResponse;

public class BinanceMarketSerivce extends BinanceExchangeStandardService implements ExchangeMarketService {
	public BinanceMarketSerivce(String apiKey, String apiKeySerect) {
		super(apiKey, apiKeySerect);
	}

	@Override
	public synchronized String buyLimit(String market, double quantity, double rate) throws ExchangeServiceRequestFailException {
		String priceText = BigDecimal.valueOf(rate).toPlainString();
		String qtyText = quantity + "";

		NewOrderResponse newOrderResponse = getBnApiclient().newOrder(NewOrder.limitBuy(convertToBinanceMarket(market), TimeInForce.GTC, qtyText, priceText));
		return newOrderResponse.getClientOrderId();
	}

	@Override
	public synchronized String sellLimit(String market, double quantity, double rate) throws ExchangeServiceRequestFailException {
		String priceText = BigDecimal.valueOf(rate).toPlainString();
		String qtyText = quantity + "";

		NewOrderResponse newOrderResponse = getBnApiclient().newOrder(NewOrder.limitSell(convertToBinanceMarket(market), TimeInForce.GTC, qtyText, priceText));
		return newOrderResponse.getClientOrderId();
	}

	@Override
	public boolean cancel(String uuid) throws ExchangeServiceRequestFailException {
		throw new UnsupportedOperationException("cancel");
	}

	@Override
	public List<IOrder> getOpenOrders(String market) throws ExchangeServiceRequestFailException {
		throw new UnsupportedOperationException("getOpenOrders");
	}
}
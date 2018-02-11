package org.assassin.jr.attabot.service.scheduler;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.assassin.jr.attabot.config.CurrencySetting;
import org.assassin.jr.attabot.config.PredictType;
import org.assassin.jr.attabot.pojo.exchange.IOrder;
import org.assassin.jr.attabot.pojo.exchange.ITicker;
import org.assassin.jr.attabot.pojo.exchange.bittrex.BittrexOpenOrder;
import org.assassin.jr.attabot.pojo.exchange.bittrex.BittrexOrderHistory;
import org.assassin.jr.attabot.pojo.exchange.bittrex.BittrexTicker;
import org.assassin.jr.attabot.service.exchange.ExchangeAccountService;
import org.assassin.jr.attabot.service.exchange.ExchangeMarketService;
import org.assassin.jr.attabot.service.exchange.ExchangePublicService;
import org.assassin.jr.attabot.service.exchange.ExchangeServiceHandler;
import org.assassin.jr.attabot.service.exchange.bittrex.BittrexAccountService;
import org.assassin.jr.attabot.service.exchange.bittrex.BittrexExchangeService;
import org.assassin.jr.attabot.service.exchange.bittrex.BittrexMaketService;
import org.assassin.jr.attabot.service.exchange.bittrex.BittrexPublicService;
import org.assassin.jr.attabot.service.management.OrdersManagement;
import org.assassin.jr.attabot.service.predictor.PredictorFactoryCache;
import org.assassin.jr.attabot.utility.AttaConstant;
import org.junit.Test;
import org.mockito.Mockito;

public class BidAskHandlerSimpleTest {

	@Test
	public void testBidFirstTime() throws Exception {
		System.out.println("==================================================================");
		System.out.println("testBidFirstTime");
		BidAskHandlerSimple handleSimpleSpy = spy(new BidAskHandlerSimple());
		CurrencySetting currency = getStandardCurrencySetting();
		ExchangeServiceHandler exchangeServiceSpy = getStandardMockExchangeService(currency);
		
		handleSimpleSpy.handleOrder(currency, exchangeServiceSpy, PredictorFactoryCache.getPredictor(PredictType.OVERLAP));

		ITicker ticker = exchangeServiceSpy.getPublicService().getTicker(currency.getMarket());
		verify(exchangeServiceSpy.getMarketService(), times(0)).sellLimit(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble());
		verify(exchangeServiceSpy.getMarketService(), times(1)).buyLimit(currency.getMarket(), currency.getBudget() / ticker.getBid(), ticker.getBid());
		System.out.println("==================================================================");
	}

	@Test
	public void testBidSecondTime() throws Exception {
		System.out.println("==================================================================");
		System.out.println("testBidSecondTime");
		// check time 1
		BidAskHandlerSimple handleSimpleSpy = spy(new BidAskHandlerSimple());
		CurrencySetting currency = getStandardCurrencySetting();
		ExchangeServiceHandler exchangeServiceSpy = getStandardMockExchangeService(currency);
		//handleSimpleSpy.handleOrder(currency, exchangeServiceSpy, new ExchangePredictorWrapper(PredictType.OVERLAP, spy(new PredictorOverlapBid()), spy(new PredictorOverlapAsk())));

		ITicker ticker = exchangeServiceSpy.getPublicService().getTicker(currency.getMarket());
		verify(exchangeServiceSpy.getMarketService(), times(0)).sellLimit(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble());
		verify(exchangeServiceSpy.getMarketService(), times(1)).buyLimit(currency.getMarket(), currency.getBudget() / ticker.getBid(), ticker.getBid());

		// check time 2
		List<IOrder> lstOpenOrder = new ArrayList<>();
		lstOpenOrder.add(new BittrexOpenOrder(OrdersManagement.getInstance().getOrder(currency.getId()).getUuid()));
		Mockito.when(exchangeServiceSpy.getMarketService().getOpenOrders(currency.getMarket())).thenReturn(lstOpenOrder);

		handleSimpleSpy.handleOrder(currency, exchangeServiceSpy, PredictorFactoryCache.getPredictor(PredictType.OVERLAP));

		verify(exchangeServiceSpy.getMarketService(), times(0)).sellLimit(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble());
		verify(exchangeServiceSpy.getMarketService(), times(1)).buyLimit(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble());
		System.out.println("==================================================================");
	}

	@Test
	public void testAskFirstTime() throws Exception {
		System.out.println("==================================================================");
		System.out.println("testAskFirstTime");

		// bid
		testBidFirstTime();

		// ask
		BidAskHandlerSimple handleSimpleSpy = spy(new BidAskHandlerSimple());
		CurrencySetting currency = getStandardCurrencySetting();
		ExchangeServiceHandler exchangeServiceSpy = getStandardMockExchangeService(currency);

		ITicker ticker = exchangeServiceSpy.getPublicService().getTicker(currency.getMarket());
		List<IOrder> lstOrderHistory = new ArrayList<>();
		BittrexOrderHistory orderHistory = new BittrexOrderHistory(OrdersManagement.getInstance().getOrder(currency.getId()).getUuid());
		orderHistory.setPrice(ticker.getBid());
		orderHistory.setQuantity(currency.getBudget() / ticker.getBid());
		orderHistory.setOrderType(AttaConstant.LIMIT_BUY);
		lstOrderHistory.add(orderHistory);
		Mockito.when(exchangeServiceSpy.getAccountService().getOrderHistory(currency.getMarket())).thenReturn(lstOrderHistory);

		handleSimpleSpy.handleOrder(currency, exchangeServiceSpy, PredictorFactoryCache.getPredictor(PredictType.OVERLAP));

		double sellPrice = orderHistory.getPrice() + orderHistory.getPrice() * currency.getProfitRate();
		verify(exchangeServiceSpy.getMarketService(), times(0)).buyLimit(Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble());
		verify(exchangeServiceSpy.getMarketService(), times(1)).sellLimit(currency.getMarket(), orderHistory.getQuantity(), sellPrice);
		System.out.println("==================================================================");
	}

	private CurrencySetting getStandardCurrencySetting() throws Exception {
		return new CurrencySetting("BETH-1", "BETH", 0.01f, 0.5f, 0.25f, true, 0.00000867);
	}

	private ExchangeServiceHandler getStandardMockExchangeService(CurrencySetting currencySetting) throws Exception {
		String apiKeyTest = "api-key-123";
		String apiKeySerectTest = "api-key-serect-123";
		String buyUuid = "uuid-buy-key-12345";

		ExchangePublicService publicServiceSpy = spy(new BittrexPublicService(apiKeyTest, apiKeySerectTest));
		BittrexTicker ticker = new BittrexTicker(0.000535f, 0.0005f, 0.00051f);
		doReturn(ticker).when(publicServiceSpy).getTicker(currencySetting.getMarket());

		ExchangeMarketService marketServiceSpy = spy(new BittrexMaketService(apiKeyTest, apiKeySerectTest));
		List<BittrexOpenOrder> lstOpenOrder = new ArrayList<BittrexOpenOrder>();
		doReturn(lstOpenOrder).when(marketServiceSpy).getOpenOrders(currencySetting.getMarket());
		Mockito.when((marketServiceSpy.buyLimit(currencySetting.getMarket(), currencySetting.getBudget() / ticker.getBid(), ticker.getBid()))).thenReturn(buyUuid);

		ExchangeAccountService accountServiceSpy = spy(new BittrexAccountService(apiKeyTest, apiKeySerectTest));
		List<BittrexOrderHistory> lstOrderHistory = new ArrayList<>();
		doReturn(lstOrderHistory).when(accountServiceSpy).getOrderHistory(currencySetting.getMarket());

		ExchangeServiceHandler exchangeServiceSpy = spy(new BittrexExchangeService(apiKeyTest, apiKeySerectTest));
		doReturn(marketServiceSpy).when(exchangeServiceSpy).getMarketService();
		doReturn(accountServiceSpy).when(exchangeServiceSpy).getAccountService();
		doReturn(publicServiceSpy).when(exchangeServiceSpy).getPublicService();

		return exchangeServiceSpy;
	}
}
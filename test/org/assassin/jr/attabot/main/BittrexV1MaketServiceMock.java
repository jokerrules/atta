package org.assassin.jr.attabot.main;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.pojo.exchange.IOrder;
import org.assassin.jr.attabot.service.exchange.ExchangeMarketService;
import org.assassin.jr.attabot.service.exchange.bittrex.BittrexExchangeStandardService;

public class BittrexV1MaketServiceMock extends BittrexExchangeStandardService implements ExchangeMarketService {
	private static Logger logger = LogManager.getLogger(BittrexV1MaketServiceMock.class);

	public BittrexV1MaketServiceMock(String apiKey, String apiKeySerect) {
		super(apiKey, apiKeySerect);
	}

	@Override
	public synchronized String buyLimit(String market, double quantity, double rate) throws ExchangeServiceRequestFailException {
		String logContent = String.format("Buy Makert:%-10s   Quantity:%-13f   Rate:%10.9f", market, quantity, rate);
		logger.info(logContent);
		// DynamicLogger.getInstance().getLogger(market).info(logContent);

		return "uuid-buy-success-01";
	}

	@Override
	public synchronized String sellLimit(String market, double quantity, double rate) throws ExchangeServiceRequestFailException {
		String logContent = String.format("Sell Makert:%-10s   Quantity:%-13f   Rate:%10.9f", market, quantity, rate);
		logger.info(logContent);
		// DynamicLogger.getInstance().getLogger(market).info(logContent);
		return "uuid-sell-success-01";
	}

	@Override
	public boolean cancel(String market, String uuid) throws ExchangeServiceRequestFailException {
		return false;
	}

	@Override
	public List<IOrder> getOpenOrders(String market) throws ExchangeServiceRequestFailException {
		return new ArrayList<IOrder>();
	}

}

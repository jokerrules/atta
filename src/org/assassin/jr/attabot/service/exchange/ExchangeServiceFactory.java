package org.assassin.jr.attabot.service.exchange;

import java.util.HashMap;
import java.util.Map;

import org.assassin.jr.attabot.config.ExchangeMarket;
import org.assassin.jr.attabot.service.exchange.binance.BinanceExchangeService;
import org.assassin.jr.attabot.service.exchange.bittrex.BittrexExchangeService;

public class ExchangeServiceFactory {
	private final static Map<ExchangeMarket, ExchangeServiceHandler> mapExchangeServiceCache = new HashMap<>();

	public static ExchangeServiceHandler getService(ExchangeMarket exchangeName, String apiKey, String apiKeySerect) {
		if (!mapExchangeServiceCache.containsKey(exchangeName)) {
			mapExchangeServiceCache.put(exchangeName, createService(exchangeName, apiKey, apiKeySerect));
		}
		return mapExchangeServiceCache.get(exchangeName);
	}

	private static ExchangeServiceHandler createService(ExchangeMarket exchangeName, String apiKey, String apiKeySerect) {
		switch (exchangeName) {
		case BITTREX:
			return new BittrexExchangeService(apiKey, apiKeySerect);

		case BINANCE:
			return new BinanceExchangeService(apiKey, apiKeySerect);

		default:
			throw new NullPointerException(exchangeName + " doens't found");
		}
	}
}

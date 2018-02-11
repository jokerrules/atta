package org.assassin.jr.attabot.service.exchange.binance;

import org.assassin.jr.attabot.service.exchange.ExchangeAccountService;
import org.assassin.jr.attabot.service.exchange.ExchangeMarketService;
import org.assassin.jr.attabot.service.exchange.ExchangePublicService;
import org.assassin.jr.attabot.service.exchange.ExchangeServiceHandler;

public class BinanceExchangeService extends ExchangeServiceHandler {

	public BinanceExchangeService(String apiKey, String apiKeySerect) {
		super(apiKey, apiKeySerect);
	}

	@Override
	protected ExchangeAccountService createAccountService(String apiKey, String apiKeySerect) {
		return new BinanceAccountService(apiKey, apiKeySerect);
	}

	@Override
	protected ExchangeMarketService createMarketService(String apiKey, String apiKeySerect) {
		return new BinanceMarketSerivce(apiKey, apiKeySerect);
	}

	@Override
	protected ExchangePublicService createPublicService(String apiKey, String apiKeySerect) {
		return new BinancePublicService(apiKey, apiKeySerect);
	}

}

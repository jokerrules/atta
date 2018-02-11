package org.assassin.jr.attabot.service.exchange.bittrex;

import org.assassin.jr.attabot.service.exchange.ExchangeAccountService;
import org.assassin.jr.attabot.service.exchange.ExchangeMarketService;
import org.assassin.jr.attabot.service.exchange.ExchangePublicService;
import org.assassin.jr.attabot.service.exchange.ExchangeServiceHandler;

public class BittrexExchangeService extends ExchangeServiceHandler {

	public BittrexExchangeService(String apiKey, String apiKeySerect) {
		super(apiKey, apiKeySerect);
	}

	@Override
	protected ExchangeAccountService createAccountService(String apiKey, String apiKeySerect) {
		return new BittrexAccountService(apiKey, apiKeySerect);
	}

	@Override
	protected ExchangeMarketService createMarketService(String apiKey, String apiKeySerect) {
		return new BittrexMaketService(apiKey, apiKeySerect);
	}

	@Override
	protected ExchangePublicService createPublicService(String apiKey, String apiKeySerect) {
		return new BittrexPublicService(apiKey, apiKeySerect);
	}
}
package org.assassin.jr.attabot.main;

import org.assassin.jr.attabot.service.exchange.ExchangeAccountService;
import org.assassin.jr.attabot.service.exchange.ExchangeMarketService;
import org.assassin.jr.attabot.service.exchange.ExchangePublicService;
import org.assassin.jr.attabot.service.exchange.ExchangeServiceHandler;
import org.assassin.jr.attabot.service.exchange.bittrex.BittrexAccountService;
import org.assassin.jr.attabot.service.exchange.bittrex.BittrexPublicService;

public class BittrexV1ExchangeServiceMock extends ExchangeServiceHandler {

	public BittrexV1ExchangeServiceMock(String apiKey, String apiKeySerect) {
		super(apiKey, apiKeySerect);
	}

	@Override
	protected ExchangeAccountService createAccountService(String apiKey, String apiKeySerect) {
		return new BittrexAccountService(apiKey, apiKeySerect);
	}

	@Override
	protected ExchangeMarketService createMarketService(String apiKey, String apiKeySerect) {
		return new BittrexV1MaketServiceMock(apiKey, apiKeySerect);
	}

	@Override
	protected ExchangePublicService createPublicService(String apiKey, String apiKeySerect) {
		return new BittrexPublicService(apiKey, apiKeySerect);
	}

}

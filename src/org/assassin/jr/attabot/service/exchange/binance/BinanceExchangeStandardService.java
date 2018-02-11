package org.assassin.jr.attabot.service.exchange.binance;

import org.assassin.jr.attabot.service.exchange.ExchangeStandardService;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;

public class BinanceExchangeStandardService extends ExchangeStandardService {
	private BinanceApiClientFactory bnfactory;
	private BinanceApiRestClient bnApiclient;

	public BinanceExchangeStandardService(String apiKey, String apiKeySerect) {
		super(apiKey, apiKeySerect);
		bnfactory = BinanceApiClientFactory.newInstance(apiKey, apiKeySerect);
		bnApiclient = bnfactory.newRestClient();
	}

	protected BinanceApiClientFactory getBnfactory() {
		return bnfactory;
	}

	protected BinanceApiRestClient getBnApiclient() {
		return bnApiclient;
	}

	protected String convertToBinanceMarket(String market) {
		return market.replace("-", "");
	}
}
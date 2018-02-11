package org.assassin.jr.attabot.service.exchange.imp.binance;

import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.service.exchange.binance.BinanceAccountService;
import org.junit.Test;

public class BinanceAccountServiceTest {

	@Test
	public void testGetOrder() throws ExchangeServiceRequestFailException {
		// id ordered K8AkxZhB63HjHt9TGndZX3
		BinanceAccountService as = new BinanceAccountService(BinaceApiKey.apiKey, BinaceApiKey.apiKeySerect);
		System.out.println(as.getOrder("ICXBTC", "DVDAMcfMpp93FDbBhzzYej"));
	}
}
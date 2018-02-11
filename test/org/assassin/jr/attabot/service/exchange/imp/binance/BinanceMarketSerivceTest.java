package org.assassin.jr.attabot.service.exchange.imp.binance;

import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.service.exchange.binance.BinanceMarketSerivce;
import org.junit.Test;

public class BinanceMarketSerivceTest {

	@Test
	public void testBuyLimit() throws ExchangeServiceRequestFailException {
		BinanceMarketSerivce ms = new BinanceMarketSerivce(BinaceApiKey.apiKey, BinaceApiKey.apiKeySerect);
		System.out.println(ms.buyLimit("ADABTC", 50, 0.00004259));
	}

}

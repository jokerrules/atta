package org.assassin.jr.attabot.service.exchange.imp.binance;

import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.pojo.exchange.TickInterval;
import org.assassin.jr.attabot.service.exchange.binance.BinancePublicService;
import org.junit.Test;

public class BinancePublicServiceTest {

	//@Test
	public void testGetTick() throws ExchangeServiceRequestFailException {
		BinancePublicService bs = new BinancePublicService(BinaceApiKey.apiKey, BinaceApiKey.apiKeySerect);
		System.out.println(bs.getTicks("ETHBTC", TickInterval.THIRTY_MIN));
	}
	
	@Test
	public void testGetTicker() throws ExchangeServiceRequestFailException {
		BinancePublicService bs = new BinancePublicService(BinaceApiKey.apiKey, BinaceApiKey.apiKeySerect);
		System.out.println(bs.getTicker("XVGBTC"));
	}
}
package org.assassin.jr.attabot.service.exchange.imp.bittrex;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.pojo.exchange.IMarket;
import org.assassin.jr.attabot.service.exchange.ExchangePublicService;
import org.assassin.jr.attabot.service.exchange.bittrex.BittrexPublicService;
import org.junit.Before;
import org.junit.Test;

public class BittrexPublicServiceTest {
	private String apiKey;
	private String apiKeySerect;

	@Before
	public void before() {
		apiKey = "1234";
		apiKeySerect = "987";
	}

	@Test
	public void testGetMarkets() throws ExchangeServiceRequestFailException {
		ExchangePublicService ps = new BittrexPublicService(apiKey, apiKeySerect);
		List<IMarket> lst = ps.getMarkets();
		System.out.println(lst);
		assertTrue(lst.size() > 0);
		System.out.println(lst.get(0).getBaseCurrency());
	}

	@Test
	public void testGetCurrencies() {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetTicker() {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetMarketSummary() {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetOrderBook() {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetMarketHistory() {
		// fail("Not yet implemented");
	}
}

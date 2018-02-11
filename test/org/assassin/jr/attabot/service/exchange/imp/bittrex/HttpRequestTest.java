package org.assassin.jr.attabot.service.exchange.imp.bittrex;

import static org.junit.Assert.assertTrue;

import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.service.exchange.bittrex.BittrexExchangeStandardService;
import org.junit.Before;
import org.junit.Test;

public class HttpRequestTest {
	private String apiKey = "123";
	private String apiKeySerect = "456";
	private BittrexExchangeStandardService sService;

	@Before
	public void before() {
		sService = new BittrexExchangeStandardService(apiKey, apiKeySerect);
	}

	@Test
	public void testRequestServerDown() {
		String url = "http://testabcxasdf.com";

		try {
			sService.getDataByGetMethod(url, "");
			assertTrue(false);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertTrue(true);
		}

		try {
			sService.getDataByGetMethodWithAPIKey(url, "");
			assertTrue(false);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			assertTrue(true);
		}
	}

	@Test
	public void testRequestCantParseJSON() {
		String url = "http://www.google.com/";
		try {
			sService.getDataByGetMethodWithAPIKey(url, "a");
			assertTrue(false);
		} catch (ExchangeServiceRequestFailException e) {
			System.out.println(e.getMessage());
			assertTrue(true);
		}
	}

	public void testRequestJsonSuccessFail() {

	}

	@Test
	public void testRequestNotFound() {
		String url = "http://www.google.com/abcdef";
		try {
			sService.getDataByGetMethodWithAPIKey(url, "a");
			assertTrue(false);
		} catch (ExchangeServiceRequestFailException e) {
			System.out.println(e.getMessage());
			assertTrue(true);
		}
	}
}
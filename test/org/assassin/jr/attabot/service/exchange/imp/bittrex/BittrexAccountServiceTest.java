package org.assassin.jr.attabot.service.exchange.imp.bittrex;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.pojo.exchange.IBalance;
import org.assassin.jr.attabot.pojo.exchange.IOrder;
import org.assassin.jr.attabot.service.exchange.ExchangeAccountService;
import org.assassin.jr.attabot.service.exchange.bittrex.BittrexAccountService;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BittrexAccountServiceTest {
	private String apiKey = BittrexApiKey.apiKey;
	private String apiKeySerect = BittrexApiKey.apiKeySerect;

	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	/* @Test */
	public void testGetBalances() throws ExchangeServiceRequestFailException {
		ExchangeAccountService as = new BittrexAccountService(apiKey, apiKeySerect);
		List<IBalance> lstBalance = as.getBalances();
		// System.out.println(lstBalance);
		assertTrue(lstBalance.size() > 0);
	}

	/* @Test */
	public void testGetOrderHistory() throws ExchangeServiceRequestFailException {
		ExchangeAccountService as = new BittrexAccountService(apiKey, apiKeySerect);
		// List<OrderHistory> lstOrderHistory = as.getOrderHistory("BTC-ADA");
		List<IOrder> lstOrderHistory = as.getOrderHistory("BTC-XVG");
		// System.out.println(lstOrderHistory);
		assertTrue(lstOrderHistory.size() > 0);
	}

	/* @Test */
	public void testGetOrderOpen() throws ExchangeServiceRequestFailException {
		ExchangeAccountService as = new BittrexAccountService(apiKey, apiKeySerect);
		IOrder order = as.getOrder("", "cf4ab9fc-4c1e-4804-b574-4f52a7da5933");
		System.out.println("order is open : " + gson.toJson(order));
	}

	/* @Test */
	public void testGetOrderClosed() throws ExchangeServiceRequestFailException {
		ExchangeAccountService as = new BittrexAccountService(apiKey, apiKeySerect);
		IOrder order = as.getOrder("", "f65040e2-7082-4df0-9b01-6160b5a37534");
		System.out.println("order is closed : " + order);
		// System.out.println(as.getOrderHistory("BTC-ADA").get(0).getOrderUuid());
	}

	/* @Test */
	public void testGetOrderFail() throws ExchangeServiceRequestFailException {
		ExchangeAccountService as = new BittrexAccountService(apiKey, apiKeySerect);
		IOrder order = as.getOrder("", "21111111-1111-1111-9b01-6160b5a37534");
		System.out.println(order);
	}

	/* @Test */
	public void testGetOrderCancelled() throws ExchangeServiceRequestFailException {
		ExchangeAccountService as = new BittrexAccountService(apiKey, apiKeySerect);
		IOrder order = as.getOrder("", "15b267de-c3c5-40cd-ac39-2022da8c1f08");
		System.out.println(gson.toJson(order));
	}

	// @Test
	public void testGetOrderValidUuid() throws ExchangeServiceRequestFailException {
		ExchangeAccountService as = new BittrexAccountService(apiKey, apiKeySerect);
		IOrder order = as.getOrder("", "9c1974ba-e220-407c-83a7-d69aa0d21029");
		System.out.println("order is open : " + gson.toJson(order));
	}

	//
	@Test
	public void testGetOrderEmptyUuid() throws ExchangeServiceRequestFailException {
		try {
			ExchangeAccountService as = new BittrexAccountService(apiKey, apiKeySerect);
			IOrder order = as.getOrder("", "");
			System.out.println("order is open : " + gson.toJson(order));
			assertTrue(false);

		} catch (ExchangeServiceRequestFailException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetOrderWrongUuid() throws ExchangeServiceRequestFailException {
		ExchangeAccountService as = new BittrexAccountService(apiKey, apiKeySerect);
		IOrder order = as.getOrder("", "9c197411-e220-407c-83a7-d69aa0d21021");
		assert (order == null);
		System.out.println("order is open : " + gson.toJson(order));
	}

	// @Test
	public void testGetOrderInvalidUuid() throws ExchangeServiceRequestFailException {
		try {
			ExchangeAccountService as = new BittrexAccountService(apiKey, apiKeySerect);
			IOrder order = as.getOrder("", "123");
			System.out.println("order is open : " + gson.toJson(order));
			assertTrue(false);

		} catch (ExchangeServiceRequestFailException e) {
			assertTrue(true);
		}
	}
}
package org.assassin.jr.attabot.service.exchange.imp.bittrex;

import java.util.List;

import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.pojo.exchange.IOrder;
import org.assassin.jr.attabot.service.exchange.ExchangeMarketService;
import org.assassin.jr.attabot.service.exchange.bittrex.BittrexMaketService;
import org.junit.Test;

public class BittrexV1MaketServiceTest {

	@Test
	public void testGetOpenOrders() throws ExchangeServiceRequestFailException {
		ExchangeMarketService ms = new BittrexMaketService(BittrexApiKey.apiKey, BittrexApiKey.apiKeySerect);
		List<IOrder> lstOpenOrders = ms.getOpenOrders("BTC-ADA");
		System.out.println(lstOpenOrders);
	}

}

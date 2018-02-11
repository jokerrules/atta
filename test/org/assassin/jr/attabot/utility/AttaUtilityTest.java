package org.assassin.jr.attabot.utility;

import org.assassin.jr.attabot.service.exchange.imp.bittrex.BittrexApiKey;
import org.junit.Test;

public class AttaUtilityTest {

	// @Test
	public void test() {
		String apiKeySerect = BittrexApiKey.apiKeySerect;
		String newUrl = "https://bittrex.com/api/v1.1/account/getorderhistory?apikey=431e4e95011a4f7aa3032d09ec75a36e&nonce=1516299076456&market=BTC-XVG";
		System.out.println(AttaUtility.calculateHMAC(newUrl, apiKeySerect));

		newUrl = "https://bittrex.com/api/v1.1/account/getorderhistory?apikey=431e4e95011a4f7aa3032d09ec75a36e&nonce=1516299278214&market=BTC-XVG";
		System.out.println(AttaUtility.calculateHMAC(newUrl, apiKeySerect));

		newUrl = "https://bittrex.com/api/v1.1/account/getorderhistory?apikey=431e4e95011a4f7aa3032d09ec75a36e&nonce=1516299279213&market=BTC-XVG";
		System.out.println(AttaUtility.calculateHMAC(newUrl, apiKeySerect));
	}

	@Test
	public void testRoundNumber() {
		Double d = AttaMath.round(123, 3);
		System.out.println(d);
		assert (d == 123.0);

		d = AttaMath.round(0.3, 3);
		System.out.println(d);
		assert (d == 0.3);

		d = AttaMath.round(0.12, 3);
		System.out.println(d);
		assert (d == 0.12);

		d = AttaMath.round(0.125, 3);
		System.out.println(d);
		assert (d == 0.125);

		d = AttaMath.round(3.125, 0);
		System.out.println(d);
		assert (d == 3);

		d = AttaMath.round(123.43599, 3);
		System.out.println(d);
		assert (d == 123.435);
	}
}

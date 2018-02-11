package org.assassin.jr.attabot.service.predictor.bollingerband;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.assassin.jr.attabot.pojo.exchange.bittrex.BittrexOrder;
import org.assassin.jr.attabot.pojo.exchange.bittrex.BittrexTick;
import org.assassin.jr.attabot.pojo.exchange.bittrex.BittrexTicker;
import org.assassin.jr.attabot.service.predictor.PredictParams;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;

public class PredictorBollingerBandTest {
	// ETH :BollingerBands [middleBand=0.09372648949999998, upperBand=0.09505129994729386, lowerBand=0.0924016790527061]
	private String ethJson = "[{\"O\":0.09377000,\"H\":0.09439996,\"L\":0.09324301,\"C\":0.09409990,\"V\":670.20056972,\"T\":\"2018-01-25T16:00:00\",\"BV\":62.97144726},{\"O\":0.09409980,\"H\":0.09409990,\"L\":0.09399960,\"C\":0.09409971,\"V\":288.50486306,\"T\":\"2018-01-25T16:30:00\",\"BV\":27.14104415},{\"O\":0.09409970,\"H\":0.09469617,\"L\":0.09409970,\"C\":0.09454999,\"V\":591.16495756,\"T\":\"2018-01-25T17:00:00\",\"BV\":55.78852136},{\"O\":0.09431671,\"H\":0.09469615,\"L\":0.09431671,\"C\":0.09443511,\"V\":385.43441072,\"T\":\"2018-01-25T17:30:00\",\"BV\":36.43116249},{\"O\":0.09467900,\"H\":0.09467900,\"L\":0.09411501,\"C\":0.09437590,\"V\":606.72451890,\"T\":\"2018-01-25T18:00:00\",\"BV\":57.29491847},{\"O\":0.09437590,\"H\":0.09437590,\"L\":0.09411000,\"C\":0.09437000,\"V\":275.49943833,\"T\":\"2018-01-25T18:30:00\",\"BV\":25.95795466},{\"O\":0.09437000,\"H\":0.09492787,\"L\":0.09411501,\"C\":0.09492787,\"V\":483.87531029,\"T\":\"2018-01-25T19:00:00\",\"BV\":45.79418039},{\"O\":0.09492787,\"H\":0.09492998,\"L\":0.09471002,\"C\":0.09491000,\"V\":439.75503021,\"T\":\"2018-01-25T19:30:00\",\"BV\":41.67893726},{\"O\":0.09480475,\"H\":0.09493000,\"L\":0.09425900,\"C\":0.09487900,\"V\":325.30014562,\"T\":\"2018-01-25T20:00:00\",\"BV\":30.85421131},{\"O\":0.09487900,\"H\":0.09493000,\"L\":0.09470000,\"C\":0.09493000,\"V\":330.60310321,\"T\":\"2018-01-25T20:30:00\",\"BV\":31.37924986},{\"O\":0.09485061,\"H\":0.09493000,\"L\":0.09446736,\"C\":0.09446736,\"V\":616.12729038,\"T\":\"2018-01-25T21:00:00\",\"BV\":58.35595382},{\"O\":0.09470000,\"H\":0.09479999,\"L\":0.09410000,\"C\":0.09466631,\"V\":630.70595806,\"T\":\"2018-01-25T21:30:00\",\"BV\":59.52133514},{\"O\":0.09447638,\"H\":0.09483000,\"L\":0.09410000,\"C\":0.09479151,\"V\":369.30796105,\"T\":\"2018-01-25T22:00:00\",\"BV\":34.92346888},{\"O\":0.09479150,\"H\":0.09481999,\"L\":0.09405639,\"C\":0.09475781,\"V\":426.96718953,\"T\":\"2018-01-25T22:30:00\",\"BV\":40.40618291},{\"O\":0.09475800,\"H\":0.09483000,\"L\":0.09410000,\"C\":0.09449981,\"V\":447.66952650,\"T\":\"2018-01-25T23:00:00\",\"BV\":42.35541594},{\"O\":0.09449980,\"H\":0.09477050,\"L\":0.09406029,\"C\":0.09410001,\"V\":383.27494819,\"T\":\"2018-01-25T23:30:00\",\"BV\":36.11422348},{\"O\":0.09412002,\"H\":0.09429897,\"L\":0.09385000,\"C\":0.09405627,\"V\":486.78459427,\"T\":\"2018-01-26T00:00:00\",\"BV\":45.79691694},{\"O\":0.09425000,\"H\":0.09435000,\"L\":0.09333790,\"C\":0.09352305,\"V\":1392.57330419,\"T\":\"2018-01-26T00:30:00\",\"BV\":130.88044178},{\"O\":0.09399500,\"H\":0.09399500,\"L\":0.09352303,\"C\":0.09369100,\"V\":535.80109358,\"T\":\"2018-01-26T01:00:00\",\"BV\":50.22562049},{\"O\":0.09388500,\"H\":0.09404371,\"L\":0.09330000,\"C\":0.09399000,\"V\":763.29128068,\"T\":\"2018-01-26T01:30:00\",\"BV\":71.39604328},{\"O\":0.09375959,\"H\":0.09395622,\"L\":0.09300000,\"C\":0.09300012,\"V\":766.83922946,\"T\":\"2018-01-26T02:00:00\",\"BV\":71.53071119},{\"O\":0.09300014,\"H\":0.09396270,\"L\":0.09241400,\"C\":0.09249599,\"V\":725.61754012,\"T\":\"2018-01-26T02:30:00\",\"BV\":67.53728549},{\"O\":0.09249599,\"H\":0.09325000,\"L\":0.09207453,\"C\":0.09269510,\"V\":600.53622655,\"T\":\"2018-01-26T03:00:00\",\"BV\":55.71974016},{\"O\":0.09295174,\"H\":0.09324999,\"L\":0.09261501,\"C\":0.09310003,\"V\":442.19200801,\"T\":\"2018-01-26T03:30:00\",\"BV\":41.14618767},{\"O\":0.09310006,\"H\":0.09325000,\"L\":0.09260011,\"C\":0.09305104,\"V\":434.72985295,\"T\":\"2018-01-26T04:00:00\",\"BV\":40.44631394},{\"O\":0.09305104,\"H\":0.09325000,\"L\":0.09262101,\"C\":0.09300000,\"V\":497.83996053,\"T\":\"2018-01-26T04:30:00\",\"BV\":46.34819443},{\"O\":0.09322133,\"H\":0.09386805,\"L\":0.09290000,\"C\":0.09366324,\"V\":441.02327899,\"T\":\"2018-01-26T05:00:00\",\"BV\":41.12699326},{\"O\":0.09302123,\"H\":0.09385131,\"L\":0.09302000,\"C\":0.09350000,\"V\":644.80005209,\"T\":\"2018-01-26T05:30:00\",\"BV\":60.27845841},{\"O\":0.09350000,\"H\":0.09400000,\"L\":0.09307805,\"C\":0.09394950,\"V\":371.88437561,\"T\":\"2018-01-26T06:00:00\",\"BV\":34.89770116},{\"O\":0.09394950,\"H\":0.09399900,\"L\":0.09361612,\"C\":0.09399900,\"V\":335.87027587,\"T\":\"2018-01-26T06:30:00\",\"BV\":31.50646093},{\"O\":0.09397891,\"H\":0.09400000,\"L\":0.09365150,\"C\":0.09400000,\"V\":147.60523579,\"T\":\"2018-01-26T07:00:00\",\"BV\":13.86240165}]";
	private PredictParams predictParams;

	private PredictorBollingerBandAsk ask;
	private PredictorBollingerBandBid bid;

	@Before
	public void beforeTest() {
		Gson gson = new Gson();
		predictParams = Mockito.spy(new PredictParams());
		Mockito.when(predictParams.getTicks()).thenReturn(Arrays.asList(gson.fromJson(ethJson, BittrexTick[].class)));

		setTickerForPredictPAramsSpy(0.09501129994729386, 0.09505009994729386, 0.09505129994729386);
		setCurrentHistoryPriceBid(0.0905129994729386);

		ask = new PredictorBollingerBandAsk();
		bid = new PredictorBollingerBandBid();
	}

	private void setTickerForPredictPAramsSpy(double bid, double ask, double last) {
		BittrexTicker ticker = new BittrexTicker(bid, ask, last);
		Mockito.when(predictParams.getTicker()).thenReturn(ticker);
	}

	private void setCurrentHistoryPriceBid(double limit) {
		BittrexOrder order = new BittrexOrder();
		order.setLimit(limit);
		order.setType("LIMIT_SELL");
		Mockito.when(predictParams.getCurrentOrder()).thenReturn(order);
	}

	private void setCurrentHistoryPriceAsk(double limit) {
		BittrexOrder orderHistory = new BittrexOrder();
		orderHistory.setLimit(limit);
		orderHistory.setType("LIMIT_BUY");
		Mockito.when(predictParams.getCurrentOrder()).thenReturn(orderHistory);
	}

	@Test
	public void testPredictorBBSellWhenLastPriceInUpper() {
		assertTrue(predictParams.getTicker().getLast() == ask.predict(predictParams));
	}

	@Test
	public void testPredictorBBSellWhenLastPriceInUpperButBidPriceGreater() {
		setCurrentHistoryPriceAsk(0.0956129994729386);
		assertTrue(-1 == ask.predict(predictParams));
	}

	@Test
	public void testPredictorBBSellWhenLastPriceInUpperAndInRangeRate() {
		// range rate = 0.05 = 0.004752564997364693
		setTickerForPredictPAramsSpy(0.09501129994729386, 0.09505009994729386, 0.09029873494992917);
		setCurrentHistoryPriceAsk(0.0900129994729386);

		assertTrue(0.09029873494992917 == ask.predict(predictParams));
	}

	@Test
	public void testPredictorBBSellWhenLastPriceInUpperAndOutRate() {
		// range rate = 0.05 = 0.004752564997364693
		setTickerForPredictPAramsSpy(0.09501129994729386, 0.09505009994729386, 0.09029873494992916);
		setCurrentHistoryPriceAsk(0.0900129994729386);
		assertTrue(-1 == ask.predict(predictParams));
	}

	@Test
	public void testPredictorBBBuyAtLowerAndInRange() {
		// range rate = 0.05 = 0.004620083952635305
		setTickerForPredictPAramsSpy(0.09501129994729386, 0.09505009994729386, 0.0970217630053414);
		setCurrentHistoryPriceBid(0.0980129994729386);

		assertTrue(0.0970217630053414 == bid.predict(predictParams));
	}

	@Test
	public void testPredictorBBBuyAtLowerAndOutRange() {
		setTickerForPredictPAramsSpy(0.09501129994729386, 0.09505009994729386, 0.0970217630053415);
		setCurrentHistoryPriceBid(0.0980129994729386);

		assertTrue(-1 == bid.predict(predictParams));
	}
}

package org.assassin.jr.attabot.service.predictor.bollingerband;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.pojo.exchange.ITick;
import org.assassin.jr.attabot.pojo.exchange.TickInterval;
import org.assassin.jr.attabot.pojo.exchange.bittrex.BittrexTick;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class IndicatorBollingerBandsCalculatorTest {
	private Map<String, List<ITick>> mapJson;

	@Before
	public void beforeRun() {
		Gson gson = new Gson();
		mapJson = new HashMap<>();
		String neoJson = "[{\"O\":0.01208487,\"H\":0.01208487,\"L\":0.01200127,\"C\":0.01206507,\"V\":532.51516000,\"T\":\"2018-01-25T18:30:00\",\"BV\":6.41544178},{\"O\":0.01206505,\"H\":0.01210000,\"L\":0.01205523,\"C\":0.01208520,\"V\":686.55351257,\"T\":\"2018-01-25T19:00:00\",\"BV\":8.29316773},{\"O\":0.01208520,\"H\":0.01215616,\"L\":0.01205460,\"C\":0.01215616,\"V\":1436.66955831,\"T\":\"2018-01-25T19:30:00\",\"BV\":17.40795629},{\"O\":0.01215002,\"H\":0.01218999,\"L\":0.01205638,\"C\":0.01218999,\"V\":1112.54992647,\"T\":\"2018-01-25T20:00:00\",\"BV\":13.51059312},{\"O\":0.01219000,\"H\":0.01230265,\"L\":0.01218999,\"C\":0.01225800,\"V\":1879.32764150,\"T\":\"2018-01-25T20:30:00\",\"BV\":23.04240833},{\"O\":0.01225800,\"H\":0.01238200,\"L\":0.01205000,\"C\":0.01237434,\"V\":3235.52991131,\"T\":\"2018-01-25T21:00:00\",\"BV\":39.56436114},{\"O\":0.01237434,\"H\":0.01240000,\"L\":0.01225064,\"C\":0.01236944,\"V\":5397.90850701,\"T\":\"2018-01-25T21:30:00\",\"BV\":66.69917593},{\"O\":0.01235339,\"H\":0.01240297,\"L\":0.01231361,\"C\":0.01240296,\"V\":3818.18561783,\"T\":\"2018-01-25T22:00:00\",\"BV\":47.25569259},{\"O\":0.01236301,\"H\":0.01240297,\"L\":0.01228017,\"C\":0.01230016,\"V\":2183.00305018,\"T\":\"2018-01-25T22:30:00\",\"BV\":26.92776384},{\"O\":0.01230016,\"H\":0.01237693,\"L\":0.01224702,\"C\":0.01234696,\"V\":2031.46656964,\"T\":\"2018-01-25T23:00:00\",\"BV\":24.99099116},{\"O\":0.01234608,\"H\":0.01236000,\"L\":0.01224000,\"C\":0.01228448,\"V\":1782.00554564,\"T\":\"2018-01-25T23:30:00\",\"BV\":21.91217818},{\"O\":0.01225345,\"H\":0.01236891,\"L\":0.01206000,\"C\":0.01225000,\"V\":5031.70617435,\"T\":\"2018-01-26T00:00:00\",\"BV\":61.86494236},{\"O\":0.01225000,\"H\":0.01227086,\"L\":0.01180000,\"C\":0.01220002,\"V\":7798.18824666,\"T\":\"2018-01-26T00:30:00\",\"BV\":94.38500437},{\"O\":0.01223533,\"H\":0.01233264,\"L\":0.01218330,\"C\":0.01221000,\"V\":1414.05909263,\"T\":\"2018-01-26T01:00:00\",\"BV\":17.31393704},{\"O\":0.01221000,\"H\":0.01228073,\"L\":0.01212434,\"C\":0.01217631,\"V\":1451.38399364,\"T\":\"2018-01-26T01:30:00\",\"BV\":17.68446227},{\"O\":0.01217631,\"H\":0.01222108,\"L\":0.01202910,\"C\":0.01207177,\"V\":2194.48026738,\"T\":\"2018-01-26T02:00:00\",\"BV\":26.61945137},{\"O\":0.01215108,\"H\":0.01215126,\"L\":0.01206822,\"C\":0.01211765,\"V\":1817.82545759,\"T\":\"2018-01-26T02:30:00\",\"BV\":22.05303384},{\"O\":0.01210003,\"H\":0.01218616,\"L\":0.01210001,\"C\":0.01212200,\"V\":930.83710930,\"T\":\"2018-01-26T03:00:00\",\"BV\":11.29626570},{\"O\":0.01215003,\"H\":0.01217099,\"L\":0.01209000,\"C\":0.01209745,\"V\":690.68633116,\"T\":\"2018-01-26T03:30:00\",\"BV\":8.37240889},{\"O\":0.01214604,\"H\":0.01218197,\"L\":0.01210200,\"C\":0.01210850,\"V\":908.43226103,\"T\":\"2018-01-26T04:00:00\",\"BV\":11.02036356},{\"O\":0.01210420,\"H\":0.01212618,\"L\":0.01205630,\"C\":0.01210420,\"V\":983.16124974,\"T\":\"2018-01-26T04:30:00\",\"BV\":11.87153439},{\"O\":0.01210420,\"H\":0.01212218,\"L\":0.01200000,\"C\":0.01200399,\"V\":1935.86430261,\"T\":\"2018-01-26T05:00:00\",\"BV\":23.27419634},{\"O\":0.01200400,\"H\":0.01212290,\"L\":0.01197001,\"C\":0.01206998,\"V\":2529.55618491,\"T\":\"2018-01-26T05:30:00\",\"BV\":30.38123015},{\"O\":0.01206989,\"H\":0.01206998,\"L\":0.01200001,\"C\":0.01206983,\"V\":649.72653627,\"T\":\"2018-01-26T06:00:00\",\"BV\":7.80665847},{\"O\":0.01200321,\"H\":0.01210000,\"L\":0.01200321,\"C\":0.01206986,\"V\":1138.65397837,\"T\":\"2018-01-26T06:30:00\",\"BV\":13.73371657},{\"O\":0.01206991,\"H\":0.01213014,\"L\":0.01206986,\"C\":0.01210000,\"V\":720.78481707,\"T\":\"2018-01-26T07:00:00\",\"BV\":8.71469002}]";
		String ethJson = "[{\"O\":0.09377000,\"H\":0.09439996,\"L\":0.09324301,\"C\":0.09409990,\"V\":670.20056972,\"T\":\"2018-01-25T16:00:00\",\"BV\":62.97144726},{\"O\":0.09409980,\"H\":0.09409990,\"L\":0.09399960,\"C\":0.09409971,\"V\":288.50486306,\"T\":\"2018-01-25T16:30:00\",\"BV\":27.14104415},{\"O\":0.09409970,\"H\":0.09469617,\"L\":0.09409970,\"C\":0.09454999,\"V\":591.16495756,\"T\":\"2018-01-25T17:00:00\",\"BV\":55.78852136},{\"O\":0.09431671,\"H\":0.09469615,\"L\":0.09431671,\"C\":0.09443511,\"V\":385.43441072,\"T\":\"2018-01-25T17:30:00\",\"BV\":36.43116249},{\"O\":0.09467900,\"H\":0.09467900,\"L\":0.09411501,\"C\":0.09437590,\"V\":606.72451890,\"T\":\"2018-01-25T18:00:00\",\"BV\":57.29491847},{\"O\":0.09437590,\"H\":0.09437590,\"L\":0.09411000,\"C\":0.09437000,\"V\":275.49943833,\"T\":\"2018-01-25T18:30:00\",\"BV\":25.95795466},{\"O\":0.09437000,\"H\":0.09492787,\"L\":0.09411501,\"C\":0.09492787,\"V\":483.87531029,\"T\":\"2018-01-25T19:00:00\",\"BV\":45.79418039},{\"O\":0.09492787,\"H\":0.09492998,\"L\":0.09471002,\"C\":0.09491000,\"V\":439.75503021,\"T\":\"2018-01-25T19:30:00\",\"BV\":41.67893726},{\"O\":0.09480475,\"H\":0.09493000,\"L\":0.09425900,\"C\":0.09487900,\"V\":325.30014562,\"T\":\"2018-01-25T20:00:00\",\"BV\":30.85421131},{\"O\":0.09487900,\"H\":0.09493000,\"L\":0.09470000,\"C\":0.09493000,\"V\":330.60310321,\"T\":\"2018-01-25T20:30:00\",\"BV\":31.37924986},{\"O\":0.09485061,\"H\":0.09493000,\"L\":0.09446736,\"C\":0.09446736,\"V\":616.12729038,\"T\":\"2018-01-25T21:00:00\",\"BV\":58.35595382},{\"O\":0.09470000,\"H\":0.09479999,\"L\":0.09410000,\"C\":0.09466631,\"V\":630.70595806,\"T\":\"2018-01-25T21:30:00\",\"BV\":59.52133514},{\"O\":0.09447638,\"H\":0.09483000,\"L\":0.09410000,\"C\":0.09479151,\"V\":369.30796105,\"T\":\"2018-01-25T22:00:00\",\"BV\":34.92346888},{\"O\":0.09479150,\"H\":0.09481999,\"L\":0.09405639,\"C\":0.09475781,\"V\":426.96718953,\"T\":\"2018-01-25T22:30:00\",\"BV\":40.40618291},{\"O\":0.09475800,\"H\":0.09483000,\"L\":0.09410000,\"C\":0.09449981,\"V\":447.66952650,\"T\":\"2018-01-25T23:00:00\",\"BV\":42.35541594},{\"O\":0.09449980,\"H\":0.09477050,\"L\":0.09406029,\"C\":0.09410001,\"V\":383.27494819,\"T\":\"2018-01-25T23:30:00\",\"BV\":36.11422348},{\"O\":0.09412002,\"H\":0.09429897,\"L\":0.09385000,\"C\":0.09405627,\"V\":486.78459427,\"T\":\"2018-01-26T00:00:00\",\"BV\":45.79691694},{\"O\":0.09425000,\"H\":0.09435000,\"L\":0.09333790,\"C\":0.09352305,\"V\":1392.57330419,\"T\":\"2018-01-26T00:30:00\",\"BV\":130.88044178},{\"O\":0.09399500,\"H\":0.09399500,\"L\":0.09352303,\"C\":0.09369100,\"V\":535.80109358,\"T\":\"2018-01-26T01:00:00\",\"BV\":50.22562049},{\"O\":0.09388500,\"H\":0.09404371,\"L\":0.09330000,\"C\":0.09399000,\"V\":763.29128068,\"T\":\"2018-01-26T01:30:00\",\"BV\":71.39604328},{\"O\":0.09375959,\"H\":0.09395622,\"L\":0.09300000,\"C\":0.09300012,\"V\":766.83922946,\"T\":\"2018-01-26T02:00:00\",\"BV\":71.53071119},{\"O\":0.09300014,\"H\":0.09396270,\"L\":0.09241400,\"C\":0.09249599,\"V\":725.61754012,\"T\":\"2018-01-26T02:30:00\",\"BV\":67.53728549},{\"O\":0.09249599,\"H\":0.09325000,\"L\":0.09207453,\"C\":0.09269510,\"V\":600.53622655,\"T\":\"2018-01-26T03:00:00\",\"BV\":55.71974016},{\"O\":0.09295174,\"H\":0.09324999,\"L\":0.09261501,\"C\":0.09310003,\"V\":442.19200801,\"T\":\"2018-01-26T03:30:00\",\"BV\":41.14618767},{\"O\":0.09310006,\"H\":0.09325000,\"L\":0.09260011,\"C\":0.09305104,\"V\":434.72985295,\"T\":\"2018-01-26T04:00:00\",\"BV\":40.44631394},{\"O\":0.09305104,\"H\":0.09325000,\"L\":0.09262101,\"C\":0.09300000,\"V\":497.83996053,\"T\":\"2018-01-26T04:30:00\",\"BV\":46.34819443},{\"O\":0.09322133,\"H\":0.09386805,\"L\":0.09290000,\"C\":0.09366324,\"V\":441.02327899,\"T\":\"2018-01-26T05:00:00\",\"BV\":41.12699326},{\"O\":0.09302123,\"H\":0.09385131,\"L\":0.09302000,\"C\":0.09350000,\"V\":644.80005209,\"T\":\"2018-01-26T05:30:00\",\"BV\":60.27845841},{\"O\":0.09350000,\"H\":0.09400000,\"L\":0.09307805,\"C\":0.09394950,\"V\":371.88437561,\"T\":\"2018-01-26T06:00:00\",\"BV\":34.89770116},{\"O\":0.09394950,\"H\":0.09399900,\"L\":0.09361612,\"C\":0.09399900,\"V\":335.87027587,\"T\":\"2018-01-26T06:30:00\",\"BV\":31.50646093},{\"O\":0.09397891,\"H\":0.09400000,\"L\":0.09365150,\"C\":0.09400000,\"V\":147.60523579,\"T\":\"2018-01-26T07:00:00\",\"BV\":13.86240165}]";
		String xrpJson = "[{\"O\":0.00011733,\"H\":0.00011770,\"L\":0.00011690,\"C\":0.00011719,\"V\":230421.77881549,\"T\":\"2018-01-25T17:30:00\",\"BV\":27.00126427},{\"O\":0.00011719,\"H\":0.00011734,\"L\":0.00011701,\"C\":0.00011701,\"V\":106539.98490105,\"T\":\"2018-01-25T18:00:00\",\"BV\":12.48309215},{\"O\":0.00011701,\"H\":0.00011715,\"L\":0.00011670,\"C\":0.00011690,\"V\":196536.23191362,\"T\":\"2018-01-25T18:30:00\",\"BV\":22.98986472},{\"O\":0.00011690,\"H\":0.00011720,\"L\":0.00011675,\"C\":0.00011686,\"V\":104759.92723949,\"T\":\"2018-01-25T19:00:00\",\"BV\":12.25848852},{\"O\":0.00011712,\"H\":0.00011712,\"L\":0.00011640,\"C\":0.00011652,\"V\":166720.66060357,\"T\":\"2018-01-25T19:30:00\",\"BV\":19.44566533},{\"O\":0.00011652,\"H\":0.00011750,\"L\":0.00011640,\"C\":0.00011661,\"V\":567657.09801462,\"T\":\"2018-01-25T20:00:00\",\"BV\":66.45412604},{\"O\":0.00011661,\"H\":0.00011661,\"L\":0.00011609,\"C\":0.00011644,\"V\":234608.94375223,\"T\":\"2018-01-25T20:30:00\",\"BV\":27.27172196},{\"O\":0.00011630,\"H\":0.00011694,\"L\":0.00011611,\"C\":0.00011666,\"V\":127729.98224303,\"T\":\"2018-01-25T21:00:00\",\"BV\":14.87811269},{\"O\":0.00011692,\"H\":0.00011765,\"L\":0.00011650,\"C\":0.00011661,\"V\":209965.73605260,\"T\":\"2018-01-25T21:30:00\",\"BV\":24.56871907},{\"O\":0.00011700,\"H\":0.00011731,\"L\":0.00011651,\"C\":0.00011654,\"V\":89327.48545470,\"T\":\"2018-01-25T22:00:00\",\"BV\":10.43407704},{\"O\":0.00011666,\"H\":0.00011730,\"L\":0.00011651,\"C\":0.00011720,\"V\":230236.36405489,\"T\":\"2018-01-25T22:30:00\",\"BV\":26.90711549},{\"O\":0.00011700,\"H\":0.00011725,\"L\":0.00011647,\"C\":0.00011648,\"V\":252271.58830098,\"T\":\"2018-01-25T23:00:00\",\"BV\":29.45451016},{\"O\":0.00011648,\"H\":0.00011700,\"L\":0.00011608,\"C\":0.00011615,\"V\":286240.71643968,\"T\":\"2018-01-25T23:30:00\",\"BV\":33.31987213},{\"O\":0.00011615,\"H\":0.00011615,\"L\":0.00011555,\"C\":0.00011559,\"V\":439729.53725246,\"T\":\"2018-01-26T00:00:00\",\"BV\":50.94513270},{\"O\":0.00011559,\"H\":0.00011580,\"L\":0.00011500,\"C\":0.00011537,\"V\":304071.73600602,\"T\":\"2018-01-26T00:30:00\",\"BV\":35.07077203},{\"O\":0.00011537,\"H\":0.00011620,\"L\":0.00011502,\"C\":0.00011502,\"V\":204363.56718965,\"T\":\"2018-01-26T01:00:00\",\"BV\":23.57614313},{\"O\":0.00011502,\"H\":0.00011502,\"L\":0.00011400,\"C\":0.00011400,\"V\":572651.88755217,\"T\":\"2018-01-26T01:30:00\",\"BV\":65.56860337},{\"O\":0.00011400,\"H\":0.00011495,\"L\":0.00011379,\"C\":0.00011400,\"V\":300286.12291198,\"T\":\"2018-01-26T02:00:00\",\"BV\":34.28813897},{\"O\":0.00011400,\"H\":0.00011400,\"L\":0.00011346,\"C\":0.00011351,\"V\":375505.53764560,\"T\":\"2018-01-26T02:30:00\",\"BV\":42.72131244},{\"O\":0.00011360,\"H\":0.00011495,\"L\":0.00011334,\"C\":0.00011354,\"V\":475326.33617763,\"T\":\"2018-01-26T03:00:00\",\"BV\":54.07929933},{\"O\":0.00011350,\"H\":0.00011363,\"L\":0.00011334,\"C\":0.00011363,\"V\":526745.79072957,\"T\":\"2018-01-26T03:30:00\",\"BV\":59.76292137},{\"O\":0.00011363,\"H\":0.00011363,\"L\":0.00011301,\"C\":0.00011346,\"V\":341320.23302582,\"T\":\"2018-01-26T04:00:00\",\"BV\":38.68976430},{\"O\":0.00011346,\"H\":0.00011363,\"L\":0.00011302,\"C\":0.00011359,\"V\":376832.68311122,\"T\":\"2018-01-26T04:30:00\",\"BV\":42.72084887},{\"O\":0.00011360,\"H\":0.00011400,\"L\":0.00011201,\"C\":0.00011202,\"V\":1567950.36744976,\"T\":\"2018-01-26T05:00:00\",\"BV\":176.61306824},{\"O\":0.00011202,\"H\":0.00011249,\"L\":0.00011100,\"C\":0.00011172,\"V\":1584737.59963299,\"T\":\"2018-01-26T05:30:00\",\"BV\":176.87600684},{\"O\":0.00011172,\"H\":0.00011199,\"L\":0.00011090,\"C\":0.00011133,\"V\":540246.75133249,\"T\":\"2018-01-26T06:00:00\",\"BV\":60.12233914},{\"O\":0.00011133,\"H\":0.00011228,\"L\":0.00011093,\"C\":0.00011145,\"V\":445295.01050282,\"T\":\"2018-01-26T06:30:00\",\"BV\":49.67268411},{\"O\":0.00011145,\"H\":0.00011145,\"L\":0.00010903,\"C\":0.00010927,\"V\":938702.62216550,\"T\":\"2018-01-26T07:00:00\",\"BV\":103.22422089}]";

		mapJson.put("BTC-ETH", Arrays.asList(gson.fromJson(ethJson, BittrexTick[].class)));
		mapJson.put("BTC-XRP", Arrays.asList(gson.fromJson(xrpJson, BittrexTick[].class)));
		mapJson.put("BTC-NEO", Arrays.asList(gson.fromJson(neoJson, BittrexTick[].class)));
	}

	@Test
	public void testBollingerBandETH() throws ExchangeServiceRequestFailException {
		BollingerBands bbEth = getBollingerBandFor("BTC-ETH", TickInterval.THIRTY_MIN);
		System.out.println("ETH :" + bbEth.toString());
		assertTrue(bbEth.equals(new BollingerBands(0.0924016790527061, 0.09372648949999998, 0.09505129994729386)));
	}

	@Test
	public void testBollingerBandXRP() throws ExchangeServiceRequestFailException {
		BollingerBands bbXRP = getBollingerBandFor("BTC-XRP", TickInterval.THIRTY_MIN);
		// System.out.println("XRP :" + bbXRP.toString());
		assertTrue(bbXRP.equals(new BollingerBands(0.00010987956083408139, 0.00011402399999999998, 0.00011816843916591857)));
	}

	@Test
	public void testBollingerBandNEO() throws ExchangeServiceRequestFailException {
		BollingerBands bbNeo = getBollingerBandFor("BTC-NEO", TickInterval.THIRTY_MIN);
		// System.out.println("NEO :" + bbNeo.toString());
		assertTrue(bbNeo.equals(new BollingerBands(0.01194809734929197, 0.012173778, 0.01239945865070803)));
	}

	private BollingerBands getBollingerBandFor(String market, TickInterval interval) throws ExchangeServiceRequestFailException {
		return new IndicatorBollingerBandsCalculator().calculate(mapJson.get(market), new ExchangeIndicatorParamsBollingerBand());
	}
}
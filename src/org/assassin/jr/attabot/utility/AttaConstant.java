package org.assassin.jr.attabot.utility;

public class AttaConstant {
	public static final String PATH_CONFIG_BSAUTO = "config/config.json";

	public static final int MAX_LOT = 8;

	public static final String LIMIT_SELL = "LIMIT_SELL";
	public static final String LIMIT_BUY = "LIMIT_BUY";

	public static final String RESPONSE_JSON_ELM_SUCCESS = "success";
	public static final String RESPONSE_JSON_ELM_MESSAGE = "message";
	public static final String RESPONSE_JSON_ELM_RESULT = "result";

	public static final String API_BITTEX_ROOT = "https://bittrex.com/api/";
	public static final String API_BITTEX_VERSION_V1 = "v1.1";
	public static final String API_BITTEX_VERSION_V2 = "v2.0";

	public static final String API_BITTEX_V1_PUBLIC = API_BITTEX_ROOT + API_BITTEX_VERSION_V1 + "/public";
	public static final String API_BITTEX_V1_PUBLIC_GET_MARKETS = API_BITTEX_V1_PUBLIC + "/getmarkets";
	public static final String API_BITTEX_V1_PUBLIC_GET_CURRENCIES = API_BITTEX_V1_PUBLIC + "/getcurrencies";
	public static final String API_BITTEX_V1_PUBLIC_GET_TICKER = API_BITTEX_V1_PUBLIC + "/getticker";
	public static final String API_BITTEX_V1_PUBLIC_GET_MARKET_SUMMARY = API_BITTEX_V1_PUBLIC + "/getmarketsummary";
	public static final String API_BITTEX_V1_PUBLIC_GET_ORDER_BOOK = API_BITTEX_V1_PUBLIC + "/getorderbook";
	public static final String API_BITTEX_V1_PUBLIC_GET_MARKET_HISTORY = API_BITTEX_V1_PUBLIC + "/getmarkethistory";

	public static final String API_BITTEX_V1_MARKET = API_BITTEX_ROOT + API_BITTEX_VERSION_V1 + "/market";
	public static final String API_BITTEX_V1_MARKET_BUY_LIMIT = API_BITTEX_V1_MARKET + "/buylimit";
	public static final String API_BITTEX_V1_MARKET_SELL_LIMIT = API_BITTEX_V1_MARKET + "/selllimit";
	public static final String API_BITTEX_V1_MARKET_CANCEL = API_BITTEX_V1_MARKET + "/cancel";
	public static final String API_BITTEX_V1_MARKET_GET_OPEN_ORDERS = API_BITTEX_V1_MARKET + "/getopenorders";

	public static final String API_BITTEX_V1_ACCOUNT = API_BITTEX_ROOT + API_BITTEX_VERSION_V1 + "/account";
	public static final String API_BITTEX_V1_ACCOUNT_GET_BALANCES = API_BITTEX_V1_ACCOUNT + "/getbalances";
	public static final String API_BITTEX_V1_ACCOUNT_GET_BALANCE = API_BITTEX_V1_ACCOUNT + "/getbalance";
	public static final String API_BITTEX_V1_ACCOUNT_GET_ORDER = API_BITTEX_V1_ACCOUNT + "/getorder";
	public static final String API_BITTEX_V1_ACCOUNT_GET_ORDER_HISTORY = API_BITTEX_V1_ACCOUNT + "/getorderhistory";

	public static final String API_BITTEX_V2_PUBLIC = API_BITTEX_ROOT + API_BITTEX_VERSION_V2 + "/pub";
	public static final String API_BITTEX_V2_PUBLIC_GET_TICKS = API_BITTEX_V2_PUBLIC + "/market/GetTicks";

	public static final String ATTA_CONFIG_FIELD_PLAY_TYPE = "playType";
	public static final String ATTA_CONFIG_FIELD_PLAY_TYPE_NAME = "typeName";
	public static final String ATTA_CONFIG_FIELD_PLAY_TYPE_BB = "BollingerBand";
	public static final String ATTA_CONFIG_FIELD_PLAY_TYPE_BB_LENGTH = "length";
	public static final String ATTA_CONFIG_FIELD_PLAY_TYPE_BB_STD_DEVIATION = "standardDeviation";
	public static final String ATTA_CONFIG_FIELD_PLAY_TYPE_BB_RANGE_RATE = "rangeRate";

	public static final String ATTA_CONFIG_FIELD_PLAY_TYPE_OVERLAP = "Overlap";
}
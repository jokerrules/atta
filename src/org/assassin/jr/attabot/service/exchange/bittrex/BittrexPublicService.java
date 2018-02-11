package org.assassin.jr.attabot.service.exchange.bittrex;

import java.util.List;

import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.pojo.exchange.ICurrency;
import org.assassin.jr.attabot.pojo.exchange.IMarket;
import org.assassin.jr.attabot.pojo.exchange.IMarketHistory;
import org.assassin.jr.attabot.pojo.exchange.ITick;
import org.assassin.jr.attabot.pojo.exchange.TickInterval;
import org.assassin.jr.attabot.pojo.exchange.bittrex.BittrexCurrency;
import org.assassin.jr.attabot.pojo.exchange.bittrex.BittrexMarket;
import org.assassin.jr.attabot.pojo.exchange.bittrex.BittrexMarketHistory;
import org.assassin.jr.attabot.pojo.exchange.bittrex.BittrexMarketSummary;
import org.assassin.jr.attabot.pojo.exchange.bittrex.BittrexOrderBook;
import org.assassin.jr.attabot.pojo.exchange.bittrex.BittrexTick;
import org.assassin.jr.attabot.pojo.exchange.bittrex.BittrexTicker;
import org.assassin.jr.attabot.service.exchange.ExchangePublicService;
import org.assassin.jr.attabot.service.network.StandardHttpResult;
import org.assassin.jr.attabot.utility.AttaConstant;

public class BittrexPublicService extends BittrexExchangeStandardService implements ExchangePublicService {
	public BittrexPublicService(String apiKey, String apiKeySerect) {
		super(apiKey, apiKeySerect);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IMarket> getMarkets() throws ExchangeServiceRequestFailException {
		StandardHttpResult bResult = getDataByGetMethod(AttaConstant.API_BITTEX_V1_PUBLIC_GET_MARKETS, "");
		return (List<IMarket>) (List<?>) toListFromJson(bResult, BittrexMarket[].class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ICurrency> getCurrencies() throws ExchangeServiceRequestFailException {
		StandardHttpResult bResult = getDataByGetMethod(AttaConstant.API_BITTEX_V1_PUBLIC_GET_CURRENCIES, "");
		return (List<ICurrency>) (List<?>) toListFromJson(bResult, BittrexCurrency[].class);
	}

	@Override
	public BittrexTicker getTicker(String market) throws ExchangeServiceRequestFailException {
		String params = String.format("market=%s", market);
		StandardHttpResult bResult = getDataByGetMethod(AttaConstant.API_BITTEX_V1_PUBLIC_GET_TICKER, params);
		return toObjectFromJson(bResult, BittrexTicker.class);
	}

	@Override
	public BittrexMarketSummary getMarketSummary(String market) throws ExchangeServiceRequestFailException {
		String params = String.format("market=%s", market);
		StandardHttpResult bResult = getDataByGetMethod(AttaConstant.API_BITTEX_V1_PUBLIC_GET_MARKET_SUMMARY, params);
		List<BittrexMarketSummary> lst = toListFromJson(bResult, BittrexMarketSummary[].class);
		if (lst.size() == 0) {
			return null;
		}
		return lst.get(0);
	}

	@Override
	public BittrexOrderBook getOrderBook(String market, String type) throws ExchangeServiceRequestFailException {
		String params = String.format("market=%s&type=%s", market, type);
		StandardHttpResult bResult = getDataByGetMethod(AttaConstant.API_BITTEX_V1_PUBLIC_GET_ORDER_BOOK, params);
		return toObjectFromJson(bResult, BittrexOrderBook.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IMarketHistory> getMarketHistory(String market) throws ExchangeServiceRequestFailException {
		String params = String.format("market=%s", market);
		StandardHttpResult bResult = getDataByGetMethod(AttaConstant.API_BITTEX_V1_PUBLIC_GET_MARKET_HISTORY, params);
		return (List<IMarketHistory>) (List<?>) toListFromJson(bResult, BittrexMarketHistory[].class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ITick> getTicks(String market, TickInterval tickInterval) throws ExchangeServiceRequestFailException {
		String params = String.format("marketName=%s&tickInterval=%s&_=%s", market, tickInterval, System.currentTimeMillis());
		StandardHttpResult bResult = getDataByGetMethod(AttaConstant.API_BITTEX_V2_PUBLIC_GET_TICKS, params);
		return ((List<ITick>) (List<?>) toListFromJson(bResult, BittrexTick[].class));
	}
}
package org.assassin.jr.attabot.service.exchange.bittrex;

import java.util.ArrayList;
import java.util.List;

import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.service.exchange.ExchangeStandardService;
import org.assassin.jr.attabot.service.network.BittrexHttpResult;
import org.assassin.jr.attabot.service.network.HttpRequestHelper;
import org.assassin.jr.attabot.service.network.HttpRequestHelper.HttpResponseData;
import org.assassin.jr.attabot.service.network.StandardHttpResult;
import org.assassin.jr.attabot.utility.AttaUtility;
import org.assassin.jr.attabot.utility.Pair;

public class BittrexExchangeStandardService extends ExchangeStandardService {
	public BittrexExchangeStandardService(String apiKey, String apiKeySerect) {
		super(apiKey, apiKeySerect);
	}

	public StandardHttpResult getDataByGetMethod(String url, String params) throws ExchangeServiceRequestFailException {
		String queryUrl = url + "?" + params;
		try {
			HttpResponseData responseData = HttpRequestHelper.getDataByGetMethod(queryUrl);
			return validResponseDataSuccess(new BittrexHttpResult(responseData.getDataString()));

		} catch (Exception e) {
			throw new ExchangeServiceRequestFailException(String.format("Request fail URL=%s", queryUrl), e);
		}
	}

	public StandardHttpResult getDataByGetMethodWithAPIKey(String url, String params) throws ExchangeServiceRequestFailException {
		long currentTimestamp = System.currentTimeMillis();
		String queryUrl = String.format(url + "?apikey=%s&nonce=%d&%s", getApiKey(), currentTimestamp, params);
		try {
			String apiSignValue = AttaUtility.calculateHMAC(queryUrl, getApiKeySerect());
			List<Pair<String, String>> lstHeaderValues = new ArrayList<>();
			lstHeaderValues.add(Pair.create("apisign", apiSignValue));
			HttpResponseData responseData = HttpRequestHelper.getDataByGetMethod(queryUrl, lstHeaderValues);
			return validResponseDataSuccess(new BittrexHttpResult(responseData.getDataString()));

		} catch (Exception e) {
			throw new ExchangeServiceRequestFailException(String.format("Request fail URL=%s", queryUrl), e);
		}
	}

	protected StandardHttpResult getDataByPostMethod(String url, List<Pair<String, String>> nameValuePairs) throws ExchangeServiceRequestFailException {
		try {
			HttpResponseData responseData = HttpRequestHelper.getDataByPostMethod(url, nameValuePairs);
			if (responseData.getHttpStatus() != 200 && responseData.getHttpStatus() != 201) {
				throw new ExchangeServiceRequestFailException("HTTP Status = " + responseData.getHttpStatus());
			}
			return validResponseDataSuccess(new BittrexHttpResult(responseData.getDataString()));

		} catch (Exception e) {
			throw new ExchangeServiceRequestFailException(String.format("Request fail URL=%s", url), e);
		}
	}

	private StandardHttpResult validResponseDataSuccess(StandardHttpResult result) throws ExchangeServiceRequestFailException {
		if (!result.isSucess()) {
			throw new ExchangeServiceRequestFailException(String.format("Request with unsuccessful.%s", result));
		}
		return result;
	}
}
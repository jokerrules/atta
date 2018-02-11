package org.assassin.jr.attabot.service.exchange;

import java.util.Arrays;
import java.util.List;

import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.service.network.StandardHttpResult;
import org.assassin.jr.attabot.utility.AttaUtility;

import com.google.gson.Gson;

public class ExchangeStandardService {
	private String apiKey;
	private String apiKeySerect;
	private Gson gson;

	public ExchangeStandardService(String apiKey, String apiKeySerect) {
		this.apiKey = apiKey;
		this.apiKeySerect = apiKeySerect;
		this.gson = AttaUtility.createGsonUpperCamel();
	}

	protected String getApiKey() {
		return this.apiKey;
	}

	protected String getApiKeySerect() {
		return this.apiKeySerect;
	}

	protected Gson getGson() {
		return this.gson;
	}

	protected <T> T toObjectFromJson(StandardHttpResult result, Class<T> type) throws ExchangeServiceRequestFailException {
		try {
			return getGson().fromJson(result.getResult(), type);
		} catch (Exception e) {
			throw new ExchangeServiceRequestFailException(String.format("Parse json to object error[ content=%s ]", result.getResult()), e);
		}
	}

	protected <T> List<T> toListFromJson(StandardHttpResult bResult, Class<T[]> type) throws ExchangeServiceRequestFailException {
		try {
			return Arrays.asList(getGson().fromJson(bResult.getResult(), type));
		} catch (Exception e) {
			throw new ExchangeServiceRequestFailException(String.format("Parse json to list object error[ content=%s ]", bResult.getResult()), e);
		}
	}
}
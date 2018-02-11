package org.assassin.jr.attabot.config;

public class ApiKeyInfo {
	private String apiKey;
	private String apiKeySerect;

	public ApiKeyInfo(String apiKey, String apiKeySerect) {
		this.apiKey = apiKey;
		this.apiKeySerect = apiKeySerect;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiKeySerect() {
		return apiKeySerect;
	}

	public void setApiKeySerect(String apiKeySerect) {
		this.apiKeySerect = apiKeySerect;
	}

	@Override
	public String toString() {
		return "ApiInfo [apiKey=" + apiKey + ", apiKeySerect=" + apiKeySerect + "]";
	}
}

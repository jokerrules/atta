package org.assassin.jr.attabot.service.exchange;

public abstract class ExchangeServiceHandler {
	private ExchangeAccountService accountService;
	private ExchangeMarketService marketService;
	private ExchangePublicService publicService;
	private String apiKey;
	private String apiKeySerect;

	public ExchangeServiceHandler(String apiKey, String apiKeySerect) {
		this.apiKey = apiKey;
		this.apiKeySerect = apiKeySerect;
		this.accountService = createAccountService(this.apiKey, this.apiKeySerect);
		this.marketService = createMarketService(this.apiKey, this.apiKeySerect);
		this.publicService = createPublicService(this.apiKey, this.apiKeySerect);
	}

	protected abstract ExchangeAccountService createAccountService(String apiKey, String apiKeySerect);

	protected abstract ExchangeMarketService createMarketService(String apiKey, String apiKeySerect);

	protected abstract ExchangePublicService createPublicService(String apiKey, String apiKeySerect);

	public ExchangeAccountService getAccountService() {
		return accountService;
	}

	public ExchangeMarketService getMarketService() {
		return marketService;
	}

	public ExchangePublicService getPublicService() {
		return publicService;
	}

	protected String getApiKey() {
		return this.apiKey;
	}

	protected String getApiKeySerect() {
		return this.getApiKeySerect();
	}
}
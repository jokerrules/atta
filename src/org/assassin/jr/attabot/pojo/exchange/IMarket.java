package org.assassin.jr.attabot.pojo.exchange;

public interface IMarket {
	public String getMarketCurrency();

	public String getBaseCurrency();

	public String getMarketCurrencyLong();

	public String getBaseCurrencyLong();

	public double getMinTradeSize();

	public String getMarketName();

	public boolean isActive();
}
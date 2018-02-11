package org.assassin.jr.attabot.pojo.exchange.bittrex;

import org.assassin.jr.attabot.pojo.exchange.IMarket;

public class BittrexMarket implements IMarket {
	private String marketCurrency;
	private String baseCurrency;
	private String marketCurrencyLong;
	private String baseCurrencyLong;
	private double minTradeSize;
	private String marketName;
	private boolean isActive;
	private String created;

	@Override
	public String getMarketCurrency() {
		return marketCurrency;
	}

	@Override
	public String getBaseCurrency() {
		return baseCurrency;
	}

	@Override
	public String getMarketCurrencyLong() {
		return marketCurrencyLong;
	}

	@Override
	public String getBaseCurrencyLong() {
		return baseCurrencyLong;
	}

	@Override
	public double getMinTradeSize() {
		return minTradeSize;
	}

	@Override
	public String getMarketName() {
		return marketName;
	}

	@Override
	public boolean isActive() {
		return isActive;
	}

	public String getCreated() {
		return created;
	}

	public void setMarketCurrency(String marketCurrency) {
		this.marketCurrency = marketCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public void setMarketCurrencyLong(String marketCurrencyLong) {
		this.marketCurrencyLong = marketCurrencyLong;
	}

	public void setBaseCurrencyLong(String baseCurrencyLong) {
		this.baseCurrencyLong = baseCurrencyLong;
	}

	public void setMinTradeSize(double minTradeSize) {
		this.minTradeSize = minTradeSize;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "CEMarket [marketCurrency=" + marketCurrency + ", baseCurrency=" + baseCurrency + ", marketCurrencyLong=" + marketCurrencyLong + ", baseCurrencyLong=" + baseCurrencyLong + ", minTradeSize=" + minTradeSize + ", marketName=" + marketName + ", isActive="
				+ isActive + ", created=" + created + "]";
	}
}

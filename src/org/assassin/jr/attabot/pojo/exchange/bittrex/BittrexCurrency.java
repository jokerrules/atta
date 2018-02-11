package org.assassin.jr.attabot.pojo.exchange.bittrex;

import org.assassin.jr.attabot.pojo.exchange.ICurrency;

public class BittrexCurrency implements ICurrency {
	private String currency;
	private String currencyLong;
	private int minConfirmation;
	private double txFee;
	private boolean isActive;
	private String coinType;
	private String baseAddress;

	@Override
	public String getCurrency() {
		return currency;
	}

	@Override
	public String getCurrencyLong() {
		return currencyLong;
	}

	@Override
	public int getMinConfirmation() {
		return minConfirmation;
	}

	@Override
	public double getTxFee() {
		return txFee;
	}

	@Override
	public boolean isActive() {
		return isActive;
	}

	@Override
	public String getCoinType() {
		return coinType;
	}

	@Override
	public String getBaseAddress() {
		return baseAddress;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setCurrencyLong(String currencyLong) {
		this.currencyLong = currencyLong;
	}

	public void setMinConfirmation(int minConfirmation) {
		this.minConfirmation = minConfirmation;
	}

	public void setTxFee(double txFee) {
		this.txFee = txFee;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}

	public void setBaseAddress(String baseAddress) {
		this.baseAddress = baseAddress;
	}
}

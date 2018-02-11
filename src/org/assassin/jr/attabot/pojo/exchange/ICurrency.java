package org.assassin.jr.attabot.pojo.exchange;

public interface ICurrency {
	public String getCurrency();

	public String getCurrencyLong();

	public int getMinConfirmation();

	public double getTxFee();

	public boolean isActive();

	public String getCoinType();

	public String getBaseAddress();
}
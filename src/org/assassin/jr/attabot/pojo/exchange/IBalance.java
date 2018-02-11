package org.assassin.jr.attabot.pojo.exchange;

public interface IBalance {
	public String getCurrency();

	public double getBalance();

	public double getAvailable();

	public String getCryptoAddress();

	public String getUuid();
}
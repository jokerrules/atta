package org.assassin.jr.attabot.pojo.exchange.bittrex;

import org.assassin.jr.attabot.pojo.exchange.IBalance;

public class BittrexBalance implements IBalance {
	private String currency;
	private double balance;
	private double available;
	private double pending;
	private String cryptoAddress;
	private boolean requested;
	private String Uuid;

	@Override
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public double getAvailable() {
		return available;
	}

	public void setAvailable(double available) {
		this.available = available;
	}

	public double getPending() {
		return pending;
	}

	public void setPending(double pending) {
		this.pending = pending;
	}

	@Override
	public String getCryptoAddress() {
		return this.cryptoAddress;
	}

	public void setCryptoAddress(String cryptoAddress) {
		this.cryptoAddress = cryptoAddress;
	}

	public boolean isRequested() {
		return requested;
	}

	public void setRequested(boolean requested) {
		this.requested = requested;
	}

	public String getUuid() {
		return Uuid;
	}

	public void setUuid(String uuid) {
		Uuid = uuid;
	}

	@Override
	public String toString() {
		return "CEBalance [currency=" + currency + ", balance=" + balance + ", available=" + available + ", pending=" + pending + ", CryptoAddress=" + cryptoAddress + ", requested=" + requested + ", Uuid=" + Uuid + "]";
	}
}

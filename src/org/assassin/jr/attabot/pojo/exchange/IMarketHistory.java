package org.assassin.jr.attabot.pojo.exchange;

public interface IMarketHistory {
	public long getId();

	public String getTimeStamp();

	public double getQuantity();

	public double getPrice();

	public double getTotal();

	public String getOrderType();
}
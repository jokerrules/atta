package org.assassin.jr.attabot.pojo.exchange;

public interface IMarketSummary {

	public String getMarketName();

	public double getHigh();

	public double getLow();

	public double getVolume();

	public double getLast();

	public double getBaseVolume();

	public String getTimeStamp();

	public double getBid();

	public double getAsk();

	public String getDisplayMarketName();
}

package org.assassin.jr.attabot.pojo.exchange;

import java.util.Date;

public interface ITick {
	public double getOpenPrice();

	public double getHigh24Price();

	public double getLow24Price();

	public double getClosePrice();

	public double getVolumn24h();

	public Date getTimestamp();

	public double getBaseVolumn();
}
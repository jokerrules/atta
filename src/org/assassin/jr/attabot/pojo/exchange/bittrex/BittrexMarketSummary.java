package org.assassin.jr.attabot.pojo.exchange.bittrex;

import org.assassin.jr.attabot.pojo.exchange.IMarketSummary;

public class BittrexMarketSummary implements IMarketSummary {
	private String marketName;
	private double high;
	private double low;
	private double volume;
	private double last;
	private double baseVolume;
	private String timeStamp;
	private double bid;
	private double ask;
	private int openBuyOrders;
	private int openSellOrders;
	private double prevDay;
	private String created;
	private String displayMarketName;

	@Override
	public String getMarketName() {
		return marketName;
	}

	@Override
	public double getHigh() {
		return high;
	}

	@Override
	public double getLow() {
		return low;
	}

	@Override
	public double getVolume() {
		return volume;
	}

	@Override
	public double getLast() {
		return last;
	}

	@Override
	public double getBaseVolume() {
		return baseVolume;
	}

	@Override
	public String getTimeStamp() {
		return timeStamp;
	}

	@Override
	public double getBid() {
		return bid;
	}

	@Override
	public double getAsk() {
		return ask;
	}

	public int getOpenBuyOrders() {
		return openBuyOrders;
	}

	public int getOpenSellOrders() {
		return openSellOrders;
	}

	public double getPrevDay() {
		return prevDay;
	}

	public String getCreated() {
		return created;
	}

	@Override
	public String getDisplayMarketName() {
		return displayMarketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public void setLast(double last) {
		this.last = last;
	}

	public void setBaseVolume(double baseVolume) {
		this.baseVolume = baseVolume;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public void setBid(double bid) {
		this.bid = bid;
	}

	public void setAsk(double ask) {
		this.ask = ask;
	}

	public void setOpenBuyOrders(int openBuyOrders) {
		this.openBuyOrders = openBuyOrders;
	}

	public void setOpenSellOrders(int openSellOrders) {
		this.openSellOrders = openSellOrders;
	}

	public void setPrevDay(double prevDay) {
		this.prevDay = prevDay;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public void setDisplayMarketName(String displayMarketName) {
		this.displayMarketName = displayMarketName;
	}

}

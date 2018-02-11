package org.assassin.jr.attabot.pojo.exchange.bittrex;

import org.assassin.jr.attabot.pojo.exchange.ITicker;

public class BittrexTicker implements ITicker {
	private double bid;
	private double ask;
	private double last;

	public BittrexTicker(double bid, double ask, double last) {
		this.bid = bid;
		this.ask = ask;
		this.last = last;
	}

	@Override
	public double getBid() {
		return bid;
	}

	public void setBid(double bid) {
		this.bid = bid;
	}

	@Override
	public double getAsk() {
		return ask;
	}

	public void setAsk(double ask) {
		this.ask = ask;
	}

	@Override
	public double getLast() {
		return last;
	}

	public void setLast(double last) {
		this.last = last;
	}
}
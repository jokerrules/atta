package org.assassin.jr.attabot.pojo.exchange.bittrex;

import java.util.Date;

import org.assassin.jr.attabot.pojo.exchange.ITick;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

public class BittrexTick implements ITick {
	@SerializedName(value = "openPrice", alternate = { "O" })
	private double openPrice;

	@SerializedName(value = "high24Price", alternate = { "H" })
	private double high24Price;

	@SerializedName(value = "low24Price", alternate = { "L" })
	private double low24Price;

	@SerializedName(value = "closePrice", alternate = { "C" })
	private double closePrice;

	@SerializedName(value = "volumn24h", alternate = { "V" })
	private double volumn24h;

	@SerializedName(value = "timestamp", alternate = { "T" })
	@JsonAdapter(BittrexDateTypeAdapter.class)
	private Date timestamp;

	@SerializedName(value = "baseVolumn", alternate = { "BV" })
	private double baseVolumn;

	public BittrexTick() {
	}

	@Override
	public double getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}

	@Override
	public double getHigh24Price() {
		return high24Price;
	}

	public void setHigh24Price(double high24Price) {
		this.high24Price = high24Price;
	}

	@Override
	public double getLow24Price() {
		return low24Price;
	}

	public void setLow24Price(double low24Price) {
		this.low24Price = low24Price;
	}

	@Override
	public double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}

	@Override
	public double getVolumn24h() {
		return volumn24h;
	}

	public void setVolumn24h(double volumn24h) {
		this.volumn24h = volumn24h;
	}

	@Override
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public double getBaseVolumn() {
		return baseVolumn;
	}

	public void setBaseVolumn(double baseVolumn) {
		this.baseVolumn = baseVolumn;
	}

	@Override
	public String toString() {
		return "CETick [openPrice=" + openPrice + ", high24Price=" + high24Price + ", low24Price=" + low24Price + ", closePrice=" + closePrice + ", volumn24h=" + volumn24h + ", timestamp=" + timestamp + ", baseVolumn=" + baseVolumn + "]";
	}
}

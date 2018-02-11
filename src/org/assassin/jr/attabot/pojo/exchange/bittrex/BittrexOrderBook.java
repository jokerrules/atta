package org.assassin.jr.attabot.pojo.exchange.bittrex;

import java.util.List;

import org.assassin.jr.attabot.pojo.exchange.IOrderBook;

public class BittrexOrderBook implements IOrderBook {
	private List<OrderBookItem> buy;
	private List<OrderBookItem> sell;

	@Override
	public List<OrderBookItem> getBuy() {
		return buy;
	}

	public void setBuy(List<OrderBookItem> buy) {
		this.buy = buy;
	}

	@Override
	public List<OrderBookItem> getSell() {
		return sell;
	}

	public void setSell(List<OrderBookItem> sell) {
		this.sell = sell;
	}

	public static class OrderBookItem {
		private double quantity;
		private double rate;

		public double getQuantity() {
			return quantity;
		}

		public void setQuantity(double quantity) {
			this.quantity = quantity;
		}

		public double getRate() {
			return rate;
		}

		public void setRate(double rate) {
			this.rate = rate;
		}

	}

}

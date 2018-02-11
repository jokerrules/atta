package org.assassin.jr.attabot.pojo.exchange.bittrex;

import org.assassin.jr.attabot.pojo.exchange.IMarketHistory;

public class BittrexMarketHistory implements IMarketHistory {
	private long id;
	private String timeStamp;
	private double quantity;
	private double price;
	private double total;
	private String fillType;
	private String orderType;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getTimeStamp() {
		return timeStamp;
	}

	@Override
	public double getQuantity() {
		return quantity;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public double getTotal() {
		return total;
	}

	public String getFillType() {
		return fillType;
	}

	@Override
	public String getOrderType() {
		return orderType;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void setFillType(String fillType) {
		this.fillType = fillType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	@Override
	public String toString() {
		return "BittrexMarketHistory [id=" + id + ", timeStamp=" + timeStamp + ", quantity=" + quantity + ", price=" + price + ", total=" + total + ", fillType=" + fillType + ", orderType=" + orderType + "]";
	}
}
package org.assassin.jr.attabot.service.management;

import org.assassin.jr.attabot.pojo.exchange.OrderCategory;

public class OrderStored {
	private String market;
	private double quantiy;
	private double price;
	private OrderCategory orderType;
	private String uuid;
	private double takenProfit;
	private double lastProfit;

	public OrderStored(String market, double quantiy, double price, OrderCategory orderType, String uuid, OrderStored lastOrderStore) {
		this.market = market;
		this.quantiy = quantiy;
		this.price = price;
		this.orderType = orderType;
		this.uuid = uuid;
		setTakenProfit(lastOrderStore);
		setLastProfit(lastOrderStore);
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public double getQuantiy() {
		return quantiy;
	}

	public void setQuantiy(double quantiy) {
		this.quantiy = quantiy;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public OrderCategory getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderCategory orderType) {
		this.orderType = orderType;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public double getTakenProfit() {
		return takenProfit;
	}

	public void setTakenProfit(double takenProfit) {
		this.takenProfit = takenProfit;
	}

	public void setTakenProfit(OrderStored lastOrderStore) {
		if (lastOrderStore == null || OrderCategory.BUY == this.orderType) {
			setLastProfit(getTakenProfit());
			return;
		}

		double currentProfit = (getPrice() * getQuantiy()) - (lastOrderStore.getPrice() * lastOrderStore.getQuantiy());
		setTakenProfit(lastOrderStore.getTakenProfit() + currentProfit);
	}

	public double getLastProfit() {
		return lastProfit;
	}

	public void setLastProfit(double lastProfit) {
		this.lastProfit = lastProfit;
	}

	public void setLastProfit(OrderStored lastOrderStore) {
		if (lastOrderStore == null || OrderCategory.BUY == this.orderType) {
			setLastProfit(getLastProfit());
			return;
		}
		double currentProfit = (getPrice() * getQuantiy()) - (lastOrderStore.getPrice() * lastOrderStore.getQuantiy());
		setLastProfit(currentProfit);
	}

	@Override
	public String toString() {
		return "OrderStored [market=" + market + ", quantiy=" + quantiy + ", price=" + price + ", orderType=" + orderType + ", uuid=" + uuid + ", takenProfit=" + takenProfit + "]";
	}
}
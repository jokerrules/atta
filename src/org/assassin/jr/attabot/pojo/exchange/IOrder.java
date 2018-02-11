package org.assassin.jr.attabot.pojo.exchange;

public interface IOrder {
	public String getOrderUuid();

	public double getLimit();

	public double getQuantity();

	public double getCommissionReserveRemaining();

	public boolean isOpen();

	public boolean isBuyOrder();

	public boolean isSellOrder();

	public boolean isOrderCompleted();

	public boolean isOrdercancelled();
}
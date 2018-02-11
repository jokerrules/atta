package org.assassin.jr.attabot.pojo.exchange.bittrex;

import java.math.BigDecimal;

import org.assassin.jr.attabot.pojo.exchange.IOrder;
import org.assassin.jr.attabot.utility.AttaConstant;

public class BittrexOrderHistory implements IOrder {
	private String orderUuid;
	private String exchange;
	private String timeStamp;
	private String orderType;
	private double limit;
	private double quantity;
	private double quantityRemaining;
	private double commission;
	private double price;
	private double pricePerUnit;
	private boolean isConditional;
	private String condition;
	private String conditionTarget;
	private boolean immediateOrCancel;

	public BittrexOrderHistory() {
	}

	public BittrexOrderHistory(String orderUuid) {
		super();
		this.orderUuid = orderUuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderUuid == null) ? 0 : orderUuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BittrexOrderHistory other = (BittrexOrderHistory) obj;
		if (orderUuid == null) {
			if (other.orderUuid != null)
				return false;
		} else if (!orderUuid.equals(other.orderUuid))
			return false;
		return true;
	}

	@Override
	public String getOrderUuid() {
		return orderUuid;
	}

	public void setOrderUuid(String orderUuid) {
		this.orderUuid = orderUuid;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getOrderType() {
		return orderType;
	}

	@Override
	public boolean isBuyOrder() {
		return AttaConstant.LIMIT_BUY.equals(orderType);
	}

	@Override
	public boolean isSellOrder() {
		return AttaConstant.LIMIT_SELL.equals(orderType);
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	@Override
	public double getLimit() {
		return limit;
	}

	public void setLimit(double limit) {
		this.limit = limit;
	}

	@Override
	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getQuantityRemaining() {
		return quantityRemaining;
	}

	public void setQuantityRemaining(double quantityRemaining) {
		this.quantityRemaining = quantityRemaining;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public boolean isConditional() {
		return isConditional;
	}

	public void setConditional(boolean isConditional) {
		this.isConditional = isConditional;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getConditionTarget() {
		return conditionTarget;
	}

	public void setConditionTarget(String conditionTarget) {
		this.conditionTarget = conditionTarget;
	}

	public boolean isImmediateOrCancel() {
		return immediateOrCancel;
	}

	public void setImmediateOrCancel(boolean immediateOrCancel) {
		this.immediateOrCancel = immediateOrCancel;
	}

	@Override
	public String toString() {
		return "CEOrderHistory [orderUuid=" + orderUuid + ", exchange=" + exchange + ", timeStamp=" + timeStamp + ", orderType=" + orderType + ", limit=" + new BigDecimal(limit) + ", quantity=" + quantity + ", quantityRemaining=" + quantityRemaining + ", commission="
				+ commission + ", price=" + new BigDecimal(price) + ", pricePerUnit=" + new BigDecimal(pricePerUnit) + ", isConditional=" + isConditional + ", condition=" + condition + ", conditionTarget=" + conditionTarget + ", immediateOrCancel=" + immediateOrCancel
				+ "]";
	}

	@Override
	public double getCommissionReserveRemaining() {
		return quantityRemaining;
	}

	@Override
	public boolean isOpen() {
		return false;
	}

	@Override
	public boolean isOrderCompleted() {
		return !isOpen() && getCommissionReserveRemaining() == 0;
	}

	@Override
	public boolean isOrdercancelled() {
		return !isOpen() && getCommissionReserveRemaining() > 0;
	}
}
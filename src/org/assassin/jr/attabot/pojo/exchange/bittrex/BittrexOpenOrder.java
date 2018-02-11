package org.assassin.jr.attabot.pojo.exchange.bittrex;

import org.assassin.jr.attabot.pojo.exchange.IOrder;
import org.assassin.jr.attabot.utility.AttaConstant;

public class BittrexOpenOrder implements IOrder {
	private String uuid;
	private String orderUuid;
	private String exchange;
	private String orderType;
	private double quantity;
	private double quantityRemaining;
	private double limit;
	private double commissionPaid;
	private double price;
	private String PricePerUnit;
	private String Opened;
	private String Closed;
	private boolean CancelInitiated;
	private boolean ImmediateOrCancel;
	private boolean IsConditional;
	private String Condition;
	private String ConditionTarget;

	public BittrexOpenOrder() {
	}

	public BittrexOpenOrder(String orderUuid) {
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
		BittrexOpenOrder other = (BittrexOpenOrder) obj;
		if (orderUuid == null) {
			if (other.orderUuid != null)
				return false;
		} else if (!orderUuid.equals(other.orderUuid))
			return false;
		return true;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

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

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

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

	public double getLimit() {
		return limit;
	}

	public void setLimit(double limit) {
		this.limit = limit;
	}

	public double getCommissionPaid() {
		return commissionPaid;
	}

	public void setCommissionPaid(double commissionPaid) {
		this.commissionPaid = commissionPaid;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPricePerUnit() {
		return PricePerUnit;
	}

	public void setPricePerUnit(String pricePerUnit) {
		PricePerUnit = pricePerUnit;
	}

	public String getOpened() {
		return Opened;
	}

	public void setOpened(String opened) {
		Opened = opened;
	}

	public String getClosed() {
		return Closed;
	}

	public void setClosed(String closed) {
		Closed = closed;
	}

	public boolean isCancelInitiated() {
		return CancelInitiated;
	}

	public void setCancelInitiated(boolean cancelInitiated) {
		CancelInitiated = cancelInitiated;
	}

	public boolean isImmediateOrCancel() {
		return ImmediateOrCancel;
	}

	public void setImmediateOrCancel(boolean immediateOrCancel) {
		ImmediateOrCancel = immediateOrCancel;
	}

	public boolean isIsConditional() {
		return IsConditional;
	}

	public void setIsConditional(boolean isConditional) {
		IsConditional = isConditional;
	}

	public String getCondition() {
		return Condition;
	}

	public void setCondition(String condition) {
		Condition = condition;
	}

	public String getConditionTarget() {
		return ConditionTarget;
	}

	public void setConditionTarget(String conditionTarget) {
		ConditionTarget = conditionTarget;
	}

	@Override
	public String toString() {
		return "OpenOrder [uuid=" + uuid + ", orderUuid=" + orderUuid + ", exchange=" + exchange + ", orderType=" + orderType + ", quantity=" + quantity + ", quantityRemaining=" + quantityRemaining + ", limit=" + limit + ", commissionPaid=" + commissionPaid + ", price="
				+ price + ", PricePerUnit=" + PricePerUnit + ", Opened=" + Opened + ", Closed=" + Closed + ", CancelInitiated=" + CancelInitiated + ", ImmediateOrCancel=" + ImmediateOrCancel + ", IsConditional=" + IsConditional + ", Condition=" + Condition
				+ ", ConditionTarget=" + ConditionTarget + "]";
	}

	@Override
	public double getCommissionReserveRemaining() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isOpen() {
		return true;
	}

	@Override
	public boolean isBuyOrder() {
		return AttaConstant.LIMIT_BUY.equals(orderType);
	}

	@Override
	public boolean isSellOrder() {
		return AttaConstant.LIMIT_SELL.equals(orderType);
	}

	@Override
	public boolean isOrderCompleted() {
		return false;
	}

	@Override
	public boolean isOrdercancelled() {
		return false;
	}
}
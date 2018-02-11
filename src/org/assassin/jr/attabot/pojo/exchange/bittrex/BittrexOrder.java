package org.assassin.jr.attabot.pojo.exchange.bittrex;

import org.assassin.jr.attabot.pojo.exchange.IOrder;
import org.assassin.jr.attabot.utility.AttaConstant;

public class BittrexOrder implements IOrder {
	private String accountId;
	private String orderUuid;
	private String exchange;
	private String type;
	private double quantity;
	private double quantityRemaining;
	private double limit;
	private double reserved;
	private double reserveRemaining;
	private double commissionReserved;
	private double commissionReserveRemaining;
	private double commissionPaid;
	private double price;
	private String pricePerUnit;
	private String opened;
	private String closed;
	private boolean isOpen;
	private String sentinel;
	private boolean cancelInitiated;
	private boolean immediateOrCancel;
	private boolean isConditional;
	private String condition;
	private String conditionTarget;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	@Override
	public double getLimit() {
		return limit;
	}

	public void setLimit(double limit) {
		this.limit = limit;
	}

	public double getReserved() {
		return reserved;
	}

	public void setReserved(double reserved) {
		this.reserved = reserved;
	}

	public double getReserveRemaining() {
		return reserveRemaining;
	}

	public void setReserveRemaining(double reserveRemaining) {
		this.reserveRemaining = reserveRemaining;
	}

	public double getCommissionReserved() {
		return commissionReserved;
	}

	public void setCommissionReserved(double commissionReserved) {
		this.commissionReserved = commissionReserved;
	}

	@Override
	public double getCommissionReserveRemaining() {
		return commissionReserveRemaining;
	}

	public void setCommissionReserveRemaining(double commissionReserveRemaining) {
		this.commissionReserveRemaining = commissionReserveRemaining;
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
		return pricePerUnit;
	}

	public void setPricePerUnit(String pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public String getOpened() {
		return opened;
	}

	public void setOpened(String opened) {
		this.opened = opened;
	}

	public String getClosed() {
		return closed;
	}

	public void setClosed(String closed) {
		this.closed = closed;
	}

	@Override
	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public String getSentinel() {
		return sentinel;
	}

	public void setSentinel(String sentinel) {
		this.sentinel = sentinel;
	}

	public boolean isCancelInitiated() {
		return cancelInitiated;
	}

	public void setCancelInitiated(boolean cancelInitiated) {
		this.cancelInitiated = cancelInitiated;
	}

	public boolean isImmediateOrCancel() {
		return immediateOrCancel;
	}

	public void setImmediateOrCancel(boolean immediateOrCancel) {
		this.immediateOrCancel = immediateOrCancel;
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

	@Override
	public boolean isBuyOrder() {
		return AttaConstant.LIMIT_BUY.equals(this.getType());
	}

	@Override
	public boolean isSellOrder() {
		return AttaConstant.LIMIT_SELL.equals(this.getType());
	}

	@Override
	public String toString() {
		return "Order [accountId=" + accountId + ", orderUuid=" + orderUuid + ", exchange=" + exchange + ", type=" + type + ", quantity=" + quantity + ", quantityRemaining=" + quantityRemaining + ", limit=" + limit + ", reserved=" + reserved + ", reserveRemaining="
				+ reserveRemaining + ", commissionReserved=" + commissionReserved + ", commissionReserveRemaining=" + commissionReserveRemaining + ", commissionPaid=" + commissionPaid + ", price=" + price + ", pricePerUnit=" + pricePerUnit + ", opened=" + opened
				+ ", closed=" + closed + ", isOpen=" + isOpen + ", sentinel=" + sentinel + ", cancelInitiated=" + cancelInitiated + ", immediateOrCancel=" + immediateOrCancel + ", isConditional=" + isConditional + ", condition=" + condition + ", conditionTarget="
				+ conditionTarget + "]";
	}

	@Override
	public boolean isOrderCompleted() {
		return !isOpen() && getQuantityRemaining() == 0;
	}

	@Override
	public boolean isOrdercancelled() {
		return !isOpen() && getQuantityRemaining() > 0;
	}
}
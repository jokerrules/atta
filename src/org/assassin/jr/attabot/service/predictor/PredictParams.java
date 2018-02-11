package org.assassin.jr.attabot.service.predictor;

import java.util.List;

import org.assassin.jr.attabot.config.CurrencySetting;
import org.assassin.jr.attabot.pojo.exchange.IMarketHistory;
import org.assassin.jr.attabot.pojo.exchange.IMarketSummary;
import org.assassin.jr.attabot.pojo.exchange.IOrder;
import org.assassin.jr.attabot.pojo.exchange.IOrderBook;
import org.assassin.jr.attabot.pojo.exchange.ITick;
import org.assassin.jr.attabot.pojo.exchange.ITicker;
import org.assassin.jr.attabot.pojo.exchange.bittrex.BittrexOrderBook;

public class PredictParams {
	private List<ITick> lstTicks;
	private List<IMarketHistory> lstMarketHistory;
	private IMarketSummary marketSummary;
	private IOrderBook orderBook;
	private ITicker ticker;
	private List<IOrder> lstOrderHistory;
	private IOrder currentOrder;
	private CurrencySetting currency;

	public PredictParams() {
	}

	public PredictParams(List<ITick> lstTicks, List<IMarketHistory> lstMarketHistory, IMarketSummary marketSummary, IOrderBook orderBook, ITicker ticker, List<IOrder> lstOrderHistory, IOrder currentOrder, CurrencySetting currency) {
		super();
		this.lstTicks = lstTicks;
		this.lstMarketHistory = lstMarketHistory;
		this.marketSummary = marketSummary;
		this.orderBook = orderBook;
		this.ticker = ticker;
		this.lstOrderHistory = lstOrderHistory;
		this.currentOrder = currentOrder;
		this.currency = currency;
	}

	public List<ITick> getTicks() {
		return lstTicks;
	}

	public void setTicks(List<ITick> lstTicks) {
		this.lstTicks = lstTicks;
	}

	public List<IMarketHistory> getLstMarketHistory() {
		return lstMarketHistory;
	}

	public void setMarketHistories(List<IMarketHistory> lstMarketHistory) {
		this.lstMarketHistory = lstMarketHistory;
	}

	public IMarketSummary getMarketSummary() {
		return marketSummary;
	}

	public void setMarketSummary(IMarketSummary marketSummary) {
		this.marketSummary = marketSummary;
	}

	public IOrderBook getOrderBook() {
		return orderBook;
	}

	public void setOrderBook(BittrexOrderBook orderBook) {
		this.orderBook = orderBook;
	}

	public ITicker getTicker() {
		return ticker;
	}

	public void setTicker(ITicker ticker) {
		this.ticker = ticker;
	}

	public List<IOrder> getLstOrderHistory() {
		return lstOrderHistory;
	}

	public void setOrderHistories(List<IOrder> lstOrderHistory) {
		this.lstOrderHistory = lstOrderHistory;
	}

	public CurrencySetting getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencySetting currency) {
		this.currency = currency;
	}

	public IOrder getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(IOrder currentOrder) {
		this.currentOrder = currentOrder;
	}

	@Override
	public String toString() {
		return "PredictParams [lstTicks=" + lstTicks + ", lstMarketHistory=" + lstMarketHistory + ", marketSummary=" + marketSummary + ", orderBook=" + orderBook + ", ticker=" + ticker + ", lstOrderHistory=" + lstOrderHistory + ", currentOrder=" + currentOrder
				+ ", currency=" + currency + "]";
	}
}
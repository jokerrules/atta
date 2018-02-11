package org.assassin.jr.attabot.pojo.exchange;

import java.util.List;

import org.assassin.jr.attabot.pojo.exchange.bittrex.BittrexOrderBook.OrderBookItem;

public interface IOrderBook {
	public List<OrderBookItem> getBuy();

	public List<OrderBookItem> getSell();
}

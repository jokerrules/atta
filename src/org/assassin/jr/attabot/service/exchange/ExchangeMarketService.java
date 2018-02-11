package org.assassin.jr.attabot.service.exchange;

import java.util.List;

import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.pojo.exchange.IOrder;

public interface ExchangeMarketService {
	public String buyLimit(String market, double quantity, double rate) throws ExchangeServiceRequestFailException;

	public String sellLimit(String market, double quantity, double rate) throws ExchangeServiceRequestFailException;

	public boolean cancel(String uuid) throws ExchangeServiceRequestFailException;

	public List<IOrder> getOpenOrders(String market) throws ExchangeServiceRequestFailException;
}

package org.assassin.jr.attabot.service.exchange;

import java.util.List;

import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.pojo.exchange.IBalance;
import org.assassin.jr.attabot.pojo.exchange.IOrder;

public interface ExchangeAccountService {
	public List<IBalance> getBalances() throws ExchangeServiceRequestFailException;

	public IBalance getBalance(String currency) throws ExchangeServiceRequestFailException;

	public IOrder getOrder(String market, String uuid) throws ExchangeServiceRequestFailException;

	public List<IOrder> getOrderHistory(String market) throws ExchangeServiceRequestFailException;
}

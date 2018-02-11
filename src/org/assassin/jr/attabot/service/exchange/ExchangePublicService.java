package org.assassin.jr.attabot.service.exchange;

import java.util.List;

import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.pojo.exchange.ICurrency;
import org.assassin.jr.attabot.pojo.exchange.IMarket;
import org.assassin.jr.attabot.pojo.exchange.IMarketHistory;
import org.assassin.jr.attabot.pojo.exchange.IMarketSummary;
import org.assassin.jr.attabot.pojo.exchange.IOrderBook;
import org.assassin.jr.attabot.pojo.exchange.ITick;
import org.assassin.jr.attabot.pojo.exchange.ITicker;
import org.assassin.jr.attabot.pojo.exchange.TickInterval;

public interface ExchangePublicService {

	public List<IMarket> getMarkets() throws ExchangeServiceRequestFailException;

	public List<ICurrency> getCurrencies() throws ExchangeServiceRequestFailException;

	public ITicker getTicker(String market) throws ExchangeServiceRequestFailException;

	public IMarketSummary getMarketSummary(String market) throws ExchangeServiceRequestFailException;

	public IOrderBook getOrderBook(String makert, String type) throws ExchangeServiceRequestFailException;

	public List<IMarketHistory> getMarketHistory(String makert) throws ExchangeServiceRequestFailException;

	public List<ITick> getTicks(String market, TickInterval tickInterval) throws ExchangeServiceRequestFailException;
}
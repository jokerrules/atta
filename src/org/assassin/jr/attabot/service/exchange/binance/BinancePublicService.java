package org.assassin.jr.attabot.service.exchange.binance;

import java.util.List;

import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.pojo.exchange.ICurrency;
import org.assassin.jr.attabot.pojo.exchange.IMarket;
import org.assassin.jr.attabot.pojo.exchange.IMarketHistory;
import org.assassin.jr.attabot.pojo.exchange.ITick;
import org.assassin.jr.attabot.pojo.exchange.ITicker;
import org.assassin.jr.attabot.pojo.exchange.TickInterval;
import org.assassin.jr.attabot.pojo.exchange.bittrex.BittrexMarketSummary;
import org.assassin.jr.attabot.pojo.exchange.bittrex.BittrexOrderBook;
import org.assassin.jr.attabot.service.exchange.ExchangePublicService;

import com.binance.api.client.domain.market.CandlestickInterval;

public class BinancePublicService extends BinanceExchangeStandardService implements ExchangePublicService {

	public BinancePublicService(String apiKey, String apiKeySerect) {
		super(apiKey, apiKeySerect);
	}

	@Override
	public List<IMarket> getMarkets() throws ExchangeServiceRequestFailException {
		throw new UnsupportedOperationException("getMarkets");
	}

	@Override
	public List<ICurrency> getCurrencies() throws ExchangeServiceRequestFailException {
		throw new UnsupportedOperationException("getCurrencies");
	}

	@Override
	public ITicker getTicker(String market) throws ExchangeServiceRequestFailException {
		return getBnApiclient().get24HrPriceStatistics(convertToBinanceMarket(market));
	}

	@Override
	public BittrexMarketSummary getMarketSummary(String market) throws ExchangeServiceRequestFailException {
		throw new UnsupportedOperationException("getMarketSummary");
	}

	@Override
	public BittrexOrderBook getOrderBook(String makert, String type) throws ExchangeServiceRequestFailException {
		throw new UnsupportedOperationException("getOrderBook");
	}

	@Override
	public List<IMarketHistory> getMarketHistory(String makert) throws ExchangeServiceRequestFailException {
		throw new UnsupportedOperationException("getMarketHistory");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ITick> getTicks(String market, TickInterval tickInterval) throws ExchangeServiceRequestFailException {
		CandlestickInterval interval = CandlestickInterval.HALF_HOURLY;
		if (tickInterval == TickInterval.HOUR) {
			interval = CandlestickInterval.HOURLY;
		}
		return ((List<ITick>) (List<?>) getBnApiclient().getCandlestickBars(convertToBinanceMarket(market), interval, 50, null, null));
	}
}
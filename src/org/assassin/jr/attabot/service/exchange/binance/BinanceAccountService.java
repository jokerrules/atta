package org.assassin.jr.attabot.service.exchange.binance;

import java.util.List;

import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.pojo.exchange.IBalance;
import org.assassin.jr.attabot.pojo.exchange.IOrder;
import org.assassin.jr.attabot.service.exchange.ExchangeAccountService;

import com.binance.api.client.domain.account.request.AllOrdersRequest;
import com.binance.api.client.domain.account.request.OrderStatusRequest;

public class BinanceAccountService extends BinanceExchangeStandardService implements ExchangeAccountService {

	public BinanceAccountService(String apiKey, String apiKeySerect) {
		super(apiKey, apiKeySerect);
	}

	@Override
	public List<IBalance> getBalances() throws ExchangeServiceRequestFailException {
		throw new UnsupportedOperationException("getBalances");
	}

	@Override
	public IBalance getBalance(String currency) throws ExchangeServiceRequestFailException {
		throw new UnsupportedOperationException("getBalances");
	}

	@Override
	public IOrder getOrder(String market, String uuid) throws ExchangeServiceRequestFailException {
		return getBnApiclient().getOrderStatus(new OrderStatusRequest(convertToBinanceMarket(market), uuid));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IOrder> getOrderHistory(String market) throws ExchangeServiceRequestFailException {
		return (List<IOrder>) (List<?>) getBnApiclient().getAllOrders(new AllOrdersRequest(convertToBinanceMarket(market)).limit(50));
	}
}
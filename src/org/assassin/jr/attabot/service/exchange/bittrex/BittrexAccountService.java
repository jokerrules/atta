package org.assassin.jr.attabot.service.exchange.bittrex;

import java.util.List;

import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.pojo.exchange.IBalance;
import org.assassin.jr.attabot.pojo.exchange.IOrder;
import org.assassin.jr.attabot.pojo.exchange.bittrex.BittrexBalance;
import org.assassin.jr.attabot.pojo.exchange.bittrex.BittrexOrder;
import org.assassin.jr.attabot.pojo.exchange.bittrex.BittrexOrderHistory;
import org.assassin.jr.attabot.service.exchange.ExchangeAccountService;
import org.assassin.jr.attabot.service.network.StandardHttpResult;
import org.assassin.jr.attabot.utility.AttaConstant;

public class BittrexAccountService extends BittrexExchangeStandardService implements ExchangeAccountService {

	public BittrexAccountService(String apiKey, String apiKeySerect) {
		super(apiKey, apiKeySerect);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IBalance> getBalances() throws ExchangeServiceRequestFailException {
		StandardHttpResult bresult = getDataByGetMethodWithAPIKey(AttaConstant.API_BITTEX_V1_ACCOUNT_GET_BALANCES, "");
		return (List<IBalance>) (List<?>) toListFromJson(bresult, BittrexBalance[].class);
	}

	@Override
	public IBalance getBalance(String currency) throws ExchangeServiceRequestFailException {
		String params = String.format("currency=%s", currency);
		StandardHttpResult bresult = getDataByGetMethodWithAPIKey(AttaConstant.API_BITTEX_V1_ACCOUNT_GET_BALANCE, params);
		return toObjectFromJson(bresult, BittrexBalance.class);
	}

	@Override
	public IOrder getOrder(String market, String uuid) throws ExchangeServiceRequestFailException {
		String params = String.format("uuid=%s", uuid);
		StandardHttpResult bresult = getDataByGetMethodWithAPIKey(AttaConstant.API_BITTEX_V1_ACCOUNT_GET_ORDER, params);
		return toObjectFromJson(bresult, BittrexOrder.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IOrder> getOrderHistory(String market) throws ExchangeServiceRequestFailException {
		String params = String.format("market=%s", market);
		StandardHttpResult bresult = getDataByGetMethodWithAPIKey(AttaConstant.API_BITTEX_V1_ACCOUNT_GET_ORDER_HISTORY, params);
		return (List<IOrder>) (List<?>) toListFromJson(bresult, BittrexOrderHistory[].class);
	}
}

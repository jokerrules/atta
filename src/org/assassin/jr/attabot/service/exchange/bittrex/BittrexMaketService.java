package org.assassin.jr.attabot.service.exchange.bittrex;

import java.util.List;

import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.pojo.exchange.IOrder;
import org.assassin.jr.attabot.pojo.exchange.bittrex.BittrexOpenOrder;
import org.assassin.jr.attabot.pojo.exchange.bittrex.BittrexResponseUuid;
import org.assassin.jr.attabot.service.exchange.ExchangeMarketService;
import org.assassin.jr.attabot.service.network.StandardHttpResult;
import org.assassin.jr.attabot.utility.AttaConstant;

public class BittrexMaketService extends BittrexExchangeStandardService implements ExchangeMarketService {
	public BittrexMaketService(String apiKey, String apiKeySerect) {
		super(apiKey, apiKeySerect);
	}

	@Override
	public synchronized String buyLimit(String market, double quantity, double rate) throws ExchangeServiceRequestFailException {
		String params = String.format("market=%s&quantity=%-10.8f&rate=%10.8f", market, quantity, rate);
		StandardHttpResult bresult = getDataByGetMethodWithAPIKey(AttaConstant.API_BITTEX_V1_MARKET_BUY_LIMIT, params);
		BittrexResponseUuid responsedUuid = toObjectFromJson(bresult, BittrexResponseUuid.class);
		if (!bresult.isSucess() || !responsedUuid.isUuidValid()) {
			throw new ExchangeServiceRequestFailException(String.format("Error can't buy with market=%s;quantity=%f,rate=%f;%s;%s", market, quantity, rate, bresult.toString(), responsedUuid.toString()));
		}

		return responsedUuid.getUuid();
	}

	@Override
	public synchronized String sellLimit(String market, double quantity, double rate) throws ExchangeServiceRequestFailException {
		String params = String.format("market=%s&quantity=%-10.8f&rate=%-10.8f", market, quantity, rate);
		StandardHttpResult bresult = getDataByGetMethodWithAPIKey(AttaConstant.API_BITTEX_V1_MARKET_SELL_LIMIT, params);
		BittrexResponseUuid responsedUuid = toObjectFromJson(bresult, BittrexResponseUuid.class);
		if (!bresult.isSucess() || !responsedUuid.isUuidValid()) {
			throw new ExchangeServiceRequestFailException(String.format("Error can't sell with market=%s;quantity=%f,rate=%f;%s;%s", market, quantity, rate, bresult.toString(), responsedUuid.toString()));
		}
		return responsedUuid.getUuid();
	}

	@Override
	public boolean cancel(String market, String uuid) throws ExchangeServiceRequestFailException {
		String params = String.format("uuid=%s", uuid);
		StandardHttpResult bresult = getDataByGetMethodWithAPIKey(AttaConstant.API_BITTEX_V1_MARKET_CANCEL, params);
		return bresult.isSucess();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IOrder> getOpenOrders(String market) throws ExchangeServiceRequestFailException {
		String params = String.format("market=%s", market);
		StandardHttpResult bresult = getDataByGetMethodWithAPIKey(AttaConstant.API_BITTEX_V1_MARKET_GET_OPEN_ORDERS, params);
		return (List<IOrder>) (List<?>) toListFromJson(bresult, BittrexOpenOrder[].class);
	}
}
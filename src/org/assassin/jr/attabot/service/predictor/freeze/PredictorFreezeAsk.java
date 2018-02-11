package org.assassin.jr.attabot.service.predictor.freeze;

import org.assassin.jr.attabot.config.CurrencySetting;
import org.assassin.jr.attabot.pojo.exchange.IOrder;
import org.assassin.jr.attabot.pojo.exchange.ITicker;
import org.assassin.jr.attabot.service.predictor.ExchangePredictor;
import org.assassin.jr.attabot.service.predictor.PredictParams;

public class PredictorFreezeAsk implements ExchangePredictor {

	@Override
	public double predict(PredictParams predictParams) {
		IOrder currentOrder = predictParams.getCurrentOrder();
		CurrencySetting currency = predictParams.getCurrency();

		ITicker ticker = predictParams.getTicker();
		double predictAskPrice = (1 + currency.getProfitRate()) * currentOrder.getLimit();

		return Math.max(predictAskPrice, ticker.getAsk());
	}
}
package org.assassin.jr.attabot.service.predictor.overlap;

import org.assassin.jr.attabot.config.CurrencySetting;
import org.assassin.jr.attabot.pojo.exchange.IOrder;
import org.assassin.jr.attabot.service.predictor.ExchangePredictor;
import org.assassin.jr.attabot.service.predictor.PredictParams;

public class PredictorOverlapAsk implements ExchangePredictor {

	@Override
	public double predict(PredictParams predictParams) {
		IOrder currentOrder = predictParams.getCurrentOrder();
		CurrencySetting currency = predictParams.getCurrency();

		return (1 + currency.getProfitRate()) * currentOrder.getLimit();
	}
}

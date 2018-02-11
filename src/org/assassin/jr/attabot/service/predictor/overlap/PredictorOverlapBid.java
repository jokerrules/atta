package org.assassin.jr.attabot.service.predictor.overlap;

import org.assassin.jr.attabot.config.CurrencySetting;
import org.assassin.jr.attabot.pojo.exchange.IOrder;
import org.assassin.jr.attabot.service.predictor.ExchangePredictor;
import org.assassin.jr.attabot.service.predictor.PredictParams;

public class PredictorOverlapBid implements ExchangePredictor {

	@Override
	public double predict(PredictParams predictParams) {
		IOrder currentOrder = predictParams.getCurrentOrder();
		CurrencySetting currency = predictParams.getCurrency();

		// ReBidRate = reBuyRate /(1 + profitRate)
		return currentOrder.getLimit() - currentOrder.getLimit() * currency.getReBidRate();
	}
}
package org.assassin.jr.attabot.service.predictor.freeze;

import org.assassin.jr.attabot.config.CurrencySetting;
import org.assassin.jr.attabot.pojo.exchange.ITicker;
import org.assassin.jr.attabot.service.predictor.ExchangePredictor;
import org.assassin.jr.attabot.service.predictor.PredictParams;

public class PredictorFreezeBid implements ExchangePredictor {

	@Override
	public double predict(PredictParams predictParams) {
		CurrencySetting currency = predictParams.getCurrency();
		ITicker ticker = predictParams.getTicker();
		return Math.min(currency.getDebutBid(), ticker.getBid());
	}
}

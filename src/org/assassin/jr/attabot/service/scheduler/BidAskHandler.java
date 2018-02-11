package org.assassin.jr.attabot.service.scheduler;

import org.assassin.jr.attabot.config.CurrencySetting;
import org.assassin.jr.attabot.service.exchange.ExchangeServiceHandler;
import org.assassin.jr.attabot.service.predictor.PredictorStandard;

public interface BidAskHandler {
	public void handleOrder(CurrencySetting currencySetting, ExchangeServiceHandler exchangeSerive, PredictorStandard predictor) throws Exception;
}
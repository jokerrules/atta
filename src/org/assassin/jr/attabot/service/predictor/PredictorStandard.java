package org.assassin.jr.attabot.service.predictor;

import java.util.Map;

import org.assassin.jr.attabot.config.CurrencySetting;
import org.assassin.jr.attabot.config.PredictType;
import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.service.exchange.ExchangeServiceHandler;

public interface PredictorStandard {
	public PredictParams createPredictParam(ExchangeServiceHandler exchangeServicehandler, CurrencySetting currencySetting) throws ExchangeServiceRequestFailException;

	public ExchangePredictorWrapper getPreictorWrapper(Map<String, String> options);

	public PredictType getPredictType();
}

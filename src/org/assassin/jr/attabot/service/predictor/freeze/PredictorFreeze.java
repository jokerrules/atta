package org.assassin.jr.attabot.service.predictor.freeze;

import java.util.Map;

import org.assassin.jr.attabot.config.CurrencySetting;
import org.assassin.jr.attabot.config.PredictType;
import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.service.exchange.ExchangeServiceHandler;
import org.assassin.jr.attabot.service.predictor.ExchangePredictorWrapper;
import org.assassin.jr.attabot.service.predictor.PredictParams;
import org.assassin.jr.attabot.service.predictor.PredictorStandard;

public class PredictorFreeze implements PredictorStandard {

	@Override
	public ExchangePredictorWrapper getPreictorWrapper(Map<String, String> options) {
		return new ExchangePredictorWrapper(this.getPredictType(), new PredictorFreezeBid(), new PredictorFreezeAsk());
	}

	@Override
	public PredictType getPredictType() {
		return PredictType.FREEZE;
	}

	@Override
	public PredictParams createPredictParam(ExchangeServiceHandler exchangeServicehandler, CurrencySetting currencySetting) throws ExchangeServiceRequestFailException {
		return new PredictParams();
	}
}

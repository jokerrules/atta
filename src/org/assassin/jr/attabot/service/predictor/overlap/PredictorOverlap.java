package org.assassin.jr.attabot.service.predictor.overlap;

import java.util.Map;

import org.assassin.jr.attabot.config.CurrencySetting;
import org.assassin.jr.attabot.config.PredictType;
import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.pojo.exchange.ITicker;
import org.assassin.jr.attabot.service.exchange.ExchangeServiceHandler;
import org.assassin.jr.attabot.service.predictor.ExchangePredictorWrapper;
import org.assassin.jr.attabot.service.predictor.PredictParams;
import org.assassin.jr.attabot.service.predictor.PredictorStandard;

public class PredictorOverlap implements PredictorStandard {

	@Override
	public ExchangePredictorWrapper getPreictorWrapper(Map<String, String> options) {
		return new ExchangePredictorWrapper(this.getPredictType(), new PredictorOverlapBid(), new PredictorOverlapAsk());
	}

	@Override
	public PredictType getPredictType() {
		return PredictType.OVERLAP;
	}

	@Override
	public PredictParams createPredictParam(ExchangeServiceHandler exchangeServicehandler, CurrencySetting currencySetting) throws ExchangeServiceRequestFailException {
		PredictParams params = new PredictParams();

		ITicker ticker = exchangeServicehandler.getPublicService().getTicker(currencySetting.getMarket());
		params.setTicker(ticker);

		return params;
	}
}

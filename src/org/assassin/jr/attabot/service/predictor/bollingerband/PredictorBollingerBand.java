package org.assassin.jr.attabot.service.predictor.bollingerband;

import java.util.List;
import java.util.Map;

import org.assassin.jr.attabot.config.CurrencySetting;
import org.assassin.jr.attabot.config.PredictType;
import org.assassin.jr.attabot.exception.ExchangeServiceRequestFailException;
import org.assassin.jr.attabot.pojo.exchange.ITick;
import org.assassin.jr.attabot.pojo.exchange.ITicker;
import org.assassin.jr.attabot.pojo.exchange.TickInterval;
import org.assassin.jr.attabot.service.exchange.ExchangeServiceHandler;
import org.assassin.jr.attabot.service.predictor.ExchangePredictorWrapper;
import org.assassin.jr.attabot.service.predictor.PredictParams;
import org.assassin.jr.attabot.service.predictor.PredictorStandard;
import org.assassin.jr.attabot.utility.AttaConstant;

public class PredictorBollingerBand implements PredictorStandard {

	@Override
	public ExchangePredictorWrapper getPreictorWrapper(Map<String, String> options) {
		ExchangeIndicatorParamsBollingerBand params = new ExchangeIndicatorParamsBollingerBand();

		if (options.containsKey(AttaConstant.ATTA_CONFIG_FIELD_PLAY_TYPE_BB_LENGTH)) {
			params.setLength(Integer.parseInt(options.get(AttaConstant.ATTA_CONFIG_FIELD_PLAY_TYPE_BB_LENGTH)));
		}

		if (options.containsKey(AttaConstant.ATTA_CONFIG_FIELD_PLAY_TYPE_BB_STD_DEVIATION)) {
			params.setStandardDeviation(Integer.parseInt(options.get(AttaConstant.ATTA_CONFIG_FIELD_PLAY_TYPE_BB_STD_DEVIATION)));
		}

		if (options.containsKey(AttaConstant.ATTA_CONFIG_FIELD_PLAY_TYPE_BB_RANGE_RATE)) {
			params.setRangeRate(Double.parseDouble(options.get(AttaConstant.ATTA_CONFIG_FIELD_PLAY_TYPE_BB_RANGE_RATE)));
		}

		return new ExchangePredictorWrapper(getPredictType(), new PredictorBollingerBandBid(params), new PredictorBollingerBandAsk(params));
	}

	@Override
	public PredictType getPredictType() {
		return PredictType.BOLLINGER_BAND;
	}

	@Override
	public PredictParams createPredictParam(ExchangeServiceHandler exchangeService, CurrencySetting currencySetting) throws ExchangeServiceRequestFailException {
		PredictParams params = new PredictParams();

		ITicker ticker = exchangeService.getPublicService().getTicker(currencySetting.getMarket());
		params.setTicker(ticker);

		List<ITick> lstTick = exchangeService.getPublicService().getTicks(currencySetting.getMarket(), TickInterval.THIRTY_MIN);
		params.setTicks(lstTick);

		return params;
	}
}

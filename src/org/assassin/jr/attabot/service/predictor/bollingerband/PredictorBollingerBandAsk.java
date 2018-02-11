package org.assassin.jr.attabot.service.predictor.bollingerband;

import java.util.List;

import org.assassin.jr.attabot.pojo.exchange.ITick;
import org.assassin.jr.attabot.pojo.exchange.ITicker;
import org.assassin.jr.attabot.service.predictor.ExchangePredictor;
import org.assassin.jr.attabot.service.predictor.PredictParams;
import org.assassin.jr.attabot.utility.AttaUtility;

public class PredictorBollingerBandAsk implements ExchangePredictor {
	private ExchangeIndicatorParamsBollingerBand bbParams;

	public PredictorBollingerBandAsk() {
		bbParams = new ExchangeIndicatorParamsBollingerBand();
	}

	public PredictorBollingerBandAsk(ExchangeIndicatorParamsBollingerBand params) {
		this.bbParams = params;
	}

	@Override
	public double predict(PredictParams predictParams) {
		List<ITick> lstTicks = predictParams.getTicks();
		ITicker ticker = predictParams.getTicker();

		double rangePercent = bbParams.getRangeRate();
		BollingerBands bb = new IndicatorBollingerBandsCalculator().calculate(lstTicks, bbParams);

		double upperUpperRange = bb.getUpperBand() + rangePercent * bb.getUpperBand();
		double lowerUpperRange = bb.getUpperBand() - rangePercent * bb.getUpperBand();
		boolean isLastTickerInRangeUpper = ticker.getLast() <= upperUpperRange && ticker.getLast() >= lowerUpperRange;

		boolean isLastTickCandleDown = bbParams.isUseFlipSign() ? AttaUtility.isLastTickDown(lstTicks) : true;

		if (isLastTickerInRangeUpper && isLastTickCandleDown) {
			return ticker.getLast();
		}

		return -1;
	}
}
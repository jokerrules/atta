package org.assassin.jr.attabot.service.predictor.bollingerband;

import java.util.List;

import org.assassin.jr.attabot.pojo.exchange.ITick;
import org.assassin.jr.attabot.pojo.exchange.ITicker;
import org.assassin.jr.attabot.service.predictor.ExchangePredictor;
import org.assassin.jr.attabot.service.predictor.PredictParams;
import org.assassin.jr.attabot.utility.AttaUtility;

public class PredictorBollingerBandBid implements ExchangePredictor {
	private ExchangeIndicatorParamsBollingerBand bbParams;

	public PredictorBollingerBandBid() {
		bbParams = new ExchangeIndicatorParamsBollingerBand();
	}

	public PredictorBollingerBandBid(ExchangeIndicatorParamsBollingerBand params) {
		this.bbParams = params;
	}

	@Override
	public double predict(PredictParams predictParams) {
		List<ITick> lstTicks = predictParams.getTicks();
		ITicker ticker = predictParams.getTicker();

		double rangePercent = bbParams.getRangeRate();
		BollingerBands bb = new IndicatorBollingerBandsCalculator().calculate(lstTicks, bbParams);

		double upperLowRange = bb.getLowerBand() + rangePercent * bb.getLowerBand();
		double lowerLowRange = bb.getLowerBand() - rangePercent * bb.getLowerBand();
		boolean isLastTickerInRangeLower = ticker.getLast() <= upperLowRange && ticker.getLast() >= lowerLowRange;
		boolean isLastTickCandleUp = bbParams.isUseFlipSign() ? AttaUtility.isLastTickUp(lstTicks) : true;

		if (isLastTickerInRangeLower && isLastTickCandleUp) {
			return ticker.getLast();
		}

		return -1;
	}
}
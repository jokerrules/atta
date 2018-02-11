package org.assassin.jr.attabot.service.predictor.bollingerband;

import java.util.List;

import org.assassin.jr.attabot.pojo.exchange.ITick;
import org.assassin.jr.attabot.service.predictor.ExchangeIndicatorCalculator;
import org.assassin.jr.attabot.service.predictor.ExchangeIndicatorParams;
import org.assassin.jr.attabot.utility.AttaMath;

public class IndicatorBollingerBandsCalculator implements ExchangeIndicatorCalculator<BollingerBands> {

	@Override
	public BollingerBands calculate(List<ITick> lstTicks, ExchangeIndicatorParams genericParams) {
		ExchangeIndicatorParamsBollingerBand params = (ExchangeIndicatorParamsBollingerBand) genericParams;

		BollingerBands bb = new BollingerBands();
		double smaValue = AttaMath.calSMA(lstTicks, params.getLength());
		double standardDeviation = AttaMath.calStandardDeviation(lstTicks, params.getLength());
		bb.setMiddleBand(smaValue);
		bb.setUpperBand(smaValue + standardDeviation * params.getStandardDeviation());
		bb.setLowerBand(smaValue - standardDeviation * params.getStandardDeviation());
		return bb;
	}
}

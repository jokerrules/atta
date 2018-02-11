package org.assassin.jr.attabot.service.predictor;

import java.util.List;

import org.assassin.jr.attabot.pojo.exchange.ITick;

public interface ExchangeIndicatorCalculator<T> {
	public T calculate(List<ITick> lstTicks, ExchangeIndicatorParams params);
}

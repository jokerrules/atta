package org.assassin.jr.attabot.service.predictor.bollingerband;

import org.assassin.jr.attabot.service.predictor.ExchangeIndicatorParams;

public class ExchangeIndicatorParamsBollingerBand implements ExchangeIndicatorParams {
	private static final int DEFAULT_PERIOD = 20;
	private static final int DEFAULT_STD_DEVIATION = 2;
	private static final double DEFAULT_RANGE_RATE = 0.05;

	private int length;
	private int standardDeviation;
	private double rangeRate;
	private boolean isUseFlipSign;

	public ExchangeIndicatorParamsBollingerBand(int length, int standardDeviation, double rangeRate, boolean isUseFlipSign) {
		this.length = length;
		this.standardDeviation = standardDeviation;
		this.rangeRate = rangeRate;
		this.isUseFlipSign = isUseFlipSign;
	}

	public ExchangeIndicatorParamsBollingerBand() {
		this.length = DEFAULT_PERIOD;
		this.standardDeviation = DEFAULT_STD_DEVIATION;
		this.rangeRate = DEFAULT_RANGE_RATE;
		this.isUseFlipSign = false;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getStandardDeviation() {
		return standardDeviation;
	}

	public void setStandardDeviation(int standardDeviation) {
		this.standardDeviation = standardDeviation;
	}

	public double getRangeRate() {
		return rangeRate;
	}

	public void setRangeRate(double rangeRate) {
		this.rangeRate = rangeRate;
	}

	public boolean isUseFlipSign() {
		return isUseFlipSign;
	}

	public void setUseFlipSign(boolean isUseFlipSign) {
		this.isUseFlipSign = isUseFlipSign;
	}
}
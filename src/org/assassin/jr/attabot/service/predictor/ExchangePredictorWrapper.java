package org.assassin.jr.attabot.service.predictor;

import org.assassin.jr.attabot.config.PredictType;

public class ExchangePredictorWrapper {
	private PredictType predictType;
	private ExchangePredictor bidPredictor;
	private ExchangePredictor askPredictor;

	public ExchangePredictorWrapper(PredictType predictType, ExchangePredictor bidPredictor, ExchangePredictor askPredictor) {
		this.bidPredictor = bidPredictor;
		this.askPredictor = askPredictor;
		this.predictType = predictType;
	}

	public PredictType getPredictType() {
		return predictType;
	}

	public void setPredictType(PredictType predictType) {
		this.predictType = predictType;
	}

	public ExchangePredictor getBidPredictor() {
		return bidPredictor;
	}

	public void setBidPredictor(ExchangePredictor bidPredictor) {
		this.bidPredictor = bidPredictor;
	}

	public ExchangePredictor getAskPredictor() {
		return askPredictor;
	}

	public void setAskPredictor(ExchangePredictor askPredictor) {
		this.askPredictor = askPredictor;
	}
}
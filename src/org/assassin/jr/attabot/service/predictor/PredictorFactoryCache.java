package org.assassin.jr.attabot.service.predictor;

import java.util.HashMap;
import java.util.Map;

import org.assassin.jr.attabot.config.PredictType;
import org.assassin.jr.attabot.service.predictor.bollingerband.PredictorBollingerBand;
import org.assassin.jr.attabot.service.predictor.freeze.PredictorFreeze;
import org.assassin.jr.attabot.service.predictor.overlap.PredictorOverlap;

public class PredictorFactoryCache {
	private static Map<PredictType, PredictorStandard> mapPredictInstance = new HashMap<>();

	static {
		mapPredictInstance.put(PredictType.OVERLAP, new PredictorOverlap());
		mapPredictInstance.put(PredictType.BOLLINGER_BAND, new PredictorBollingerBand());
		mapPredictInstance.put(PredictType.FREEZE, new PredictorFreeze());
	}

	public static PredictorStandard getPredictor(PredictType predictType) {
		if (!mapPredictInstance.containsKey(predictType)) {
			throw new IllegalArgumentException(String.format("Predict type %s is not found", predictType.toString()));
		}
		return mapPredictInstance.get(predictType);
	}
}

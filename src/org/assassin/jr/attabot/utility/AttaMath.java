package org.assassin.jr.attabot.utility;

import java.util.List;

import org.assassin.jr.attabot.config.CurrencySetting;
import org.assassin.jr.attabot.pojo.exchange.ITick;

public class AttaMath {

	public static double calStandardDeviation(List<ITick> lstTicks, int periods) {
		int lstTicksSize = lstTicks.size();
		double mean = AttaMath.calSMA(lstTicks, periods);
		double variance = 0;

		for (int i = lstTicksSize - periods; i < lstTicksSize; i++) {
			variance += Math.pow(lstTicks.get(i).getClosePrice() - mean, 2);
		}

		return Math.sqrt(variance / periods);
	}

	public static double calSMA(List<ITick> lstTicks, int periods) {
		int lstTicksSize = lstTicks.size();

		if (lstTicksSize < periods) {
			throw new IllegalArgumentException(String.format("List tick size %d < periods %d", lstTicksSize, periods));
		}

		double sum = 0;
		for (int i = lstTicksSize - periods; i < lstTicksSize; i++) {
			sum += lstTicks.get(i).getClosePrice();
		}

		return sum / periods;
	}

	public static double round(double value, int places) {
		if (places < 0) {
			throw new IllegalArgumentException("decimal places can't less than 0.");
		}

		double factor = Math.pow(10, places);
		return Math.floor(value * factor) / factor;
	}

	public static double roundQty(CurrencySetting currency, double quantity) {
		if (currency.getQuantityRound() == null) {
			return quantity;
		}
		return round(quantity, currency.getQuantityRound());
	}

	public static double roundPrice(CurrencySetting currency, double price) {
		if (currency.getPriceRound() == null) {
			return price;
		}
		return round(price, currency.getPriceRound());
	}
}

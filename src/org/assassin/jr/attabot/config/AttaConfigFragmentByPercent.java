package org.assassin.jr.attabot.config;

import java.util.List;

import org.assassin.jr.attabot.utility.AttaConstant;
import org.assassin.jr.attabot.utility.AttaMath;

public class AttaConfigFragmentByPercent extends AttaConfigFragmentDefault {

	@Override
	protected boolean validate(CurrencySetting currency) {
		CurrencyFragment fgm = currency.getFragment();
		if (fgm.getFragmentType() != CurrencyFragmentType.BID_PERCENT) {
			return false;
		}

		if (fgm.getBidRates() == null) {
			throw new IllegalArgumentException("bidRate is null");
		}

		if (fgm.getProfits() == null) {
			throw new IllegalArgumentException("profits is null");
		}

		if (fgm.getBidRates().length != fgm.getProfits().length) {
			throw new IllegalArgumentException(String.format("bidRate length is : %d, profits length is: %s. It doesn't same", fgm.getBidRates().length, fgm.getProfits().length));
		}

		if (fgm.getBidRatesCond() != null && fgm.getBidRatesCond().length != fgm.getProfits().length) {
			throw new IllegalArgumentException(String.format("bidRatesCond length is : %d, profits length is: %s. It doesn't same", fgm.getBidRatesCond().length, fgm.getProfits().length));
		}

		return true;
	}

	@Override
	protected void createAttaConfig(CurrencySetting currency, List<CurrencySetting> lstNewCurrencyItem) {
		CurrencyFragment fgm = currency.getFragment();

		Double[] bidRates = fgm.getBidRates();
		Double[] bidRatesConds = fgm.getBidRatesCond();
		Double[] profits = fgm.getProfits();

		double avgPrice = fgm.getAvgPrice();
		int priceRound = currency.getPriceRound() == null ? AttaConstant.MAX_LOT : currency.getPriceRound();

		for (int i = 0; i < bidRates.length; i++) {
			CurrencySetting newSetting = currency.clone();
			newSetting.setId(String.format("%s-%s-%d", newSetting.getId(), fgm.getPrefixId(), i));
			newSetting.setBudget(AttaMath.round(currency.getBudget() / bidRates.length, priceRound));
			newSetting.setDebutBid(AttaMath.round(avgPrice * (1 - bidRates[i]), priceRound));
			newSetting.setProfitRate(profits[i]);

			if (bidRatesConds != null) {
				newSetting.setBidPriceCond(AttaMath.round(avgPrice * (1 - bidRatesConds[i]), priceRound));
			}

			lstNewCurrencyItem.add(newSetting);
		}
	}
}
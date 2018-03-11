package org.assassin.jr.attabot.config;

import java.util.List;

import org.assassin.jr.attabot.utility.AttaConstant;
import org.assassin.jr.attabot.utility.AttaMath;

public class AttaConfigFragmentByPrice extends AttaConfigFragmentDefault {

	@Override
	protected boolean validate(CurrencySetting currency) {
		CurrencyFragment fgm = currency.getFragment();
		if (fgm.getFragmentType() != CurrencyFragmentType.BID_PRICE) {
			return false;
		}

		if (fgm.getBidPrices() == null) {
			throw new IllegalArgumentException("bidPrice is null");
		}

		if (fgm.getProfits() == null) {
			throw new IllegalArgumentException("profits is null");
		}

		if (fgm.getBidPrices().length != fgm.getProfits().length) {
			throw new IllegalArgumentException(String.format("bidPrice length is : %d, profits length is: %s. It doesn't same", fgm.getBidPrices().length, fgm.getProfits().length));
		}

		if (fgm.getBidPricesCond() != null && fgm.getBidPricesCond().length != fgm.getProfits().length) {
			throw new IllegalArgumentException(String.format("bidPriceCond length is : %d, profits length is: %s. It doesn't same", fgm.getBidPricesCond().length, fgm.getProfits().length));
		}

		return true;
	}

	@Override
	protected void createAttaConfig(CurrencySetting currency, List<CurrencySetting> lstNewCurrencyItem) {
		CurrencyFragment fgm = currency.getFragment();
		Double[] bidPrices = fgm.getBidPrices();
		Double[] bidPriceConds = fgm.getBidPricesCond();
		Double[] bidPriceCondBelows = fgm.getBidPricesCondBelow();
		Double[] profits = fgm.getProfits();
		int priceRound = currency.getPriceRound() == null ? AttaConstant.MAX_LOT : currency.getPriceRound();

		for (int i = 0; i < bidPrices.length; i++) {
			CurrencySetting newSetting = currency.clone();
			newSetting.setId(String.format("%s-%s-%d", newSetting.getId(), fgm.getPrefixId(), i));
			newSetting.setBudget(AttaMath.round(currency.getBudget() / bidPrices.length, priceRound));
			newSetting.setDebutBid(bidPrices[i]);
			newSetting.setProfitRate(profits[i]);

			if (bidPriceConds != null) {
				newSetting.setBidPriceCond(bidPriceConds[i]);
			}

			if (bidPriceCondBelows != null) {
				newSetting.setBidPriceCondBelow(bidPriceCondBelows[i]);
			}

			lstNewCurrencyItem.add(newSetting);
		}
	}
}

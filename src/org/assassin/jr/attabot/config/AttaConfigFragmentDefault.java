package org.assassin.jr.attabot.config;

import java.util.ArrayList;
import java.util.List;

public abstract class AttaConfigFragmentDefault implements AttaConfigHandler {

	@Override
	public AttaConfig handle(AttaConfig attaConfig) {
		List<CurrencySetting> lstCurrency = attaConfig.getLstCurrencies();

		for (int i = lstCurrency.size() - 1; i >= 0; i--) {
			CurrencySetting currency = lstCurrency.get(i);

			if (!currency.isActive() || currency.getFragment() == null) {
				continue;
			}

			if (!validate(currency)) {
				continue;
			}

			List<CurrencySetting> lstNewCurrencyItem = new ArrayList<>();
			createAttaConfig(currency, lstNewCurrencyItem);
			lstCurrency.addAll(lstNewCurrencyItem);
			lstCurrency.remove(currency);
		}

		return attaConfig;
	}

	protected boolean validateAvgPrice(CurrencyFragment fgm) {
		if (fgm.getAvgPrice() == null) {
			throw new IllegalArgumentException("avgPrice attribute is required.");
		}
		return true;
	}

	protected boolean validateProfits(CurrencyFragment fgm) {
		if (fgm.getProfits() == null) {
			throw new IllegalArgumentException("profits attribute is required.");
		}
		return true;
	}

	protected abstract boolean validate(CurrencySetting currency);

	protected abstract void createAttaConfig(CurrencySetting currency, List<CurrencySetting> lstNewCurrencyItem);
}

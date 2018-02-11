package org.assassin.jr.attabot.config;

public enum ExchangeMarket {
	BITTREX("BITTREX"), BINANCE("BINANCE");

	private String value;

	private ExchangeMarket(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static ExchangeMarket valueOfType(String value) {
		if (BITTREX.getValue().equals(value)) {
			return BITTREX;
		}

		if (BINANCE.getValue().equals(value)) {
			return BINANCE;
		}

		return null;
	}

	@Override
	public String toString() {
		return this.value;
	}
}

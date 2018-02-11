package org.assassin.jr.attabot.config;

public enum CurrencyFragmentType {
	BID_PERCENT("BID_PERCENT"), BID_PRICE("BID_PRICE");

	private String value;

	private CurrencyFragmentType(String type) {
		this.value = type;
	}

	public String getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}

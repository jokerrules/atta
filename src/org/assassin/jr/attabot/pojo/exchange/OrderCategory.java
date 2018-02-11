package org.assassin.jr.attabot.pojo.exchange;

public enum OrderCategory {
	BUY("BUY"), SELL("SELL");

	private String value;

	private OrderCategory(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value;
	}
}

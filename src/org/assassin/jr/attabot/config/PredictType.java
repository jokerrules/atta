package org.assassin.jr.attabot.config;

public enum PredictType {
	OVERLAP("OVERLAP"), BOLLINGER_BAND("BOLLINGER_BAND"), FREEZE("FREEZE");

	private String value;

	private PredictType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public String toString() {
		return this.value;
	}
}

package org.assassin.jr.attabot.pojo.exchange;

public enum TickInterval {
	ONE_MIN("oneMin"), FIVE_MIN("fiveMin"), THIRTY_MIN("thirtyMin"), HOUR("hour"), DAY("day");

	private String value;

	private TickInterval(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value;
	}
}

package org.assassin.jr.attabot.service.management;

public class ProfitStoredDetail {
	private double profit;
	private int times;
	private double percent;

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public void addProfit(double profit) {
		setProfit(getProfit() + profit);
		addOneTimes();
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	private void addOneTimes() {
		setTimes(getTimes() + 1);
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public void addPercent(double percent) {
		setPercent(getPercent() + percent);
	}
}
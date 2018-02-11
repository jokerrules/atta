package org.assassin.jr.attabot.service.management;

import java.util.HashMap;
import java.util.Map;

public class ProfitStored {
	private double totalProfit;
	private double totalTimes;

	private Map<String, ProfitStoredDetail> details;

	public ProfitStored() {
		totalProfit = 0;
		details = new HashMap<>();
	}

	public double getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(double totalProfit) {
		this.totalProfit = totalProfit;
	}

	public void addTotalProfit(double profit) {
		setTotalProfit(getTotalProfit() + profit);
		addOneTime();
	}

	public double getTotalTimes() {
		return totalTimes;
	}

	public void setTotalTimes(double totalTimes) {
		this.totalTimes = totalTimes;
	}

	private void addOneTime() {
		setTotalTimes(getTotalTimes() + 1);
	}

	public Map<String, ProfitStoredDetail> getDetails() {
		return details;
	}

	public void setDetails(Map<String, ProfitStoredDetail> details) {
		this.details = details;
	}
}
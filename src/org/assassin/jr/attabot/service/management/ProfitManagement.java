package org.assassin.jr.attabot.service.management;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.assassin.jr.attabot.utility.AttaGobalSetting;

import com.google.gson.reflect.TypeToken;

public class ProfitManagement extends DataManagementWrapper {
	private static final String PROFIT_FILE_PATH = AttaGobalSetting.getInstance().getStringValue(AttaGobalSetting.MANAGEMENT_PROFIT_SAVE_PATH);
	private static final int MAX_DISPLAY_DAY = AttaGobalSetting.getInstance().getIntValue(AttaGobalSetting.MANAGEMENT_PROFIT_MAX_DISPLAY_DAY);

	private static ProfitManagement singleton;
	private Map<String, ProfitStored> mapProfitStored;
	private SimpleDateFormat dateFormatter;

	private ProfitManagement() {
		super();
		dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		mapProfitStored = new HashMap<>();
	}

	public synchronized static ProfitManagement getInstance() {
		if (singleton == null) {
			singleton = new ProfitManagement();
			singleton.readProfitFromFile(PROFIT_FILE_PATH);
			singleton.restoreToQueue(PROFIT_FILE_PATH);
		}

		return singleton;
	}

	@SuppressWarnings("unchecked")
	private void readProfitFromFile(String path) {
		Type listType = new TypeToken<Map<String, ProfitStored>>() {
		}.getType();

		Map<String, ProfitStored> mapCurrencyProfitTemp = (Map<String, ProfitStored>) readFile(path, listType);
		if (mapCurrencyProfitTemp != null) {
			mapProfitStored = mapCurrencyProfitTemp;
		}
	}

	public synchronized void addProfit(String date, String currencyId, double profit, double percent) {
		if (mapProfitStored.size() >= MAX_DISPLAY_DAY && !mapProfitStored.containsKey(date)) {
			String minDay = mapProfitStored.keySet().stream().min(String::compareTo).get();
			mapProfitStored.remove(minDay);
		}

		if (!mapProfitStored.containsKey(date)) {
			mapProfitStored.put(date, new ProfitStored());
		}

		ProfitStored profitStored = mapProfitStored.get(date);
		profitStored.addTotalProfit(profit);

		if (!profitStored.getDetails().containsKey(currencyId)) {
			profitStored.getDetails().put(currencyId, new ProfitStoredDetail());
		}

		ProfitStoredDetail detail = profitStored.getDetails().get(currencyId);
		detail.addProfit(profit);
		detail.addPercent(percent);
	}

	public void addProfit(Date date, String currencyId, double profit, double percent) {
		addProfit(dateFormatter.format(date), currencyId, profit, percent);
	}

	public ProfitStored getProfit(String date) {
		return mapProfitStored.get(date);
	}

	public void save() {
		saveFile(PROFIT_FILE_PATH, mapProfitStored);
	}
}
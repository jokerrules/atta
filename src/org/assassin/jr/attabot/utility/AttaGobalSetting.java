package org.assassin.jr.attabot.utility;

import java.util.HashMap;
import java.util.Map;

import org.assassin.jr.attabot.config.CommonSetting;

public class AttaGobalSetting {
	public static final String MANAGEMENT_ORDER_SAVE_PATH = "ORDER_MANAGEMENT_SAVE_PATH";
	public static final String MANAGEMENT_PROFIT_SAVE_PATH = "PROFIT_MANAGEMENT_SAVE_PATH";
	public static final String MANAGEMENT_PROFIT_MAX_DISPLAY_DAY = "PROFIT_MANAGEMENT_MAX_DISPLAY_DAY";
	public static final String MANAGEMENT_MAX_BACKUP_FILE_QTY = "MANAGEMENT_MAX_BACKUP_FILE_QTY";
	public static final String LOG_DIR = "LOG_DIR";

	private Map<String, String> mapStringValue = new HashMap<>();
	private Map<String, Integer> mapIntValue = new HashMap<>();
	
	private static AttaGobalSetting singleton;
	
	public AttaGobalSetting() {
		mapStringValue.put(MANAGEMENT_ORDER_SAVE_PATH, "orders-management/pl.json");
		mapStringValue.put(MANAGEMENT_PROFIT_SAVE_PATH, "profits-management/pp.json");
		mapStringValue.put(LOG_DIR, "logs/");
		mapIntValue.put(MANAGEMENT_PROFIT_MAX_DISPLAY_DAY, 5);
		mapIntValue.put(MANAGEMENT_MAX_BACKUP_FILE_QTY, 9);
	}
	
	public static AttaGobalSetting getInstance() {
		if (singleton == null) {
			singleton = new AttaGobalSetting();
		}
		return singleton;
	}

	public void setStringValue(String key, String value) {
		if (value == null) {
			return;
		}
		mapStringValue.put(key, value);
	}

	public String getStringValue(String key) {
		return mapStringValue.get(key);
	}

	public void setIntValue(String key, Integer value) {
		if (value == null) {
			return;
		}
		mapIntValue.put(key, value);
	}

	public int getIntValue(String key) {
		return mapIntValue.get(key);
	}
	
	public void setSettingFromCommon(CommonSetting commonSetting) {
		AttaGobalSetting gs = AttaGobalSetting.getInstance();
		gs.setStringValue(MANAGEMENT_ORDER_SAVE_PATH, commonSetting.getManagementOrderdSavePath());
		gs.setStringValue(MANAGEMENT_PROFIT_SAVE_PATH, commonSetting.getManagementProfitSavePath());
		gs.setIntValue(MANAGEMENT_PROFIT_MAX_DISPLAY_DAY, commonSetting.getManagementProfitDisplayDay());
		gs.setIntValue(MANAGEMENT_MAX_BACKUP_FILE_QTY, commonSetting.getManagementMaxBackupFiles());
		gs.setStringValue(LOG_DIR, commonSetting.getLogDir());
	}
}
package org.assassin.jr.attabot.logger;

import java.util.HashMap;
import java.util.Map;

import org.assassin.jr.attabot.utility.AttaGobalSetting;

public class DynamicLogger {
	private static DynamicLogger singleton;
	private Map<String, LoggerStandard> mapLogger;

	private DynamicLogger() {
		this.mapLogger = new HashMap<String, LoggerStandard>();
	}

	public synchronized static DynamicLogger getInstance() {
		if (singleton == null) {
			singleton = new DynamicLogger();
		}
		return singleton;
	}

	public LoggerStandard getLogger(String name) {
		LoggerStandard logger = mapLogger.get(name);
		if (logger == null) {
			logger = new LoggerRollingDate(AttaGobalSetting.getInstance().getStringValue(AttaGobalSetting.LOG_DIR) + name + "/" + name);
			mapLogger.put(name, logger);
		}
		return logger;
	}
}
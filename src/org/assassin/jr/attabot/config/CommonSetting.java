package org.assassin.jr.attabot.config;

import java.util.Map;

public class CommonSetting {
	private short corePoolSize;
	private long updateTime;
	private String logDir;
	private Integer managementMaxBackupFiles;
	private Integer managementProfitDisplayDay;
	private String managementProfitSavePath;
	private String managementOrderdSavePath;

	private Map<ExchangeMarket, ApiKeyInfo> marketApiKey;

	public CommonSetting() {
	}

	public short getCorePoolSize() {
		return corePoolSize;
	}

	public void setCorePoolSize(short corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	public ApiKeyInfo getMarketApiKey(ExchangeMarket market) {
		return marketApiKey.get(market);
	}

	public void setMarketApiKey(Map<ExchangeMarket, ApiKeyInfo> marketApiKey) {
		this.marketApiKey = marketApiKey;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public String getLogDir() {
		return logDir;
	}

	public void setLogDir(String logDir) {
		this.logDir = logDir;
	}

	public Integer getManagementMaxBackupFiles() {
		return managementMaxBackupFiles;
	}

	public void setManagementMaxBackupFiles(Integer managementMaxBackupFiles) {
		this.managementMaxBackupFiles = managementMaxBackupFiles;
	}

	public Integer getManagementProfitDisplayDay() {
		return managementProfitDisplayDay;
	}

	public void setManagementProfitDisplayDay(Integer managementProfitDisplayDay) {
		this.managementProfitDisplayDay = managementProfitDisplayDay;
	}

	public String getManagementProfitSavePath() {
		return managementProfitSavePath;
	}

	public void setManagementProfitSavePath(String managementProfitSavePath) {
		this.managementProfitSavePath = managementProfitSavePath;
	}

	public String getManagementOrderdSavePath() {
		return managementOrderdSavePath;
	}

	public void setManagementOrderdSavePath(String managementOrderdSavePath) {
		this.managementOrderdSavePath = managementOrderdSavePath;
	}

	public Map<ExchangeMarket, ApiKeyInfo> getMarketApiKey() {
		return marketApiKey;
	}

	@Override
	public String toString() {
		return "CommonSetting [corePoolSize=" + corePoolSize + ", updateTime=" + updateTime + "]";
	}
}
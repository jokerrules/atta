package org.assassin.jr.attabot.config;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class AttaConfig {
	private CommonSetting common;

	@SerializedName(value = "currencies")
	private List<CurrencySetting> lstCurrencies;

	private transient Gson gson;

	public AttaConfig() {
		gson = new GsonBuilder().setPrettyPrinting().create();
	}

	public CommonSetting getCommon() {
		return common;
	}

	public void setCommon(CommonSetting common) {
		this.common = common;
	}

	public List<CurrencySetting> getLstCurrencies() {
		return lstCurrencies;
	}

	public void setLstCurrencies(List<CurrencySetting> lstCurrencies) {
		this.lstCurrencies = lstCurrencies;
	}

	@Override
	public String toString() {
		return "AttaConfig [common=" + common + ", lstCurrencies=" + lstCurrencies + "]";
	}

	public String toJson() {
		return gson.toJson(this);
	}
}

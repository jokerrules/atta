package org.assassin.jr.attabot.pojo.exchange.bittrex;

import com.google.gson.annotations.SerializedName;

public class BittrexResponseUuid {
	@SerializedName(value = "uuid")
	private String uuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public boolean isUuidValid() {
		if (getUuid() == null || getUuid().equals("") || getUuid().equals("null")) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ResponseUuid [uuid=" + uuid + "]";
	}
}

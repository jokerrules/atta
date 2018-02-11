package org.assassin.jr.attabot.service.management;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assassin.jr.attabot.utility.AttaGobalSetting;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class OrdersManagement extends DataManagementWrapper {
	private static Logger logger = LogManager.getLogger(OrdersManagement.class);
	private static final String ORDER_FILE_PATH = AttaGobalSetting.getInstance().getStringValue(AttaGobalSetting.MANAGEMENT_ORDER_SAVE_PATH);

	private static OrdersManagement singleton;
	private Map<String, OrderStored> mapCurrencySettingUuidOrder;

	private OrdersManagement() {
		super();
		this.mapCurrencySettingUuidOrder = new HashMap<String, OrderStored>();
	}

	public static synchronized OrdersManagement getInstance() {
		if (singleton == null) {
			singleton = new OrdersManagement();
			singleton.readOrdersFromFile(ORDER_FILE_PATH);
			singleton.restoreToQueue(ORDER_FILE_PATH);
		}
		return singleton;
	}

	@SuppressWarnings("unchecked")
	private void readOrdersFromFile(String path) {
		Type listType = new TypeToken<Map<String, OrderStored>>() {
		}.getType();

		Map<String, OrderStored> mapCurrencyOrderTemp = (Map<String, OrderStored>) readFile(path, listType);
		if (mapCurrencyOrderTemp != null) {
			mapCurrencySettingUuidOrder = mapCurrencyOrderTemp;
		}
	}

	public void putOrder(String currencyId, OrderStored orderStore) {
		logger.info("Last OrderStored: " + this.mapCurrencySettingUuidOrder.get(currencyId));
		this.mapCurrencySettingUuidOrder.put(currencyId, orderStore);
		logger.info(String.format("Add new OrderStored id=%s, value=%s", currencyId, orderStore));
	}

	public OrderStored getOrder(String currencyId) {
		return this.mapCurrencySettingUuidOrder.get(currencyId);
	}

	public void save() {
		saveFile(ORDER_FILE_PATH, mapCurrencySettingUuidOrder);
	}

	@Override
	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(mapCurrencySettingUuidOrder);
	}
}
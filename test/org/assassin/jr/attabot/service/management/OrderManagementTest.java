package org.assassin.jr.attabot.service.management;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.assassin.jr.attabot.pojo.exchange.OrderCategory;
import org.assassin.jr.attabot.service.management.OrderStored;
import org.assassin.jr.attabot.service.management.OrdersManagement;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OrderManagementTest {

	// @Test
	public void testTransformData() {
		Map<String, String> map = new HashMap<>();
		map.put("BTC-ETH-overlap-1", "3d3c379a-3146-4b79-bc89-26bbf8fe1474");
		map.put("BTC-XLM-bb-1", "94122bc1-cb41-497b-ae96-4ee675ab2cc0");
		map.put("BTC-XVG-bb-1", "c0b1ab0a-489a-4d91-9b40-684d787df107");
		map.put("BN-BTC-ADA-overlap-1", "bgVWFv2GqSvUTmtcxKx1aP");
		map.put("ADA-bb-1", "VjCfdBc1K5CdVOFarCYrVD");
		map.put("ICX-overlap-1", "AxUAvzzi0Me0FKl8aoUbAi");
		map.put("NEO-overlap-1", "qDAIcZgJQbAZtLcYdwVo5a");

		Map<String, OrderStored> mapNew = new HashMap<>();

		Set<String> setKeys = map.keySet();
		for (String key : setKeys) {
			mapNew.put(key, getEmptyOrderStore(map.get(key)));
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(mapNew));

	}

	private OrderStored getEmptyOrderStore(String uuid) {
		return new OrderStored("BITTREX", 0, 0, OrderCategory.BUY, uuid, null);
	}

	@Test
	public void testLoadOrderStored() {
		System.out.println(OrdersManagement.getInstance());
	}

}

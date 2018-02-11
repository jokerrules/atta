package org.assassin.jr.attabot.main;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.assassin.jr.attabot.config.AttaConfig;
import org.assassin.jr.attabot.service.scheduler.BidAskHandler;
import org.assassin.jr.attabot.service.scheduler.BidAskHandlerSimple;
import org.assassin.jr.attabot.service.scheduler.ExchangeScheduler;
import org.assassin.jr.attabot.service.scheduler.ExchangeSchedulerSimple;
import org.assassin.jr.attabot.utility.AttaConstant;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class MainTest {
	public static void main(String[] args) throws FileNotFoundException {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader(AttaConstant.PATH_CONFIG_BSAUTO));
		AttaConfig config = gson.fromJson(reader, AttaConfig.class);
		System.out.println(config);
        
		BidAskHandler bisdAskHandler = new BidAskHandlerSimple();
        
		ExchangeScheduler scheduleSimple = new ExchangeSchedulerSimple(bisdAskHandler);
		scheduleSimple.runSchedule(config);
	}
}

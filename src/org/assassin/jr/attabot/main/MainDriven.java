package org.assassin.jr.attabot.main;

import org.assassin.jr.attabot.config.AttaConfig;
import org.assassin.jr.attabot.config.AttaConfigFragmentByPercent;
import org.assassin.jr.attabot.config.AttaConfigHandlerChain;
import org.assassin.jr.attabot.config.AttaConfigReader;
import org.assassin.jr.attabot.service.scheduler.BidAskHandler;
import org.assassin.jr.attabot.service.scheduler.BidAskHandlerSimple;
import org.assassin.jr.attabot.service.scheduler.ExchangeScheduler;
import org.assassin.jr.attabot.service.scheduler.ExchangeSchedulerSimple;
import org.assassin.jr.attabot.utility.AttaConstant;
import org.assassin.jr.attabot.utility.AttaGobalSetting;

public class MainDriven {
	public static void main(String[] args) throws Exception {
		AttaConfig config = AttaConfigReader.readAttaConfigFrom(AttaConstant.PATH_CONFIG_BSAUTO);

		AttaConfigHandlerChain configHandlerChain = new AttaConfigHandlerChain();
		configHandlerChain.add(new AttaConfigFragmentByPercent());

		configHandlerChain.execute(config);
		AttaGobalSetting.getInstance().setSettingFromCommon(config.getCommon());
		System.out.println(config.toJson());

		BidAskHandler bisdAskHandler = new BidAskHandlerSimple();

		ExchangeScheduler scheduleSimple = new ExchangeSchedulerSimple(bisdAskHandler);
		scheduleSimple.runSchedule(config);
	}
}
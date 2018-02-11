package org.assassin.jr.attabot.service.scheduler;

import org.assassin.jr.attabot.config.AttaConfig;

public interface ExchangeScheduler {
	public void runSchedule(AttaConfig bsAutoConfig);
}

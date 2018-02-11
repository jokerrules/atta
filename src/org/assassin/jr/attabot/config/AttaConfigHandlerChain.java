package org.assassin.jr.attabot.config;

import java.util.ArrayList;
import java.util.List;

public class AttaConfigHandlerChain {
	private List<AttaConfigHandler> lstHandler;

	public AttaConfigHandlerChain() {
		this.lstHandler = new ArrayList<>();
	}

	public void add(AttaConfigHandler handler) {
		this.lstHandler.add(handler);
	}

	public AttaConfig execute(AttaConfig attaConfig) {
		for (AttaConfigHandler attaConfigHandler : lstHandler) {
			attaConfigHandler.handle(attaConfig);
		}
		return attaConfig;
	}
}

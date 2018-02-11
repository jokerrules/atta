package org.assassin.jr.attabot.service.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.assassin.jr.attabot.config.ApiKeyInfo;
import org.assassin.jr.attabot.config.AttaConfig;
import org.assassin.jr.attabot.config.CurrencySetting;
import org.assassin.jr.attabot.service.exchange.ExchangeServiceFactory;
import org.assassin.jr.attabot.service.exchange.ExchangeServiceHandler;
import org.assassin.jr.attabot.service.predictor.PredictorFactoryCache;
import org.assassin.jr.attabot.service.predictor.PredictorStandard;
import org.assassin.jr.attabot.utility.AttaUtility;

public class ExchangeSchedulerSimple implements ExchangeScheduler {
	private ScheduledExecutorService scheduledExecutor;

	private BidAskHandler bidAskHandler;

	public ExchangeSchedulerSimple(BidAskHandler bisdAskHandler) {
		this.bidAskHandler = bisdAskHandler;
	}

	@Override
	public void runSchedule(final AttaConfig attaConfig) {
		scheduledExecutor = Executors.newScheduledThreadPool(attaConfig.getCommon().getCorePoolSize());

		for (CurrencySetting currencySetting : attaConfig.getLstCurrencies()) {
			if (!currencySetting.isActive()) {
				continue;
			}

			long updateTime = currencySetting.getUpdateTime() == null ? attaConfig.getCommon().getUpdateTime() : currencySetting.getUpdateTime();

			scheduledExecutor.scheduleAtFixedRate(new Runnable() {

				@Override
				public void run() {
					new BidAskRunable(currencySetting, attaConfig, bidAskHandler).run();
				}
			}, 0, updateTime, TimeUnit.MILLISECONDS);
		}
	}

	private static class BidAskRunable implements Runnable {
		private CurrencySetting currencySetting;
		private AttaConfig attaConfig;
		private BidAskHandler bidAskHandler;

		public BidAskRunable(CurrencySetting currencySetting, AttaConfig attaConfig, BidAskHandler bidAskHandler) {
			this.currencySetting = currencySetting;
			this.attaConfig = attaConfig;
			this.bidAskHandler = bidAskHandler;
		}

		@Override
		public void run() {
			synchronized (currencySetting) {
				try {
					PredictorStandard predictor = PredictorFactoryCache.getPredictor(currencySetting.getPredictType());
					ApiKeyInfo apiInfo = attaConfig.getCommon().getMarketApiKey(currencySetting.getExchangeMarket());
					ExchangeServiceHandler exchangeServiceHandler = ExchangeServiceFactory.getService(currencySetting.getExchangeMarket(), apiInfo.getApiKey(), apiInfo.getApiKeySerect());
					bidAskHandler.handleOrder(currencySetting, exchangeServiceHandler, predictor);

				} catch (Throwable e) {
					AttaUtility.writeLogError(LogManager.getLogger(BidAskHandler.class), currencySetting, AttaUtility.getStackTraceMessage(e));
				}
			}
		}
	}
}
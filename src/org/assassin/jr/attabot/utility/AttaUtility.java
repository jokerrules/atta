package org.assassin.jr.attabot.utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Formatter;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assassin.jr.attabot.config.CurrencySetting;
import org.assassin.jr.attabot.logger.DynamicLogger;
import org.assassin.jr.attabot.logger.LoggerStandard;
import org.assassin.jr.attabot.pojo.exchange.ITick;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AttaUtility {
	private static Logger logger = LogManager.getLogger(AttaUtility.class);
	private static final String HMAC_SHA512 = "HmacSHA512";

	private static String toHexString(byte[] bytes) {
		Formatter formatter = new Formatter();
		for (byte b : bytes) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	public static Gson createGsonUpperCamel() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE);
		return gsonBuilder.create();
	}

	public static String calculateHMAC(String data, String key) {
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), HMAC_SHA512);
			Mac mac = Mac.getInstance(HMAC_SHA512);
			mac.init(secretKeySpec);
			return toHexString(mac.doFinal(data.getBytes()));
		} catch (Exception e) {
			logger.error(e);
		}
		return "";
	}

	public static String getStackTraceMessage(final Throwable throwable) {
		StringWriter sw = null;
		PrintWriter pw = null;

		try {
			sw = new StringWriter();
			pw = new PrintWriter(sw, true);
			throwable.printStackTrace(pw);
			return sw.getBuffer().toString();

		} catch (Exception e) {
			logger.error(e);

		} finally {
			if (sw != null) {
				try {
					sw.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
			if (pw != null) {
				pw.close();
			}
		}

		return "";
	}

	public static boolean isLastTickDown(List<ITick> lstTicks) {
		ITick lastTick = lstTicks.get(lstTicks.size() - 1);
		return lastTick.getOpenPrice() < lastTick.getClosePrice();
	}

	public static boolean isLastTickUp(List<ITick> lstTicks) {
		ITick lastTick = lstTicks.get(lstTicks.size() - 1);
		return lastTick.getClosePrice() < lastTick.getOpenPrice();
	}

	public static void writeLogInfo(Logger log4jLogger, CurrencySetting currency, String message) {
		writeLogInfo(log4jLogger, DynamicLogger.getInstance().getLogger(currency.getId()), currency.getId(), currency.getMarket(), message);
	}

	public static void writeLogDebug(Logger log4jLogger, CurrencySetting currency, String message) {
		writeLogDebug(log4jLogger, DynamicLogger.getInstance().getLogger(currency.getId()), currency.getId(), currency.getMarket(), message);
	}

	public static void writeLogError(Logger log4jLogger, CurrencySetting currency, String message) {
		writeLogError(log4jLogger, DynamicLogger.getInstance().getLogger(currency.getId()), currency.getId(), currency.getMarket(), message);
	}

	public static void writeLogInfo(Logger log4jLogger, LoggerStandard dynamicLogger, String currencyId, String market, String message) {
		String content = String.format("[%s|%s] %s", currencyId, market, message);
		logger.info(content);
		dynamicLogger.info(content);
	}

	public static void writeLogDebug(Logger log4jLogger, LoggerStandard dynamicLogger, String currencyId, String market, String message) {
		String content = String.format("[%s|%s] %s", currencyId, market, message);
		logger.debug(content);
		dynamicLogger.debug(content);
	}

	public static void writeLogError(Logger log4jLogger, LoggerStandard dynamicLogger, String currencyId, String market, String message) {
		String content = String.format("[%s|%s] %s", currencyId, market, message);
		logger.error(content);
		dynamicLogger.error(content);
	}

}
package org.assassin.jr.attabot.logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assassin.jr.attabot.utility.AttaUtility;

public class LoggerRollingDate implements LoggerStandard {
	private static Logger logger = LogManager.getLogger(LoggerRollingDate.class);

	private static final String FORMATTER_DATE = "yyyy-MM-dd";
	private static final String FORMATTER_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

	private static final String LOG_PATTERN = "[%s][%-5s] %s";
	private static final String PATTERN_LOG_FILE_PATH = "%s-%s.log";

	private boolean isOuputConsole = false;

	private enum LogLevel {
		DEBUG("debug", 0), INFO("info", 1), WARN("warn", 2), ERROR("error", 3);

		private String levelName;
		private int level;

		private LogLevel(String levelName, int level) {
			this.levelName = levelName;
			this.level = level;
		}

		public int getLevel() {
			return this.level;
		}

		public static LogLevel createLogByLevel(int level) {
			switch (level) {
			case 0:
				return DEBUG;
			case 1:
				return INFO;
			case 2:
				return ERROR;
			}
			return null;
		}

		public String toString() {
			return this.levelName;
		}
	}

	private File logFileDebug;
	private File logFileInfo;
	private File logFileError;
	private File logFileWarn;
	private String beginDateWriteLog;
	private SimpleDateFormat dateFormatter;
	private SimpleDateFormat dateTimeFormatter;

	public LoggerRollingDate(String filePath) {
		this.logFileDebug = new File(String.format(PATTERN_LOG_FILE_PATH, filePath, LogLevel.DEBUG));
		this.logFileInfo = new File(String.format(PATTERN_LOG_FILE_PATH, filePath, LogLevel.INFO));
		this.logFileError = new File(String.format(PATTERN_LOG_FILE_PATH, filePath, LogLevel.ERROR));
		this.logFileWarn = new File(String.format(PATTERN_LOG_FILE_PATH, filePath, LogLevel.WARN));
		dateFormatter = new SimpleDateFormat(FORMATTER_DATE);
		dateTimeFormatter = new SimpleDateFormat(FORMATTER_DATE_TIME);
		beginDateWriteLog = getCurrentDate();
	}

	@Override
	public void debug(String message) {
		createFileIfNotExists(logFileDebug);
		writeLogByLevelWay(LogLevel.DEBUG, message);
	}

	@Override
	public void info(String message) {
		createFileIfNotExists(logFileInfo);
		writeLogByLevelWay(LogLevel.INFO, message);
	}

	@Override
	public void warn(String message) {
		createFileIfNotExists(logFileWarn);
		writeLogByLevelWay(LogLevel.WARN, message);
	}

	@Override
	public void error(String message) {
		createFileIfNotExists(logFileError);
		writeLogByLevelWay(LogLevel.ERROR, message);
	}

	@Override
	public void error(Exception e) {
		createFileIfNotExists(logFileError);
		writeLogByLevelWay(LogLevel.ERROR, AttaUtility.getStackTraceMessage(e));
	}

	private File getLogFileByLevel(LogLevel logFile) {
		switch (logFile) {
		case DEBUG:
			return logFileDebug;
		case INFO:
			return logFileInfo;
		case WARN:
			return logFileWarn;
		case ERROR:
			return logFileError;
		}
		return null;
	}

	private synchronized void rollingLogByDate(File file) {
		String currentDate = getCurrentDate();
		if (!beginDateWriteLog.equals(currentDate)) {
			try {
				Files.move(file.toPath(), new File(file.getAbsoluteFile() + "." + beginDateWriteLog).toPath());
				file.createNewFile();
				beginDateWriteLog = currentDate;
			} catch (IOException e) {
				logger.error(e);
			}
		}
	}

	private String getCurrentDate() {
		return dateFormatter.format(new Date());
	}

	private String getCurrentDateTime() {
		return dateTimeFormatter.format(new Date());
	}

	private String getSimplePatternContent(LogLevel level, String message) {
		return String.format(LOG_PATTERN, getCurrentDateTime(), level.toString().toUpperCase(), message);
	}

	private void writeLog(File fileLog, LogLevel level, String message) {
		rollingLogByDate(fileLog);

		String content = getSimplePatternContent(level, message);
		PrintWriter pw = null;
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(fileLog, true);
			pw = new PrintWriter(fos);
			pw.println(content);
			pw.flush();

		} catch (Exception e) {
			logger.error(e);

		} finally {
			if (pw != null) {
				pw.close();
			}

			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
	}

	public void setOuputConsole(boolean isOuputConsole) {
		this.isOuputConsole = isOuputConsole;
	}

	private void writeLogByLevelWay(LogLevel level, String message) {
		if (isOuputConsole) {
			System.out.println(getSimplePatternContent(level, message));
		}

		for (int i = level.getLevel(); i >= 0; i--) {
			writeLog(getLogFileByLevel(LogLevel.createLogByLevel(i)), level, message);
		}
	}

	private void createFileIfNotExists(File file) {
		File parentFile = file.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				logger.error(e);
			}
		}
	}
}
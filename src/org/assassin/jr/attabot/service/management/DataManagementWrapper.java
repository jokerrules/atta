package org.assassin.jr.attabot.service.management;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assassin.jr.attabot.utility.AttaGobalSetting;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class DataManagementWrapper {
	private static Logger logger = LogManager.getLogger(DataManagementWrapper.class);

	private Gson gson;
	private SimpleDateFormat sdf;
	private Random random;

	private Queue<String> queueSavedFilePath;

	public DataManagementWrapper() {
		this.gson = new Gson();
		this.sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		this.queueSavedFilePath = new ArrayBlockingQueue<>(getMaxQueueSize());
		this.random = new Random(0);
	}

	protected Object readFile(String path, Type listType) {
		try {
			if (new File(path).exists()) {
				return gson.fromJson(new FileReader(path), listType);
			}
		} catch (JsonSyntaxException e) {
			logger.error(e);
		} catch (JsonIOException e) {
			logger.error(e);
		} catch (FileNotFoundException e) {
			logger.error(e);
		}
		return null;
	}

	protected void restoreToQueue(String filePath) {
		File pmFile = new File(filePath);
		File parentDir = pmFile.getParentFile();

		if (!parentDir.exists()) {
			return;
		}

		File[] lstFile = parentDir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				if (Pattern.compile(pmFile.getName() + "\\d+").matcher(name) != null) {
					return true;
				}
				return false;
			}
		});

		if (lstFile == null) {
			return;
		}

		Arrays.sort(lstFile);

		for (int i = 0; i < lstFile.length; i++) {
			addToSavedFilePathQueue(lstFile[i].getAbsolutePath());
		}
	}

	protected synchronized void saveFile(String filePath, Object data) {
		FileWriter fileWriter = null;
		try {
			File file = new File(filePath);
			File newFile = null;
			if (file.exists()) {
				String newFilePath = filePath + sdf.format(new Date()) + (random.nextInt(900) + 100);
				newFile = new File(newFilePath);
				Files.copy(file.toPath(), newFile.toPath());
				addToSavedFilePathQueue(newFilePath);
				file.delete();
			}

			File parentDir = file.getParentFile();
			if (!parentDir.exists()) {
				parentDir.mkdirs();
			}

			fileWriter = new FileWriter(filePath);
			gson.toJson(data, fileWriter);

			if (!isBakupFileWhenSave() && newFile != null) {
				newFile.delete();
			}

		} catch (JsonIOException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
	}

	private void addToSavedFilePathQueue(String filePath) {
		if (queueSavedFilePath.size() >= getMaxQueueSize()) {
			if (deleteFileWhenQueueFull()) {
				new File(queueSavedFilePath.poll()).delete();
			}
		}
		queueSavedFilePath.add(filePath);
	}

	protected int getMaxQueueSize() {
		return AttaGobalSetting.getInstance().getIntValue(AttaGobalSetting.MANAGEMENT_MAX_BACKUP_FILE_QTY);
	}

	protected boolean isBakupFileWhenSave() {
		return true;
	}

	protected boolean deleteFileWhenQueueFull() {
		return true;
	}
}
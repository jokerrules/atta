package org.assassin.jr.attabot.config;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class AttaConfigReader {
	public static AttaConfig readAttaConfigFrom(String path) throws FileNotFoundException {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader(path));
		return gson.fromJson(reader, AttaConfig.class);
	}
}

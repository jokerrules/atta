package org.assassin.jr.attabot.pojo.exchange.bittrex;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class BittrexDateTypeAdapter extends TypeAdapter<Date> {
	private static Logger logger = LogManager.getLogger(BittrexDateTypeAdapter.class);

	@Override
	public Date read(JsonReader reader) throws IOException {
		if (reader.peek() == JsonToken.NULL) {
			reader.nextNull();
			return null;
		}
		Date result = null;
		String str = reader.nextString();
		if (!"".equals(str)) {
			try {
				result = getDateFormater().parse(str);
			} catch (ParseException e) {
				logger.error(e);
			}
		}
		return result;
	}

	@Override
	public final void write(final JsonWriter out, final Date value) throws IOException {
		out.value(getDateFormater().format(value));
	}

	public SimpleDateFormat getDateFormater() {
		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	}
}
package org.assassin.jr.attabot.service.network;

import org.assassin.jr.attabot.exception.JSONParsingFailException;
import org.assassin.jr.attabot.utility.AttaConstant;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BittrexHttpResult implements StandardHttpResult {
	private boolean isSuccess;
	private String message;
	private String result;

	public BittrexHttpResult(String json) throws JSONParsingFailException {
		parseJSON(json);
	}

	private void parseJSON(String jsonString) throws JSONParsingFailException {
		try {
			JsonParser jsonParser = new JsonParser();
			JsonObject jo = (JsonObject) jsonParser.parse(jsonString);

			isSuccess = jo.get(AttaConstant.RESPONSE_JSON_ELM_SUCCESS).getAsBoolean();
			message = jo.get(AttaConstant.RESPONSE_JSON_ELM_MESSAGE).getAsString();
			result = jo.get(AttaConstant.RESPONSE_JSON_ELM_RESULT).toString();

		} catch (Exception e) {
			throw new JSONParsingFailException(String.format("Error parse json %s", jsonString), e);
		}
	}

	@Override
	public boolean isSucess() {
		return isSuccess;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String getResult() {
		return result;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "BittrexHttpResult [isSuccess=" + isSuccess + ", message=" + message + ", result=" + result + "]";
	}
}
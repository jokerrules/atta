package org.assassin.jr.attabot.service.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assassin.jr.attabot.utility.Pair;

public class HttpRequestHelper {
	private static Logger logger = LogManager.getLogger(HttpRequestHelper.class);

	public static final int TIME_OUT_CONNECTION = 10000;

	public static final int TIME_OUT_SOCKET = 0;

	private static final String UTF8 = "UTF-8";

	private static final int READ_BYTE_SIZE = 512;

	private static CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);

	static {
		CookieHandler.setDefault(cookieManager);
	}

	public static boolean isConnectedToServer(String url) {
		try {
			URL myUrl = new URL(url);
			URLConnection connection = myUrl.openConnection();
			connection.setConnectTimeout(TIME_OUT_CONNECTION);
			connection.connect();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static HttpResponseData getDataByPostMethod(String url, List<Pair<String, String>> nameValuePairs) {
		StringBuilder sbParameter = new StringBuilder();
		try {
			if (nameValuePairs != null) {
				for (Pair<String, String> item : nameValuePairs) {
					sbParameter.append(URLEncoder.encode(item.getFirst().toString(), UTF8));
					sbParameter.append("=");
					sbParameter.append(URLEncoder.encode(item.getSecond().toString(), UTF8));
					sbParameter.append("&");
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}

		return requestData(url, "POST", sbParameter.toString(), null);
	}

	public static HttpResponseData getDataByGetMethod(String url) {
		return requestData(url, "GET", null, null);
	}

	public static HttpResponseData getDataByGetMethod(String url, List<Pair<String, String>> headerValues) {
		return requestData(url, "GET", null, headerValues);
	}

	private static HttpResponseData requestData(String url, String method, String parameter, List<Pair<String, String>> headerValues) {
		String firstHeader = "";
		if (headerValues != null && headerValues.size() > 0) {
			firstHeader = String.format("%s:%s", headerValues.get(0).getFirst(), headerValues.get(0).getSecond());
		}
		logger.debug(String.format("HTTP %s=%s, PARAMS=%s, F_HEADER=%s", method, url, parameter, firstHeader));

		HttpURLConnection connection = null;
		InputStream connectionInputStream = null;
		ByteArrayOutputStream byteOutputStream = null;

		try {
			connection = (HttpURLConnection) new URL(url).openConnection();

			// add request header
			connection.setRequestMethod(method);
			connection.setConnectTimeout(TIME_OUT_CONNECTION);
			if (headerValues != null) {
				for (Pair<String, String> pair : headerValues) {
					connection.setRequestProperty(pair.getFirst(), pair.getSecond());
				}
			}

			if (parameter != null && parameter.trim().length() != 0) {
				// Send post request
				connection.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
				wr.writeBytes(parameter);
				wr.flush();
				wr.close();
			}

			int responseCode = connection.getResponseCode();
			byteOutputStream = new ByteArrayOutputStream();
			connectionInputStream = connection.getInputStream();
			int read = 0;
			byte[] buffer = new byte[READ_BYTE_SIZE];
			while (true) {
				read = connectionInputStream.read(buffer);
				if (read == -1) {
					break;
				}
				byteOutputStream.write(buffer, 0, read);
			}
			byteOutputStream.flush();
			return new HttpResponseData(responseCode, new String(byteOutputStream.toByteArray(), UTF8));

		} catch (Exception e) {
			logger.error(e);

		} finally {
			if (connection != null) {
				connection.disconnect();
			}

			if (connectionInputStream != null) {
				try {
					connectionInputStream.close();
				} catch (Exception e) {
				}
			}

			if (byteOutputStream != null) {
				try {
					byteOutputStream.close();
				} catch (Exception e) {
				}
			}
		}

		return new HttpResponseData(-1, "");
	}

	public static class HttpResponseData {
		private Integer httpStatus;
		private String data;

		public HttpResponseData(Integer httpStatus, String data) {
			this.httpStatus = httpStatus;
			this.data = data;
		}

		public Integer getHttpStatus() {
			return httpStatus;
		}

		public String getDataString() {
			return data;
		}

		public boolean isSuccess() {
			return false;
		}

		@Override
		public String toString() {
			return "ResponseData [httpStatus=" + httpStatus + ", data=" + data + "]";
		}
	}
}
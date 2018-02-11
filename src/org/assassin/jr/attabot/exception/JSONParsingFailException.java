package org.assassin.jr.attabot.exception;

public class JSONParsingFailException extends Exception {
	private static final long serialVersionUID = 1L;

	public JSONParsingFailException() {
	}

	public JSONParsingFailException(String message) {
		super(message);
	}

	public JSONParsingFailException(Throwable cause) {
		super(cause);
	}

	public JSONParsingFailException(String message, Throwable cause) {
		super(message, cause);
	}

	public JSONParsingFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}

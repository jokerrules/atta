package org.assassin.jr.attabot.exception;

public class ExchangeServiceRequestFailException extends Exception {
	private static final long serialVersionUID = 1L;

	public ExchangeServiceRequestFailException() {
		super();
	}

	public ExchangeServiceRequestFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExchangeServiceRequestFailException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExchangeServiceRequestFailException(String message) {
		super(message);
	}

	public ExchangeServiceRequestFailException(Throwable cause) {
		super(cause);
	}
}

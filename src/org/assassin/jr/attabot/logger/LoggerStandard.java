package org.assassin.jr.attabot.logger;

public interface LoggerStandard {

	public void info(String massage);

	public void debug(String message);

	public void error(String message);

	public void error(Exception e);

	public void setOuputConsole(boolean isShow);
}

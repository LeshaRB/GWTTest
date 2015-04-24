package by.gwttest.client.utils;

import com.google.gwt.core.client.GWT;

public class OperationTime {

	public static final TimeTemplate timeTemplate = GWT
			.create(TimeTemplate.class);

	private long timestart;
	private long timestop;

	OperationTime() {
	}

	private static class OperationTimeHolder {
		private final static OperationTime instance = new OperationTime();
	}

	public static OperationTime getInstance() {
		return OperationTimeHolder.instance;
	}

	public void startTimer() {
		timestart = System.currentTimeMillis();
	}

	public void stopTimer() {
		timestop = System.currentTimeMillis();
	}

	public String getTime() {
		return convertMStoTime(timestop - timestart);
	}

	private String convertMStoTime(long millis) {
		long mil = millis % 1000;
		long sec = millis / 1000;
		long hrs = (sec / 3600);
		long min = (sec / 60) % 60;
		return timeTemplate.timeTemplate(hrs, min, sec, mil).asString();
	}
}

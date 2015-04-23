package by.gwttest.client.utils;

import java.util.Date;

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

	public String getTime(Date date) {
		return getTime(date.getTime());
	}

	public String getTime(long timefinish) {
		return convertMStoTime(timefinish - timestart);
	}

	private String convertMStoTime(long millis) {
		// DONT'T REALSE
		// return String.format(
		// "%02d:%02d:%02d",
		// TimeUnit.MILLISECONDS.toHours(millis),
		// TimeUnit.MILLISECONDS.toMinutes(millis)
		// - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
		// .toHours(millis)),
		// TimeUnit.MILLISECONDS.toSeconds(millis)
		// - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
		// .toMinutes(millis)));

		long mil = millis % 1000;
		long sec = millis / 1000;
		long hrs = (sec / 3600);
		long min = (sec / 60) % 60;
		return timeTemplate.timeTemplate(hrs, min, sec, mil).asString();
	}
}

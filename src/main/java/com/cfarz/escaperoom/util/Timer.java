package com.cfarz.escaperoom.util;

public class Timer {

	private final long timeLimit;
	private long startTime;

	/**
	 * 
	 * Creates a timer and sets the time limit.
	 *
	 * @param timeLimit the maximum time in seconds.
	 */
	public Timer(long timeLimit) {
		this.timeLimit = timeLimit;
	}

	/**
	 * 
	 * Starts the timer by recording the current system time.
	 */
	public void start() {
		startTime = System.currentTimeMillis();
	}

	/**
	 * Calculates the amount of time that has passed since the timer started.
	 *
	 * @return elapsed time in seconds
	 */
	public long getElapsedTime() {
		return (System.currentTimeMillis() - startTime) / 1000;
	}

	/**
	 * 
	 * Calculates the amount of time left before the timer ends.
	 *
	 * @return time left in seconds.
	 */
	public long getRemainingTime() {
		long remaining = timeLimit - getElapsedTime();
		return Math.max(remaining, 0);
	}

	/**
	 * 
	 * Outputs the time in minutes and seconds.
	 *
	 * @return Returns the time left in minutes:seconds.
	 */
	public String getFormattedTime() {

		long remaining = getRemainingTime();

		long minutes = remaining / 60;
		long seconds = remaining % 60;

		return String.format("%02d:%02d", minutes, seconds);
	}

	/**
	 * 
	 * We check if the time is up.
	 *
	 * @return Reutrns true if there's no time left, otherwise false
	 */
	public boolean isTimeUp() {
		return getRemainingTime() <= 0;
	}

}

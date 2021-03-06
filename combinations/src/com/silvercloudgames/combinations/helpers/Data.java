/*
 * This class will store all the data being passed by each of the answered questions made by the user.
 * We will use static variables because there will only be one instance of this class at any given time.
 */

package com.silvercloudgames.combinations.helpers;

public class Data {
	private static int totalSize, choiceSize;
	private static boolean orderMatters, repetitionAllowed, isKnown;
	
	// Default constructor
	public Data() {
		totalSize = 1;
		choiceSize = 1;
		orderMatters = true;
		repetitionAllowed = true;
		isKnown = true; // will determine if order activity is skipped, will be skipped if user knows whether order matters or not
	}
	
	public static int getTotalSize() {
		return totalSize;
	}
	
	public static void setTotalSize(int totalSize) {
		Data.totalSize = totalSize;
	}
	
	public static int getChoiceSize() {
		return choiceSize;
	}
	
	public static void setChoiceSize(int choiceSize) {
		Data.choiceSize = choiceSize;
	}
	
	public static boolean isOrderMatters() {
		return orderMatters;
	}
	
	public static void setOrderMatters(boolean orderMatters) {
		Data.orderMatters = orderMatters;
	}
	
	public static boolean isRepetitionAllowed() {
		return repetitionAllowed;
	}
	
	public static void setRepetitionAllowed(boolean repetitionAllowed) {
		Data.repetitionAllowed = repetitionAllowed;
	}
	
	public static boolean isKnown() {
		return isKnown;
	}

	public static void setKnown(boolean isKnown) {
		Data.isKnown = isKnown;
	}

	public static String getAllDataToString() {
		String toReturn = "";
				
		toReturn += "Total Size: " + totalSize + "\n" +
				"Choice Size: " + choiceSize + "\n" +
				"Order Matters: " + orderMatters + "\n" +
				"Repetition Allowed: " + repetitionAllowed + "\n";
		
		return toReturn;
		
	}
	
}

/*
 * This class gets the result we need for our combinations and permutations
 */

package com.silvercloudgames.combinations.helpers;

import java.math.BigInteger;

public class Calculator {
	
	public String getResult(int totalSize, int choiceSize, boolean orderAllowed, boolean repetitionAllowed) {
		
		// Will use one of the 4 helper methods based on all the combinations of both booleans (4 combinations total)
		if (orderAllowed) {
			if (repetitionAllowed) 
				return getOrderAndRepetition(totalSize, choiceSize);
			else 
				return getOrderAndNoRepetition(totalSize, choiceSize);
		} else {
			if (repetitionAllowed) 
				return getNoOrderAndRepetition(totalSize, choiceSize);
			else 
				return getNoOrderAndNoRepetition(totalSize, choiceSize);
		}
	}
	
	/* 
	 * Helper files for getResult()
	 */
	
	public String getOrderAndRepetition(int totalSize, int choiceSize) {	
		
		// The formula is n^r 
		
		// We assign toReturn to the totalSize (n), then raise it to the choiceSize (r) power
		BigInteger toReturn = new BigInteger(Integer.toString(totalSize));
		
		toReturn = toReturn.pow(choiceSize);
				
		return toReturn.toString();
	}
	
	public String getOrderAndNoRepetition(int totalSize, int choiceSize) {

		// The formula is n! / (n - r)!
		
		// First half of the formula, where totalSize is n
		BigInteger toReturn = new BigInteger(getFactorial(totalSize));
		
		// Second half of the formula, where choiceSize is r
		BigInteger toDivide = new BigInteger(getFactorial(totalSize - choiceSize));
				
		// We divide the first half by the second half
		toReturn = toReturn.divide(toDivide); 

		return toReturn.toString();
	}
	
	public String getNoOrderAndRepetition(int totalSize, int choiceSize) {
		
		// The formula is (n + r - 1)! / r!(n - 1)!
		
		// First half of the formula, where totalSize is n and choiceSize is r
		BigInteger toReturn = new BigInteger(getFactorial(totalSize + choiceSize - 1));
		
		// The second half of the formula, r!
		BigInteger toDivide = new BigInteger(getFactorial(choiceSize).toString());
		
		// Other second half of the formula, (n - 1)!
		BigInteger toDividePart2 = new BigInteger(getFactorial(totalSize - 1));
		
		// We multiply them both now to get the complete second half
		toDivide = toDivide.multiply(toDividePart2);
		
		// We divide the first half by the second half
		toReturn = toReturn.divide(toDivide);
		
		return toReturn.toString();
	}
	
	public String getNoOrderAndNoRepetition(int totalSize, int choiceSize) {
		
		// The formula is n! / r!(n - r)!
		
		// First half of the formula, where totalSize is n
		BigInteger toReturn = new BigInteger(getFactorial(totalSize));
		
		// The second half of the formula, r!
		BigInteger toDivide = new BigInteger(getFactorial(choiceSize));
		
		// Other second half of the formula, (n - r)!
		BigInteger toDividePart2 = new BigInteger(getFactorial(totalSize - choiceSize));
		
		// We multiply them both now to get the complete second half
		toDivide = toDivide.multiply(toDividePart2);
		
		// We divide the first half by the second half
		toReturn = toReturn.divide(toDivide);
		
		return toReturn.toString();
	}

 	public String getFactorial(int number) {

 		BigInteger toReturn = BigInteger.ONE;
 		
 		// We are looping from (number - 1) to 1, multiplying toReturn by the current
 		// number. If we get passed a 1, then i will be 0 and the loop will never
 		// go through, we will return 1.
 		for (int i = number; i > 0; i--) {
 			toReturn = toReturn.multiply(BigInteger.valueOf(i));
 		}
 		
 		// We are returning a String because we are passing it to a BigInteger object
 		return toReturn.toString();
 		
	}
 	
}

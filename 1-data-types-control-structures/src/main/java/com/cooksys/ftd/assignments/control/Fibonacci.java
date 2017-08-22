package com.cooksys.ftd.assignments.control;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * The Fibonacci sequence is simply and recursively defined: the first two elements are `1`, and
 * every other element is equal to the sum of its two preceding elements. For example:
 * <p>
 * [1, 1] =>
 * [1, 1, 1 + 1]  => [1, 1, 2] =>
 * [1, 1, 2, 1 + 2] => [1, 1, 2, 3] =>
 * [1, 1, 2, 3, 2 + 3] => [1, 1, 2, 3, 5] =>
 * ...etc
 */
public class Fibonacci {

    /**
     * Calculates the value in the Fibonacci sequence at a given index. For example,
     * `atIndex(0)` and `atIndex(1)` should return `1`, because the first two elements of the
     * sequence are both `1`.
     *
     * @param i the index of the element to calculate
     * @return the calculated element
     * @throws IllegalArgumentException if the given index is less than zero
     */
    public static int atIndex(int i) throws IllegalArgumentException {
    	//throw an exception if argument is negative
    	if(i < 0)
        throw new IllegalArgumentException();
    	//create an array with the size one greater than the index passed
    	int[] arr = new int[i + 1];
    	//iterate through the array and load values
    	for(int index = 0; index < arr.length;index++)
    	{
    		//if index is 0 or 1 assign 1 to the element
    		if(index == 0 || index == 1)
    		{
    			arr[index] = 1;
    		}
    		//if index is anything other than 0 or 1 assign the sum of two previous indices
    		else
    		{
    			arr[index] = arr[index - 1] + arr[index - 2];
    		}
    		
    	}
    	return arr[i];
    	
    }

    /**
     * Calculates a slice of the fibonacci sequence, starting from a given start index (inclusive) and
     * ending at a given end index (exclusive).
     *
     * @param start the starting index of the slice (inclusive)
     * @param end   the ending index of the slice(exclusive)
     * @return the calculated slice as an array of int elements
     * @throws IllegalArgumentException if either the given start or end is negative, or if the
     *                                  given end is less than the given start
     */
    public static int[] slice(int start, int end) throws IllegalArgumentException {
    	//if start or end value is negative or start is greater than the end throw an exception
    	if(start < 0 || end < 0 || end < start)
        throw new IllegalArgumentException();
    	
    	//create and array to hold the values for the given slice of the pattern
    	int[] arr = new int[end - start];
    	//load an array
    	for(int i = 0; i < arr.length; i++)
    	{
    		arr[i] = atIndex(start++);
    	}
    	return arr;
    }

    /**
     * Calculates the beginning of the fibonacci sequence, up to a given count.
     *
     * @param count the number of elements to calculate
     * @return the beginning of the fibonacci sequence, up to the given count, as an array of int elements
     * @throws IllegalArgumentException if the given count is negative
     */
    public static int[] fibonacci(int count) throws IllegalArgumentException {
    	//if count is negative throw an exception
    	if(count < 0)
        throw new IllegalArgumentException();
    	
    	//create the array to hold values for the given slice of the pattern
    	int[] arr = new int[count];
    	//load an array
    	for(int i = 0; i < arr.length; i++)
    	{
    		arr[i] = atIndex(i);
    	}
    	return arr;
    }
}

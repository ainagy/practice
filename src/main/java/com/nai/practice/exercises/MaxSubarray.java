package com.nai.practice.exercises;

/**
 * Created by Andras on 11/18/2016.
 */
public class MaxSubarray {
    public static int maxSum(int[] array) {
        int startPosition = 0;
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;
        for (int i = 0; i < array.length; i++) {
            currentSum += array[i];
            if (currentSum < 0) {
                startPosition = i + 1;
                currentSum = 0;
            }
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        System.out.println("startPosition: " + startPosition + ", sum: " + maxSum);
        return maxSum;
    }

    static int sum(int[] array, int startPosition, int length) {
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum = sum + array[startPosition + i];
        }

        return sum;
    }
}

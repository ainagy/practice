package com.nai.practice.exercises;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(MockitoJUnitRunner.class)
public class MaxSubarrayTest {

    @Test
    public void shouldFindFullArrayForTrivialCase() {
        // given
        int[] array = new int[]{1, 2, 3};

        // when
        int result = MaxSubarray.maxSum(array);

        // then
        Assert.assertThat("should find whole array", result, equalTo(6));
    }

    @Test
    public void shouldFindMaxSubArrayWhenNegativeValuesAreInTheList() {
        // given
        int[] array = new int[]{1, 2, 3, -100, 10};

        // when
        int result = MaxSubarray.maxSum(array);

        // then
        Assert.assertThat("should find subarray", result, equalTo(10));
    }

    @Test
    public void shouldFindMaxSubArrayWhenNegativeValueIsAfterSubarray() {
        // given
        int[] array = new int[]{1, 2, 3, -100, 2};

        // when
        int result = MaxSubarray.maxSum(array);

        // then
        Assert.assertThat("should find subarray", result, equalTo(6));
    }

    @Test
    public void shouldFindMaxSubArrayWhenNegativeValuesAreInTheSubarray() {
        // given
        int[] array = new int[]{10, -2, 10};

        // when
        int result = MaxSubarray.maxSum(array);

        // then
        Assert.assertThat("should find subarray", result, equalTo(18));
    }

    @Test
    public void shouldFindMaxSubArrayOfLength1() {
        // given
        int[] array = new int[]{1, -1, 1, -1, 1};

        // when
        int result = MaxSubarray.maxSum(array);

        // then
        Assert.assertThat("should find subarray", result, equalTo(1));
    }

    @Test
    public void shouldFindMaxSubArrayWhenArrayStartsWithNegative() {
        // given
        int[] array = new int[]{-1, -2, 2};

        // when
        int result = MaxSubarray.maxSum(array);

        // then
        Assert.assertThat("should find subarray", result, equalTo(2));
    }

    @Test
    public void shouldFindZeroLengthSubarrayWithZeroSumForNegativeArrays() {
        // given
        int[] array = new int[]{-1, -2, -3};

        // when
        int result = MaxSubarray.maxSum(array);

        // then
        Assert.assertThat("should find zero length subarray", result, equalTo(0));
    }

    @Test
    public void sumShouldCorrectlySum1Value() {
        // given
        int[] array = new int[]{3};

        // when
        int result = MaxSubarray.sum(array, 0, 1);

        // then
        Assert.assertThat("should sum 1 value", result, equalTo(3));
    }

    @Test
    public void sumShouldCorrectlySumMoreValues() {
        // given
        int[] array = new int[]{1,2,3};

        // when
        int result = MaxSubarray.sum(array, 0, 3);

        // then
        Assert.assertThat("should sum 1 value", result, equalTo(6));
    }
}

package com.nai.practice.exercises;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(MockitoJUnitRunner.class)
public class InversionCountTest extends TestCase {

    @Test
    public void shouldFindNoInversionsInSortedArray() {
        // given
        int[] values = new int[]{1, 2, 3};

        //when
        int inversions = InversionCount.countInversions(values);

        //then
        Assert.assertThat(inversions, equalTo(0));
    }

    @Test
    public void shouldFindNoInversionsInEmptyArray() {
        // given
        int[] values = new int[0];

        //when
        int inversions = InversionCount.countInversions(values);

        //then
        Assert.assertThat(inversions, equalTo(0));
    }

    @Test
    public void shouldFindSingleInversion() {
        // given
        int[] values = new int[]{1, 3, 2, 4};

        //when
        int inversions = InversionCount.countInversions(values);

        //then
        Assert.assertThat(inversions, equalTo(1));
    }

    @Test
    public void shouldFindAllInversionsForEqualGreaterItems() {
        // given
        int[] values = new int[]{1, 3, 3, 2, 4};

        //when
        int inversions = InversionCount.countInversions(values);

        //then
        Assert.assertThat(inversions, equalTo(2));
    }

    @Test
    public void shouldFindNoInversionsForEqualItems() {
        // given
        int[] values = new int[]{2, 2, 2};

        //when
        int inversions = InversionCount.countInversions(values);

        //then
        Assert.assertThat(inversions, equalTo(0));
    }

    @Test
    public void shouldFindMultipleInversions() {
        // given
        int[] values = new int[]{2, 4, 1, 3, 5};

        //when
        int inversions = InversionCount.countInversions(values);

        //then
        Assert.assertThat(inversions, equalTo(3));
    }

    @Test
    public void shouldFindAllInversionsInReverseSortedArray() {
        // given
        int[] values = new int[]{4, 3, 2, 1};

        //when
        int inversions = InversionCount.countInversions(values);

        //then
        Assert.assertThat(inversions, equalTo(6));
    }
}
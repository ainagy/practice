package com.nai.practice.exercises;

import com.google.common.collect.BoundType;
import com.google.common.collect.TreeMultiset;

/**
 * Created by Andras on 11/19/2016.
 */
public class InversionCount {
    public static int countInversions2(int[] values) {
        TreeMultiset<Integer> ordered = TreeMultiset.create();
        int max = Integer.MIN_VALUE;
        int inversions = 0;
        for (int current : values) {
            ordered.add(current);
            if (current < max) {
                inversions += countInversions(ordered, current);
            }else {
                max = current;
            }
        }
        return inversions;
    }
    public static int countInversions(int[] values) {
        OrderedSizeTrackingTree<Integer> ordered = new OrderedSizeTrackingTree<>();
        int max = Integer.MIN_VALUE;
        int inversions = 0;
        for (int current : values) {
            ordered.addValue(current);
            if (current < max) {
                inversions += countInversions(ordered, current);
            }else {
                max = current;
            }
        }
        return inversions;
    }

    private static int countInversions(TreeMultiset<Integer> ordered, int current) {
        return ordered
                .tailMultiset(current, BoundType.OPEN)
                .size();
    }

    private static int countInversions(OrderedSizeTrackingTree<Integer> ordered, int current) {
        return ordered
                .countItemsLargerThan(current);
    }

}

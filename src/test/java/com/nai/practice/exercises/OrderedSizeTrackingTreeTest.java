package com.nai.practice.exercises;

import junit.framework.TestCase;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

/**
 * Created by Andras on 11/19/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderedSizeTrackingTreeTest extends TestCase {

    @Test
    public void shouldMaintainDifferentItems() {
        //given
        OrderedSizeTrackingTree<Integer> tree = new OrderedSizeTrackingTree<>();

        //when
        tree.addValue(2);
        tree.addValue(1);
        tree.addValue(3);

        //then
        Assert.assertThat(tree.getRoot().get().getValue(), CoreMatchers.equalTo(2));
        Assert.assertThat(tree.getRoot().get().getLeftSubTree().get().getValue(), CoreMatchers.equalTo(1));
        Assert.assertThat(tree.getRoot().get().getRightSubTree().get().getValue(), CoreMatchers.equalTo(3));
    }

    @Test
    public void shouldMaintainSizeForTreeWithDifferentItems() {
        //given
        OrderedSizeTrackingTree<Integer> tree = new OrderedSizeTrackingTree<>();

        //when
        tree.addValue(2);
        tree.addValue(1);
        tree.addValue(3);

        //then
        Assert.assertThat(tree.getRoot().get().getSize(), CoreMatchers.equalTo(3));
        Assert.assertThat(tree.getRoot().get().getLeftSubTree().get().getSize(), CoreMatchers.equalTo(1));
        Assert.assertThat(tree.getRoot().get().getRightSubTree().get().getSize(), CoreMatchers.equalTo(1));
    }

    @Test
    public void shouldMaintainSizeForUnbalancedTree() {
        //given
        OrderedSizeTrackingTree<Integer> tree = new OrderedSizeTrackingTree<>();

        //when
        tree.addValue(1);
        tree.addValue(2);
        tree.addValue(3);

        //then
        Assert.assertThat(tree.getRoot().get().getSize(), CoreMatchers.equalTo(3));
        Assert.assertThat(tree.getRoot().get().getRightSubTree().get().getSize(), CoreMatchers.equalTo(2));
        Assert.assertThat(tree.getRoot().get().getRightSubTree().get().getRightSubTree().get().getSize(), CoreMatchers.equalTo(1));
    }

    @Test
    public void shouldMaintainSameItems() {
        //given
        OrderedSizeTrackingTree<Integer> tree = new OrderedSizeTrackingTree<>();

        //when
        tree.addValue(2);
        tree.addValue(2);
        tree.addValue(2);

        //then
        Assert.assertThat(tree.getRoot().get().getValue(), CoreMatchers.equalTo(2));
        Assert.assertThat(tree.getRoot().get().getLeftSubTree().get().getValue(), CoreMatchers.equalTo(2));
        Assert.assertThat(tree.getRoot().get().getRightSubTree(), CoreMatchers.equalTo(Optional.empty()));
        Assert.assertThat(tree.getRoot().get().getLeftSubTree().get().getLeftSubTree().get().getValue(), CoreMatchers.equalTo(2));
        Assert.assertThat(tree.getRoot().get().getLeftSubTree().get().getRightSubTree(), CoreMatchers.equalTo(Optional.empty()));
    }
}
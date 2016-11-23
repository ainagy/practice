package com.nai.practice.exercises;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by Andras_Istvan_Nagy on 11/21/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderedSizeTrackingTreeIteratorTest {
    @Test
    public void shouldWalkInOrder() {
        //given
        OrderedSizeTrackingTree<Integer> tree = new OrderedSizeTrackingTree<>();
        tree.addValue(10);
        tree.addValue(5);
        tree.addValue(20);
        tree.addValue(2);

        //when
        Iterator<Integer> iterator = tree.iterator(OrderedSizeTrackingTree.IterationOrder.INORDER);

        //then
        Assert.assertThat(iterator.next(), equalTo(2));
        Assert.assertThat(iterator.next(), equalTo(5));
        Assert.assertThat(iterator.next(), equalTo(10));
        Assert.assertThat(iterator.next(), equalTo(20));
        Assert.assertThat(iterator.hasNext(), equalTo(false));
    }

    @Test
    public void shouldWalkFourLevelsInOrder() {
        //given
        OrderedSizeTrackingTree<Integer> tree = new OrderedSizeTrackingTree<>();
        tree.addValue(100);
        tree.addValue(50);
        tree.addValue(200);
        tree.addValue(25);
        tree.addValue(75);
        tree.addValue(125);
        tree.addValue(275);

        tree.addValue(12);
        tree.addValue(30);
        tree.addValue(60);
        tree.addValue(90);
        tree.addValue(110);
        tree.addValue(150);
        tree.addValue(250);
        tree.addValue(300);

        //when
        Iterator<Integer> iterator = tree.iterator(OrderedSizeTrackingTree.IterationOrder.INORDER);

        //then
        //Note - walk order depends on insertion order
        Assert.assertThat(iterator.next(), equalTo(12));
        Assert.assertThat(iterator.next(), equalTo(25));
        Assert.assertThat(iterator.next(), equalTo(30));
        Assert.assertThat(iterator.next(), equalTo(50));
        Assert.assertThat(iterator.next(), equalTo(60));
        Assert.assertThat(iterator.next(), equalTo(75));
        Assert.assertThat(iterator.next(), equalTo(90));
        Assert.assertThat(iterator.next(), equalTo(100));
        Assert.assertThat(iterator.next(), equalTo(110));
        Assert.assertThat(iterator.next(), equalTo(125));
        Assert.assertThat(iterator.next(), equalTo(150));
        Assert.assertThat(iterator.next(), equalTo(200));
        Assert.assertThat(iterator.next(), equalTo(250));
        Assert.assertThat(iterator.next(), equalTo(275));
        Assert.assertThat(iterator.next(), equalTo(300));

        Assert.assertThat(iterator.hasNext(), equalTo(false));
    }

    @Test
    public void shouldWalkDepthFirst() {
        //given
        OrderedSizeTrackingTree<Integer> tree = new OrderedSizeTrackingTree<>();
        tree.addValue(10);
        tree.addValue(5);
        tree.addValue(20);
        tree.addValue(40);
        tree.addValue(15);
        tree.addValue(2);

        //when
        Iterator<Integer> iterator = tree.iterator(OrderedSizeTrackingTree.IterationOrder.DFS);

        //then
        //Note - walk order depends on insertion order
        Assert.assertThat(iterator.next(), equalTo(10));
        Assert.assertThat(iterator.next(), equalTo(5));
        Assert.assertThat(iterator.next(), equalTo(2));
        Assert.assertThat(iterator.next(), equalTo(20));
        Assert.assertThat(iterator.next(), equalTo(15));
        Assert.assertThat(iterator.next(), equalTo(40));
        Assert.assertThat(iterator.hasNext(), equalTo(false));
    }

    @Test
    public void shouldWalkBreadthFirst() {
        //given
        OrderedSizeTrackingTree<Integer> tree = new OrderedSizeTrackingTree<>();
        tree.addValue(100);
        tree.addValue(50);
        tree.addValue(200);
        tree.addValue(25);
        tree.addValue(75);
        tree.addValue(125);
        tree.addValue(275);

        tree.addValue(12);
        tree.addValue(30);
        tree.addValue(60);
        tree.addValue(90);
        tree.addValue(110);
        tree.addValue(150);
        tree.addValue(250);
        tree.addValue(300);


        //when
        Iterator<Integer> iterator = tree.iterator(OrderedSizeTrackingTree.IterationOrder.BFS);

        //then
        //Note - walk order depends on insertion order
        Assert.assertThat(iterator.next(), equalTo(100));
        Assert.assertThat(iterator.next(), equalTo(50));
        Assert.assertThat(iterator.next(), equalTo(200));
        Assert.assertThat(iterator.next(), equalTo(25));
        Assert.assertThat(iterator.next(), equalTo(75));
        Assert.assertThat(iterator.next(), equalTo(125));
        Assert.assertThat(iterator.next(), equalTo(275));

        Assert.assertThat(iterator.next(), equalTo(12));
        Assert.assertThat(iterator.next(), equalTo(30));
        Assert.assertThat(iterator.next(), equalTo(60));
        Assert.assertThat(iterator.next(), equalTo(90));
        Assert.assertThat(iterator.next(), equalTo(110));
        Assert.assertThat(iterator.next(), equalTo(150));
        Assert.assertThat(iterator.next(), equalTo(250));
        Assert.assertThat(iterator.next(), equalTo(300));
        Assert.assertThat(iterator.hasNext(), equalTo(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExceptionForEmptyTree() {
        //given
        OrderedSizeTrackingTree<Integer> tree = new OrderedSizeTrackingTree<>();

        //when
        Iterator<Integer> iterator = tree.iterator(OrderedSizeTrackingTree.IterationOrder.INORDER);

        //then
        iterator.next();
    }

}
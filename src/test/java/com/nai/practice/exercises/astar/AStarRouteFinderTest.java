package com.nai.practice.exercises.astar;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AStarRouteFinderTest {

    @Test
    public void shouldFindCheapestRoute() {
        //given
        Node start = new Node("start", 10);
        Node mid1 = new Node("mid1", 8);
        Link startToMid1 = new Link(start, mid1, 5);
        start.addLink(startToMid1);

        Node mid2 = new Node("mid2", 5);
        Link startToMid2 = new Link(start, mid2, 9);
        start.addLink(startToMid2);

        Node target = new Node("target", 0);
        Link mid1ToTarget = new Link(mid1, target, 9);
        Link mid2ToTarget = new Link(mid2, target, 7);
        mid1.addLink(mid1ToTarget);
        mid2.addLink(mid2ToTarget);

        //when
        Optional<Route> bestRoute = AStarRouteFinder.findCheapestRoute(start, target);

        //then
        assertThat(bestRoute.isPresent(), is(true));
        assertThat(bestRoute.get().getLinksTravelled(), hasItems(startToMid1, mid1ToTarget));
        System.out.println(bestRoute);
    }


}
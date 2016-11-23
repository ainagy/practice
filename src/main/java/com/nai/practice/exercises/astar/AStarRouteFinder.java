package com.nai.practice.exercises.astar;

import java.util.*;

public class AStarRouteFinder {

    public static Optional<Route> findCheapestRoute(Node start, Node target) {
        Comparator<Route> routeCostComparator = (route1, route2) ->
                route1.getCostEstimate() == route2.getCostEstimate() ? 0
                        : (route1.getCostEstimate() > route2.getCostEstimate() ? 1
                        : -1);
        Queue<Route> openRoutes = new PriorityQueue<>(routeCostComparator);

        openRoutes.add(new Route(Arrays.asList(new Link(start, start, 0))));

        Queue<Route> routesToTarget = new PriorityQueue<>(routeCostComparator);
        while (openRoutes.size() > 0) {
            Route route = openRoutes.remove();
            Node startNode = route.getEndNode();
            for (Link link : startNode.getLinks()) {
                final Route routeViaLink = new Route(route, link);
                openRoutes.add(routeViaLink);
                if (target.equals(link.getTo())) {
                    routesToTarget.add(routeViaLink);
                }
            }
        }
        if (routesToTarget.size() > 0) {
            return Optional.of(routesToTarget.remove());
        }else{
            return Optional.empty();
        }
    }
}

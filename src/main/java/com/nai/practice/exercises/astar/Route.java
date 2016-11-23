package com.nai.practice.exercises.astar;

import java.util.LinkedList;
import java.util.List;

public class Route {
    final List<Link> linksTravelled;

    public Route(List<Link> linksTravelled) {
        this.linksTravelled = linksTravelled;
    }

    public Route(Route routeHere, Link nextLink) {
        linksTravelled = new LinkedList<>();
        linksTravelled.addAll(routeHere.linksTravelled);
        linksTravelled.add(nextLink);
    }

    public int getCostEstimate() {
        int costSoFar = getCostSoFar();
        int estimateToTarget = getEndNode().getHeuristicDistanceToTarget();
        return costSoFar + estimateToTarget;
    }

    private int getCostSoFar() {
        return linksTravelled.stream().mapToInt(Link::getCost).sum();
    }

    public Node getEndNode() {
        return linksTravelled.get(linksTravelled.size() - 1).getTo();
    }

    public List<Link> getLinksTravelled() {
        return linksTravelled;
    }

    @Override
    public String toString() {
        return "Route{" +
                "linksTravelled=" + linksTravelled +
                ", costSoFar="+getCostSoFar()+
                ", costEstimate="+getCostEstimate()+
                '}';
    }
}

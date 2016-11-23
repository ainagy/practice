package com.nai.practice.exercises.astar;

public class Link {
    private final Node from;
    private final Node to;
    private final int cost;

    public Link(Node from, Node to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "Link{" +
                "from=" + from +
                ", to=" + to +
                ", cost=" + cost +
                '}';
    }
}

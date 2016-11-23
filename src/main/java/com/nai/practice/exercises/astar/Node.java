package com.nai.practice.exercises.astar;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Node {
    private final String name;
    private List<Link> links;
    private final int heuristicDistanceToTarget;

    public Node(String name, int heuristicDistanceToTarget) {
        this.name = name;
        this.heuristicDistanceToTarget = heuristicDistanceToTarget;
        links = new LinkedList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return name.equals(node.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public void addLink(Link link) {
        links.add(link);
    }

    public List<Link> getLinks() {
        return links;
    }

    public int getHeuristicDistanceToTarget() {
        return heuristicDistanceToTarget;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", heuristicDistanceToTarget=" + heuristicDistanceToTarget +
                '}';
    }
}

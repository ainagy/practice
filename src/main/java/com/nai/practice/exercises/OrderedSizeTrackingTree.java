package com.nai.practice.exercises;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * Created by Andras on 11/19/2016.
 */
public class OrderedSizeTrackingTree<T extends Comparable<T>> {
    Optional<Node<T>> root = Optional.empty();

    public void addValue(T newValue) {
        Node<T> newNode = new Node<>(newValue);
        if (!root.isPresent()) {
            root = Optional.of(newNode);
        } else {
            Node<T> parent = findParent(newNode, tNode -> tNode.incrementSize());
            addNodeToParent(newNode, parent);
        }
    }

    private void addNodeToParent(Node<T> newNode, Node<T> parent) {
        if (newNode.getValue().compareTo(parent.getValue()) <= 0) {
            parent.setLeftSubTree(newNode);
            newNode.setParent(parent);
        } else {
            parent.setRightSubTree(newNode);
            newNode.setParent(parent);
        }
    }

    public Optional<Node<T>> getRoot() {
        return root;
    }

    Node<T> findParent(Node<T> node, Consumer<Node<T>> visitor) {
        if (!root.isPresent()) {
            throw new IllegalStateException("No parent possibly exists in an empty tree");
        }
        Node<T> parent = root.get();
        visitor.accept(parent);

        if (parent.isParentFor(node.getValue())) {
            return parent;
        }

        while (!parent.isParentFor(node.getValue())) {
            if (node.getValue().compareTo(parent.getValue()) <= 0) {
                parent = parent.getLeftSubTree().get();
                visitor.accept(parent);
            } else {
                parent = parent.getRightSubTree().get();
                visitor.accept(parent);
            }
        }
        return parent;
    }

    public Optional<Node<T>> findNodeWithEqualValue(T value, Consumer<Node<T>> visitor) {
        Optional<Node<T>> node = root;
        while (node.isPresent()) {
            visitor.accept(node.get());
            if (value.compareTo(node.get().getValue()) == 0) {
                return node;
            }
            if (value.compareTo(node.get().getValue()) < 0) {
                //we go left
                node = node.get().getLeftSubTree();
            }
            if (value.compareTo(node.get().getValue()) > 0) {
                //we go right
                node = node.get().getRightSubTree();
            }
        }
        return Optional.empty();
    }

    public int countItemsLargerThan(T value) {
        final AtomicInteger count = new AtomicInteger(0);
        findNodeWithEqualValue(value, node -> {
                    if (value.compareTo(node.getValue()) < 0) {
                        count.addAndGet(
                                node.getRightSubTree().isPresent()
                                        ? node.getRightSubTree().get().getSize() + 1
                                        : 1);
                    }
                }
        );
        return count.get();
    }

    class Node<NT extends Comparable<NT>> {
        final NT value;
        Optional<Node<NT>> parent;
        Optional<Node<NT>> leftSubTree;
        Optional<Node<NT>> rightSubTree;
        int subtreeSize;
        private int size;

        public Node(NT value) {
            this.value = value;
            this.leftSubTree = Optional.empty();
            this.rightSubTree = Optional.empty();
            this.parent = Optional.empty();
            this.size = 1;
        }

        boolean hasNoLeftSubtree() {
            return !leftSubTree.isPresent();
        }

        boolean hasNoRightSubtree() {
            return !rightSubTree.isPresent();
        }

        boolean isParentFor(NT value) {
            return
                    (value.compareTo(getValue()) <= 0 && hasNoLeftSubtree()) ||
                            (value.compareTo(getValue()) > 0 && hasNoRightSubtree());
        }

        public void incrementSize() {
            size++;
        }

        public NT getValue() {
            return value;
        }

        public Optional<Node<NT>> getLeftSubTree() {
            return leftSubTree;
        }

        public void setLeftSubTree(Node<NT> leftSubTree) {
            this.leftSubTree = Optional.of(leftSubTree);
        }

        public Optional<Node<NT>> getRightSubTree() {
            return rightSubTree;
        }

        public void setRightSubTree(Node<NT> rightSubTree) {
            this.rightSubTree = Optional.of(rightSubTree);
        }

        public void setParent(Node<NT> parent) {
            this.parent = Optional.of(parent);
        }

        public int getSize() {
            return size;
        }

        public Optional<Node<NT>> getParent() {
            return parent;
        }
    }
}

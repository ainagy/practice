package com.nai.practice.exercises;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * Created by Andras on 11/19/2016.
 */
public class OrderedSizeTrackingTree<T extends Comparable<T>> {
    public enum IterationOrder {INORDER, BFS, DFS}

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

    public Iterator<T> iterator(IterationOrder iterationOrder) {
        return new OrderedSizeTrackingTreeIterator(this, iterationOrder);
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

    class OrderedSizeTrackingTreeIterator<S extends Comparable<S>> implements Iterator<S> {
        private final OrderedSizeTrackingTree<S> instance;
        private List<OrderedSizeTrackingTree<S>.Node<S>> itemsInOrder;
        private final Consumer<OrderedSizeTrackingTree<S>.Node<S>> walkStrategy;
        int index;

        public OrderedSizeTrackingTreeIterator(OrderedSizeTrackingTree<S> instance, IterationOrder iterationOrder) {
            this.instance = instance;
            itemsInOrder = new ArrayList<>(instance.getRoot().map(sNode -> sNode.getSize()).orElse(0));
            index = 0;
            switch (iterationOrder) {
                case INORDER:
                    walkStrategy = new InOrderWalkStrategy();
                    break;
                case BFS:
                    walkStrategy = new BFSWalkStrategy();
                    break;
                case DFS:
                    walkStrategy = new DFSWalkStrategy();
                    break;
                default:
                    throw new RuntimeException("Unsupported iteration order");
            }

            instance.getRoot().ifPresent(root -> pushSubtree(root));
        }

        private void pushSubtree(OrderedSizeTrackingTree<S>.Node<S> subtree) {
            walkStrategy.accept(subtree);
        }

        @Override
        public boolean hasNext() {
            return index < itemsInOrder.size();
        }

        @Override
        public S next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return itemsInOrder.get(index++).getValue();
        }

        class InOrderWalkStrategy implements Consumer<OrderedSizeTrackingTree<S>.Node<S>> {
            @Override
            public void accept(OrderedSizeTrackingTree<S>.Node<S> root) {
                root.getLeftSubTree().ifPresent(leftNode -> pushSubtree(leftNode));

                itemsInOrder.add(root);

                root.getRightSubTree().ifPresent(rightNode -> pushSubtree(rightNode));
            }
        }

        class DFSWalkStrategy implements Consumer<OrderedSizeTrackingTree<S>.Node<S>> {
            @Override
            public void accept(OrderedSizeTrackingTree<S>.Node<S> root) {
                itemsInOrder.add(root);

                root.getLeftSubTree().ifPresent(leftNode -> pushSubtree(leftNode));

                root.getRightSubTree().ifPresent(rightNode -> pushSubtree(rightNode));
            }
        }

        class BFSWalkStrategy implements Consumer<OrderedSizeTrackingTree<S>.Node<S>> {
            @Override
            public void accept(OrderedSizeTrackingTree<S>.Node<S> root) {
                Queue<OrderedSizeTrackingTree<S>.Node<S>> itemsToWalk = new LinkedList<>();
                itemsToWalk.add(root);
                while (itemsToWalk.size()>0) {
                    OrderedSizeTrackingTree<S>.Node<S> item = itemsToWalk.remove();
                    itemsInOrder.add(item);
                    item.getLeftSubTree().ifPresent(leftNode -> itemsToWalk.add(leftNode));
                    item.getRightSubTree().ifPresent(rightNode -> itemsToWalk.add(rightNode));
                }
            }
        }
    }
}

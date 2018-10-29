package com.chenhz.excel.Entity;

import java.util.List;

public class Node<E> {
    E item;
    List<Node<E>> nexts;
    Node<E> prev;

    Node(Node<E> prev, E element, List<Node<E>> next) {
        this.item = element;
        this.nexts = next;
        this.prev = prev;
    }

    @Override
    public String toString() {
        return "Node{" +
                "item=" + item +
                ", nexts=" + nexts +
                ", prev=" + prev +
                '}';
    }
}

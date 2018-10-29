package com.chenhz.excel.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Node<E> implements Serializable{
    E item;
    List<Node<E>> nexts;
    Node<E> prev;

    public Node(Node<E> prev, E element, List<Node<E>> next) {
        this.item = element;
        this.nexts = next;
        this.prev = prev;
    }

    public Node(E element){
        this.item = element;
        this.nexts = new ArrayList<>();
        this.prev = null;
    }

    public Node addNext(Node<E> n){
        if (nexts == null){
            nexts = new ArrayList<>();
        }
        nexts.add(n);
        return this;
    }

    public E getItem() {
        return item;
    }

    public void setItem(E item) {
        this.item = item;
    }

    public List<Node<E>> getNexts() {
        return nexts;
    }

    public Node<E> getNextLast(){
        return nexts.get(nexts.size()-1);
    }

    public void setNexts(List<Node<E>> nexts) {
        this.nexts = nexts;
    }

    public void setPrev(Node<E> prev) {
        this.prev = prev;
    }

    public Node<E> getPrev() {
        return prev;
    }

//    @Override
//    public String toString() {
////        if (nexts == null || nexts.size()==0){
////            return "},";
////        }
////        StringBuilder sb = new StringBuilder("item=" + item + ", nexts=" +"Node{" );
////        for (Node<E> n:nexts) {
////            sb.append(n);
////        }
//////        sb.append(", prev=" + (prev.item == null? "" : prev.item) + '}');
////
////        return sb.toString();
//
//
//    }


}

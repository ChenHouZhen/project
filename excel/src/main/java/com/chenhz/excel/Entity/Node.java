package com.chenhz.excel.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String,Object> iteratopMap(Node<E> top){
        Map<String,Object> m = new HashMap<>();
        m.put("value",top.item);
        if (top != null){
            List<Map<String,Object>> l = new ArrayList<>();
            for (Node n :top.nexts){
                Map<String,Object> mm = new HashMap<>();
                mm.put("value",n.item);
                l.add(mm);
                if (n.getNexts() != null && n.getNexts().size() > 0){
                    mm.putAll(iteratopMap(n));
                }
            }
            m.put("next",l);
        }

        return m;
    }

//    public String iterator(Node<E> top){
//        StringBuilder sb = new StringBuilder();
//        sb.append("\n");
//        if (top != null){
//            for (Node n : top.nexts){
//                sb.append(n.item+",");
//                if (n.getNexts() != null && n.getNexts().size() >0){
//                    sb.append(iterator(n));
//                }
//            }
//        }
//        sb.append("\n");
//        return sb.toString();
//    }
}

package com.chenhz.excel.entity;

import com.chenhz.common.entity.ZNode;

import java.io.Serializable;
import java.util.*;

public class Node<E> implements Serializable{
    E item;
    String id;
    List<Node<E>> nexts;
    Node<E> prev;

    public Node(Node<E> prev, E element, List<Node<E>> next) {
        this.item = element;
        this.nexts = next;
        this.prev = prev;
        this.id = UUID.randomUUID().toString();
    }

    public Node(E element){
        this.item = element;
        this.nexts = new ArrayList<>();
        this.prev = null;
        this.id = UUID.randomUUID().toString();
    }

    public Node addNext(Node<E> n){
        if (nexts == null){
            nexts = new ArrayList<>();
        }
        nexts.add(n);
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = UUID.randomUUID().toString();
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

    public List<ZNode> getBackList(){
        ZNode zNode = new ZNode();
        zNode.setPid(null);
        zNode.setId(this.id);
        zNode.setName(this.item.toString());
        zNode.setLever(0);
        List<ZNode> treeList = new ArrayList<>();
        treeList.add(zNode);
        List<Node<E>> children = this.nexts;
        return iterator(children,1,treeList);
    }


    private List<ZNode> iterator(List<Node<E>> allChildren, int level, List<ZNode> treeList){
        for (Node n : allChildren){
            ZNode zNode = new ZNode();
            zNode.setId(n.id);
            zNode.setLever(level);
            zNode.setName(n.item.toString());
            zNode.setPid(n.prev.getId());
            treeList.add(zNode);
            List<Node<E>> children = n.nexts;
            if (children != null && children.size() > 0){
                iterator(children,level+1,treeList);
            }

        }
        return treeList;
    }

}

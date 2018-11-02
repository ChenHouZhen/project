package com.chenhz.common.entity;

import com.chenhz.common.utils.UUIDGenerate;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TreeNode<E> {

    private String id;

    private E value;

    private TreeNode<E> prev;

    private List<TreeNode<E>> nexts;

    public TreeNode(E value, TreeNode<E> prev, List<TreeNode<E>> nexts) {
        this.id = UUIDGenerate.create();
        this.value = value;
        this.prev = prev;
        this.nexts = nexts;
    }

    public TreeNode(E value) {
        this(value,null,new ArrayList<>());
    }

    public TreeNode(){
        this(null);
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = UUIDGenerate.create();
    }

    public void setId(String id) {
        this.id = id;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public TreeNode<E> getPrev() {
        return prev;
    }

    public void setPrev(TreeNode<E> prev) {
        this.prev = prev;
    }

    public List<TreeNode<E>> getNexts() {
        return nexts;
    }

    public void setNexts(List<TreeNode<E>> nexts) {
        this.nexts = nexts;
    }

    public TreeNode<E> addNext(TreeNode<E> n){
        if (nexts == null){
            nexts = new ArrayList<>();
        }
        nexts.add(n);
        return this;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TreeNode)){
            return false;
        }
        return super.equals(obj);
    }

    public static void main(String[] args) {
        TreeNode<String> n1 = new TreeNode<>("w");
        TreeNode<String> n2 = new TreeNode<>();
        TreeNode<String> n3 = new TreeNode<>("d",n1,Arrays.asList(n2));
        System.out.println(n3.value +" "+n3.prev.value +" "+n3.nexts.get(0).value);
    }

}

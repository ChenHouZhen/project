package com.chenhz.xmind.entity;

import com.chenhz.common.entity.TreeNode;
import com.chenhz.common.entity.ZNode;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeFactory {

    public static TreeNode<String> create(){
        TreeNode<String> Topic  = new TreeNode<>("ZhiMap介绍");
        TreeNode<String> level1 = new TreeNode<>("ZhiMap是什么？");
        TreeNode<String> level2 = new TreeNode<>("有什么用？");
        TreeNode<String> level3 = new TreeNode<>("更多帮助");
        TreeNode<String> level4 = new TreeNode<>("ZhiMap怎么学？");
        TreeNode<String> level21 = new TreeNode<>("写提纲、列计划");
        TreeNode<String> level22 = new TreeNode<>("知识整理");
        TreeNode<String> level23 = new TreeNode<>("记读书笔记");

        Topic.addNext(level1);
        Topic.addNext(level2);
        Topic.addNext(level3);
        Topic.addNext(level4);
        level1.setPrev(Topic);
        level2.setPrev(Topic);
        level3.setPrev(Topic);
        level4.setPrev(Topic);
        level21.setPrev(level2);
        level22.setPrev(level2);
        level23.setPrev(level2);

        level2.addNext(level21);
        level2.addNext(level22);
        level2.addNext(level23);

        return Topic;
    }


    public static List<ZNode> createZNode(){

        List<ZNode> list = new ArrayList<>();
        ZNode zNode1 = new ZNode("1",null,"ZhiMap介绍");
        ZNode zNode2 = new ZNode("10","1","ZhiMap是什么？");
        ZNode zNode3 = new ZNode("11","1","有什么用？");
        ZNode zNode4 = new ZNode("12","1","更多帮助");
        ZNode zNode5 = new ZNode("13","1","ZhiMap怎么学？");
        ZNode zNode6 = new ZNode("130","13","写提纲、列计划");
        ZNode zNode7 = new ZNode("131","13","知识整理");
        ZNode zNode8 = new ZNode("132","13","记读书笔记");

        list.add(zNode1);
        list.add(zNode2);
        list.add(zNode3);
        list.add(zNode4);
        list.add(zNode5);
        list.add(zNode6);
        list.add(zNode7);
        list.add(zNode8);

        return list;
    }

}

package com.chenhz.xmind.entity;

import com.chenhz.common.entity.TreeNode;

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
}

package com.chenhz.common.entity;

public class ZNode {

    public ZNode(){}

    public ZNode(String id, String pid, String name) {
        this.id = id;
        this.pid = pid;
        this.name = name;
    }

    private String id;

    private String pid;

    private String name;

    private Integer lever;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLever() {
        return lever;
    }

    public void setLever(Integer lever) {
        this.lever = lever;
    }

    @Override
    public String toString() {
        return "ZNode{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", name='" + name + '\'' +
                ", lever=" + lever +
                '}';
    }
}

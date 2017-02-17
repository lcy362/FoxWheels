package com.tools.bean;

/**
 * Created by lcy on 2017/2/17.
 */
public class TestObject {
    private String name;
    private String id;
    private String score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "TestObject{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}

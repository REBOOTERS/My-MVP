package com.engineer.jetpack.model;

/**
 * @author: Rookie
 * @date: 2018-09-29
 * @desc
 */
public class User {
    String name;
    String age;

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

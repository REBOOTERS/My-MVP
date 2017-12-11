package com.avaj.reflection;

/**
 * author : engineer
 * e-mail : yingkongshi11@gmail.com
 * time   : 2017/12/11
 * desc   : Just for Test Reflection ,So all the data and function is private
 * version: 1.0
 */
public class Student {
    private int age;//年龄
    private String name;//姓名
    private String address;//地址
    private static String sTest;

    public Student() {
        throw new IllegalAccessError("Access to default Constructor Error!");
    }

    private Student(int age, String name, String address) {
        this.age = age;
        this.name = name;
        this.address = address;
        sTest = "测试反射";
    }

    private int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private String getAddress() {
        return address;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    private static String getTest() {
        return sTest;
    }

    public String setMyAddress(String address, String city) {
        return address;
    }

}

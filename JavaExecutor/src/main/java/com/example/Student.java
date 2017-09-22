package com.example;

/**
 * Created by engineer on 2017/6/25.
 */

public class Student implements Comparable<Student>{
    private String name;
    private int id;


    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        Student mStudent= (Student) o;
        return getName().equals(mStudent.getName());
    }

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public int compareTo(Student student) {
        //按照name(字符串值）从小到大排序
        return name.compareTo(student.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

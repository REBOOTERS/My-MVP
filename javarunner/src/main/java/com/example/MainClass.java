package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by engineer on 2017/6/25.
 */

public class MainClass {


//    private static void printStudents(List<? extends Student> students) {
//        for (Student mStudent : students) {
//            System.out.println(mStudent.getName());
//        }
//        //当使用通配符声明后，将不能再向集合中添加元素，因此以下语句非法
//        students.add(new SeniorStudent("hacker", 999));
//    }
    private static <T extends Student> void printStudents(List<T> students) {
        for (Student mStudent : students) {
            System.out.println(mStudent.getName());
        }
        //当使用通配符声明后，将不能再向集合中添加元素，因此以下语句非法
//        students.add(new SeniorStudent("hacker", 999));
    }



    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        //因为泛型，List现在可以接受所有Student类型的对象
        students.add(new Student("mike",001));
        students.add(new CollegeStudent("lucy",002));
        students.add(new SeniorStudent("tom", 003));
        //
        printStudents(students);

        List<CollegeStudent> colleges = new ArrayList<>();
        colleges.add(new CollegeStudent("a", 100));
        colleges.add(new CollegeStudent("b", 101));
        colleges.add(new CollegeStudent("c", 102));
        //这样做是不行的
        printStudents(colleges);

    }




}

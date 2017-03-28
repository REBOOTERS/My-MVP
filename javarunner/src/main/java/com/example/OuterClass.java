package com.example;

/**
 * Created by rookie on 2017/1/18.
 */

public class OuterClass {
    private  String language = "en";
    private  String region = "US";


    public class InnerClass {
        public void printOuterClassPrivateFields() {
            String fields = "language=" + language + ";region=" + region;
            System.out.println(fields);
        }
    }

    public static class MyInnerClass{
        public void printOuterClassPrivateFields() {
            String fields = "language="  + ";region=" ;
            System.out.println(fields);
        }
    }

    public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        OuterClass.InnerClass inner = outer.new InnerClass();
        inner.printOuterClassPrivateFields();

        MyInnerClass myInnerClass=new MyInnerClass();
        myInnerClass.printOuterClassPrivateFields();

    }
}

package com.avaj;

/**
 * author : engineer
 * e-mail : yingkongshi11@gmail.com
 * time   : 2018/3/17
 * desc   :
 * version: 1.0
 */
public class EqualsDriver {


    private static class People {
        private int age;
        private String name;

        Object mObject;

        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }

            if(o==this){
                return true;
            }

            if(o instanceof People){
                return this.hashCode()==o.hashCode();
            }

            return false;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        public People(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public static void main(String[] args){
        int a=1;
        int b=1;
        int c=2;

        String strA="hello";
        String strB="hello";
        String strC="hello world";

        System.out.println("a==b  "+String.valueOf(a==b));
        System.out.println("a==c  "+String.valueOf(a==c));

        System.out.println("strA==StrB  "+strA==strB);
        System.out.println("strA==StrB  "+strA==strC);
        System.out.println("strA.equals(strB)  "+strA.equals(strB));
        System.out.println("strA.equals(strC)  "+strA.equals(strC));

        System.out.println("strA.hashCode=" + strA.hashCode());
        System.out.println("strB.hashCode=" + strB.hashCode());
        System.out.println("strC.hashCode=" + strC.hashCode());

        People peopleA=new People("zhangsan");
        People peopleB=new People("lisi");
        People peopleC=new People("zhangsan");
        People peopleD=peopleA;

        System.out.println("peopleA==peopleB "+String.valueOf(peopleA==peopleB));
        System.out.println("peopleA==peopleC "+String.valueOf(peopleA==peopleC));
        System.out.println("peopleA==peopleD "+String.valueOf(peopleA==peopleD));
        System.out.println("peopleA.equals(peopleB) "+peopleA.equals(peopleB));
        System.out.println("peopleA.equals(peopleC) "+peopleA.equals(peopleC));
        System.out.println("peopleA.equals(peopleD) "+peopleA.equals(peopleD));

        System.out.println("peopleA's hashCode=="+peopleA.hashCode());
        System.out.println("peopleB's hashCode=="+peopleB.hashCode());
        System.out.println("peopleC's hashCode=="+peopleC.hashCode());
        System.out.println("peopleD's hashCode=="+peopleD.hashCode());


    }

}

package com.avaj.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * author : engineer
 * e-mail : yingkongshi11@gmail.com
 * time   : 2017/12/11
 * desc   :
 * version: 1.0
 */
public class MainClass {
    private static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        // 由于构造函数私有，因此无法直接创建其实例，需要通过反射
//        Student mStudent=new Student();
        Class<?> mClass = Class.forName("com.avaj.reflection.Student");

//        printParams(mClass);
//        printConstructors(mClass);
//        printMethods(mClass);

        reflectClass(mClass);
    }

    /**
     * 反射创建一个自己的类
     * @param mClass
     */
    private static void reflectClass(Class<?> mClass) throws Exception{
        Constructor mConstructor=mClass.getDeclaredConstructor(int.class,String.class,String.class);
        mConstructor.setAccessible(true);
        // 使用构造器创建对象
        Object mStudent = mConstructor.newInstance(22, "小明", "花果山水帘洞");
        System.err.println("mStudent=" + mStudent.toString());
        // 获取private 类型的int 属性
        Field mAge = mClass.getDeclaredField("age");
        mAge.setAccessible(true);
        int age = mAge.getInt(mStudent);
        System.err.println("mStudent age=" + age);

        //
        Field mName = mClass.getDeclaredField("name");
        mName.setAccessible(true);
        String name = (String) mName.get(mStudent);
        System.err.println("mStudent name=" + name);

        Field mAddress = mClass.getDeclaredField("address");
        mAddress.setAccessible(true);
        String address = (String) mAddress.get(mStudent);
        System.err.println("mStudent address=" + address);

        Method mGetTestMethod = mClass.getDeclaredMethod("getTest");
        mGetTestMethod.setAccessible(true);
        String result = (String) mGetTestMethod.invoke(null);

        System.err.println("the getTest() result ="+result);
    }

    private static void printMethods(Class<?> aClass) {
        Method[] mMethods = aClass.getDeclaredMethods();
        sb = new StringBuilder();
        for (Method mMethod : mMethods) {
            sb.append("\t");
            // 获取方法的修饰符
            sb.append(Modifier.toString(mMethod.getModifiers())).append(" ");
            // 获取方法返回类型
            sb.append(mMethod.getReturnType().getSimpleName()).append(" ");
            //
            sb.append(mMethod.getName()).append("(");
            for (int i = 0; i < mMethod.getParameterCount(); i++) {
                sb.append(mMethod.getParameterTypes()[i].getSimpleName()).append(" ")
                        .append(mMethod.getParameters()[i].getName()).append(",");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append(")\n");
        }

        System.out.println("the Class=所有方法:\n" + sb.toString());
    }

    private static void printConstructors(Class<?> aClass) {
        Constructor[] mConstructors= aClass.getDeclaredConstructors();
        sb = new StringBuilder();
        for (Constructor mConstructor : mConstructors) {
            sb.append(Modifier.toString(mConstructor.getModifiers())).append(" ");
            sb.append(mConstructor.getName()).append("(");
            for(int i=0;i<mConstructor.getParameterCount();i++) {
                sb.append(mConstructor.getParameterTypes()[i].getSimpleName()).append(" ")
                .append(mConstructor.getParameters()[i].getName()).append(",");
            }
            if (mConstructor.getParameterCount() > 0) {
                sb.deleteCharAt(sb.length()-1);
            }
            sb.append(")\n");
        }
        System.out.println("the Class=构造方法:\n" + sb.toString());
    }

    private static void printParams(Class<?> aClass) {
        Field[] mFields = aClass.getDeclaredFields();
        sb = new StringBuilder();
        for (Field mField : mFields) {
            sb.append("\t");
            //获取属性的修饰符 public/private/static 等
            sb.append(Modifier.toString(mField.getModifiers())).append(" ");
            // 属性的类型名称
            sb.append(mField.getType().getSimpleName()).append(" ");
            // 属性的名称
            sb.append(mField.getName()).append(";\n");
        }
        System.out.println("the Class=所有属性:\n" + sb.toString());
    }
}

package com.avaj.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by engineer on 2017/11/5.
 */

public class ClassLoaderTest {
    private static final String LOCAL_PATH = "C:\\Users\\zyq\\Desktop\\javaLib";
    private static final String CLASS_NAME = "Jobs";
    public static void main(String[]args) {
        LocalClassLoader mLocalClassLoader = new LocalClassLoader(LOCAL_PATH);
        try {
            Class c = mLocalClassLoader.findClass(CLASS_NAME);
            if (c != null) {
                Object object=c.newInstance();
                System.out.println("ClassLoader=" + object.getClass().getClassLoader());
                Method mMethod = c.getDeclaredMethod("say", null);
                mMethod.invoke(object, null);
            }
        } catch (ClassNotFoundException | IllegalAccessException
                | InstantiationException | NoSuchMethodException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

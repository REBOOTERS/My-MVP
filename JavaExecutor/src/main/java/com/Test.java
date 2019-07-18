package com;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author zhuyongging @ Zhihu Inc.
 * @since 06-18-2019
 */
public class Test {

    private String name = "111";
    private int value = 0;

    public Test(String name) {
        this.name = name;
    }

    public Test(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public void setNameAndValue(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static void main(String[] args) {
        System.out.println("now");


        Type type = new TypeWrapper<String>() {
        }.getType();
        System.out.println("type is " + type.getTypeName());
    }


    private static class TypeWrapper<T> {
        private final Type mType;

        protected TypeWrapper() {
            Type superClass = getClass().getGenericSuperclass();
            ParameterizedType parameterizedType = (ParameterizedType) superClass;
            mType = parameterizedType.getActualTypeArguments()[0];
        }

        public Type getType() {
            return mType;
        }
    }
}

package com.avaj.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by engineer on 2017/11/5.
 */

public class LocalClassLoader extends ClassLoader {
    private String path;


    public LocalClassLoader(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String s) throws ClassNotFoundException {
        Class mClass = null;
        byte[] classData = loadClassData(s);
        if (classData == null) {
            throw new ClassNotFoundException();
        }else {
            mClass = defineClass(s, classData,0,classData.length);
        }

        return mClass;
    }

    private byte[] loadClassData(String className) {
        className = getFileName(className);
        File mFile = new File(path, className);
        InputStream mInputStream=null;
        ByteArrayOutputStream mOutputStream=null;

        try {
            mInputStream = new FileInputStream(mFile);
            mOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length=0;
            while ((length=mInputStream.read(buffer))!=-1){
                mOutputStream.write(buffer, 0, length);
            }
            return mOutputStream.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {


            try {
                mInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                mOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private String getFileName(String name) {
        int index = name.lastIndexOf('.');
        if(index == -1){//如果没有找到'.'则直接在末尾添加.class
            return name+".class";
        }else{
            return name.substring(index+1)+".class";
        }
    }
}

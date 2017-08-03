package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MyClass {
    public static void main(String[] args) {
        File mFile = new File("readme.txt");
        try {
            FileReader mFileReader = new FileReader(mFile);
            FileWriter mFileWriter=new FileWriter("copy.txt");
            BufferedReader mReader = new BufferedReader(mFileReader);
            BufferedWriter mWriter = new BufferedWriter(mFileWriter);
            String line;
            while ((line=mReader.readLine())!=null){
                mWriter.write(line+"\n");
            }
            mWriter.flush();
            mReader.close();
            mWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

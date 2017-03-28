package com.example.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by rookie on 2016/11/4.
 */

public class FileCopy {


    public static void copy(String source, String destination) {
        File sourceFile = new File(source);
        File destFile = new File(destination);

        System.err.println("copy file from " + source + " to " + destination);

        try {


            FileReader reader = new FileReader(sourceFile);
            FileWriter writer = new FileWriter(destFile);

            char[] buffer = new char[1024];


            int len = 0;

            while ((len = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, len);
            }

            writer.flush();
            reader.close();
            writer.close();

            System.err.println("Success");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

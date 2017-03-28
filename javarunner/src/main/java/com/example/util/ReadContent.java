package com.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by rookie on 2016/11/4.
 */

public class ReadContent {

    public static String read(String fileName) {
        String result = "";
        File file = new File(fileName);
        try {
            InputStream inputStream = new FileInputStream(file);
            byte[] b = new byte[(int) file.length()];
            inputStream.read(b);
            result = new String(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }
}

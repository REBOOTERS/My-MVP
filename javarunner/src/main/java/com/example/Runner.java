package com.example;

import com.example.util.ReadContent;

public class Runner {
    private static final String DOWNLOAD_URL = "https://raw.githubusercontent.com/REBOOTERS/SomeFile/master/App.pdf";

    public static void main(String[] args) {
//        System.err.println("File Download ----->");
//        new HttpDownload(DOWNLOAD_URL).start();

//        FileCopy.copy("README.md", "README1.md");

        System.err.println("read content of ch_file.txt ------> " + ReadContent.read("HttpDemo\\src\\main\\java\\home\\smart\\fly\\httpurlconnectiondemo\\RxJavaDemoActivity.java"));




    }


}

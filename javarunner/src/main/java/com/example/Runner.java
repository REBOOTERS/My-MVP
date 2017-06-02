package com.example;

public class Runner {
    private static final String DOWNLOAD_URL = "https://raw.githubusercontent.com/REBOOTERS/SomeFile/master/App.pdf";

    public static void main(String[] args) {
//        System.err.println("File Download ----->");
//        new HttpDownload(DOWNLOAD_URL).start();

//        FileCopy.copy("README.md", "README1.md");

//        System.err.println("read content of ch_file.txt ------> " + ReadContent.read("HttpDemo\\src\\main\\java\\home\\smart\\fly\\httpurlconnectiondemo\\RxJavaDemoActivity.java"));

        String appVersion = "4.4.0";
        String uuid = "3.3.5";

        int result = appVersion.compareTo(uuid);

        System.out.println("the result is " + result);


        int a = 1 << 0;
        int c = 1;
        int b = 1 << 1;

        System.out.println("a = " + a);
        System.out.println("~a = " + ~a);
        System.out.println("~c = " + ~c);
//        System.out.println("a = " + (a | b));
//        System.out.println("a = " + (c | b));
//        System.out.println("a = " + (a & b));
//        System.out.println("a = " + (c & b));
//
//        System.out.println("a = c" + String.valueOf(a == c));
//        System.out.println("b = " + b);
    }


}

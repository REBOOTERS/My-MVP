package com;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Driver {
    public static void main(String[] args) {
        float result = getSizeInMB(0);
//        System.out.println("the result is " + result);

        String pic1 = "https://pic4.zhimg.com/50/v2-90a75e1031331207e91827548184d18e_qhd.jpg";
        String pic2 = "https://pic1.zhimg.com/v2-7e2aef6272186b346ad468311acca63c_hd.jpg";


        System.err.println("replace /50 with /80  " + fixImageQuality(pic1,"/50","/80"));
        System.err.println("replace /50 with /80  " + fixImageQuality(pic1,"/50",""));


        System.err.println("replace /80 with /100  " + fixImageQuality(pic1,"/50","/80"));

    }



    public static String fixImageQuality(String url,String oldQuality,String newQuality){
        String result = url;

        int indexOfQuality = url.indexOf(oldQuality);

        if (indexOfQuality > 0) {
            // 图片地址中包含有可替换的质量系数,直接替换
            result = result.replace(oldQuality, newQuality);
        } else {
            // 图片中原来没有质量系数，需要添加
            result = result.replace("com/","com/"+newQuality+"/");
        }

        return result;
    }


    public static float getSizeInMB(long sizeInBytes) {
        DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
        df.applyPattern("0.0");
        String result = df.format((float) sizeInBytes / 1024 / 1024);
        result = result.replaceAll(",","."); // in some case , 0.0 will be 0,0
        return Float.valueOf(result);
    }
}

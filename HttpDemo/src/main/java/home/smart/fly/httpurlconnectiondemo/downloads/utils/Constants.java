package home.smart.fly.httpurlconnectiondemo.downloads.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by engineer on 2017/3/25.
 */

public class Constants {
    public static final String PACKAGE_URL = "https://static.dongqiudi.com/app/apk/dongqiudi_website.apk";
    public static final String FILE_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator + "dqd.apk";

}

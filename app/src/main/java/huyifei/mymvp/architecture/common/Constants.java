package huyifei.mymvp.architecture.common;

import android.os.Environment;

import java.io.File;

/**
 * Created by rookie on 2017/1/12.
 */

public class Constants {
    public static final String DOWNLOAD_URL = "http://dl.bizhi.sogou.com/images/2015/06/26/1214911.jpg";
    public static final String DOWNLOAD_ERROR_URL = "http://dl.bizhi.sogou.com/images/06/26/1214911.jpg";

    public static final String LOCAL_FILE_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator + "test.jpg";
}

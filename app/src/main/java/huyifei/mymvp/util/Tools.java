package huyifei.mymvp.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by rookie on 2017/2/10.
 */

public class Tools {
    /**
     * 获取系统时间 从月份开始到秒结束
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日    HH:mm:ss", Locale.CHINA);
        Date curDate = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(curDate);
    }
}

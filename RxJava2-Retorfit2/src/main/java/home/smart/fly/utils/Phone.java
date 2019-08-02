package home.smart.fly.utils;

import android.os.Build;

/**
 * @author zhuyongging @ Zhihu Inc.
 * @since 08-02-2019
 */
public enum Phone {
    /**
     * 小米手机，及其子品牌
     */
    XIAOMI("Xiaomi"),
    /**
     * Oppo 手机，及其子品牌
     */
    OPPO("OPPO"),
    /**
     * Vivo 手机，及其子品牌
     */
    VIVO("vivo"),
    /**
     * 华为及荣耀，还有 Nexus 6P
     */
    HUAWEI("HUAWEI"),
    /**
     * 索尼
     */
    SONY("Sony"),
    /**
     * 三星
     */
    SAMSUNG("samsung");

    private String value;

    Phone(String val) {
        value = val;
    }

    /**
     * 是否是当前操作系统
     */
    boolean isCurrent() {
        return value.equalsIgnoreCase(Build.MANUFACTURER) || value.equalsIgnoreCase(Build.BRAND);
    }
}

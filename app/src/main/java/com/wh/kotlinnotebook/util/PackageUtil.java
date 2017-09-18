package com.wh.kotlinnotebook.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.wh.kotlinnotebook.MyApplication;

/**
 * 包相关工具类
 * Date: 2017-04-20  17:22
 */

public class PackageUtil {
    /**
     * 获取当前应用的版本号
     */
    public static String getVersionName() {
        // 获取packagemanager的实例
        PackageManager packageManager = MyApplication.getInstance().getApplicationContext().getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(MyApplication.getInstance().getApplicationContext().getPackageName(), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0";
        }
    }
    /**
     * 获取当前应用的版本号
     */
    public static int getVersionCode() {
        // 获取packagemanager的实例
        PackageManager packageManager = MyApplication.getInstance().getApplicationContext().getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(MyApplication.getInstance().getApplicationContext().getPackageName(), 0);
            return packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 1;
        }
    }
}

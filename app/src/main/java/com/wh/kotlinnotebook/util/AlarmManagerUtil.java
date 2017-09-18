package com.wh.kotlinnotebook.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/**
 * 作者： wh
 * 时间：  2017/8/31
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class AlarmManagerUtil {
    public static final String ALARM_ACTION = "com.loonggg.alarm.clock";

    public static void setAlarmTime(Context context, long timeInMillis, long betweenTime, Intent intent) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent sender = PendingIntent.getBroadcast(context, intent.getIntExtra("", 0),
                intent, PendingIntent.FLAG_CANCEL_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            am.setWindow(AlarmManager.RTC_WAKEUP, timeInMillis, betweenTime, sender);
        }
    }
}

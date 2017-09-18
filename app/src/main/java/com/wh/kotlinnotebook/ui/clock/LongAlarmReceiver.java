package com.wh.kotlinnotebook.ui.clock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wh.kotlinnotebook.util.AlarmManagerUtil;
import com.wh.kotlinnotebook.util.DateUtil;

import java.util.Calendar;

/**
 * 作者： wh
 * 时间：  2017/8/31
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class LongAlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(AlarmManagerUtil.ALARM_ACTION)){
            int hour = intent.getIntExtra("hour",7);
            int minute = intent.getIntExtra("minute",30);
            String time =intent.getStringExtra("time");
            Calendar mCalendar = DateUtil.getCalender(hour,minute);
            AlarmManagerUtil.setAlarmTime(context, mCalendar.getTimeInMillis(),(1000*24*60*60),intent);
            Intent clockIntent = new Intent(context, AlarmClockActivity.class);
            clockIntent.putExtra("time",time);
            clockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(clockIntent);
        }
    }
}

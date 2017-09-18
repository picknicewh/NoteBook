package com.wh.kotlinnotebook.ui.clock;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/9/14
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class AlarmClockPresenter  implements  AlarmClockContract.Presenter{
     private AlarmClockContract.View view;
    public AlarmClockPresenter(AlarmClockContract.View view){
       this.view  =view;
    }




    @Override
    public List<String> getHour() {
        List<String> mouths = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            mouths.add(String.valueOf(i));
        }
        return mouths;
    }

    @Override
    public List<String> getDates() {
        List<String> date = new ArrayList<>();
        for (int i = 0; i < 61; i++) {
            date.add(String.valueOf(i));
        }
        return date;
    }

    @Override
    public String getFormatTime(int hour ,int minute ) {
        String mTime;
        if (hour<10 && minute<10){
            mTime  = "0"+ hour + ":" +"0"+ minute;
        }else if (hour>10&&minute<10){
            mTime  =  hour + ":" +"0"+ minute;
        }else if (hour<10&&minute>10){
            mTime  = "0"+ hour + ":" + minute;
        }else {
            mTime = hour + ":" + minute;
        }
        return mTime;
    }

}

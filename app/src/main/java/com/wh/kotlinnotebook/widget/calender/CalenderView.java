package com.wh.kotlinnotebook.widget.calender;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.database.UserInfo;
import com.wh.kotlinnotebook.util.DateUtil;
import com.wh.kotlinnotebook.util.G;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/8/23
 * 名称：日历控件
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class CalenderView extends LinearLayout {
    /**
     * 上次记录月经日期
     */
    private int menseDay;
    /**
     * 月经间隔天数
     */
    private int menseBetweenDay;
    /**
     * 月经天数
     */
    private int menseDays;
    /**
     * 当前年
     */
    private int currentYear;
    /**
     * 当前月
     */
    private int currentMouth;
    /**
     * 当前日
     */
    private int currentDay;
    /**
     * 当前年
     */
    private int changeYear;
    /**
     * 当前月
     */
    private int changeMouth;
    /**
     * 当前日
     */
    private int changeDay;
    /**
     * 日历
     */
    private Calendar mCalendar;
    /**
     * 日历文字列表
     */
   private RecyclerView rvCalender;
    /**
     * 适配器
     */
    private CalenderAdapter adapter;
    public CalenderView(Context context) {
        super(context, null);
    }

    public CalenderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalenderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mCalendar =Calendar.getInstance();
        mCalendar.setTime(new Date(System.currentTimeMillis()));
        currentYear = mCalendar.get(Calendar.YEAR);
        currentMouth = mCalendar.get(Calendar.MONTH) +1;
        currentDay =mCalendar.get(Calendar.DAY_OF_MONTH);
        changeYear = currentYear;
        changeMouth = currentMouth;
        changeDay = currentDay;
        UserInfo userInfo = UserInfo.getInstance();
        menseDay =userInfo.getSecretDay();
        menseBetweenDay = userInfo.getBetweenDays();
        menseDays = userInfo.getSecretDays();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_calender, this);
        rvCalender = (RecyclerView) findViewById(R.id.rv_calender);
        rvCalender.setLayoutManager(new GridLayoutManager(context,7));
        adapter  = new CalenderAdapter(getDateList(),context);
        rvCalender.setAdapter(adapter);
    }
    /**
     * 设置每月的天数，对应的日期
     */
    private List<DateVo> getDateList() {
        List<DateVo> dataList = new ArrayList<>();
        int rows = 6;
        int week = DateUtil.getWeekofday(currentYear, currentMouth);
        int days = DateUtil.getMouthDays(currentYear, currentMouth);
        int lastMouthday = DateUtil.getMouthDays(currentYear, currentMouth - 1) - week + 1;
        int date = 1;
        int j = 1;
        for (int row = 1; row <= rows; row++) {
            if (row == 1) {
                for (int i = 0; i < 7; i++) {
                    if (i < week && week != 7) {
                        DateVo dateVo = new DateVo();
                        dateVo.setDate(lastMouthday++);
                        dateVo.setSign(-1);
                        dataList.add(dateVo);
                    } else {
                        DateVo dateVo = new DateVo();
                        dateVo.setDate(date++);
                        if (isCurrentDate(date)){
                            dateVo.setSign(2);
                        }else if (isMenseDate(date,days)){
                            dateVo.setSign(1);
                        }else if (isDangerDate(date)){
                            dateVo.setSign(5);
                        } else {
                            dateVo.setSign(0);
                        }
                        dataList.add(dateVo);
                    }
                }
            } else {
                for (int i = 0; i < 7; i++) {
                    if (date > days) {
                        DateVo dateVo = new DateVo();
                        dateVo.setDate(j++);
                        dateVo.setSign(-1);
                        dataList.add(dateVo);
                    } else {
                        DateVo dateVo = new DateVo();
                        dateVo.setDate(date++);
                        if (isCurrentDate(date)){
                            dateVo.setSign(2);
                        }else if (isMenseDate(date,days)){
                            dateVo.setSign(1);
                        }else if (isDangerDate(date)){
                            dateVo.setSign(5);
                        }else {
                            dateVo.setSign(0);
                        }
                        dataList.add(dateVo);
                    }
                }
            }
        }
        return dataList;
    }
    private boolean isCurrentDate(int date){
        date = date-1;
        return date==currentDay&&changeMouth==currentMouth&&changeYear ==currentYear;
    }
    private boolean isMenseDate(int date,int mouthDays){
        date=date-1;
        if (date>=menseDay && date<menseDay+menseDays && date<mouthDays){
            return true;
        }
        return false;
    }
    private boolean isDangerDate(int date){
        date=date-1;
        int  dangerBetween= menseBetweenDay-18;
        int maxDangerDate = menseDay-14;
        int minDangerDate = menseDay+14;
        if (date<maxDangerDate && date>maxDangerDate-dangerBetween){
            return true;
        }else if (date>minDangerDate && date<maxDangerDate+dangerBetween){
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        G.log("xxxxxxxxxxxxxxxxxx"+"upx:" + event.getX() + "===" + "dwonx:" + event.getY() + "===");
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                G.log("xxxxxxxxxxxxxxxxxx"+"ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                G.log("xxxxxxxxxxxxxxxxxx"+"ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                G.log("xxxxxxxxxxxxxxxxxx"+"ACTION_MOVE");
                break;
        }
        return false;
    }
}

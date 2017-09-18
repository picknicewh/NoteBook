package com.wh.kotlinnotebook.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.wh.kotlinnotebook.MyApplication;

import java.util.Calendar;

/**
 * 作者： wh
 * 时间：  2017/8/23
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class UserInfo {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private static  UserInfo ourInstance;
    private Calendar calendar;
    public static UserInfo getInstance() {
        if (ourInstance==null){
            ourInstance = new UserInfo();
        }
        return ourInstance;
    }
    public UserInfo() {
        sp  = MyApplication.getInstance().getApplicationContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        editor = sp.edit();
        calendar = Calendar.getInstance();
    }

    public void setSecretYear(int secretYear){
        editor.putInt("secretYear",secretYear);
        editor.commit();
    }
    public int getSecretYear(){
        return sp.getInt("secretYear",calendar.YEAR);
    }

    public void setSecretMouth(int secretMouth){
        editor.putInt("secretMouth",secretMouth);
        editor.commit();
    }
    public int getSecretMouth(){
        return sp.getInt("secretMouth",calendar.MONTH);
    }
    public void setSecretDay(int secretDay){
        editor.putInt("secretDay",secretDay);
        editor.commit();
    }
    public int getSecretDay(){
        return sp.getInt("secretDay",23);
    }
    public void setSecretDate(String secretDate){
        editor.putString("secretDate",secretDate);
        editor.commit();
    }
    public String getSecretDate(){
       return sp.getString("secretDate","");
    }

    public void setSecretDays(int days){
        editor.putInt("secretDays",days);
        editor.commit();
    }
    public int getSecretDays(){
        return sp.getInt("secretDays",-1);
    }

    public void setBetweenDays(int days){
        editor.putInt("betweenDays",days);
        editor.commit();
    }
    public int getBetweenDays(){
        return sp.getInt("betweenDays",-1);
    }
}

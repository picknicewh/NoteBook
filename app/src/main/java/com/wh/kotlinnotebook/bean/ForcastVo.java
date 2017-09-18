package com.wh.kotlinnotebook.bean;

import java.io.Serializable;

/**
 * 作者： wh
 * 时间：  2017/8/28
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ForcastVo  implements Serializable {
    /**
     * temp : 34/26
     * weather : 雷阵雨
     * day : 今天
     * date : 08-25
     * weather_img : d04
     * weather_img_url : http://d.fenfenriji.com/web/act/weather/static/images1/d04.png
     */

    private String temp;
    private String weather;
    private String day;
    private String date;
    private String weather_img;
    private String weather_img_url;

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeather_img() {
        return weather_img;
    }

    public void setWeather_img(String weather_img) {
        this.weather_img = weather_img;
    }

    public String getWeather_img_url() {
        return weather_img_url;
    }

    public void setWeather_img_url(String weather_img_url) {
        this.weather_img_url = weather_img_url;
    }
}

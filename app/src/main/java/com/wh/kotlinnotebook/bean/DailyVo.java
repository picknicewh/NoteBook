package com.wh.kotlinnotebook.bean;

import java.io.Serializable;

/**
 * 作者： wh
 * 时间：  2017/9/16
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class DailyVo implements Serializable {

    private int id;
    private String date;
    private int emotion;
    private int weather;
    private int font_size;
    private int font_color;
    private int content_bg;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getEmotion() {
        return emotion;
    }

    public void setEmotion(int emotion) {
        this.emotion = emotion;
    }

    public int getWeather() {
        return weather;
    }

    public void setWeather(int weather) {
        this.weather = weather;
    }

    public int getFont_size() {
        return font_size;
    }

    public void setFont_size(int font_size) {
        this.font_size = font_size;
    }

    public int getFont_color() {
        return font_color;
    }

    public void setFont_color(int font_color) {
        this.font_color = font_color;
    }

    public int getContent_bg() {
        return content_bg;
    }

    public void setContent_bg(int content_bg) {
        this.content_bg = content_bg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

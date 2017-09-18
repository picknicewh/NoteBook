package com.wh.kotlinnotebook.bean;

import java.io.Serializable;

/**
 * 作者： wh
 * 时间：  2017/8/29
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ClockVo  implements Serializable{
    private int id;
    private String time;
    private int ringtone;
    private String weeks;
    private String remark;
    private int delay;
    private int isOpen;
    private String weekNumbers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRingtone() {
        return ringtone;
    }

    public void setRingtone(int ringtone) {
        this.ringtone = ringtone;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }

    public String getWeekNumbers() {
        return weekNumbers;
    }

    public void setWeekNumbers(String weekNumbers) {
        this.weekNumbers = weekNumbers;
    }
}

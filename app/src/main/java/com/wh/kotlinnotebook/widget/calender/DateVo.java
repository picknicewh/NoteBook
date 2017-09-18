package com.wh.kotlinnotebook.widget.calender;

/**
 * 作者： wh
 * 时间：  2017/8/23
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class DateVo {
    private int date;
    /**
     * 标记为=-1上个月下个月的日子
     *       =0本月正常日子
     *       =1本月月经日子
     *       =2今天的日子
     *       =3选中普通的日子
     *       =4选中的月经日子
     *       =5选中的日子
     */
    private int sign;

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }
}

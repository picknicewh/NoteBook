package com.wh.kotlinnotebook.util;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.database.UserInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 作者： wh
 * 时间：  2017/6/30
 * 名称：日期工具类
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class DateUtil {

    public final static SimpleDateFormat DATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public final static SimpleDateFormat DATE_EMPTY = new SimpleDateFormat("yyyyMMdd");
    public final static SimpleDateFormat DATE = new SimpleDateFormat("yyyy-MM-dd");
    public final static SimpleDateFormat DATE_CHINESE = new SimpleDateFormat("yyyy年MM月dd日");
    public final static SimpleDateFormat format_chinese = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
    public final static SimpleDateFormat TIME = new SimpleDateFormat("HH:mm");
    public final static SimpleDateFormat DATE_MOUTH = new SimpleDateFormat("MM月dd日");
    public final static  int menseDay =  UserInfo.getInstance().getSecretDay();
    public final static  int menseDays = UserInfo.getInstance().getSecretDays();
    public final static  int menseBetweenDay = UserInfo.getInstance().getBetweenDays();

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static Date parseDateTime(String dateStr) {
        Date date = null;
        try {
            date = DATE_TIME.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
        return date;
    }
    /**
     * 获取相对于时间的闹钟
     * @param hour 小时
     * @param  minute 分钟
     */
    public static Calendar getCalender(int hour,int minute){
        //得到日历实例，主要是为了下面的获取时间
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        //获取当前毫秒值
        long systemTime = System.currentTimeMillis();
        // 这里时区需要设置一下，不然可能个别手机会有8个小时的时间差
        mCalendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        //设置在几点提醒
        mCalendar.set(Calendar.HOUR_OF_DAY, hour);
        //设置在几分提醒
        mCalendar.set(Calendar.MINUTE, minute);
        mCalendar.set(Calendar.SECOND, 0);
        mCalendar.set(Calendar.MILLISECOND, 0);
        long selectTime = mCalendar.getTimeInMillis();
        // 如果当前时间大于设置的时间，那么就从第二天的设定时间开始
        if(systemTime > selectTime) {
            mCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return mCalendar;
    }
    /**
     * 是否处于生理期
     */
    public static boolean isMenseDate(int date){
        Calendar calendar = Calendar.getInstance();
        int  year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        if (date>=menseDay && date<menseDay+menseDays && date<getMouthDays(year,month)){
            return true;
        }
        return false;
    }
    /**
     * 是否处于排卵期
     */

    public static boolean isDangerDate(int date){
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
    public static String getSecretTime(int date){
        String secretTime="";
        if (isDangerDate(date)){
            secretTime = "排卵期";
        }else if (isMenseDate(date)){
            secretTime = "月经期";
        }else {
            secretTime = "安全期";
        }
        return secretTime;
    }
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static String formatDateTime(Date date) {
        if (date != null) {
            return DATE_TIME.format(date);
        }
        return "";
    }

    /**
     * yyyy-MM-dd
     */
    public static Date parseDate(String dateStr) {
        Date date = null;
        try {
            date = DATE.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
        return date;
    }

    /**
     * yyyy-MM-dd
     */
    public static String formatDate(Date date) {
        if (date != null) {
            return DATE.format(date);
        }
        return "";
    }

    /**
     * 获取当前时间
     */
    public static String getCurrentDateTime() {
        return formatDateTime(new Date());
    }

    /**
     * 获取当前日期
     */
    public static String getCurrentDate() {
        return formatDate(new Date());
    }

    /**
     * 获取N天后的日期
     *
     * @return
     */
    public static String getNDaysLaterDate(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, n);
        return formatDate(calendar.getTime());
    }

    /**
     * 比较目标时间与当前时间的天数差
     */
    public static int getDaysLeft(String dateStr) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDate(dateStr));//传
        int targetDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.setTime(new Date());//现在
        int currentDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        return targetDayOfYear - currentDayOfYear;
    }

    /**
     * /**
     * 获取传入的date为星期几
     *
     * @param dt
     * @return
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取i天后的工作日
     *
     * @param i 如今天星期4,2或3或4天后就是星期一
     * @return yyyy年MM月dd日 HH时
     */
    public static String nextBusinessDay(int i) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时");
        Date dt = new Date();
        dt = new Date(dt.getTime() + i * 86400000L);
        String s = getWeekOfDate(dt);
        if (s.equals("星期六")) {
            dt.setTime(dt.getTime() + 2 * 86400000L);
        } else if (s.equals("星期日")) {
            dt.setTime(dt.getTime() + 86400000L);
        } else {
            dt.setTime(dt.getTime());
        }
        return sdf.format(dt);
    }

    /**
     * 获取传入的yyyy-MM-dd为星期几
     *
     * @param str
     * @return
     */
    public static String getWeekOfDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取传入的数目为星期几
     *
     * @param week
     * @return
     */
    public static String getWeekOfday(int week) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        if (week < 0)
            week = 0;
        return weekDays[week];
    }

    /**
     * 获取传入的yyyy-MM-dd与今天间隔i天的时间
     *
     * @param i
     * @return
     */
    public static String getIntrventDaysTime(int i) {
        String date;
        long l = new Date().getTime() - (1000 * 60 * 60 * 24 * i);
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        date = sdformat.format(new Date(l));
        return date;
    }

    public static void main(String[] args) {
        System.out.println(getDaysLeft("2015-6-27"));
    }

    /**
     * 获取月份
     */
    public static String getMyMouth(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date(Long.parseLong(time)));
        String mouth = getMouthByChinese(Integer.parseInt(date.substring(5, 7)));
        return mouth;
    }

    /**
     * 获取日期
     */
    public static String getMyDay(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date(Long.parseLong(time)));
        String day = date.substring(8, 10);
        return day;
    }

    /**
     * 获取多少列
     *
     * @param mouth 月
     * @param year  年
     */
    public static int getRows(int year, int mouth) {
        int rows;
        int days = getMouthDays(year, mouth);
        int week = getWeekofday(year, mouth);
        int totledays = week + days;
        rows = totledays / 7;
        if (totledays % 7 != 0) {
            rows = rows + 1;
        }
        return rows;
    }

    public static boolean isEmoji(String string) {
        Pattern p = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(string);
        return m.find();
    }

    /**
     * 获取某年某月的第一天是星期几
     *
     * @param year  年
     * @param month 月
     */
    public static int getWeekofday(int year, int month) {
        int day = 1;
        if (month == 1 || month == 2) {
            month += 12;
            year--;
        }
        int week = (day + 2 * month + 3 * (month + 1) / 5 + year + year / 4 - year / 100 + year / 400) % 7;
        return week + 1;
    }

    /**
     * 判读是否是闰年
     *
     * @param year 年
     */
    private static boolean isleap(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {//是闰年
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取某个月多少天
     *
     * @param year  年
     * @param mouth 月
     */
    public static int getMouthDays(int year, int mouth) {
        int days = 0;
        Log.i("ssssss", "year:" + year + "====" + "mouth:" + mouth);
        switch (mouth) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            case 2:
                if (isleap(year)) {
                    days = 29;
                } else {
                    days = 28;
                }
                break;
        }
        return days;
    }

    public static String getMouthByChinese(int mouth) {
        String s = "1月";
        switch (mouth) {
            case 1:
                s = "1月";
                break;
            case 2:
                s = "2月";
                break;
            case 3:
                s = "3月";
                break;
            case 4:
                s = "4月";
                break;
            case 5:
                s = "5月";
                break;
            case 6:
                s = "6月";
                break;
            case 7:
                s = "7月";
                break;
            case 8:
                s = "8月";
                break;
            case 9:
                s = "9月";
                break;
            case 10:
                s = "10月";
                break;
            case 11:
                s = "11月";
                break;
            case 12:
                s = "12月";
                break;
        }
        return s;
    }

    public static List<String> getDayList() {
        List<String> dayList = new ArrayList<>();
        for (int i = 0; i <= 24; i++) {
            if (i < 10) {
                dayList.add("0" + i);
            } else {
                dayList.add(String.valueOf(i));
            }
        }
        return dayList;
    }

    public static List<String> getWholeTime() {
        List<String> dayList = new ArrayList<>();
        dayList.add("00");
        dayList.add("30");
        return dayList;

    }

    /**
     * 计算文字的长度，如果文字的长度大于100则不能再输入
     *
     * @param et_content 编辑框
     * @param tv_count   计数显示view
     */
    public static void setEditContent(final EditText et_content, final TextView tv_count) {
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Editable editable = et_content.getText();
                int len = editable.length();

                if (len > 140) {
                    int selEndIndex = Selection.getSelectionEnd(editable);
                    String str = editable.toString();
                    //截取新字符串
                    String newStr = str.substring(0, 140);
                    et_content.setText(newStr);
                    editable = et_content.getText();
                    //新字符串的长度
                    int newLen = editable.length();
                    //旧光标位置超过字符串长度
                    if (selEndIndex > newLen) {
                        selEndIndex = editable.length();
                    }
                    //设置新光标所在的位置
                    Selection.setSelection(editable, selEndIndex);

                } else {
                    tv_count.setText(len + "/140");
                }

            }

        });
    }

    /**
     * 是否为全空格
     */
    public static boolean isallsapce(EditText et_sign) {
        String sign = et_sign.getText().toString();
        char[] arr = sign.toCharArray();
        int length = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ' ') {
                length++;
            }
        }
        if (length == arr.length) {
            sign = "";
            et_sign.setText(sign);
            return true;
        }
        return false;
    }

    public static String getFromatDate(int year, int mouth, int day) {
        String m;
        String d;
        String date;

        if (mouth < 10) {
            m = "0" + mouth;
        } else {
            m = "" + mouth;
        }
        if (day < 10) {
            d = "0" + day;
        } else {
            d = "" + day;
        }
        date = year + "-" + m + "-" + d;
        return date;
    }

    /**
     * 获取最近的时间
     */
    public static String getLastUpdateTime() {
        String text;
        long time = System.currentTimeMillis();
        SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        if (0 == time) {
            text = "";
        } else {
            text = mDateFormat.format(new Date(time));
        }
        return text;
    }
    /**
     * 以yyyy-MM-dd格式获取日期
     * @param date 日期
     */
    public static  String  getFormatDate(Date date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
    /**
     * 以yyyy-MM-dd格式获取与今天相差的days天的字符串数据
     * @param date 日期
     *@param days 相隔天数
     */
    public static  String  getFormatDateBetweenDays(Date date,int days){
        long between = days*24*60*60*1000;
        long betweenTime = date.getTime() -between;
        Date mDate = new Date(betweenTime);
        return new SimpleDateFormat("yyyy-MM-dd").format(mDate);
    }
    /**
     * 以yyyy-MM-dd格式获取与今天相差的days天的字符串数据
     * @param date 日期
     *@param days 相隔天数
     */
    public static  String  getFormatDateDays(Date date,int days){
        long between = days*24*60*60*1000;
        long betweenTime = date.getTime() -between;
        Date mDate = new Date(betweenTime);
        return new SimpleDateFormat("yyyyMMdd").format(mDate);
    }

    /**
     * 以yyyy-MM-dd格式获取与今天相差的days天的日期
     * @param date 日期
     *@param days 相隔天数
     */
    public static  Date  getDateBetweenDays(Date date,int days){
        long between = days*24*60*60*1000;
        long betweenTime = date.getTime() -between;
        Date mDate = new Date(betweenTime);
        return mDate;
    }
    /**
     * 日期选择对话框
     * @param  title 标题
     *@param  context 上下文
     * @param  listener 回掉监听
     */
    public static TimePickerView getTimePickerView(String title, Context context, Calendar calendar, TimePickerView.OnTimeSelectListener listener) {
        TimePickerView.Builder builder = new TimePickerView.Builder(context, listener);
        builder.setType(getType())
                .setLabel("","","", "", "", "")
                .setCancelColor(ContextCompat.getColor(context, R.color.colorAccent))
                .setSubmitColor(ContextCompat.getColor(context, R.color.colorAccent))
                .setTitleText(title)
                .isCyclic(true)//是否循环滚动
                .setContentSize(16);
        TimePickerView timePickerView = builder.build();
        timePickerView.setDate(calendar);
        return timePickerView;
    }
    private static boolean[] getType() {
        boolean[] type = new boolean[6];
        for (int i = 0; i < 6; i++) {
            if (i >2) {
                type[i] = false;
            } else {
                type[i] = true;
            }
        }
        return type;
    }
    /**
     * 选择选项对话框
     * @param options 选项列表
     * @param  title 标题
     * @param  context 上下文
     *@param  listener 回调监听
     */
    public static OptionsPickerView getOptionPickerView(String title, List<String> options,int position, Context context, OptionsPickerView.OnOptionsSelectListener listener) {
        OptionsPickerView.Builder builder = new OptionsPickerView.Builder(context,listener);
        builder.setCancelColor(ContextCompat.getColor(context, R.color.colorAccent))
                .setSubmitColor(ContextCompat.getColor(context, R.color.colorAccent))
                .setTitleText(title)
                .setSelectOptions(position)
                .setCyclic(false,false,false);
        OptionsPickerView optionsPickerView =  builder.build();
        optionsPickerView.setPicker(options);
        return optionsPickerView;
    }


}

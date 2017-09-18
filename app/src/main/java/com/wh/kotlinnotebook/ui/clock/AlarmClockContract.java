package com.wh.kotlinnotebook.ui.clock;

import com.wh.kotlinnotebook.base.BaseView;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/8/24
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class AlarmClockContract {
    public interface  View  extends BaseView{

    }
    public interface Presenter{
        List<String> getHour();
        List<String> getDates();
        String getFormatTime(int hour ,int minute );
    }
}

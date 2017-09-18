package com.wh.kotlinnotebook.ui.home;

import com.wh.kotlinnotebook.bean.CityWeatherVo;
import com.wh.kotlinnotebook.bean.DailyReadVo;
import com.wh.kotlinnotebook.ui.home.daily.DailyWordContract;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/8/24
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class HomeContract {
    public interface  View extends DailyWordContract.View{
       //  void setCityVo(CityVo cityVo);
         void setCityWeather(CityWeatherVo cityWeather);
        void setDailyReadVo(List<DailyReadVo> dailyReadVo);
         String getCityId();
    }
    public interface Presenter extends DailyWordContract.Presenter{
        void getCityVo();
        void getCityWeather();
        void getDailyRead();
    }
}

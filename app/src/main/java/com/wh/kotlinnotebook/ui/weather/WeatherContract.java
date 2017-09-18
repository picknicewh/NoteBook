package com.wh.kotlinnotebook.ui.weather;

import com.wh.kotlinnotebook.base.BasePresenter;
import com.wh.kotlinnotebook.base.BaseView;
import com.wh.kotlinnotebook.bean.CityWeatherVo;

/**
 * 作者： wh
 * 时间：  2017/8/24
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class WeatherContract {
    public interface  View extends BaseView{
          String getCityId();
     //    void setCityVo(CityVo cityVo);
         void setCityWeather(CityWeatherVo cityWeather);
    }
    public interface Presenter extends BasePresenter{
        void getCityVo();
        void getCityWeather();

    }
}

package com.wh.kotlinnotebook.network;


import com.wh.kotlinnotebook.bean.CityVo;
import com.wh.kotlinnotebook.bean.CityWeatherVo;
import com.wh.kotlinnotebook.bean.DailyReadVo;
import com.wh.kotlinnotebook.bean.DailyWordVo;
import com.wh.kotlinnotebook.bean.LatestStoryVo;
import com.wh.kotlinnotebook.bean.StoryDetail;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者： wh
 * 时间：  2017/8/21
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public interface ApiHttpService {

    /*** 获取每人一语*/
     @GET("activity/wisdoms?")
     Observable<List<DailyWordVo>> getDailyVo(@Query("start") String start, @Query("end") String end);

    /**获取定位城市*/
    @GET("v3/weather/cityidbyip")
    Observable<CityVo> getCityVo();

    /**获取定位城市*/
    @GET("v3/weather/detail?")
    Observable<CityWeatherVo> getCityWeather(@Query("cityid") String cityid);

    /**获取每日必读*/
    @GET("activity/recommend")
    Observable<List<DailyReadVo>> getDailyRead();

    /**获取今天（最新的）的故事列表*/
    @GET("api/4/stories/latest")
    Observable<LatestStoryVo>  getSubject();

    /**获取今天之前的故事列表*/
    @GET("api/4/stories/before/{date}")
    Observable<LatestStoryVo> getSubjectByDate(@Path("date") String  date);


    /**获取故事详情*/
    @GET("api/4/story/{storyId}")
    Observable<StoryDetail> getStoryDetail(@Path("storyId") int storyId);




}

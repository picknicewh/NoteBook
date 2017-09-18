package com.wh.kotlinnotebook.ui.weather;

import android.content.Context;

import com.wh.kotlinnotebook.bean.CityVo;
import com.wh.kotlinnotebook.bean.CityWeatherVo;
import com.wh.kotlinnotebook.network.ApiManager;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/8/24
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class WeatherPresenter implements WeatherContract.Presenter {
    private WeatherContract.View view;
    private Context context;
    private Subscription mSubscription;
    public WeatherPresenter(WeatherContract.View view, Context context){
        this.view = view;
        this.context = context;
    }
    @Override
    public void subscribe() {
    }
    @Override
    public void unSubscribe() {
      if (mSubscription!=null && !mSubscription.isUnsubscribed()){
          mSubscription.unsubscribe();
      }
    }


    @Override
    public void getCityVo() {
        view.showProgress();
        mSubscription = ApiManager.getApiManagerService().
                getCityVo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CityVo>() {
                    @Override
                    public void onCompleted() {
                        view.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();
                        view.showMessage(e.getMessage());
                    }
                    @Override
                    public void onNext(CityVo cityVo) {
                        view.hideProgress();
                        if (cityVo!=null){
                          //  view.setCityVo(cityVo);
                           //  getCityWeather();
                        }
                    }
                });
    }

    @Override
    public void getCityWeather() {
        view.showProgress();
        mSubscription = ApiManager.getApiManagerService().
                getCityWeather(view.getCityId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CityWeatherVo>() {
                    @Override
                    public void onCompleted() {
                        view.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();
                        view.showMessage(e.getMessage());
                    }
                    @Override
                    public void onNext(CityWeatherVo cityWeatherVo) {
                        view.hideProgress();
                        if (cityWeatherVo!=null){
                            view.setCityWeather(cityWeatherVo);
                        }
                    }
                });
    }

}

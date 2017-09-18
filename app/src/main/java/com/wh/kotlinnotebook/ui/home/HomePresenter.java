package com.wh.kotlinnotebook.ui.home;

import android.content.Context;

import com.wh.kotlinnotebook.bean.CityVo;
import com.wh.kotlinnotebook.bean.CityWeatherVo;
import com.wh.kotlinnotebook.bean.DailyReadVo;
import com.wh.kotlinnotebook.bean.DailyWordVo;
import com.wh.kotlinnotebook.network.ApiManager;

import java.util.List;

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
public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View view;
    private Context context;
    private Subscription mSubscription;
    public HomePresenter(HomeContract.View view,Context context){
        this.view = view;
        this.context = context;
    }
    @Override
    public void subscribe() {
        getDailyWordVo();
    }
    @Override
    public void unSubscribe() {
      if (mSubscription!=null && !mSubscription.isUnsubscribed()){
          mSubscription.unsubscribe();
      }
    }

    @Override
    public void getDailyWordVo() {
        view.showProgress();
        mSubscription = ApiManager.getApiManagerService().
                getDailyVo(view.getStartDate(),view.getEndDate())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DailyWordVo>>() {
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
                    public void onNext(List<DailyWordVo> dailyWordVos) {
                        view.hideProgress();
                       if (dailyWordVos!=null){
                           view.setDailyWordVo(dailyWordVos);
                       }
                    }
                });
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

    @Override
    public void getDailyRead() {
        view.showProgress();
        mSubscription = ApiManager.getApiManagerService().
                getDailyRead()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DailyReadVo>>() {
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
                    public void onNext(List<DailyReadVo> dailyReadVo) {
                        view.hideProgress();
                        if (dailyReadVo!=null){
                           view.setDailyReadVo(dailyReadVo);
                        }
                    }
                });
    }
}

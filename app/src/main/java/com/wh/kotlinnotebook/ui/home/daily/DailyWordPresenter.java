package com.wh.kotlinnotebook.ui.home.daily;

import android.content.Context;

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
public class DailyWordPresenter implements DailyWordContract.Presenter {
    private DailyWordContract.View view;
    private Context context;
    private Subscription mSubscription;
    public DailyWordPresenter(DailyWordContract.View view, Context context){
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
}

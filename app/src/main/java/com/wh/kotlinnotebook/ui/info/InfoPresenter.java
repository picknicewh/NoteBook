package com.wh.kotlinnotebook.ui.info;


import com.wh.kotlinnotebook.bean.LatestStoryVo;
import com.wh.kotlinnotebook.network.ZhihuApiManager;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者： wh
 * 时间：  2017/8/28
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class InfoPresenter implements InfoContract.Presenter {
    private InfoContract.View view;
    private Subscription mSubscription;
    public InfoPresenter(InfoContract.View view){
        this.view = view;
    }
    @Override
    public void getConsultList() {
        view.showProgress();
        mSubscription = ZhihuApiManager.getApiManagerService().
                getSubjectByDate(view.getDate())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LatestStoryVo>() {
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
                    public void onNext(LatestStoryVo latestStoryVo) {
                        view.hideProgress();
                        if (latestStoryVo!=null){
                            view.setLatestStoryVo(latestStoryVo);
                        }
                    }
                });
    }

    @Override
    public void subscribe() {
        getConsultList();

    }

    @Override
    public void unSubscribe() {
      if (mSubscription!=null&&!mSubscription.isUnsubscribed()){
          mSubscription.unsubscribe();
      }
    }
}

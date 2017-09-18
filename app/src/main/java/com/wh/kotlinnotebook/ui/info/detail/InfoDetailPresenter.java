package com.wh.kotlinnotebook.ui.info.detail;


import com.wh.kotlinnotebook.bean.StoryDetail;
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
public class InfoDetailPresenter implements InfoDetailContract.Presenter {
    private InfoDetailContract.View view;
    private Subscription mSubscription;
    public InfoDetailPresenter(InfoDetailContract.View view){
        this.view = view;
    }



    @Override
    public void subscribe() {
        getStoryDetail();

    }

    @Override
    public void unSubscribe() {
      if (mSubscription!=null&&!mSubscription.isUnsubscribed()){
          mSubscription.unsubscribe();
      }
    }

    @Override
    public void getStoryDetail() {
        view.showProgress();
        mSubscription = ZhihuApiManager.getApiManagerService().
                getStoryDetail(view.getStoryId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StoryDetail>() {
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
                    public void onNext(StoryDetail storyDetail) {
                        view.hideProgress();
                        if (storyDetail!=null){
                            view.setStoryDetailVo(storyDetail);
                        }
                    }
                });
    }
}

package com.wh.kotlinnotebook.ui.info;

import com.wh.kotlinnotebook.base.BasePresenter;
import com.wh.kotlinnotebook.base.BaseView;
import com.wh.kotlinnotebook.bean.LatestStoryVo;


/**
 * 作者： wh
 * 时间：  2017/8/24
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class InfoContract {
    public interface  View extends BaseView{
        void setLatestStoryVo( LatestStoryVo latestStoryList);
        String getDate();
    }
    public interface Presenter extends BasePresenter{
        void getConsultList();
    }
}

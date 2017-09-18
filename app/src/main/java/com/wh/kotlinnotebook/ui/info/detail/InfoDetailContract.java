package com.wh.kotlinnotebook.ui.info.detail;

import com.wh.kotlinnotebook.base.BasePresenter;
import com.wh.kotlinnotebook.base.BaseView;
import com.wh.kotlinnotebook.bean.StoryDetail;


/**
 * 作者： wh
 * 时间：  2017/8/24
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class InfoDetailContract {
    public interface  View extends BaseView{
        void setStoryDetailVo(StoryDetail storyDetail);
        int getStoryId();
    }
    public interface Presenter extends BasePresenter{
       void   getStoryDetail();
    }
}

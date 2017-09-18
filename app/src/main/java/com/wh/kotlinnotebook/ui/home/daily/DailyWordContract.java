package com.wh.kotlinnotebook.ui.home.daily;

import com.wh.kotlinnotebook.base.BasePresenter;
import com.wh.kotlinnotebook.base.BaseView;
import com.wh.kotlinnotebook.bean.DailyWordVo;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/8/25
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class DailyWordContract {
    public interface  View extends BaseView {
        String getStartDate();
        String getEndDate();
        void setDailyWordVo(List<DailyWordVo> dailyWordVo);

    }
    public interface Presenter extends BasePresenter {
        void getDailyWordVo();
    }
}

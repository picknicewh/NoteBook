package com.wh.kotlinnotebook.ui.about;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.base.BaseFragment;

/**
 * 作者： wh
 * 时间：  2017/8/21
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class AboutFragment extends BaseFragment {
    @Override
    public void setTop() {
        isHideNavigation(false);
    }

    @Override
    public void initData() {
       setTitle("关于");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about;
    }
}

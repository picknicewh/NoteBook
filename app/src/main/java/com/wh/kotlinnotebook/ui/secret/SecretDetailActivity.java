package com.wh.kotlinnotebook.ui.secret;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.base.BaseActivity;

import java.util.Calendar;

/**
 * 作者： wh
 * 时间：  2017/8/23
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class SecretDetailActivity extends BaseActivity {
    @Override
    public void setTop() {
        isHideNavigation(false);
    }

    @Override
    public void initData() {
        setTitle("小秘密");
    }

    @Override
    public void setTooBar() {
        super.setTooBar();
        Calendar calendar = Calendar.getInstance();
        String title = calendar.get(Calendar.YEAR)+"年"+(calendar.get(Calendar.MONTH)+1)+"月";
        setTitle(title);
        setLeftImage(R.mipmap.ic_back);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_calender;
    }
}

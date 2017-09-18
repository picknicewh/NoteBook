package com.wh.kotlinnotebook.ui.secret;

import android.content.Intent;
import android.widget.TextView;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.base.BaseFragment;
import com.wh.kotlinnotebook.util.DateUtil;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： wh
 * 时间：  2017/8/23
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class SecretDetailFragment extends BaseFragment {
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_between_days)
    TextView tvBetweenDays;
    @Override
    public void setTop() {
        isHideNavigation(false);
    }

    @Override
    public void initData() {
        setTitle("小秘密");
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH)+1;
        String title =  "今天是"+month+"月"+day+"日"+"处于";
        tvDate.setText(title);
        tvTime.setText(DateUtil.getSecretTime(day));
        int betweenDays = DateUtil.menseBetweenDay-(day-DateUtil.menseDay);
        tvBetweenDays.setText(String.valueOf(betweenDays));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_secret_detail;
    }




    @OnClick(R.id.rl_days)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(),SecretDetailActivity.class);
        startActivity(intent);
    }
}

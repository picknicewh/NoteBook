package com.wh.kotlinnotebook.ui.secret;

import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.base.BaseFragment;
import com.wh.kotlinnotebook.database.UserInfo;
import com.wh.kotlinnotebook.util.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： wh
 * 时间：  2017/8/21
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class SecretFragment extends BaseFragment implements TimePickerView.OnTimeSelectListener, OptionsPickerView.OnOptionsSelectListener {
    /**
     * 日期
     */
    @BindView(R.id.tv_date)
    TextView tvDate;
    /**
     * 时间
     */
    @BindView(R.id.tv_days)
    TextView tvDays;
    /**
     * 时间间隔
     */
    @BindView(R.id.tv_between_days)
    TextView tvBetweenDays;
    /**
     * 选择日期
     */
    private String  currentDate="";
    /**
     * 选择的间隔时间
     */
    private int betweenDays=6;
    /**
     * 选择天数
     */
    private int days=28;
    /**
     * 记录选中开始日期
     */
    private Calendar currentCalendar = Calendar.getInstance();
    /**
     * 月经天数选择为位置
     */
    private int daysPosition;
    /**
     * 月经周期数选择位置
     */
    private int betweenPosition;
    /**
     * 月经天数选项
     */
    private OptionsPickerView daysOptionPickerView;
    /**
     * 月经周期数选项
     */
    private OptionsPickerView betweenOptionPickerView;
    /**
     *日期选择
     */
    private TimePickerView datePickerView;
    /**
     * 月经天数选择列表
     */
    private List<String> daysList;
    /**
     * 月经周期数选择列表
     */
    private List<String> betweenList;
    /**
     * 用户信息
     */
    private UserInfo userInfo;
    /**
     * 回调监听
     */
    private  ReplaceFragmentListener  replaceFragmentListener;
    @Override
    public void setTop() {
        isHideNavigation(false);
    }

    @Override
    public void setTooBar() {
        super.setTooBar();
        setTitle("小秘密---经期预测");
    }

    @Override
    public void initData() {
        userInfo= UserInfo.getInstance();
        daysList = new ArrayList<>();
        betweenList = new ArrayList<>();
        for (int i =2;i<15;i++){
            daysList.add(String.valueOf(i));
        }
        for (int i = 16 ;i<33;i++){
            betweenList.add(String.valueOf(i));
        }
        daysOptionPickerView= DateUtil.getOptionPickerView("请选择经期天数", daysList, daysPosition, getActivity(),this);
        betweenOptionPickerView= DateUtil.getOptionPickerView("请选择周期天数", betweenList, betweenPosition, getActivity(),sourceListener);
        datePickerView =    DateUtil.getTimePickerView("请选择日期", getActivity(),currentCalendar,this);
    }
    private   OptionsPickerView.OnOptionsSelectListener  sourceListener = new OptionsPickerView.OnOptionsSelectListener() {
        @Override
        public void onOptionsSelect(int options1, int options2, int options3, View v) {
            betweenPosition = options1;
            tvBetweenDays.setText(betweenList.get(options1)+"天");
            betweenDays = Integer.parseInt(betweenList.get(options1));

        }
    };
    @Override
    public void onOptionsSelect(int options1, int options2, int options3, View v) {
        daysPosition = options1;
        tvDays.setText(daysList.get(daysPosition)+"天");
        days = Integer.parseInt(daysList.get(options1));
    }
    @Override
    public void onTimeSelect(Date date, View v) {
        currentCalendar.setTime(date);
        currentDate= DateUtil.getFormatDate(date);
        tvDate.setText(currentDate);
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_secret;
    }
    @OnClick({R.id.ll_date, R.id.ll_days, R.id.ll_between_days, R.id.tv_conform})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_date:
                datePickerView.show();
                break;
            case R.id.ll_days:
                daysOptionPickerView.show();
                break;
            case R.id.ll_between_days:
                betweenOptionPickerView.show();
                break;
            case R.id.tv_conform:
                userInfo.setBetweenDays(betweenDays);
                userInfo.setSecretDays(days);
                userInfo.setSecretDate(currentDate);
                userInfo.setSecretYear(currentCalendar.get(Calendar.YEAR));
                userInfo.setSecretMouth(currentCalendar.get(Calendar.MONTH)+1);
                userInfo.setSecretDay(currentCalendar.get(Calendar.DAY_OF_MONTH));
                if (replaceFragmentListener!=null){
                    replaceFragmentListener.replace();
                }
                break;
        }
    }


    public void setReplaceFragmentListener(ReplaceFragmentListener replaceFragmentListener) {
        this.replaceFragmentListener = replaceFragmentListener;
    }

    public interface  ReplaceFragmentListener{
        void replace();
    }
}

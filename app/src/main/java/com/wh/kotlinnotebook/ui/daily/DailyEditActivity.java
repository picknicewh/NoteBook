package com.wh.kotlinnotebook.ui.daily;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.base.BaseActivity;
import com.wh.kotlinnotebook.bean.DailyVo;
import com.wh.kotlinnotebook.common.Consist;
import com.wh.kotlinnotebook.database.DailyDb;
import com.wh.kotlinnotebook.util.DateUtil;
import com.wh.kotlinnotebook.util.G;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class DailyEditActivity extends BaseActivity implements DailyImageFragment.OnBgChooseClickListener, TimePickerView.OnTimeSelectListener, DailyFontFragment.OnFontColorClickListener, DailyFontFragment.OnFontSizeClickListener {

    /**
     * 设置下的内容
     */
    @BindView(R.id.fl_mcontent)
    FrameLayout flContent;
    /**
     * 根目录
     */
    @BindView(R.id.ll_mcontent)
    LinearLayout llContent;
    @BindView(R.id.et_daily)
    EditText etDaily;
    /**
     * 天气
     */
    @BindView(R.id.iv_weather_image)
    ImageView ivWeatherImage;
    /**
     * 心情
     */
    @BindView(R.id.iv_emotion_image)
    ImageView ivEmotionImage;
    /**
     * 日期
     */
    @BindView(R.id.tv_date)
    TextView tvDate;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    /**
     * 图片选择布局//天气、表情、背景
     */
    private DailyImageFragment dailyBgFragment;
    /**
     * 字体选布局
     */
    private DailyFontFragment dailyFontFragment;
    private Bundle bundle;
    /**
     * 时间选择器
     */
    private TimePickerView timePickerView;
    /**
     * 当前选择日历的时间
     */
    private Calendar currentCalender;

    private DailyDb dailyDb;
    /**
     * 默认字体大小
     */
    private int fontSize=14;
    /**
     * 默认字体颜色
     */
    private int fontColor=R.color.black;
    /**
     * 默认时间戳
     */
    private String dateStamp= String.valueOf(System.currentTimeMillis());
    /**
     * 默认背景颜色
     */
    private int bgColor= -16181;
    /**
     * 默认表情
     */
    private int  emotion = R.mipmap.ic_cute;
    /**
     * 默认天气
     */
    private int weather = R.mipmap.ic_sunny;
    private String content;
    /**
     *编辑日记
     */
    private DailyVo dailyVo;
    /**
     * 操作编辑
     */
    private int operation;
    private int id;
    @Override
    public int getLayoutId() {
        return R.layout.activity_daily_edit;
    }

    @Override
    public void setTop() {
        isHideNavigation(false);
    }

    @Override
    public void initData() {
        setLeftImage(R.mipmap.ic_back);
        setTitle("编写日历");
        setSubTitle("保存");
        dailyDb  = DailyDb.getInstance();
        fragmentManager = getSupportFragmentManager();
        initOperation();
        timePickerView = DateUtil.getTimePickerView("选择时间", this, currentCalender, this);
        dailyFontFragment = new DailyFontFragment();
        dailyFontFragment.setOnFontColorClickListener(this);
        dailyFontFragment.setOnFontSizeClickListener(this);
    }
    private void initOperation(){
        operation = getIntent().getIntExtra("operation",-1);
        switch (operation){
            case Consist.REQUEST_ADD:
                currentCalender = Calendar.getInstance();
                tvDate.setText(DateUtil.DATE_CHINESE.format(new Date(System.currentTimeMillis())));
                break;
            case Consist.REQUEST_EDIT:
                DailyVo dailyVo = (DailyVo) getIntent().getSerializableExtra("dailyVo");
                etDaily.setText(dailyVo.getContent());
                etDaily.setTextSize(dailyVo.getFont_size());
                etDaily.setTextColor(dailyVo.getFont_color());
                dateStamp = dailyVo.getDate();
                fontSize = dailyVo.getFont_size();
                fontColor = dailyVo.getFont_color();
                emotion = dailyVo.getEmotion();
                weather = dailyVo.getWeather();
                bgColor = dailyVo.getContent_bg();
                content = dailyVo.getContent();
                id = dailyVo.getId();
                Date date = new Date(Long.parseLong(dateStamp));
                currentCalender.setTime(date);
                tvDate.setText(DateUtil.DATE_CHINESE.format(date));
                ivEmotionImage.setImageResource(emotion);
                ivWeatherImage.setImageResource(weather);
                etDaily.setBackgroundColor(bgColor);
                llContent.setBackgroundColor(bgColor);
                G.log("xxxxxxxxxx emotion="+emotion);
                G.log("xxxxxxxxxx weather="+weather);
                G.log("xxxxxxxxxx bgColor="+bgColor);
                break;
        }
    }
    @OnClick(R.id.iv_weather)
    public void weather() {
        transaction = fragmentManager.beginTransaction();
        dailyBgFragment = new DailyImageFragment();
        if (flContent.getVisibility() == View.VISIBLE) {
            flContent.setVisibility(View.GONE);
            transaction = null;
            bundle = null;
            dailyBgFragment = null;
        } else {
            flContent.setVisibility(View.VISIBLE);
            dailyBgFragment.setOnImageChooseClickListener(this);
            bundle = new Bundle();
            bundle.putString("source", "weather");
            dailyBgFragment.setArguments(bundle);
            transaction.replace(R.id.fl_mcontent, dailyBgFragment);
            transaction.commit();
        }

    }

    @OnClick(R.id.iv_emotion)
    public void emotion() {
        dailyBgFragment = new DailyImageFragment();
        transaction = fragmentManager.beginTransaction();
        if (flContent.getVisibility() == View.VISIBLE) {
            flContent.setVisibility(View.GONE);
            dailyBgFragment = null;
            transaction = null;
            bundle = null;
        } else {
            flContent.setVisibility(View.VISIBLE);
            dailyBgFragment.setOnImageChooseClickListener(this);
            bundle = new Bundle();
            bundle.putString("source", "emotion");
            dailyBgFragment.setArguments(bundle);
            transaction.replace(R.id.fl_mcontent, dailyBgFragment);
            transaction.commit();
        }
    }

    @OnClick(R.id.iv_bg)
    public void bg() {
        dailyBgFragment = new DailyImageFragment();
        transaction = fragmentManager.beginTransaction();
        if (flContent.getVisibility() == View.VISIBLE) {
            flContent.setVisibility(View.GONE);
            transaction = null;
            dailyBgFragment = null;
        } else {
            flContent.setVisibility(View.VISIBLE);
            dailyBgFragment.setOnImageChooseClickListener(this);
            bundle = new Bundle();
            bundle.putString("source", "bg");
            dailyBgFragment.setArguments(bundle);
            transaction.replace(R.id.fl_mcontent, dailyBgFragment);
            transaction.commit();
        }
    }

    @OnClick(R.id.iv_font)
    public void font() {
        transaction = fragmentManager.beginTransaction();
        if (flContent.getVisibility() == View.VISIBLE) {
            flContent.setVisibility(View.GONE);
            transaction = null;
            bundle = null;
        } else {
            flContent.setVisibility(View.VISIBLE);
            transaction.replace(R.id.fl_mcontent, dailyFontFragment);
            transaction.commit();

        }
    }

    @Override
    public void onImageChoose(String source, int image) {
        if (source.equals("bg")) {
            llContent.setBackgroundColor(image);
            etDaily.setBackgroundColor(image);
            bgColor = image;
              G.log("xxxxxxxxxx bgColor="+image);
        } else if (source.equals("emotion")) {
            ivEmotionImage.setImageResource(image);
            emotion = image;
            G.log("xxxxxxxxxx emotion="+image);
        } else if (source.equals("weather")) {
            ivWeatherImage.setImageResource(image);
            weather = image;
            G.log("xxxxxxxxxx emotion="+image);
        }
    }

    @Override
    public void onTimeSelect(Date date, View view) {
        currentCalender.setTime(date);
        String dateString = DateUtil.DATE_CHINESE.format(date);
        tvDate.setText(dateString);
        dateStamp = String.valueOf(date.getTime());
    }

    @OnClick(R.id.tv_date)
    public void date() {
        timePickerView.show();
    }
    @Override
    public void onColorClick(int color) {
        this.fontSize =fontColor;
        etDaily.setTextColor(color);
    }
    @Override
    public void onSizeClick(int fontSize) {
        this.fontSize = fontSize;
        etDaily.setTextSize(fontSize);
    }

    @Override
    public void setSubTitleListener() {
        super.setSubTitleListener();
         content =  etDaily.getText().toString();
        G.log("xxxxxxxxxx emotion="+emotion);
        G.log("xxxxxxxxxx weather="+weather);
        G.log("xxxxxxxxxx bgColor="+bgColor);
        if (G.isEmteny(content)){
            showMessage("编辑日志内容不能为空！");
            return;
        }
        if (operation==Consist.REQUEST_ADD){
            dailyDb.insert(dateStamp,emotion,weather,fontSize,fontColor,bgColor,content);
            showMessage("插入成功！");
        }else {
            dailyDb.updateData(id,dateStamp,emotion,weather,fontSize,fontColor,bgColor,content);
            showMessage("修改成功！");
        }
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
}

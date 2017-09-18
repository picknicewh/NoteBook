package com.wh.kotlinnotebook.ui.weather;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.base.BaseActivity;
import com.wh.kotlinnotebook.bean.CityWeatherVo;
import com.wh.kotlinnotebook.bean.ForcastVo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WeatherActivity extends BaseActivity implements WeatherContract.View {
    @BindView(R.id.tv_weather)
    TextView tvWeather;
    @BindView(R.id.tv_weather_status)
    TextView tvWeatherStatus;
    @BindView(R.id.tv_weather_quality)
    TextView tvWeatherQuality;
    @BindView(R.id.rv_weather)
    RecyclerView rvWeather;
    @BindView(R.id.tv_loacation)
    TextView tvLoacation;
    private List<ForcastVo> forcastVoList;
    private WeatherAdapter adapter;
    private WeatherPresenter presenter;

    @Override
    public void setTop() {
        isHideNavigation(true);
    }

    @Override
    public void initData() {
        forcastVoList = new ArrayList<>();
        adapter = new WeatherAdapter(this, forcastVoList);
        rvWeather.setLayoutManager(new LinearLayoutManager(this));
        rvWeather.setFocusable(false);
        rvWeather.setAdapter(adapter);
        presenter = new WeatherPresenter(this, this);
        presenter.getCityWeather();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_weather;
    }


    @OnClick(R.id.iv_left)
    public void left() {
        finish();
    }

    @Override
    public String getCityId() {
        return cityId;
    }

    private String cityId = "101210101";


    @Override
    public void setCityWeather(CityWeatherVo cityWeather) {
        tvWeather.setText(cityWeather.getCurrent().getTemp());
        tvWeatherStatus.setText(cityWeather.getCurrent().getWeather());
        tvLoacation.setText(cityWeather.getCity());
        forcastVoList.clear();
        forcastVoList.addAll(cityWeather.getForcast());
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

}

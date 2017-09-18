package com.wh.kotlinnotebook.ui.home;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.base.BaseFragment;
import com.wh.kotlinnotebook.bean.CityWeatherVo;
import com.wh.kotlinnotebook.bean.DailyReadVo;
import com.wh.kotlinnotebook.bean.DailyWordVo;
import com.wh.kotlinnotebook.common.Consist;
import com.wh.kotlinnotebook.network.GlideUtils;
import com.wh.kotlinnotebook.ui.clock.AlarmClockListActivity;
import com.wh.kotlinnotebook.ui.daily.DailyListActivity;
import com.wh.kotlinnotebook.ui.home.daily.DailyWordDetailActivity;
import com.wh.kotlinnotebook.ui.weather.WeatherActivity;
import com.wh.kotlinnotebook.util.DateUtil;

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
public class HomeFragment extends BaseFragment implements HomeContract.View {
    /**
     * 温度
     */
    @BindView(R.id.tv_temprature)
    TextView tvTemprature;
    /**
     * 地点
     */
    @BindView(R.id.tv_station)
    TextView tvStation;
    /**
     * 日期
     */
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.ll_calender)
    LinearLayout llCalender;
    /**
     * 每日一语的图片
     */
    @BindView(R.id.iv_daily_image)
    ImageView ivDailyImage;
    /**
     * 每日一语的文字
     */
    @BindView(R.id.tv_daily_text)
    TextView tvDailyText;
    @BindView(R.id.iv_weather_image)
    ImageView ivWeatherImage;
    /**
     * 每日必读的图片
     */
    @BindView(R.id.tv_daily_read_image)
    ImageView tvDailyReadImage;
    /**
     * 每日必读的文字
     */
    @BindView(R.id.tv_daily_read_content)
    TextView tvDailyReadContent;


    /**
     * 数据处理类
     */
    private HomePresenter presenter;
    /**
     * 日期
     */
    private Date date;
    private String dailyReadUrl;

    @Override
    public void setTop() {
        isHideNavigation(true);
    }

    @Override
    public void initData() {
        date = new Date(System.currentTimeMillis());
        tvDate.setText(DateUtil.DATE_CHINESE.format(date));
        presenter = new HomePresenter(this, getActivity());
        presenter.subscribe();
        presenter.getCityWeather();
        //  presenter.getCityVo();
        presenter.getDailyRead();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    private String dailyUrl;

    @Override
    public String getStartDate() {
        return DateUtil.DATE_EMPTY.format(date);
    }

    @Override
    public String getEndDate() {
        return DateUtil.DATE_EMPTY.format(date);
    }

    @Override
    public void setDailyWordVo(List<DailyWordVo> dailyWordVos) {
        if (dailyWordVos.size() > 0) {
            DailyWordVo dailyWordVo = dailyWordVos.get(0);
            dailyUrl = dailyWordVo.getAction();
            GlideUtils.loadImageView(getActivity(), dailyWordVo.getImage_small(), ivDailyImage);
            tvDailyText.setText(dailyWordVo.getContent());
        }
    }

    @OnClick(R.id.ll_daily)
    public void daily() {
        Intent intent = new Intent(getActivity(), DailyWordDetailActivity.class);
        intent.putExtra("url", dailyUrl);
        intent.putExtra("from", Consist.FROM_HOME);
        startActivity(intent);
    }

    private String cityId;

    private CityWeatherVo cityWeatherVo;

    @Override
    public void setCityWeather(CityWeatherVo cityWeather) {
        this.cityWeatherVo = cityWeather;
        GlideUtils.loadImageView(getActivity(), cityWeather.getCurrent().getWeather_img_url(), ivWeatherImage);
        tvTemprature.setText(cityWeather.getCurrent().getTemp());
        tvStation.setText(cityWeather.getCity() + " " + cityWeather.getCurrent().getWeather());
    }

    @Override
    public void setDailyReadVo(List<DailyReadVo> dailyReadVoList) {
        if (dailyReadVoList.size() > 0) {
            DailyReadVo dailyReadVo = dailyReadVoList.get(0);
            if (dailyReadVo.getList().size() > 0) {
                GlideUtils.loadImageView(getActivity(), dailyReadVo.getList().get(0).getImage(), tvDailyReadImage);
                tvDailyReadContent.setText(dailyReadVo.getList().get(0).getTitle());
                dailyReadUrl = dailyReadVo.getList().get(0).getAction();
            }
        }
    }

    @Override
    public String getCityId() {
        cityId = "101210101";
        return cityId;
    }

    @OnClick(R.id.ll_daily_read)
    public void dailyRead() {
        Intent intent = new Intent(getActivity(), DailyWordDetailActivity.class);
        intent.putExtra("url", dailyReadUrl);
        intent.putExtra("from", Consist.FROM_HOME_READ);
        startActivity(intent);
    }

    @OnClick(R.id.ll_weather)
    public void weather() {
        Intent intent = new Intent(getActivity(), WeatherActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.tv_clock)
    public void clock() {
        Intent intent = new Intent(getActivity(), AlarmClockListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.tv_daliy)
    public void myDaily() {
        Intent intent = new Intent(getActivity(), DailyListActivity.class);
        startActivity(intent);
    }
}

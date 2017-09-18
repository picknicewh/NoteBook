package com.wh.kotlinnotebook.ui.home.daily;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.base.BaseActivity;
import com.wh.kotlinnotebook.bean.DailyWordVo;
import com.wh.kotlinnotebook.common.Consist;
import com.wh.kotlinnotebook.util.DateUtil;
import com.wh.kotlinnotebook.util.OnItemClickListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： wh
 * 时间：  2017/8/24
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class DailyWordListActivity extends BaseActivity implements DailyWordContract.View, OnItemClickListener {
    /**
     * 每日一记列表
     */
    @BindView(R.id.rv_daily_list)
    RecyclerView rvDailyList;
    /**
     * 数据处理类
     */
    private DailyWordPresenter presenter;
    /**
     * 开始时间
     */
    private Date startDate;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 适配器
     */
    private DailyWordListAdapter adapter;
    /**
     * 数据列表
     */
    private List<DailyWordVo> dailyWordVoList;
    @Override
    public void setTop() {
        isHideNavigation(false);
    }
    @Override
    public void setTooBar() {
        super.setTooBar();
        setLeftImage(R.mipmap.ic_back);
        setTitle("往期回顾");
    }

    @Override
    public void initData() {
        startDate = new Date(System.currentTimeMillis());
        endDate = DateUtil.getDateBetweenDays(startDate, 20);
        dailyWordVoList = new ArrayList<>();
        adapter =  new DailyWordListAdapter(this,dailyWordVoList);
        rvDailyList.setLayoutManager(new LinearLayoutManager(this));
        rvDailyList.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        presenter = new DailyWordPresenter(this, this);
        presenter.subscribe();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dailyword_list;
    }

    @Override
    public String getStartDate() {
        return  DateUtil.DATE_EMPTY.format(endDate);
    }

    @Override
    public String getEndDate() {
        return DateUtil.DATE_EMPTY.format(startDate);
    }

    @Override
    public void setDailyWordVo(List<DailyWordVo> dailyWordVo) {
        dailyWordVoList.clear();
        for (int i=dailyWordVo.size()-1;i>0;i--){
          dailyWordVoList.add(dailyWordVo.get(i));
        }
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this, DailyWordDetailActivity.class);
        intent.putExtra("url",dailyWordVoList.get(position).getAction());
        intent.putExtra("from", Consist.FROM_DETIAL_LIST);
        startActivity(intent);
    }
}

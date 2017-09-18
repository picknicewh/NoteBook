package com.wh.kotlinnotebook.ui.info;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.extras.recyclerview.PullToRefreshRecyclerView;
import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.base.BaseFragment;
import com.wh.kotlinnotebook.bean.LatestStoryVo;
import com.wh.kotlinnotebook.bean.StoriesVo;
import com.wh.kotlinnotebook.ui.info.detail.InfoDetailActivity;
import com.wh.kotlinnotebook.util.DateUtil;
import com.wh.kotlinnotebook.util.OnItemClickListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

;

/**
 * 作者： wh
 * 时间：  2017/8/21
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class InfoFragment extends BaseFragment implements InfoContract.View, OnItemClickListener, PullToRefreshBase.OnRefreshListener2<RecyclerView> {
    /**
     * 资讯列表
     */
  //  @BindView(R.id.prv_info)
    PullToRefreshRecyclerView prv;
    RecyclerView rvInfo;
    @BindView(R.id.container)
    LinearLayout container;
    /**
     * 数据处理类
     */
    private InfoPresenter presenter;
    /**
     * 适配器
     */
    private InfoAdapter adapter;
    /**
     * 数据处理列表
     */
    private List<StoriesVo> storiesVoList;

    @Override
    public void setTop() {
        isHideNavigation(false);
    }

    @Override
    public void initData() {
        setTitle("资讯");
        storiesVoList = new ArrayList<>();
        prv = new PullToRefreshRecyclerView(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        prv.setLayoutParams(lp);
        container.addView(prv);
        prv.setOnRefreshListener(this);
        prv.setMode(PullToRefreshBase.Mode.BOTH);
        rvInfo = prv.getRefreshableView();
        adapter = new InfoAdapter(getActivity(), storiesVoList);
        rvInfo.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvInfo.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        presenter = new InfoPresenter(this);
        presenter.subscribe();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_info;
    }


    @Override
    public void setLatestStoryVo(LatestStoryVo latestStoryList) {
        if (page == 0) {
            storiesVoList.clear();
        }
        storiesVoList.addAll(latestStoryList.getStories());
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    private int page = 0;

    @Override
    public String getDate() {
        return DateUtil.getFormatDateDays(new Date(), page);
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(getActivity(), InfoDetailActivity.class);
        intent.putExtra("storyId", storiesVoList.get(position).getId());
        startActivity(intent);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page = 0;
        presenter.subscribe();
        refreshView.onRefreshComplete();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        page++;
        presenter.subscribe();
        refreshView.onRefreshComplete();
    }
}

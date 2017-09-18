package com.wh.kotlinnotebook.ui.daily;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： wh
 * 时间：  2017/9/15
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class DailyImageFragment extends BaseFragment implements DailyImageListFragment.OnCurrentImageClickListener {
    /**
     * 页面
     */
    @BindView(R.id.vp_bg)
    ViewPager vpBg;

    /**
     * 背景列表
     */
    private List<DailyImageListFragment> fragmentList;
    /**
     * 适配器
     */
    private FragmentListAdapter adapter;
    /**
     * 来源
     */
    private String source;

    @Override
    public void setTop() {
        isHideNavigation(true);
    }

    @Override
    public void initData() {
        fragmentList = new ArrayList<>();
        source = getArguments().getString("source");
        for (int i = 0; i < 2; i++) {
            DailyImageListFragment detailFragment = new DailyImageListFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("page", i);
            bundle.putString("source", source);
            detailFragment.setArguments(bundle);
            detailFragment.setOnCurrentBgClickListener(this);
            fragmentList.add(detailFragment);
        }
        adapter = new FragmentListAdapter(getChildFragmentManager());
        vpBg.setAdapter(adapter);
        vpBg.setOffscreenPageLimit(2);
        vpBg.setCurrentItem(0);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_daily_image;
    }

    @Override
    public void onClick(String source, int color) {
        if (onBgChooseClickListener != null) {
            onBgChooseClickListener.onImageChoose(source, color);
        }
    }

    private class FragmentListAdapter extends FragmentPagerAdapter {
        public FragmentListAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    private OnBgChooseClickListener onBgChooseClickListener;

    public void setOnImageChooseClickListener(OnBgChooseClickListener onBgChooseClickListener) {
        this.onBgChooseClickListener = onBgChooseClickListener;
    }

    public interface OnBgChooseClickListener {
        void onImageChoose(String source, int color);
    }
}

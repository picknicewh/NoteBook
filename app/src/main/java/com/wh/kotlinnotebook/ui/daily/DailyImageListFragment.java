package com.wh.kotlinnotebook.ui.daily;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.base.BaseFragment;
import com.wh.kotlinnotebook.util.G;
import com.wh.kotlinnotebook.util.OnItemClickListener;

import butterknife.BindView;

/**
 * 作者： wh
 * 时间：  2017/9/15
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class DailyImageListFragment extends BaseFragment implements OnItemClickListener {
    @BindView(R.id.rv_daily_bg)
    RecyclerView rvDailyBg;
    private DailyImageAdapter adapter;
    /**
     * 一页背景个数
     */
    private int pageCount = 8;
    /**
     * 所以背景颜色
     */
    private   int[] bgImages;
    /**
     * 当前页背景颜色
     */
    private int[] currentImages;
    private int page;
    private   String source;
    @Override
    public void setTop() {
        isHideNavigation(true);
    }
    @Override
    public void initData() {
        G.initDisplaySize(getActivity());
        setPageBgs();
        adapter = new DailyImageAdapter(currentImages,source,getActivity());
        rvDailyBg.setLayoutManager(new GridLayoutManager(getActivity(),4));
        rvDailyBg.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_daily_image_detail;
    }
    private void setPageBgs(){
        Bundle bundle = getArguments();
        source  = bundle.getString("source");
        page = bundle.getInt("page");
        if (source.equals("weather")){
            bgImages = G.weatherImage;
            pageCount = 12;
        }else if (source.equals("emotion")){
            bgImages = G.Emotion;
            pageCount = 12;
        }else if (source.equals("bg")){
            bgImages = getResources().getIntArray(R.array.bg_color);
            pageCount = 8;
        }
        int totalBg = bgImages.length;
        if (page==0){
            currentImages= new int[pageCount];
            for (int i = 0 ;i<pageCount;i++){
                currentImages[i] =bgImages[i];
            }
        }else if (page==1){
            currentImages= new int[totalBg-pageCount];
            for (int i = 0 ;i<totalBg-pageCount;i++){
                currentImages[i] = bgImages[i+pageCount];
            }
        }

    }

    @Override
    public void onClick(View view, int position) {
        if (onCurrentImageClickListener!=null){
            onCurrentImageClickListener.onClick(source,currentImages[position]);
        }
    }
    private OnCurrentImageClickListener onCurrentImageClickListener;

    public void setOnCurrentBgClickListener(OnCurrentImageClickListener onCurrentImageClickListener) {
        this.onCurrentImageClickListener = onCurrentImageClickListener;
    }

    public interface  OnCurrentImageClickListener{
        void  onClick(String source , int image);
    }
}

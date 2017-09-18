package com.wh.kotlinnotebook.ui.daily;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.base.BaseFragment;
import com.wh.kotlinnotebook.util.OnItemClickListener;

import butterknife.BindView;

/**
 * 作者： wh
 * 时间：  2017/9/16
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class DailyFontFragment extends BaseFragment  {
    /**
     * 字号
     */
    private static final String SIZE = "size";
    /**
     * 颜色
     */
    private static final String COLOR = "color";
    /**
     * 字号
     */
    @BindView(R.id.rv_font_size)
    RecyclerView rvFontSize;
    /**
     * 颜色
     */
    @BindView(R.id.rv_font_color)
    RecyclerView rvFontColor;
    /**
     * 字号适配器
     */
    private DailyFontAdapter sizeAdapter;
    /**
     * 字体颜色适配器
     */
    private DailyFontAdapter colorAdapter;
    /**
     * 字体列表
     */
    private int fontSizes[];
    /**
     * 字体颜色列表
     */
    private int fontColors[];


    @Override
    public void setTop() {
        isHideNavigation(true);
    }

    @Override
    public void initData() {
        fontSizes = getResources().getIntArray(R.array.font_size);
        fontColors = getResources().getIntArray(R.array.font_color);
        sizeAdapter = new DailyFontAdapter(fontSizes, SIZE);
        sizeAdapter.initData(1);
        rvFontSize.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvFontSize.setAdapter(sizeAdapter);
        sizeAdapter.setOnItemClickListener(onSizeClickListener);
        colorAdapter = new DailyFontAdapter(fontColors, COLOR);
        colorAdapter.initData(0);
        rvFontColor.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvFontColor.setAdapter(colorAdapter);
        colorAdapter.setOnItemClickListener(onColorClickListener);
    }
    @Override
    public int getLayoutId() {
        return R.layout.fragment_daily_font;
    }

    public OnItemClickListener onSizeClickListener  = new OnItemClickListener() {
        @Override
        public void onClick(View view, int position) {
            if (onFontSizeClickListener!=null){
                onFontSizeClickListener.onSizeClick(fontSizes[position]);
            }
        }
    };
    public OnItemClickListener onColorClickListener  = new OnItemClickListener() {
        @Override
        public void onClick(View view, int position) {
            if (onFontColorClickListener!=null){
                onFontColorClickListener.onColorClick(fontColors[position]);
            }
        }
    };
    private OnFontSizeClickListener onFontSizeClickListener;
    private OnFontColorClickListener onFontColorClickListener;
    public void setOnFontColorClickListener(OnFontColorClickListener onFontColorClickListener) {
        this.onFontColorClickListener = onFontColorClickListener;
    }
    public void setOnFontSizeClickListener(OnFontSizeClickListener onFontSizeClickListener) {
        this.onFontSizeClickListener = onFontSizeClickListener;
    }

    public interface  OnFontSizeClickListener{
        void onSizeClick(int fontSize);
    }
    public interface  OnFontColorClickListener{
        void onColorClick(int color);
    }
}

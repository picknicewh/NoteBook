package com.wh.kotlinnotebook.ui.daily;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.util.OnItemClickListener;

import java.util.HashMap;

/**
 * 作者： wh
 * 时间：  2017/7/29
 * 名称：出库详情适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class DailyFontAdapter extends RecyclerView.Adapter<DailyFontAdapter.ViewHolder> {
    private int[] fonts;
    private String source;
    private HashMap<Integer, Boolean> checkList;
    private int position;

    public DailyFontAdapter(int[] fonts, String source) {
        this.fonts = fonts;
        this.source = source;
    }

    public void initData(int position) {
        this.position = position;
        checkList = new HashMap<>();
        for (int i = 0; i < fonts.length; i++) {
            if (i == position) {
                checkList.put(i, true);
            } else {
                checkList.put(i, false);
            }
        }
        notifyDataSetChanged();
    }

    public int getPosition() {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily_font, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        int font = fonts[position];
        if (source.equals("color")) {
            holder.tvFont.setBackgroundColor(font);
        } else if (source.equals("size")) {
            holder.tvFont.setText("Aa");
            holder.tvFont.setTextSize(font);
        }
         holder.ivCheck.setVisibility(checkList.get(position) ? View.VISIBLE : View.GONE);
         holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData(position);
                if (onItemClickListener!=null){
                    onItemClickListener.onClick(v,position);
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFont;
        ImageView ivCheck;

        ViewHolder(View itemView) {
            super(itemView);
            tvFont = (TextView) itemView.findViewById(R.id.tv_font);
            ivCheck = (ImageView) itemView.findViewById(R.id.iv_check);
            itemView.setTag(this);
        }
    }

    @Override
    public int getItemCount() {
        return fonts.length;
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

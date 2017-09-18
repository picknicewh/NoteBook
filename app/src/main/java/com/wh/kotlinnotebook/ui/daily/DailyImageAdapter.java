package com.wh.kotlinnotebook.ui.daily;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.util.G;
import com.wh.kotlinnotebook.util.OnItemClickListener;

/**
 * 作者： wh
 * 时间：  2017/7/29
 * 名称：出库详情适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class DailyImageAdapter extends RecyclerView.Adapter<DailyImageAdapter.ViewHolder> {
     private int[] bgs;
    private OnItemClickListener onItemClickListener;
    private String source;
    private Context context;
    public DailyImageAdapter(int[] bgs, String source, Context context){
        this.bgs = bgs;
        this.source   =source;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_daily_bg, null);
        return new ViewHolder(view);
    }
    @Override
      public void onBindViewHolder(final ViewHolder holder,final int position) {
        int color =bgs[position];
        G.log("--xxxxxxxxx+adapter"+bgs[position]);
        LinearLayout.LayoutParams lp ;
        if (source.equals("bg")){
            holder.iv_bg.setBackgroundColor(color);
            lp = new LinearLayout.LayoutParams(G.dp2px(context,65),G.dp2px(context,65));
        }else {
            lp = new LinearLayout.LayoutParams(  G.size.W/4, G.dp2px(context,35));
            holder.iv_bg.setImageResource(color);
        }
        int margin = G.dp2px(context,10);
        lp.gravity = Gravity.CENTER;
        lp.setMargins(margin,margin,margin,margin);
        holder.iv_bg.setLayoutParams(lp);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onClick(v,position);
                }
            }
        });
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_bg;
        ViewHolder(View itemView) {
            super(itemView);
            iv_bg = (ImageView) itemView.findViewById(R.id.iv_bg);
            itemView.setTag(this);
        }
    }
    @Override
    public int getItemCount() {
        return bgs.length;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

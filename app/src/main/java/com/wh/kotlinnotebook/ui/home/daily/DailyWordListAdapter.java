package com.wh.kotlinnotebook.ui.home.daily;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.bean.DailyWordVo;
import com.wh.kotlinnotebook.network.GlideUtils;
import com.wh.kotlinnotebook.util.OnItemClickListener;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/7/29
 * 名称：出库详情适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class DailyWordListAdapter extends RecyclerView.Adapter<DailyWordListAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private List<DailyWordVo> dailyWordVoList;
    private Context context;
    public DailyWordListAdapter(Context context,List<DailyWordVo> dailyWordVoList) {
        this.dailyWordVoList = dailyWordVoList;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily_word, null);
        return new ViewHolder(view);
    }
    @Override
      public void onBindViewHolder(final ViewHolder holder,final int position) {
        DailyWordVo dailyWordVo = dailyWordVoList.get(position);
        GlideUtils.loadImageView(context,dailyWordVo.getImage_large(),holder.iv_daily_image);
        holder.tv_daily_content.setText(dailyWordVo.getContent());
        holder.tv_daily_date.setText(dailyWordVo.getDate());
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
        TextView tv_daily_date;
        TextView tv_daily_content;
        ImageView iv_daily_image;
        ViewHolder(View itemView) {
            super(itemView);
            tv_daily_date = (TextView) itemView.findViewById(R.id.tv_daily_date);
            tv_daily_content = (TextView) itemView.findViewById(R.id.tv_daily_content);
            iv_daily_image = (ImageView) itemView.findViewById(R.id.iv_daily_image);
            itemView.setTag(this);
        }
    }
    @Override
    public int getItemCount() {
        return dailyWordVoList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

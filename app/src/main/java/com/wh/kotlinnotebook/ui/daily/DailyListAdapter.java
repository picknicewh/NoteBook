package com.wh.kotlinnotebook.ui.daily;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.bean.DailyVo;
import com.wh.kotlinnotebook.util.DateUtil;
import com.wh.kotlinnotebook.util.OnItemClickListener;
import com.wh.kotlinnotebook.util.OnItemLongClickListener;

import java.util.Date;
import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/7/29
 * 名称：出库详情适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class DailyListAdapter extends RecyclerView.Adapter<DailyListAdapter.ViewHolder> {

    private List<DailyVo> dailyVoList;
    public DailyListAdapter(List<DailyVo> dailyVoList) {
         this.dailyVoList = dailyVoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_daily_list, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
          DailyVo dailyVo = dailyVoList.get(position);
        holder.ivEmotion.setImageResource(dailyVo.getEmotion());
        holder.ivWeather.setImageResource(dailyVo.getWeather());

        holder.tvContent.setText(dailyVo.getContent());
        holder.tvDate.setText(DateUtil.DATE_CHINESE.format(new Date(Long.parseLong(dailyVo.getDate()))));
        holder.tvContent.setTextColor(dailyVo.getFont_color());
         holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onClick(v,position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemLongClickListener!=null){
                    onItemLongClickListener.onLongClick(position);
                }
                return false;
            }
        });
    }



    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivWeather;
        private ImageView ivEmotion;
        private TextView tvContent;
        private TextView tvDate;
        ViewHolder(View itemView) {
            super(itemView);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            ivWeather = (ImageView) itemView.findViewById(R.id.iv_weather);
            ivEmotion = (ImageView) itemView.findViewById(R.id.iv_emotion);
            itemView.setTag(this);
        }
    }

    @Override
    public int getItemCount() {
        return dailyVoList.size();
    }

    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}

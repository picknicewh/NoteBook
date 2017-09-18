package com.wh.kotlinnotebook.ui.clock;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.bean.ClockVo;
import com.wh.kotlinnotebook.util.OnItemClickListener;
import com.wh.kotlinnotebook.util.OnItemLongClickListener;

import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/7/29
 * 名称：出库详情适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class AlarmClockListAdapter extends RecyclerView.Adapter<AlarmClockListAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private Context context;
    private List<ClockVo> clockVos;
    /**
     * 不同样式文字
     */
    private  SpannableString msp;
    /**
     * 红色字体颜色
     */
    private ForegroundColorSpan colorSpan;
    public AlarmClockListAdapter(Context context, List<ClockVo> clockVos) {
        this.context = context;
        this.clockVos = clockVos;
        colorSpan =  new ForegroundColorSpan(ContextCompat.getColor(context,R.color.main_theme_color));
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_clock_list, null);
        return new ViewHolder(view);
    }
    @Override
      public void onBindViewHolder(final ViewHolder holder,final int position) {
        ClockVo clockVo = clockVos.get(position);
        holder.tv_time.setText(clockVo.getTime());
        msp = new SpannableString("闹钟/"+clockVo.getWeeks());
        msp.setSpan(colorSpan,3,msp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.tv_weeks.setText(msp);
        holder.tg_open.setChecked(clockVo.getIsOpen()==1?true:false);
        holder.tg_open.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    holder.tv_time.setTextColor(ContextCompat.getColor(context,R.color.grey));
                    holder.tv_weeks.setTextColor(ContextCompat.getColor(context,R.color.grey));
                }else {
                    holder.tv_time.setTextColor(ContextCompat.getColor(context,R.color.main_text_color));
                    holder.tv_weeks.setTextColor(ContextCompat.getColor(context,R.color.minor_text_color));
                }
            }
        });
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
        private TextView tv_time;
        private TextView tv_weeks;
        private ToggleButton tg_open;
        ViewHolder(View itemView) {
            super(itemView);
             tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_weeks = (TextView) itemView.findViewById(R.id.tv_weeks);
            tg_open = (ToggleButton) itemView.findViewById(R.id.tg_open);
            itemView.setTag(this);
        }
    }
    @Override
    public int getItemCount() {
        return clockVos.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}

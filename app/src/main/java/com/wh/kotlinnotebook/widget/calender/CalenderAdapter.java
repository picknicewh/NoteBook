package com.wh.kotlinnotebook.widget.calender;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.util.OnItemClickListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者： wh
 * 时间：  2017/6/27
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class CalenderAdapter extends RecyclerView.Adapter<CalenderAdapter.ViewHolder>{
    private List<DateVo> dateList;
    private Context context;
    private Map<Integer,Integer>  selectedList;
    private OnItemClickListener onItemClickListener;
    public CalenderAdapter(List<DateVo> dateList, Context context){
        this.dateList = dateList;
        this.context = context;
        selectedList = new HashMap<>();
        initData(getSelectPosition());
    }
    public void initData(int position){
        for (int i=0;i<dateList.size();i++) {
            if (i==position){
                if (dateList.get(i).getSign()==1){
                    selectedList.put(i, 1);
                }else {
                    selectedList.put(i, 2);
                }
            }else {
                selectedList.put(i, 0);
            }
        }
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calender,null);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
         final DateVo dateVo = dateList.get(position);
         holder.tv_date.setText(""+dateVo.getDate());
         if (dateVo.getSign()==-1){
             holder.tv_date.setTextColor(ContextCompat.getColor(context,R.color.grey));
         }else if (dateVo.getSign()==0){
             holder.tv_date.setTextColor(ContextCompat.getColor(context,R.color.green));
         }else if (dateVo.getSign()==1){
             holder.tv_date.setTextColor(ContextCompat.getColor(context,R.color.white));
             holder.tv_date.setBackgroundResource(R.drawable.circle_pink);
         }else if (dateVo.getSign()==5){
             holder.tv_date.setTextColor(ContextCompat.getColor(context,R.color.violet));
         }
         if (selectedList.get(position)==2){
            holder.tv_date.setBackgroundResource(R.drawable.ring_voilet);
         }else if (selectedList.get(position)==1){
             holder.tv_date.setBackgroundResource(R.drawable.circle_voilet);
         } else {
             if (dateVo.getSign()!=1){
                 holder.tv_date.setBackgroundColor(ContextCompat.getColor(context,R.color.white));
             }
         }
         holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener!=null){
                        onItemClickListener.onClick(v,position);
                    }
                    initData(position);
                }
            });
        }

    @Override
    public int getItemCount() {
        return dateList.size();
    }
    class  ViewHolder extends RecyclerView.ViewHolder {
    TextView tv_date;
        ViewHolder(View itemView) {
        super(itemView);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            itemView.setTag(this);
    }
   }
    private int getSelectPosition(){
        for (int i=0;i<dateList.size();i++) {
            if (dateList.get(i).getSign()==2){
                return i;
            }
        }
        return 0;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

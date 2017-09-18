package com.wh.kotlinnotebook.ui.clock;

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
import java.util.Map;

/**
 * 作者： wh
 * 时间：  2017/7/29
 * 名称：出库详情适配器
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class AlarmDateAdapter extends RecyclerView.Adapter<AlarmDateAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private Context context;
    private Map<Integer,Boolean> weekSelectd;
    private String[] weeks = new String[]{"日","一","二","三","四","五","六"};
    public AlarmDateAdapter(Context context) {
        this.context = context;
        initData();
    }
    private void initData(){
        weekSelectd = new HashMap<>();
        for (int i = 0 ; i<weeks.length;i++){
            weekSelectd.put(i,true);
        }
    }
     public void initEditData(int[] numbers){
         //初始化全部为false
         for (int i = 0 ; i<weeks.length;i++){
             weekSelectd.put(i,false);
         }//修改状态
        for (int i = 0 ;i<numbers.length;i++){
            weekSelectd.put(numbers[i],true);
        }
    }
    public String getWeeks(){
        StringBuffer mWeeks= new StringBuffer();
        int count=0;
        for (int i = 0 ;i< 7;i++){
            if (weekSelectd.get(i)){
                mWeeks.append("周"+weeks[i]).append(",");
                count++;
            }
        }
        if (mWeeks.length()>0){
            mWeeks.deleteCharAt(mWeeks.length()-1);
        }
        if (count==7){
            mWeeks = new StringBuffer();
            mWeeks.append("每天");
        }
        return mWeeks.toString();
    }
    public String getWeekNumbers(){
        StringBuffer mWeeks= new StringBuffer();
        for (int i = 0 ;i< 7;i++){
            if (weekSelectd.get(i)){
                mWeeks.append(i).append(",");
            }
        }
        mWeeks.deleteCharAt(mWeeks.length()-1);
        return mWeeks.toString();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm_date, null);
        return new ViewHolder(view);
    }
    @Override
      public void onBindViewHolder(final ViewHolder holder,final int position) {
         holder.tv_week.setText(weeks[position]);
        if (weekSelectd.get(position)){
            holder.tv_week.setTextColor(ContextCompat.getColor(context,R.color.white));
            holder.tv_week.setBackgroundResource(R.drawable.circle_pink);
        }else {
            holder.tv_week.setTextColor(ContextCompat.getColor(context,R.color.main_text_color));
            holder.tv_week.setBackgroundResource(R.drawable.circle_grey);
        }
         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (weekSelectd.get(position)){
                     weekSelectd.put(position,false);
                 }else {
                     weekSelectd.put(position,true);
                 }
                 notifyDataSetChanged();
             }
         });
       }
    class ViewHolder extends RecyclerView.ViewHolder {
       private TextView tv_week;
        ViewHolder(View itemView) {
            super(itemView);
            tv_week = (TextView) itemView.findViewById(R.id.tv_week);
            itemView.setTag(this);
        }
    }
    @Override
    public int getItemCount() {
        return weeks.length;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

package com.wh.kotlinnotebook.ui.weather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.bean.ForcastVo;
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
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private Context context;
    private List<ForcastVo> forcastVoList;
    public WeatherAdapter(Context context, List<ForcastVo> forcastVoList) {
        this.context = context;
        this.forcastVoList  =  forcastVoList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, null);
        return new ViewHolder(view);
    }
    @Override
      public void onBindViewHolder(final ViewHolder holder,final int position) {
        ForcastVo forcastVo = forcastVoList.get(position);
        holder.tv_week.setText(forcastVo.getDay()+"  "+forcastVo.getDate());
        GlideUtils.loadImageView(context,forcastVo.getWeather_img_url(),holder.iv_weather_image);
        String hightTemp = forcastVo.getTemp().substring(0,2);
        String lowTemp  = forcastVo.getTemp().substring(3,5);
        holder.tv_temprature.setText(hightTemp+"   -   "  +lowTemp);
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
        TextView tv_week;
        ImageView iv_weather_image;
        TextView tv_temprature;
        ViewHolder(View itemView) {
            super(itemView);
            tv_week = (TextView) itemView.findViewById(R.id.tv_week);
            tv_temprature =  (TextView) itemView.findViewById(R.id.tv_temprature);
            iv_weather_image = (ImageView) itemView.findViewById(R.id.iv_weather_image);
            itemView.setTag(this);
        }
    }
    @Override
    public int getItemCount() {
        return forcastVoList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

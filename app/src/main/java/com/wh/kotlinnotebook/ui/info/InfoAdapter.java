package com.wh.kotlinnotebook.ui.info;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.bean.StoriesVo;
import com.wh.kotlinnotebook.network.GlideUtils;
import com.wh.kotlinnotebook.util.G;
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
public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    private List<StoriesVo> storiesVoList;
    private Context context;
    public InfoAdapter(Context context, List<StoriesVo> storiesVoList) {
        this.storiesVoList = storiesVoList;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info, null);
        return new ViewHolder(view);
    }
    @Override
      public void onBindViewHolder(final ViewHolder holder,final int position) {
        StoriesVo storiesVo = storiesVoList.get(position);
        RelativeLayout.LayoutParams lp;
        if (storiesVo.getImages() != null && storiesVo.getImages().size() > 0) {
            GlideUtils.loadImageView(context, storiesVo.getImages().get(0), holder.iv_image);
            holder.iv_image.setVisibility(View.VISIBLE);
            lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, G.dp2px(context, 80.0), 0);
            holder.tv_title.setLayoutParams(lp);
        } else {
            lp = new  RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            holder.tv_title.setLayoutParams(lp);
            holder.iv_image.setVisibility(View.GONE);
        }
        holder.tv_title.setText( storiesVo.getTitle());
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
        TextView tv_title;
        ImageView iv_image;
        ViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
            itemView.setTag(this);
        }
    }
    @Override
    public int getItemCount() {
        return storiesVoList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

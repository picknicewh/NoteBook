package com.wh.kotlinnotebook.ui.daily;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.base.BaseActivity;
import com.wh.kotlinnotebook.bean.DailyVo;
import com.wh.kotlinnotebook.common.Consist;
import com.wh.kotlinnotebook.database.DailyDb;
import com.wh.kotlinnotebook.util.OnItemClickListener;
import com.wh.kotlinnotebook.util.OnItemLongClickListener;
import com.wh.kotlinnotebook.widget.ConformDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DailyListActivity extends BaseActivity  implements OnItemClickListener, ConformDialog.OnConformClickListener, OnItemLongClickListener {

    /**
     * 没有数据
     */
    @BindView(R.id.tv_nodata)
    LinearLayout tvNodata;
    /**
     * 日记列表
     */
    @BindView(R.id.rv_daily)
    RecyclerView rvDaily;
    /**
     * 数据库
     */
    private   DailyDb dailyDb;
    /**
     * 日记列表
     */
    private List<DailyVo> dailyVoList;
    /**
     * 适配器
     */
    private DailyListAdapter adapter;
    /**
     * 确认删除对话框
     */
     private ConformDialog dialog;
    @Override
    public int getLayoutId() {
        return R.layout.activity_daily_list;
    }


    @Override
    public void setTop() {
        isHideNavigation(false);
    }

    @Override
    public void initData() {
        setTitle("我的日记");
        setLeftImage(R.mipmap.ic_back);
        dailyDb = DailyDb.getInstance();
        dialog = new ConformDialog(this);
        dialog.setOnConformClickListener(this);
        dailyVoList = new ArrayList<>();
        dailyVoList = dailyDb.getDailyList();
        tvNodata.setVisibility(dailyVoList.size()==0?View.VISIBLE:View.GONE);
        adapter = new DailyListAdapter(dailyVoList);
        rvDaily.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvDaily.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemLongClickListener(this);
    }

    @OnClick(R.id.tv_add_daily)
    public void addDaily() {
        Intent intent = new Intent(this,DailyEditActivity.class);
        intent.putExtra("operation",Consist.REQUEST_ADD);
        startActivityForResult(intent, Consist.REQUEST_ADD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        dailyVoList.clear();
        dailyVoList.addAll(dailyDb.getDailyList());
        tvNodata.setVisibility(dailyVoList.size()==0?View.VISIBLE:View.GONE);
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this,DailyEditActivity.class);
        intent.putExtra("dailyVo",dailyVoList.get(position));
        intent.putExtra("operation",Consist.REQUEST_EDIT);
        startActivityForResult(intent, Consist.REQUEST_EDIT);
    }

    @Override
    public void onConform(int position) {
        int id = dailyVoList.get(position).getId();
        dailyVoList.remove(position);
        dailyDb.deleteById(id);
        if (adapter!=null){
            adapter.notifyItemRemoved(position);
        }
    }

    @Override
    public void onLongClick(int position) {
        dialog.show();
    }
}

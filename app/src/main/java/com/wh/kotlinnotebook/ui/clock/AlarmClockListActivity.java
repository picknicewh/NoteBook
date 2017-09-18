package com.wh.kotlinnotebook.ui.clock;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.base.BaseActivity;
import com.wh.kotlinnotebook.bean.ClockVo;
import com.wh.kotlinnotebook.common.Consist;
import com.wh.kotlinnotebook.database.ClockDb;
import com.wh.kotlinnotebook.util.OnItemClickListener;
import com.wh.kotlinnotebook.util.OnItemLongClickListener;
import com.wh.kotlinnotebook.widget.ConformDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AlarmClockListActivity extends BaseActivity implements OnItemClickListener, OnItemLongClickListener, ConformDialog.OnConformClickListener {


    @BindView(R.id.rv_clock)
    RecyclerView rvClock;
    private AlarmClockListAdapter adapter;
    /**
     * 闹钟数据库
     */
    private ClockDb clockDb;
   private List<ClockVo>   clockVos;
    /**
     * 确认删除对话框
     */
    private ConformDialog dialog;
    @Override
    public void setTop() {
        isHideNavigation(true);
    }

    @Override
    public void initData() {
        dialog = new ConformDialog(this);
        dialog.setOnConformClickListener(this);
        clockDb  =ClockDb.getInstance();
        clockVos = clockDb.getClockList();
        adapter  = new AlarmClockListAdapter(this,clockVos);
        rvClock.setLayoutManager(new LinearLayoutManager(this));
        rvClock.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemLongClickListener(this);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_alarm_clock_list;
    }

    @OnClick(R.id.iv_left)
    public void left() {
        finish();
    }
    @OnClick(R.id.tv_msubtitle)
    public void newClock() {
        Intent intent = new Intent(this,AlarmSettingActivity.class);
        intent.putExtra("source",Consist.REQUEST_ADD);
        startActivityForResult(intent, Consist.REQUEST_ADD);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        clockVos.clear();
        clockVos.addAll(clockDb.getClockList());
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(this,AlarmSettingActivity.class);
        intent.putExtra("clock",clockVos.get(position));
        intent.putExtra("source",Consist.REQUEST_EDIT);
        startActivityForResult(intent, Consist.REQUEST_EDIT);
    }

    @Override
    public void onLongClick(int position) {
        dialog.show();
        dialog.setPosition(position);
    }

    @Override
    public void onConform(int position) {
        clockDb.deleteById(clockVos.get(position).getId());
        clockVos.remove(position);
        if (adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }
}

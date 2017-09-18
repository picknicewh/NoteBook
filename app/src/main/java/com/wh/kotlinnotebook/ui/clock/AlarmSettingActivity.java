package com.wh.kotlinnotebook.ui.clock;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.base.BaseActivity;
import com.wh.kotlinnotebook.bean.ClockVo;
import com.wh.kotlinnotebook.common.Consist;
import com.wh.kotlinnotebook.database.ClockDb;
import com.wh.kotlinnotebook.util.AlarmManagerUtil;
import com.wh.kotlinnotebook.widget.WheelView;

import butterknife.BindView;
import butterknife.OnClick;

public class AlarmSettingActivity extends BaseActivity implements AlarmClockContract.View {


    @BindView(R.id.ll_sound)
    LinearLayout llSound;
    /**
     * 歌曲名字
     */
    @BindView(R.id.tv_song)
    TextView tvSong;
    /**
     * 星期
     */
    @BindView(R.id.rv_week)
    RecyclerView rvWeek;
    /**
     * 备注
     */
    @BindView(R.id.et_remark)
    EditText etRemark;
    /**
     * 是否推迟
     */
    @BindView(R.id.tg_delay)
    ToggleButton tgDelay;
    /**
     * 选择闹钟的小时
     */
    @BindView(R.id.wv_hour)
    WheelView wvhour;
    private int hour;
    /**
     * 选择闹钟的分钟
     */
    @BindView(R.id.wv_minute)
    WheelView wvminute;
    private int minute;
    private String mTime;
    /**
     * 适配器
     */
    private AlarmDateAdapter adapter;
    /**
     * 闹钟数据库
     */
    private ClockDb clockDb;
    /**
     * 是否编辑
     */
    private boolean isEdit;
    /**
     * 来源
     */
    private int source;
    /**
     * 数据处理
     */
    private AlarmClockPresenter presenter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_alarm_setting;
    }

    @Override
    public void setTop() {
        isHideNavigation(false);
    }
    @Override
    public void initData() {
        setLeftImage(R.mipmap.ic_back);
        setTitle("设置闹钟");
        setSubTitle("保存");
        adapter =  new AlarmDateAdapter(this);
        rvWeek.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rvWeek.setAdapter(adapter);
        initClock();
        wvhour.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                super.onSelected(selectedIndex, item);
                hour = selectedIndex-1;
            }
        });
        wvminute.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                super.onSelected(selectedIndex, item);
                minute = selectedIndex-1;
            }
        });

    }
    private ClockVo clockVo;
    private void initClock(){
        clockDb = ClockDb.getInstance();
        presenter = new AlarmClockPresenter(this);
        wvhour.setItems(presenter.getHour());
        wvminute.setItems(presenter.getDates());
        source = getIntent().getIntExtra("source", Consist.REQUEST_ADD);
        if (source==Consist.REQUEST_EDIT){
            clockVo = (ClockVo) getIntent().getSerializableExtra("clock");

            isEdit= true;
            etRemark.setText(clockVo.getRemark());
            String[] time = clockVo.getTime().split(":");
            if (time!=null&&time.length==2){
                hour = Integer.parseInt(time[0]);
                minute = Integer.parseInt(time[1]);
            }
            wvhour.setSeletion(hour);
            wvminute.setSeletion(minute);
            String[] weeksNumbers  = clockVo.getWeekNumbers().split(",");
            int[] numbers= new int[weeksNumbers.length];
            for (int i = 0 ;i<weeksNumbers.length;i++){
                numbers[i] = Integer.parseInt(weeksNumbers[i]);
            }
            adapter.initEditData(numbers);
        }else {
            isEdit =false;
            wvhour.setSeletion(7);
            wvminute.setSeletion(30);
            hour = 7;//默认选中的小时值
            minute = 30; //默认选择的分钟
        }
    }


    @Override
    public void setSubTitleListener() {
        super.setSubTitleListener();
        mTime = presenter.getFormatTime(hour,minute);
        String remark = etRemark.getText().toString();
        String weeks = adapter.getWeeks();
        String weekNumbers = adapter.getWeekNumbers();
        int delay = tgDelay.isChecked() ?1:0;
        if (isEdit){
            clockDb.updateData(clockVo.getId(),mTime,0,weeks,weekNumbers,remark,delay,1);
            showMessage("更新成功！");
        }else {
            clockDb.insert(mTime,0,weeks,weekNumbers,remark,delay,1);
            showMessage("插入成功！");
        }
        //启动闹钟广播发送闹钟
        startClock();
        setback();
    }
    /**
     * 插入成功后返回
     */
    private void setback(){
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
    /**
     * 启动闹钟广播发送闹钟
     */
   private void startClock(){
       Intent intent = new Intent();
       intent.setAction(AlarmManagerUtil.ALARM_ACTION);
       intent.putExtra("hour",hour);
       intent.putExtra("minute",minute);
       intent.putExtra("time",mTime);
       intent.putExtra("id",0);
       sendBroadcast(intent);
   }
    @OnClick(R.id.ll_sound)
    public void llSound() {

    }
}

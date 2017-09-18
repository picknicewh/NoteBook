package com.wh.kotlinnotebook.ui.clock;

import android.app.Service;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.widget.TextView;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.base.BaseActivity;
import com.wh.kotlinnotebook.bean.ClockVo;
import com.wh.kotlinnotebook.database.ClockDb;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

public class AlarmClockActivity extends BaseActivity {
    /**
     * 闹钟时间
     */
    @BindView(R.id.tv_time)
    TextView tvTime;
    /**
     * 闹钟铃声
     */
    private MediaPlayer mediaPlayer;
    /**
     * 创建震动服务对象
     */
    private Vibrator mVibrator;
    /**
     * 计时器
     */
    private Timer mTimer;
    /**
     * 是否推迟
     */
    private boolean isdelay;
    /**
     * 闹钟响应时间
     */
    private String time;
    /**
     * 闹钟数据库
     */
    private ClockDb mClockDb;
    /**
     * 闹钟信息
     */
    private ClockVo clockVo;
    @Override
    public void setTop() {
        isHideNavigation(true);
    }

    @Override
    public void initData() {
        mediaPlayer = MediaPlayer.create(this,R.raw.kn);
        mVibrator=(Vibrator)getApplication().getSystemService(Service.VIBRATOR_SERVICE); //获取手机震动服务
        mClockDb = ClockDb.getInstance();
        mTimer = new Timer();
        time = getIntent().getStringExtra("time");
        tvTime.setText(time);
        clockVo = mClockDb.getClockByTime(time);
        isdelay = clockVo.getDelay()==1?true:false;
        startClock();
    }
   private void startClock(){
       mediaPlayer.start();
       //设置震动周期，数组表示时间：等待+执行，单位是毫秒，下面操作代表:等待100，执行100，等待100，执行1000，
       mVibrator.vibrate(new long[]{100,100,100,1000},0);
   }
    @Override
    public int getLayoutId() {
        return R.layout.activity_alarm_clock;
    }

    @OnClick(R.id.tv_delay)
    public void delay() {
        if (isdelay){
            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    startClock();
                }
            }, 10 * 1000 * 60);
            isdelay = false;
        }
    }

    @OnClick(R.id.tv_close)
    public void close() {
        if (mediaPlayer!=null){
            mediaPlayer.reset();
            mediaPlayer.stop();
            mediaPlayer = null;
        }
        if (mVibrator!=null){
            mVibrator.cancel();
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer!=null){
            mediaPlayer.reset();
            mediaPlayer.stop();
            mediaPlayer = null;
        }
        if (mVibrator!=null){
            mVibrator.cancel();
        }
    }
}

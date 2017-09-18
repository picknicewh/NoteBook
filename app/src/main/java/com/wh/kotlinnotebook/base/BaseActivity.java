package com.wh.kotlinnotebook.base;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.util.G;
import com.wh.kotlinnotebook.util.StatusBarUtil;
import com.wh.kotlinnotebook.widget.LoadingDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： wh
 * 时间：  2017/8/19
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView, View.OnClickListener {
    /**
     * 导航栏
     */
    @BindView(R.id.rv_top)
    RelativeLayout rvTop;
    /**
     * 加载框
     */
    private LoadingDialog dialog;
    /**
     * 返回键
     */
    @BindView(R.id.iv_back)
     ImageView ivBack;
    /**
     * 标题
     */
    @BindView(R.id.tv_title)
    TextView tvTitle;
    /**
     * 子标题
     */
    @BindView(R.id.tv_subtitle)
    TextView tvSubtitle;
    /**
     * 右边图片
     */
    @BindView(R.id.iv_right)
    ImageView ivRight;
    /**
     * 导航栏外的内容
     */
    private FrameLayout flContent;
    /**
     * 是否隐藏导航栏
     */
    private boolean isHideTop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        flContent = (FrameLayout) findViewById(R.id.fl_content);
        flContent.requestFocus();
        getLayoutInflater().inflate(getLayoutId(), flContent, true);
        ButterKnife.bind(this);
        setTop();
        if (isHideTop){
            rvTop.setVisibility(View.GONE);
        }else {
            rvTop.setVisibility(View.VISIBLE);
            setTooBar();
            ivBack.setOnClickListener(this);
            tvSubtitle.setOnClickListener(this);
        }
        //设置导航栏颜色
        StatusBarUtil.setColor(this, ContextCompat.getColor(this,R.color.colorAccent));
        initData();
    }
    public abstract void setTop();
    public abstract void initData();
    public abstract int getLayoutId();
    public void setTooBar(){}
    public void setRightImage(int resId) {
        ivRight.setImageResource(resId);
    }

    public void setSubTitle(String subTitle) {
        tvSubtitle.setText(subTitle);
    }

    public void setLeftImage(int resId) {
        ivBack.setImageResource(resId);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setLeftOnClickListener() {
        finish();
    }
    public void setSubTitleListener(){}

    @Override
    public void showMessage(String message) {
        G.showToast(this, message);
    }

    @Override
    public void showProgress() {
        if (dialog == null) {
            dialog = new LoadingDialog(this);
        }
        dialog.show();
    }

    @Override
    public void hideProgress() {
        dialog.dismiss();
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.iv_back:
                setLeftOnClickListener();
                break;
            case R.id.tv_subtitle:
                setSubTitleListener();
                break;
        }
    }
    public void isHideNavigation(boolean isHideTop){
        this.isHideTop = isHideTop;
    }
}

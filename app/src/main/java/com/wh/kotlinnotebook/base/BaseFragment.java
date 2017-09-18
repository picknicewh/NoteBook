package com.wh.kotlinnotebook.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.util.G;
import com.wh.kotlinnotebook.widget.LoadingDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者： wh
 * 时间：  2017/8/21
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public abstract class BaseFragment extends Fragment implements BaseView, View.OnClickListener {
    Unbinder unbinder;
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
     * 加载框
     */
    private LoadingDialog dialog;
    /**
     * 导航栏
     */
    @BindView(R.id.rv_top)
    RelativeLayout rvTop;
    /**
     * 是否隐藏导航栏
     */
    private boolean isHideTop;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_base, null);
        flContent = (FrameLayout) view.findViewById(R.id.fl_content);
        flContent.requestFocus();
        getActivity().getLayoutInflater().inflate(getLayoutId(),flContent,true);
        unbinder = ButterKnife.bind(this, view);
        setTop();
        if (isHideTop){
            rvTop.setVisibility(View.GONE);
        }else {
            rvTop.setVisibility(View.VISIBLE);
            setTooBar();
            ivBack.setOnClickListener(this);
            tvSubtitle.setOnClickListener(this);
        }
        initData();
        return view;
    }
    public  abstract void setTop();
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
        getActivity().finish();
    }
    public void setSubTitleListener(){}

    @Override
    public void showMessage(String message) {
        G.showToast(getActivity(), message);
    }

    @Override
    public void showProgress() {
        if (dialog == null) {
            dialog = new LoadingDialog(getActivity());
        }
        dialog.show();
    }

    @Override
    public void hideProgress() {
        dialog.dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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

package com.wh.kotlinnotebook.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.wh.kotlinnotebook.R;

/**
 * 作者： wh
 * 时间：  2017/8/21
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class LoadingDialog extends Dialog{
    private ImageView iv_loading;
    public LoadingDialog( Context context) {
        super(context, R.style.dialog);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        iv_loading = (ImageView) findViewById(R.id.iv_loading);
        startLoading();
    }
    private void startLoading(){
          Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.loading);
        iv_loading.startAnimation(animation);
    }
}

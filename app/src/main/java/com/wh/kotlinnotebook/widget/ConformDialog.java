package com.wh.kotlinnotebook.widget;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.wh.kotlinnotebook.R;
import com.wh.kotlinnotebook.util.G;

/**
 * 作者： wh
 * 时间：  2017/8/2
 * 名称：确认对话框
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ConformDialog extends android.app.AlertDialog implements View.OnClickListener{
    /**
     * 标题
     */
    private TextView tvContent;
    /**
     * 取消
     */
    private  Button btCancel;
    /**
     * 确认
     */
    private   Button btConform;
    private Activity context;
    /**
     * 回调函数
     */
   private    OnConformClickListener onConformClickListener;
    /**
     * 选中删除的位置
     */
    private int position;
    public ConformDialog(Activity context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_conform);
        setDialogWidth();
        tvContent = (TextView) findViewById(R.id.tv_content);
        btCancel = (Button)findViewById(R.id.bt_cancel);
        btConform = (Button)findViewById(R.id.bt_conform);
        btCancel.setOnClickListener(this);
        btConform.setOnClickListener(this);
    }

    public void setTitle(String title){
        tvContent.setText(title);
    }
    /**
     * 设置屏幕的宽度
     */
    private void setDialogWidth(){
        G.initDisplaySize(context);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = (int) (G.size.W* 0.8);
        params.height=WindowManager.LayoutParams.WRAP_CONTENT;
        params.flags = WindowManager. LayoutParams.FLAG_DIM_BEHIND;
        getWindow().setAttributes(params);
    }
    @Override
    public void onClick(View v) {
        int viewId= v.getId();
        if (viewId==R.id.bt_cancel){
            dismiss();
        }else if (viewId==R.id.bt_conform){
            if (onConformClickListener!=null){
                onConformClickListener.onConform(position);
            }
            dismiss();
        }
    }

     public void setPosition(int position){
         this.position = position;
     }
    public void setOnConformClickListener(OnConformClickListener onConformClickListener) {
        this.onConformClickListener = onConformClickListener;
    }

    public interface  OnConformClickListener{
       void onConform(int position);
   }
}

package com.wh.kotlinnotebook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wh.kotlinnotebook.util.G;

/**
 * 作者： wh
 * 时间：  2017/8/29
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        //接收广播：系统启动完成后运行程序  
        if (action.equals("android.intent.action.BOOT_COMPLETED")){
            G.log("xxxxxxxx"+"BOOT_COMPLETED");
        }//接收广播：设备上新安装了一个应用程序包后自动启动新安装应用程序。
        else if (action.equals("android.intent.action.PACKAGE_ADDED")){
            G.log("xxxxxxxx"+"PACKAGE_ADDED");
        }//接收广播：设备上删除了一个应用程序包。    
        else if (action.equals("android.intent.action.PACKAGE_REMOVED")){
            G.log("xxxxxxxx"+"PACKAGE_REMOVED");
        }
    }
}

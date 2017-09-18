package com.wh.kotlinnotebook.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wh.kotlinnotebook.MyApplication;
import com.wh.kotlinnotebook.util.PackageUtil;

/**
 * 作者： wh
 * 时间：  2017/8/29
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class ClockDbHelper extends SQLiteOpenHelper{
    private static final String DBNAME = "user.db";
    private static final String TABNAME="clock";
    private static final int version =  PackageUtil.getVersionCode();
    private static ClockDbHelper clockDb;
    public static ClockDbHelper getInstance(){
        if (clockDb==null){
            clockDb = new ClockDbHelper(MyApplication.getInstance().getApplicationContext());
        }
        return clockDb;
    }
    public ClockDbHelper(Context context) {
        super(context, DBNAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table " +TABNAME+
                "(id integer primary key autoincrement," +
                "time varchar(50) not null,"+
                "ringtone  integer," +
                "weeks varchar(50) not null," +
                "weekNumbers varchar(50) not null," +
                "remark varchar(50) not null," +
                "delay  integer," +
                "isOpen integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists " + TABNAME;
        db.execSQL(sql);
        this.onCreate(db);
    }
}

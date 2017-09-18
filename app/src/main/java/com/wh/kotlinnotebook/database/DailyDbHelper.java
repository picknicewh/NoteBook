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
public class DailyDbHelper extends SQLiteOpenHelper{
    private static final String DBNAME = "daily.db";
    private static final String TABNAME="daily";
    private static final int version =  PackageUtil.getVersionCode();
    private static DailyDbHelper clockDb;
    public static DailyDbHelper getInstance(){
        if (clockDb==null){
            clockDb = new DailyDbHelper(MyApplication.getInstance().getApplicationContext());
        }
        return clockDb;
    }
    public DailyDbHelper(Context context) {
        super(context, DBNAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table " +TABNAME+
                "(id integer primary key autoincrement," +
                "date varchar(50) not null,"+
                "emotion  integer," +
                "weather  integer," +
                "font_size  integer," +
                "font_color  integer," +
                "content_bg  integer," +
                "content varchar(50) not null)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists " + TABNAME;
        db.execSQL(sql);
        this.onCreate(db);
    }
}

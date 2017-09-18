package com.wh.kotlinnotebook.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wh.kotlinnotebook.bean.DailyVo;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： wh
 * 时间：  2017/8/29
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class DailyDb {
    private static final String TABLENAME= "daily";
    private    SQLiteDatabase db;
    private static DailyDb dailyDb;
    public static DailyDb getInstance(){
        if (dailyDb==null){
            dailyDb = new DailyDb();
        }
        return dailyDb;
    }
    public DailyDb(){
       db = DailyDbHelper.getInstance().getWritableDatabase();
    }

    public  void insert(String date,int emotion ,int weather,int font_size,int font_color,int content_bg,String content){
      String sql = "insert into " +TABLENAME +"(date,emotion,weather,font_size,font_color,content_bg,content) values"+
              "('"+date+"',"+emotion+","+weather+","+font_size+","+font_color+","+content_bg+",'"+content+"')";
       db.execSQL(sql);
    }
    public  List<DailyVo> getDailyList(){
        List<DailyVo> dailyVos = new ArrayList<>();
        String sql = "select * from "+TABLENAME;
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            DailyVo dailyVo = new DailyVo();
           int idIndex = cursor.getColumnIndex("id");
           int dateIndex = cursor.getColumnIndex("date");
           int weatherIndex = cursor.getColumnIndex("weather") ;
            int emotionIndex = cursor.getColumnIndex("emotion") ;
           int fontSizeIndex = cursor.getColumnIndex("font_size");
           int fontColorIndex = cursor.getColumnIndex("font_color");
           int contentBgIndex = cursor.getColumnIndex("content_bg");
           int contentIndex = cursor.getColumnIndex("content");
            dailyVo.setId(cursor.getInt(idIndex));
            dailyVo.setDate(cursor.getString(dateIndex));
            dailyVo.setContent(cursor.getString(contentIndex));
            dailyVo.setWeather(cursor.getInt(weatherIndex));
            dailyVo.setEmotion(cursor.getInt(emotionIndex));
            dailyVo.setContent_bg(cursor.getInt(contentBgIndex));
            dailyVo.setFont_size(cursor.getInt(fontSizeIndex));
            dailyVo.setFont_color(cursor.getInt(fontColorIndex));
            dailyVos.add(dailyVo);
       }
        cursor.close();
        return dailyVos;
    }
    public DailyVo getDaily(String date) {
        List<DailyVo> clockVos = getDailyList();
        for (int i = 0 ; i<clockVos.size();i++){
            if (clockVos.get(i).getDate().equals(date)){
               return clockVos.get(i);
            }
        }
        return null;
    }
    public  void  updateData(int id,String date,int emotion ,int weather,int font_size,int font_color,int content_bg,String content){
        String sql = "update "+TABLENAME + " set date = '"+date+"',emotion = "+emotion+",weather = "+weather+",font_size = "+font_size+",font_color = "+font_color+"," +
                "content_bg = "+content_bg+ ",content = '"+content+"' where id = "+id ;
        db.execSQL(sql);
    }
    /**
     *删除通过id号删除单个闹钟
     * @param id 唯一用户id号
     */
    public  void  deleteById(int id){
        String sql="delete from "+TABLENAME+" where id =" + id;
        db.execSQL(sql);
    }
    /**
     * 删除所有
     */
    public  void delete(){
        String sql="delete from  "+TABLENAME;
        db.execSQL(sql);
    }
    /**
     * 判断是否数据是否为空
     */
    public  boolean  isEmpty(){
        Cursor cursor  =db.rawQuery("select * from "+TABLENAME,null);
        while (cursor.moveToNext()){
            return false;
        }
        return true;
    }
}

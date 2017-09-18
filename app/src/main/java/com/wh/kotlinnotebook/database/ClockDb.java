package com.wh.kotlinnotebook.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wh.kotlinnotebook.bean.ClockVo;

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
public class ClockDb {
    private static final String TABLENAME= "clock";
    private    SQLiteDatabase db;
    private static  ClockDb clockDb;
    public static ClockDb getInstance(){
        if (clockDb==null){
            clockDb = new ClockDb();
        }
        return clockDb;
    }
    public ClockDb(){
       db = ClockDbHelper.getInstance().getWritableDatabase();
    }
    public  void insert(String time,int ringtone ,String weeks,String weekNumbers,String remark,int delay,int isOpen){
      String sql = "insert into " +TABLENAME +"(time,ringtone,weeks,weekNumbers,remark,delay,isOpen) values"+
              "('"+time+"',"+ringtone+",'"+weeks+"','"+weekNumbers+"','"+remark+"',"+delay+","+isOpen+")";
       db.execSQL(sql);
    }
    public  List<ClockVo> getClockList(){
        List<ClockVo> clockVos = new ArrayList<>();
        String sql = "select * from "+TABLENAME;
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
           int idIndex = cursor.getColumnIndex("id");
           int timeIndex = cursor.getColumnIndex("time");
           int ringtoneIndex = cursor.getColumnIndex("ringtone") ;
           int  weeksIndex = cursor.getColumnIndex("weeks");
           int remarkIndex = cursor.getColumnIndex("remark");
           int delayIndex = cursor.getColumnIndex("delay");
           int isOpenIndex = cursor.getColumnIndex("isOpen");
            int weekNumbersIndex =cursor.getColumnIndex("weekNumbers");
           ClockVo clockVo  = new ClockVo();
           clockVo.setTime(cursor.getString(timeIndex));
           clockVo.setWeeks(cursor.getString(weeksIndex));
           clockVo.setRemark(cursor.getString(remarkIndex));
           clockVo.setRingtone(cursor.getInt(ringtoneIndex));
           clockVo.setDelay(cursor.getInt(delayIndex));
           clockVo.setIsOpen(cursor.getInt(isOpenIndex));
           clockVo.setId(cursor.getInt(idIndex));
            clockVo.setWeekNumbers(cursor.getString(weekNumbersIndex));
           clockVos.add(clockVo);
       }
       cursor.close();
        return clockVos;
    }
    public ClockVo getClockByTime(String time) {
        List<ClockVo> clockVos = getClockList();
        for (int i = 0 ; i<clockVos.size();i++){
            if (clockVos.get(i).getTime().equals(time)){
               return clockVos.get(i);
            }
        }
        return null;
    }
    public  void  updateData(int id,String time,int ringtone ,String weeks,String weekNumbers,String remark,int delay,int isOpen){
        String sql = "update "+TABLENAME + " set isOpen = "+isOpen+"," +
                "time = '"+time+"',weeks = '"+weeks+"',weekNumbers = '" +
                ""+weekNumbers+"',remark = '"+remark+"',ringtone = "+ringtone+",delay = "+delay+" where id = "+id ;
        db.execSQL(sql);
    }
    public  void  updateIsOpen(int isOpen,int id){
        String sql = "update "+TABLENAME + "set isOpen = "+isOpen +"where id = "+id ;
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

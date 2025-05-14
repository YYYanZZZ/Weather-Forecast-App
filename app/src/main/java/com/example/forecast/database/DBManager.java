package com.example.forecast.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    public static SQLiteDatabase database;
    //初始化数据库
    public static void iniDB(Context context) {
        Dbhelper dbhelper = new Dbhelper(context);
        database = dbhelper.getWritableDatabase();
    }

    //查找数据库中城市列表
    public static List<String> queryAllcityName() {
        Cursor cursor = database.query("info", null, null, null, null, null, null);
        List<String> citylist = new ArrayList<>();
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String city = cursor.getString(cursor.getColumnIndex("city"));
            citylist.add(city);
        }
        return citylist;
    }

    //根据城市名称，替换信息内容
    public static int updateInfoByCity(String city, String content) {
        ContentValues values = new ContentValues();
        values.put("content", content);
        return database.update("info", values, "city=?", new String[]{city});

    }

    //新增
    public static long addCityInfo(String city, String content) {
           ContentValues values = new ContentValues();
            values.put("city", city);
            values.put("content", content);
            return database.insert("info", null, values);

    }

    //根据名字查询内容
    public  static  String queryInfoByCity(String city)
    {
        Cursor cursor=database.query("info",null,"city=?",new String[]{city},null,null,null);
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            @SuppressLint("Range") String content =cursor.getString(cursor.getColumnIndex("content"));
            return content;
        }
        return null;
    }
    /*最多存储五个城市信息*/
    public static  int getCityCount()
    {
        Cursor cursor=database.query("info",null,null,null,null,null,null);
        int count =cursor.getCount();
        return count;
    }
    /*查询全部信息*/
    public  static  List<Databasebean> queryAllInfo()
    {
       Cursor cursor= database.query("info",null,null,null,null,null,null);
       List<Databasebean> list=new ArrayList<>();
       while (cursor.moveToNext())
       {
           @SuppressLint("Range") int id=cursor.getInt(cursor.getColumnIndex("_id"));
           @SuppressLint("Range") String city=cursor.getString(cursor.getColumnIndex("city"));
          @SuppressLint("Range") String content= cursor.getString(cursor.getColumnIndex("content"));
          Databasebean bean=new Databasebean(id,city,content);
          list.add(bean);

       }
       return list;
    }

    public static int deleteInfoByCity(String city) {
        return database.delete("info","city=?",new String[]{city});
    }

    public static void deleteAllInfo() {
        String sql="delete from info";
        database.execSQL(sql);
    }
}

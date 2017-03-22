package com.idc.hust.NotificationMonitor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lpchou on 2017/3/8.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="notificationInfo.db";
    public static final String TABLE_NAME="notificationinfo";
    public static final int VERSION=1;
    public static final String TABLE_CREATE_SQL="create table if not exists "+TABLE_NAME+"(recordid integer primary key autoincrement,packagename text,title text," +
            "contenttitle text,content text,infotime long)";
    public DatabaseHelper(Context context){
        //第三个参数CursorFactory指定在执行查询时获得一个游标实例的工厂类，设置为null，代表使用系统默认的工厂类
        super(context,DATABASE_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

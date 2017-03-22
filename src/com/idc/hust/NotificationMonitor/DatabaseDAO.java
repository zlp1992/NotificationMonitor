package com.idc.hust.NotificationMonitor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;

/**
 * Created by lpchou on 2017/3/8.
 */
public class DatabaseDAO {
    private DatabaseHelper helper;
    public DatabaseDAO(Context context){
        this.helper=new DatabaseHelper(context);
    }
    public boolean save(String packageName,String title,String contentTitle,String content,long when){
        try{
            SQLiteDatabase db=helper.getWritableDatabase();
            db.execSQL("insert into "+DatabaseHelper.TABLE_NAME+"(packagename,title,contenttitle,content,infotime) values(?,?,?,?,?)",
                    new Object[]{packageName,title,contentTitle,content,when });
            db.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}

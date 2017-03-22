package com.idc.hust.NotificationMonitor;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.*;

/**
 * Created by lpchou on 2017/3/13.
 */
public class FileUtil {
    public static final String FILE_NAME="notification.txt";
    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    public boolean save(String packageName,String title,String contentTitle,String content,long when){
        if(!isExternalStorageWritable()){
            Log.i("FileUtil","外部存储不可读");
        }
        File file=new File(Environment.getExternalStorageDirectory(),FILE_NAME);
        try {
            Writer writer = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(file,true),"UTF-8"));
            writer.write(packageName+";"+title+";"+contentTitle+";"+content+";"+when+"\r\n");
            writer.flush();
            writer.close();
            Log.i("FileUtil","写入成功");
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}

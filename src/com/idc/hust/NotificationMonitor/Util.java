package com.idc.hust.NotificationMonitor;

import android.app.ActivityManager;
import android.app.Notification;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lpchou on 2017/3/7.
 */
public class Util {
    public static final String TITLE_KEY="title";
    public static final String CONTENT_TITLE_KEY="contentTitle";
    public static final String CONTENT_TEXT_KEY="contentText";
    public static final String CONTENT_SUBTEXT_KEY="contentSubtext";
    public static final String WHEN_KEY="when";
    public static final String PACKAGENAME_KEY="packageName";

    public static Map<String,Object> dealNotification(String LOG_TAG, String packageName, Notification notification){
        Map<String,Object> contentMap=new HashMap<>();
        contentMap.put(PACKAGENAME_KEY,packageName);
        String title="";
        if(notification.tickerText!=null){
            title=notification.tickerText.toString();//标题
            contentMap.put(TITLE_KEY,title);
        }
        long when=notification.when;//时间
        contentMap.put(WHEN_KEY,when);
        //其他的信息存在一个bundle中，此bundle在android4.3及之前是私有的
        Bundle bundle=null;
        if(Build.VERSION.SDK_INT<=Build.VERSION_CODES.JELLY_BEAN_MR2){//android4.3及之前
            try{
                Field field=Notification.class.getDeclaredField("extras");
                bundle=(Bundle)field.get(notification);
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            bundle=notification.extras;
        }
        if(bundle==null){
            Log.e(LOG_TAG,"bundle is null");
            return null;
        }
        //内容标题、内容、副内容
        try {
            String contentTitle = (bundle.getString(Notification.EXTRA_TITLE) == null ? "" : bundle.getString(Notification.EXTRA_TITLE));
            String contentText = (bundle.getString(Notification.EXTRA_TEXT) == null ? "" : bundle.getString(Notification.EXTRA_TEXT));
            String contentSubtext = (bundle.getString(Notification.EXTRA_SUB_TEXT) == null ? "" : bundle.getString(Notification.EXTRA_SUB_TEXT));
            contentMap.put(CONTENT_TITLE_KEY,contentTitle);
            contentMap.put(CONTENT_TEXT_KEY,contentText);
            contentMap.put(CONTENT_SUBTEXT_KEY,contentSubtext);
            Log.i(LOG_TAG, "deal notification: " +" packageName: "+packageName+ " ,title: " + title + " ,when: " + longToDate(when) + " ,contentTitle: " + contentTitle
                    + " ,contentText: " + contentText + " ,contentSubText: " + contentSubtext);
        }catch (Exception e){

        }
        return contentMap;
    }
    public static String longToDate(long time){
        SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date=new Date(time);
        return format.format(date);
    }
    public static boolean isServiceRunning(Context mContext, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(100);
        if (serviceList.size() == 0) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }
}

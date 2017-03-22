package com.idc.hust.NotificationMonitor;

import android.accessibilityservice.AccessibilityService;
import android.app.Notification;
import android.os.Parcelable;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import java.util.Map;

/**
 * Created by lpchou on 2017/3/7.
 */
public class AccessNotificationService extends AccessibilityService {
    public static  final String LOG_TAG=AccessNotificationService.class.getName();
    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
       // Log.i(LOG_TAG,"receive Accessable event");
        if(accessibilityEvent.getEventType()==AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED){
            Parcelable data=accessibilityEvent.getParcelableData();
            if(data instanceof Notification){ //通知事件
                Log.i(LOG_TAG,"receive notification");
                Notification notification=(Notification)data;
                Map<String,Object> result=dealNotification(accessibilityEvent.getPackageName().toString(),notification);
                if(result!=null){
                    save(result);
                    Toast.makeText(this,"通知记录保存成功",Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    private Map<String,Object> dealNotification(String packageName,Notification notification){
        return Util.dealNotification(LOG_TAG,packageName,notification);
    }
    //保存到数据库
//    private void save(Map<String,Object> result){
//        String title=(String)result.get(Util.TITLE_KEY);
//        String contentTitle=(String)result.get(Util.CONTENT_TITLE_KEY);
//        String content=(String)result.get(Util.CONTENT_TEXT_KEY);
//        String packageName=(String)result.get(Util.PACKAGENAME_KEY);
//        long when=(long)result.get(Util.WHEN_KEY);
//        DatabaseDAO dao=new DatabaseDAO(this);
//        dao.save(packageName,title,contentTitle,content,when);
//    }
    //保存到公有文件
    private void save(Map<String,Object> result){
        String title=(String)result.get(Util.TITLE_KEY);
        String contentTitle=(String)result.get(Util.CONTENT_TITLE_KEY);
        String content=(String)result.get(Util.CONTENT_TEXT_KEY);
        String packageName=(String)result.get(Util.PACKAGENAME_KEY);
        long when=(long)result.get(Util.WHEN_KEY);
        FileUtil util=new FileUtil();
        util.save(packageName,title,contentTitle,content,when);
    }
    @Override
    public void onInterrupt() {
        Log.e(LOG_TAG,"AccessNotificationService interrupted!");
    }
}

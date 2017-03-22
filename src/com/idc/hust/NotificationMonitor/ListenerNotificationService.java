package com.idc.hust.NotificationMonitor;

import android.app.Notification;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

/**
 * Created by lpchou on 2017/3/7.
 */
public class ListenerNotificationService extends NotificationListenerService {
    public static final String LOG_TAG=ListenerNotificationService.class.getName();

    @Override
    public void onNotificationPosted(StatusBarNotification statusBarNotification) {
        //有新的通知
        Log.i(LOG_TAG,"receive notification");
        Notification notification=statusBarNotification.getNotification();
        if(notification==null) {
            return;
        }
        String packageName=statusBarNotification.getPackageName();
        Util.dealNotification(LOG_TAG,packageName,notification);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification statusBarNotification) {
        Log.i(LOG_TAG,"remove notification");
    }
}

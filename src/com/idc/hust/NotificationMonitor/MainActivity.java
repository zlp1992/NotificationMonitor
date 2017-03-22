package com.idc.hust.NotificationMonitor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
/**
 * 用于监控系统收到的通知
 * */
public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Intent serviceIntent=new Intent(this,AccessNotificationService.class);
        Intent listenerIntent=new Intent(this,ListenerNotificationService.class);
        startService(serviceIntent);
      //  startService(listenerIntent);
    }
}

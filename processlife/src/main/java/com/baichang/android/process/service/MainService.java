package com.baichang.android.process.service;

import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import com.baichang.android.process.MainActivity;

/**
 * Created by iCong on 2017/4/14.
 */

public class MainService extends Service {

  private static final String TAG = MainService.class.getSimpleName();

  @Nullable @Override public IBinder onBind(Intent intent) {
    return null;
  }

  @Override public void onCreate() {
    super.onCreate();
    Builder builder = new Builder(this);
    builder.setContentText("前台服务运行中...");
    builder.setSmallIcon(android.R.mipmap.sym_def_app_icon);
    builder.setContentTitle("iCong");
    Intent notificationIntent = new Intent(this, MainActivity.class);
    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
    builder.setContentIntent(pendingIntent);
    startForeground(1, builder.build());
  }

  @Override public int onStartCommand(Intent intent, int flags, int startId) {
    Log.d(TAG, "原地满血复活.....");
    return Service.START_STICKY;
  }

  @Override public void onDestroy() {
    super.onDestroy();
    // 该方法只有在，系统 - 设置里面 结束服务的时候回掉
    Log.d(TAG, "被杀了....");
    Log.d(TAG, "抢救中....");
    startService(new Intent(this, MainService.class));
  }

  @Override public void onTaskRemoved(Intent rootIntent) {
    super.onTaskRemoved(rootIntent);
    Log.d(TAG, "被杀了....");
    Log.d(TAG, "抢救中....");
    // 该方法在，被第三方或者任务管理器结束 回掉，原生系统，前台服务是不会被杀死。
    startService(new Intent(this, MainService.class));
  }
}

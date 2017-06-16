package com.example.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  private int notifyId = 1;
  private NotificationManager mManger;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    // init notification
    NotificationCompat.Builder builder = new Builder(this);
    builder.setSmallIcon(R.mipmap.ic_launcher_round)
        .setContentTitle("Title")
        .setContentText("Hello Notification");
    // set big icon
    builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
    // set other text
    // 24API 之前设置这个会影响进度条的显示，24以后不会。
    //builder.setSubText("This is other text");
    // set view
    //builder.setContent(new RemoteViews(getPackageName(), R.layout.notification_view));
    //builder.setCustomContentView(new RemoteViews(getPackageName(), R.layout.notification_view));
    // set time
    builder.setWhen(System.currentTimeMillis());
    builder.setShowWhen(true);
    // set progressbar
    int max = 100;
    int currentProgress = 50;
    boolean isIndeterminate = false;// 是否不明确的进度条
    builder.setColor(Color.RED);// 进度条颜色
    builder.setProgress(max, currentProgress, isIndeterminate);
    // 通知右下角数字
    builder.setNumber(1000);
    // 设置通知优先级
    builder.setPriority(Notification.PRIORITY_HIGH);
    // 设置通知震动模式
    // 延迟200毫秒震动3s，再延迟400毫秒震动1s
    long[] pattern = new long[] { 200, 3000, 400, 1000 };
    //builder.setVibrate(pattern);
    // 自定义呼吸灯
    int argb = 0xffff0000; // led灯光颜色
    int onMs = 3000;        // led亮灯持续时间
    int offMs = 500;        // led熄灭持续时间
    builder.setLights(argb, onMs, offMs);
    // 点击事件
    int flags = PendingIntent.FLAG_UPDATE_CURRENT;
    Intent intent = new Intent(this, MainActivity.class);
    PendingIntent pi = PendingIntent.getActivity(this, 0, intent, flags);
    builder.setContentIntent(pi);
    // 全屏通知
    Intent intent1 = new Intent(this, MainActivity.class);
    PendingIntent pi1 = PendingIntent.getBroadcast(this, 0, intent1, 0);
    builder.setFullScreenIntent(pi1, true);
    // send notification
    mManger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    Notification notification = builder.build();
    notification.flags = Notification.FLAG_NO_CLEAR | Notification.FLAG_SHOW_LIGHTS;
    mManger.notify(notifyId, notification);
  }

  public void cancel(View view) {
    mManger.cancel(notifyId);
  }
}

package com.example.custom;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

  private ProgressView progressView;
  private int count = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    progressView = (ProgressView) findViewById(R.id.progress_view);
  }

  @Override
  protected void onResume() {
    super.onResume();
    Timer timer = new Timer();
    timer.schedule(task, 1000, 1000);
  }

  Handler myHandler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      progressView.setCurrentProgress(count);
      count++;
      if (count > 100) {
        task.cancel();
      }
    }
  };
  TimerTask task = new TimerTask() {
    @Override
    public void run() {
      myHandler.sendEmptyMessage(0);
    }
  };

  @Override
  protected void onDestroy() {
    super.onDestroy();
    task.cancel();
    myHandler.removeMessages(0);
  }
}

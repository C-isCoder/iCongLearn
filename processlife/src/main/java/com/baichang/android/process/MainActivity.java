package com.baichang.android.process;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.baichang.android.process.service.MainService;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = MainActivity.class.getSimpleName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    startService(new Intent(this, MainService.class));
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "被杀了....");
    Log.d(TAG, "抢救中....");
    startService(new Intent(this, MainService.class));
  }
}

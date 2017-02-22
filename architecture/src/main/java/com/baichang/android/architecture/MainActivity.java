package com.baichang.android.architecture;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.baichang.android.architecture.MVC.MVCLoginActivity;
import com.baichang.android.architecture.MVP.MVPLoginActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_layout);
    findViewById(R.id.btn_mvc).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, MVCLoginActivity.class));
      }
    });
    findViewById(R.id.btn_mvp).setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(getIntent().setClass(MainActivity.this, MVPLoginActivity.class));
      }
    });
  }
}

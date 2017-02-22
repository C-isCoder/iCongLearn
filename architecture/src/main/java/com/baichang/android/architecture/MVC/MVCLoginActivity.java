package com.baichang.android.architecture.MVC;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.baichang.android.architecture.R;

public class MVCLoginActivity extends AppCompatActivity implements OnClickListener {

  private EditText etName;
  private EditText etPw;
  private ProgressBar pbBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    initView();
  }

  // view init
  private void initView() {
    etName = (EditText) findViewById(R.id.et_name);
    etPw = (EditText) findViewById(R.id.et_pw);
    findViewById(R.id.btn_login).setOnClickListener(this);
    pbBar = (ProgressBar) findViewById(R.id.pb_bar);
  }

  @Override
  public void onClick(View v) {
    pbBar.setVisibility(View.VISIBLE);
    //模拟子线程耗时操作
    new Thread() {
      @Override
      public void run() {
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        //模拟登录成功
        if ("abc".equals(etName.getText().toString()) && "123".equals(etPw.getText().toString())) {
          // 登陆成功
          runOnUiThread(new Runnable() {
            @Override
            public void run() {
              pbBar.setVisibility(View.GONE);
              Toast.makeText(MVCLoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
            }
          });
        } else {
          // 登陆失败
          runOnUiThread(new Runnable() {
            @Override
            public void run() {
              pbBar.setVisibility(View.GONE);
              Toast.makeText(MVCLoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
            }
          });
        }
      }
    }.start();
  }
}

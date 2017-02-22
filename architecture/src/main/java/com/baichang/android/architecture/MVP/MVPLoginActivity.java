package com.baichang.android.architecture.MVP;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.baichang.android.architecture.R;

public class MVPLoginActivity extends AppCompatActivity implements ILoginView, OnClickListener {

  private EditText etName;
  private EditText etPw;
  private ProgressBar pbBar;
  private ILoginPresent loginPresent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    initView();
    initMVP();
  }

  // mvp init
  private void initMVP() {
    loginPresent = new ILoginPresentImpl(this, this);
  }

  // view init
  private void initView() {
    etName = (EditText) findViewById(R.id.et_name);
    etPw = (EditText) findViewById(R.id.et_pw);
    findViewById(R.id.btn_login).setOnClickListener(this);
    pbBar = (ProgressBar) findViewById(R.id.pb_bar);
  }

  @Override
  public void showProgressBar() {
    pbBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideProgressBar() {
    pbBar.setVisibility(View.GONE);
  }

  @Override
  public void gotoHome() {
    //跳转到主页
    Toast.makeText(this, "跳转主页", Toast.LENGTH_SHORT).show();
  }

  @Override
  public void showMessage(String msg) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
  }


  @Override
  public void onClick(View v) {
    // 登陆
    loginPresent.validateCredentials(etName.getText().toString(), etPw.getText().toString());
  }

  @Override
  protected void onDestroy() {
    loginPresent.onDestroy();
    super.onDestroy();
  }
}

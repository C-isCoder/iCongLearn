package com.baichang.android.architecture.login;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baichang.android.architecture.R;
import com.baichang.android.architecture.common.BaseActivity;
import com.baichang.android.architecture.login.present.LoginPresent;
import com.baichang.android.architecture.login.present.impl.LoginPresentImpl;
import com.baichang.android.architecture.login.view.LoginView;
import com.baichang.android.architecture.news.NewsActivity;

public class LoginActivity extends BaseActivity implements LoginView {

  @BindView(R.id.et_name)
  EditText etName;
  @BindView(R.id.et_pw)
  EditText etPw;
  @BindView(R.id.pb_bar)
  ProgressBar pbBar;
  @BindView(R.id.iv_avatar)
  ImageView ivAvatar;

  private LoginPresent loginPresent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
    etName.setText("abc");
    etPw.setText("123");
    initMVP();
  }

  // mvp init
  private void initMVP() {
    loginPresent = new LoginPresentImpl(this, this);
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
    startActivity(new Intent(this, NewsActivity.class));
  }

  @Override
  public void clean() {
    etName.setText("");
    etPw.setText("");
  }

  @Override
  public void setAvatar(Drawable drawable) {
    ivAvatar.setImageDrawable(drawable);
  }

  @Override
  public void showMessage(String msg) {
    showToast(msg);
  }


  public void login(View view) {
    loginPresent.login(etName.getText().toString(), etPw.getText().toString());
  }

  @Override
  protected void onDestroy() {
    loginPresent.onDestroy();
    super.onDestroy();
  }
}

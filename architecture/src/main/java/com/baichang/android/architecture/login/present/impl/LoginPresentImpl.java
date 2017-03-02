package com.baichang.android.architecture.login.present.impl;


import android.app.Activity;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import com.baichang.android.architecture.R;
import com.baichang.android.architecture.login.model.LoginInteraction;
import com.baichang.android.architecture.login.model.impl.LoginInteractionImpl;
import com.baichang.android.architecture.login.present.LoginPresent;
import com.baichang.android.architecture.login.view.LoginView;

/**
 * Created by test on 2017/2/22.
 */

public class LoginPresentImpl implements LoginPresent, LoginInteraction.BaseListener {

  private LoginView loginView;

  private LoginInteraction loginInteraction;

  private Activity activity;

  public LoginPresentImpl(Activity activity, LoginView loginView) {
    this.loginView = loginView;
    this.activity = activity;
    this.loginInteraction = new LoginInteractionImpl();
    RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(activity.getResources(),
        BitmapFactory.decodeResource(activity.getResources(), R.mipmap.avatar));
    drawable.setCircular(true);
    loginView.setAvatar(drawable);
  }

  @Override
  public void login(String username, String password) {
    loginView.showProgressBar();
    loginInteraction.Login(username, password, this);
  }

  @Override
  public void onDestroy() {
    loginView = null;
    activity = null;
  }

  @Override
  public void success(Object o) {
    activity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        loginView.showMessage("登陆成功");
        loginView.hideProgressBar();
        loginView.gotoHome();
      }
    });
  }

  @Override
  public void error(String error) {
    activity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        loginView.hideProgressBar();
        loginView.clean();
        loginView.showMessage("登陆失败，用户名或密码错误");
      }
    });
  }
}

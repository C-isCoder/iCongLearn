package com.baichang.android.architecture.login.present;


import android.app.Activity;
import com.baichang.android.architecture.login.model.ILoginInteraction;
import com.baichang.android.architecture.login.model.ILoginInteractionImpl;
import com.baichang.android.architecture.login.view.ILoginView;

/**
 * Created by test on 2017/2/22.
 */

public class ILoginPresentImpl implements ILoginPresent, ILoginInteraction.BaseListener {

  private ILoginView loginView;

  private ILoginInteraction loginInteraction;

  private Activity activity;

  public ILoginPresentImpl(Activity activity, ILoginView loginView) {
    this.loginView = loginView;
    this.activity = activity;
    this.loginInteraction = new ILoginInteractionImpl();
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

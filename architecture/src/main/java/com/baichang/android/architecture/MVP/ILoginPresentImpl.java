package com.baichang.android.architecture.MVP;


import android.app.Activity;
import com.baichang.android.architecture.MVP.ILoginInteractor.ILoginListener;

/**
 * Created by test on 2017/2/22.
 */

public class ILoginPresentImpl implements ILoginPresent, ILoginListener {

  private ILoginView loginView;

  private ILoginInteractor loginInteractor;

  private Activity activity;

  public ILoginPresentImpl(Activity activity, ILoginView loginView) {
    this.loginView = loginView;
    this.activity = activity;
    this.loginInteractor = new ILoginInteractorImpl();
  }

  @Override
  public void success() {
    if (loginView != null && activity != null) {
      activity.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          loginView.showMessage("登陆成功");
          loginView.hideProgressBar();
          loginView.gotoHome();
        }
      });
    }
  }

  @Override
  public void error() {
    if (loginView != null && activity != null) {
      activity.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          loginView.showMessage("登陆失败");
          loginView.hideProgressBar();
        }
      });
    }
  }

  @Override
  public void validateCredentials(String username, String password) {
    if (loginView != null) {
      loginView.showProgressBar();
    }
    loginInteractor.Login(username, password, this);
  }

  @Override
  public void onDestroy() {
    loginView = null;
    activity = null;
  }
}

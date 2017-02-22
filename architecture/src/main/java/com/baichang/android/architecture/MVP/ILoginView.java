package com.baichang.android.architecture.MVP;

/**
 * Created by iscod on 2017/2/21.
 */

public interface ILoginView {

  void showProgressBar();

  void hideProgressBar();

  void gotoHome();

  void showMessage(String msg);
}

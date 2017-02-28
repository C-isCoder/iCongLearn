package com.baichang.android.architecture.common;

import android.content.Intent;

/**
 * Created by iCong on 2017/2/28.
 *
 * C is a Coder
 */

public interface IBaseView {

  void showProgressBar();

  void hideProgressBar();

  void showMessage(String msg);

  void startActivity(Intent intent, Class target);
}

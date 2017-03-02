package com.baichang.android.architecture.login.present;

/**
 * Created by iscod on 2017/2/21.
 */

public interface LoginPresent {

  void login(String username, String password);

  void onDestroy();
}

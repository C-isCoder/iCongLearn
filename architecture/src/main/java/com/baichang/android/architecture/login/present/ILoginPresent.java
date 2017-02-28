package com.baichang.android.architecture.login.present;

/**
 * Created by iscod on 2017/2/21.
 */

public interface ILoginPresent {

  void validateCredentials(String username, String password);

  void onDestroy();
}

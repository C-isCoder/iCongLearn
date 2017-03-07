package com.baichang.android.architecture.login.present;

import com.baichang.android.architecture.common.IBasePresent;

/**
 * Created by iscod on 2017/2/21.
 */

public interface LoginPresent extends IBasePresent {

  void login(String username, String password);

  void onDestroy();
}

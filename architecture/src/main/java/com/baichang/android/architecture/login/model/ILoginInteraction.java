package com.baichang.android.architecture.login.model;

import com.baichang.android.architecture.common.IBaseInteraction;

/**
 * Created by test on 2017/2/22.
 */

public interface ILoginInteraction extends IBaseInteraction {

  void Login(String name, String pw, ILoginInteraction.BaseListener listener);
}

package com.baichang.android.architecture.login.model;

/**
 * Created by test on 2017/2/22.
 */

public interface ILoginInteraction {
  interface ILoginListener {

    void success();

    void error();
  }

  void Login(String name, String pw, ILoginListener listener);
}

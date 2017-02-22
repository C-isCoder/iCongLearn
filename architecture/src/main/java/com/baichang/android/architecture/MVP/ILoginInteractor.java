package com.baichang.android.architecture.MVP;

/**
 * Created by test on 2017/2/22.
 */

public interface ILoginInteractor {
  interface ILoginListener {

    void success();

    void error();
  }

  void Login(String name, String pw, ILoginListener listener);
}

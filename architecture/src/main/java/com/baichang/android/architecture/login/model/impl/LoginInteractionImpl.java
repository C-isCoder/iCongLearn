package com.baichang.android.architecture.login.model.impl;

import com.baichang.android.architecture.login.model.LoginInteraction;

/**
 * Created by test on 2017/2/22.
 */

public class LoginInteractionImpl implements LoginInteraction {

  @Override public void Login(final String name, final String pw, final BaseListener listener) {
    //模拟子线程耗时操作
    new Thread() {
      @Override public void run() {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        //模拟登录成功
        if ("abc".equals(name) && "123".equals(pw)) {
          listener.success(null);
        } else {
          listener.error(null);
        }
      }
    }.start();
  }
}

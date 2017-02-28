package com.baichang.android.architecture.login.model;

/**
 * Created by test on 2017/2/22.
 */

public class ILoginInteractorImpl implements ILoginInteraction {

  @Override
  public void Login(final String name, final String pw, final ILoginListener listener) {
    //模拟子线程耗时操作
    new Thread() {
      @Override
      public void run() {
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        //模拟登录成功
        if ("abc".equals(name) && "123".equals(pw)) {
          listener.success();
        } else {
          listener.error();
        }
      }
    }.start();
  }
}

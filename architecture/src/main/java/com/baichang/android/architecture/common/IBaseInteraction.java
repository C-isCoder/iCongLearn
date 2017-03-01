package com.baichang.android.architecture.common;

/**
 * Created by iCong on 2017/3/1.
 *
 * C is a Coder
 */

public interface IBaseInteraction {

  interface BaseListener<T> {

    void success(T t);

    void error(String error);
  }
}

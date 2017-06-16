package com.baichang.android.architecture.common;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;

/**
 * Created by iCong on 2017/2/28.
 *
 * C is a Coder
 */

public class BaseData<T> {

  @Expose public String date;
  @Expose public ArrayList<T> stories;
}

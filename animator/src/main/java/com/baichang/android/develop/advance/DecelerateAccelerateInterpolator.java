package com.baichang.android.develop.advance;

import android.animation.TimeInterpolator;

/**
 * Created by iscod. Time:2017/1/22-10:10.
 */

public class DecelerateAccelerateInterpolator implements TimeInterpolator {

  @Override public float getInterpolation(float input) {
    float result;
    if (input <= 0.5) {
      result = (float) (Math.sin(Math.PI * input)) / 2;
    } else {
      result = (float) (2 - Math.sin(Math.PI * input)) / 2;
    }
    return result;
  }
}

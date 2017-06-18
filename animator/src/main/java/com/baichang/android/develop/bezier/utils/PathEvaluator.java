package com.baichang.android.develop.bezier.utils;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by iCong on 2017/6/18.
 */

public class PathEvaluator implements TypeEvaluator<PointF> {
  private PointF mFlag;

  public PathEvaluator(PointF flag) {
    mFlag = flag;
  }

  @Override public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
    return BezierUtil.CalculateBezierPointForQuadratic(fraction, startValue, mFlag, endValue);
  }
}

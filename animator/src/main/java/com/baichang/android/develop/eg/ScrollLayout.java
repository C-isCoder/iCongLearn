package com.baichang.android.develop.eg;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by iscod. Time:2017/2/5-15:35.
 */

public class ScrollLayout extends LinearLayout {

  private SparseArray<View> childViews;

  private SparseArray<Float> childPoints;

  public ScrollLayout(Context context) {
    super(context);
  }

  public ScrollLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ScrollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    init();
  }

  private void init() {
    int childCount = getChildCount();
    if (childCount != 4) {
      throw new IllegalArgumentException("必须是4个子View");
    }
    if (childViews == null) {
      childViews = new SparseArray<>(4);
    }
    childViews.put(0, getChildAt(0));
    childViews.put(1, getChildAt(1));
    childViews.put(2, getChildAt(2));
    childViews.put(3, getChildAt(3));
    if (childPoints == null) {
      childPoints = new SparseArray<>(4);
    }
    childPoints.put(0, childViews.get(0).getX());
    childPoints.put(1, childViews.get(1).getX());
    childPoints.put(2, childViews.get(2).getX());
    childPoints.put(3, childViews.get(3).getX());
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    super.onLayout(changed, l, t, r, b);
  }

  private ObjectAnimator startAnimator(View view, float current) {
    Log.d("CID", "current: " + current);
    ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", -current, current);
    animator.setDuration(2000);
    animator.start();
    return animator;
  }
}

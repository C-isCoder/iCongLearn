package com.baichang.android.develop.coordinatorlayout.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by iCong on 2017/3/9.
 *
 * RecyclerView 滚动ITEM到屏幕中间
 */

public class CenterLayoutManager extends LinearLayoutManager {

  public CenterLayoutManager(Context context) {
    super(context);
  }

  public CenterLayoutManager(Context context, int orientation, boolean reverseLayout) {
    super(context, orientation, reverseLayout);
  }

  public CenterLayoutManager(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state,
      int position) {
    RecyclerView.SmoothScroller smoothScroller =
        new CenterSmoothScroller(recyclerView.getContext());
    smoothScroller.setTargetPosition(position);
    startSmoothScroll(smoothScroller);
  }

  private static class CenterSmoothScroller extends LinearSmoothScroller {

    CenterSmoothScroller(Context context) {
      super(context);
    }

    @Override public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd,
        int snapPreference) {
      return (boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2);
    }
  }
}

package com.baichang.android.develop.nodatalayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import com.baichang.android.develop.R;

/**
 * Created by iCong on 2017/3/14.
 */

public class StateLinearLayout extends LinearLayout implements OnClickListener {

  private View mNoDataView;
  private Button btnRefresh;
  private SparseArray<View> mViewArray;

  public StateLinearLayout(Context context) {
    super(context);
  }

  public StateLinearLayout(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public StateLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    super.onLayout(changed, l, t, r, b);
    if (changed) {
      init();
    }
  }

  private void init() {
    int size = getChildCount();
    Log.d("SIZE", size + "");
    if (mViewArray == null) {
      mViewArray = new SparseArray<>(size);
    }
    for (int i = 0; i < size; i++) {
      mViewArray.put(i, getChildAt(i));
    }
  }

  public void loadNoDataView() {
    mNoDataView = LayoutInflater.from(getContext()).inflate(R.layout.no_data_layout, null);
    btnRefresh = (Button) mNoDataView.findViewById(R.id.btn_refresh);
    btnRefresh.setOnClickListener(this);
    removeAllViews();
    addView(mNoDataView);
  }

  public void loadNormalView() {
    removeView(mNoDataView);
    int size = getChildCount();
    for (int i = 0; i < size; i++) {
      addView(mViewArray.get(i));
    }
  }

  @Override public void onClick(View v) {
    if (listener != null) {
      listener.onRefresh();
    }
  }

  private OnRefreshListener listener;

  public void setOnRefreshListener(OnRefreshListener listener) {
    this.listener = listener;
  }

  public interface OnRefreshListener {

    void onRefresh();
  }
}

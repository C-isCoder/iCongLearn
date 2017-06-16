package com.baichang.android.develop.coordinatorlayout.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.baichang.android.develop.R;

/**
 * Created by iCong on 2017/3/9.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {

  private static final String TAG = MyAdapter.class.getSimpleName();
  private String[] mList;
  private SparseArray<TextView> mViews;

  public MyAdapter(String[] list) {
    mList = list;
    mViews = new SparseArray<>(list.length);
  }

  @Override public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new Holder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false));
  }

  @Override public void onBindViewHolder(Holder holder, int position) {
    holder.tvFairy.setText(mList[position]);
    mViews.put(position, holder.tvFairy);
  }

  @Override public int getItemCount() {
    return mList.length;
  }

  public void setSelected(int position) {
    Log.d(TAG, "setSelected: " + position);
    int size = mViews.size();
    for (int i = 0; i < size; i++) {
      mViews.get(i).setSelected(false);
    }
    mViews.get(position).setSelected(true);
  }

  class Holder extends ViewHolder {

    private TextView tvFairy;

    public Holder(View itemView) {
      super(itemView);
      tvFairy = (TextView) itemView.findViewById(R.id.item_tv_fairy);
    }
  }
}
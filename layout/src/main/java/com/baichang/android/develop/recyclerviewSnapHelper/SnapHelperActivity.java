package com.baichang.android.develop.recyclerviewSnapHelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.baichang.android.develop.R;

public class SnapHelperActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_snap_helper);
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    recyclerView.setAdapter(new MyAdapter());
    LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
    linearSnapHelper.attachToRecyclerView(recyclerView);
    //PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
    //pagerSnapHelper.attachToRecyclerView(recyclerView);
  }


  class MyAdapter extends Adapter<MyHolder> {

    private int[] images = new int[]{R.mipmap.far, R.mipmap.images1,
        R.mipmap.images2, R.mipmap.images3, R.mipmap.images4};
    private String[] strings = new String[]{"陈一发儿", "童话镇", "阿婆说",
        "67373人均XX", "陈工"};

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new MyHolder(
          LayoutInflater.from(parent.getContext()).inflate(
              R.layout.item_snaphelper_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
      int finalPos = position % images.length;
      holder.ivImage.setImageResource(images[finalPos]);
      holder.tvText.setText(strings[finalPos]);
    }

    @Override
    public int getItemCount() {
      return Integer.MAX_VALUE;
    }
  }

  class MyHolder extends ViewHolder {

    TextView tvText;
    ImageView ivImage;

    MyHolder(View itemView) {
      super(itemView);
      tvText = (TextView) itemView.findViewById(R.id.item_tv_text);
      ivImage = (ImageView) itemView.findViewById(R.id.item_image_view);
    }
  }

}

package com.baichang.android.architecture.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baichang.android.architecture.R;
import com.baichang.android.architecture.adapter.NewsAdapter.NewsHolder;
import com.baichang.android.architecture.entity.NewsStoriesData;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

/**
 * Created by iCong on 2017/2/28. C is a Coder
 */

public class NewsAdapter extends Adapter<NewsHolder> {

  private ArrayList<NewsStoriesData> mList;

  public NewsAdapter(ArrayList<NewsStoriesData> list) {
    mList = list;
  }

  private Context mContext;

  @Override
  public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    mContext = parent.getContext();
    return new NewsHolder(LayoutInflater.from(mContext).inflate(R.layout.item_news_layout, null));
  }

  @Override
  public void onBindViewHolder(NewsHolder holder, int position) {
    holder.tvId.setText(mList.get(position).title);
    Glide.with(mContext).load(mList.get(position).images.get(0)).error(R.mipmap.ic_launcher)
        .into(holder.ivImage);
  }

  @Override
  public int getItemCount() {
    return mList == null ? 0 : mList.size();
  }

  public class NewsHolder extends ViewHolder {

    @BindView(R.id.item_news_tv_title)
    TextView tvId;
    @BindView(R.id.item_joke_iv_img)
    ImageView ivImage;

    public NewsHolder(View itemView) {
      super(itemView);
      itemView.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          if (listener != null) {
            listener.ItemOnClick(getAdapterPosition());
          }
        }
      });
      ButterKnife.bind(this, itemView);
    }
  }

  public ItemOnClickListener listener;

  public NewsAdapter setItemOnClickListener(ItemOnClickListener listener) {
    this.listener = listener;
    return this;
  }

  public interface ItemOnClickListener {

    void ItemOnClick(int position);
  }
}

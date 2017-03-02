package com.baichang.android.architecture.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baichang.android.architecture.R;
import com.baichang.android.architecture.common.BaseActivity;
import com.baichang.android.architecture.common.FLAG;
import com.baichang.android.architecture.news.present.NewsPresent;
import com.baichang.android.architecture.news.present.impl.NewsPresentImpl;
import com.baichang.android.architecture.news.view.NewsView;

public class NewsActivity extends BaseActivity implements NewsView {

  @BindView(R.id.news_rv_list)
  RecyclerView rvList;
  @BindView(R.id.news_bar)
  ProgressBar newsBar;
  private NewsPresent mPresent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_news);
    ButterKnife.bind(this);
    initPresent();
  }

  private void initPresent() {
    rvList.setLayoutManager(new LinearLayoutManager(this));
    mPresent = new NewsPresentImpl(this);
    mPresent.attachRecyclerView(rvList);
  }


  @Override
  public void showProgressBar() {
    newsBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideProgressBar() {
    newsBar.setVisibility(View.GONE);
  }

  @Override
  public void showMessage(String msg) {
    showToast(msg);
  }


  @Override
  protected void onStart() {
    super.onStart();
    mPresent.onStart();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mPresent.onDestroy();
  }

  @Override
  public void gotoDetail(int newsId) {
    Intent intent = new Intent(this, NewsDetailActivity.class);
    intent.putExtra(FLAG.ACTION_NEWS_ID, newsId);
    startActivity(intent);
  }
}

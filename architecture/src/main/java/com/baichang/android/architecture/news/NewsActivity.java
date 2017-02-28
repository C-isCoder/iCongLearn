package com.baichang.android.architecture.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baichang.android.architecture.R;
import com.baichang.android.architecture.common.BaseActivity;
import com.baichang.android.architecture.news.present.INewsPresent;
import com.baichang.android.architecture.news.present.INewsPresentImpl;
import com.baichang.android.architecture.news.view.INewsView;

public class NewsActivity extends BaseActivity implements INewsView {

  @BindView(R.id.news_rv_list)
  RecyclerView rvList;
  @BindView(R.id.news_bar)
  ProgressBar newsBar;

  private INewsPresent mPresent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_news);
    ButterKnife.bind(this);
    initPresent();
  }

  private void initPresent() {
    rvList.setLayoutManager(new LinearLayoutManager(this));
    mPresent = new INewsPresentImpl(this);
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
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void startActivity(Intent intent, Class target) {
    intent.setClass(this, target);
    startActivity(intent);
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
}

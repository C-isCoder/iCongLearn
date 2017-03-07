package com.baichang.android.architecture.news.present.impl;

import android.util.Log;
import android.widget.Toast;
import com.baichang.android.architecture.mq.NewsMQ;
import com.baichang.android.architecture.news.model.NewsInteraction;
import com.baichang.android.architecture.news.model.impl.NewsInteractionImpl;
import com.baichang.android.architecture.news.present.NewsDetailPresent;
import com.baichang.android.architecture.news.present.NewsPresent;
import com.baichang.android.architecture.news.view.NewsDetailView;

/**
 * Created by iCong on 2017/3/1.
 *
 * C is a Coder
 */

public class NewsDetailPresentImpl implements NewsDetailPresent,
    NewsInteraction.BaseListener<String> {

  private NewsDetailView mView;
  private int newsId;
  private NewsInteraction mInteraction;

  public NewsDetailPresentImpl(int newsId, NewsDetailView view) {
    mView = view;
    this.newsId = newsId;
    mInteraction = new NewsInteractionImpl();
    // 获取mq
    NewsMQ mq = new NewsPresentImpl();
    mView.showMessage(mq.getTitle());
    Log.e("MQ", mq.getTitle());
  }

  @Override
  public void onDestroy() {
    mInteraction.cancel(NewsInteractionImpl.NEWS_DETAIL);
    mView = null;
  }

  @Override
  public void onStart() {
    mView.showProgressBar();
    mInteraction.getNewsDetail(newsId, this);
  }

  @Override
  public void success(String s) {
    mView.hideProgressBar();
    mView.showWebView(s);
  }

  @Override
  public void error(String error) {
    mView.hideProgressBar();
    mView.showMessage(error);
  }

}

package com.baichang.android.architecture.news.present;

import com.baichang.android.architecture.news.model.INewsInteraction;
import com.baichang.android.architecture.news.model.INewsInteractionImpl;
import com.baichang.android.architecture.news.view.INewsDetailView;

/**
 * Created by iCong on 2017/3/1.
 *
 * C is a Coder
 */

public class INewsDetailPresentImpl implements INewsDetailPresent,
    INewsInteraction.BaseListener<String> {

  private INewsDetailView mView;
  private int newsId;
  private INewsInteraction mInteraction;

  public INewsDetailPresentImpl(int newsId, INewsDetailView view) {
    mView = view;
    this.newsId = newsId;
    mInteraction = new INewsInteractionImpl();
  }

  @Override
  public void onDestroy() {
    mInteraction.cancel(INewsInteractionImpl.NEWS_DETAIL);
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

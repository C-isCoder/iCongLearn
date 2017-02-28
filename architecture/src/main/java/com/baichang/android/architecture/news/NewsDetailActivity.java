package com.baichang.android.architecture.news;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baichang.android.architecture.R;
import com.baichang.android.architecture.common.Api;
import com.baichang.android.architecture.common.ApiWrapper;
import com.baichang.android.architecture.common.BaseActivity;
import com.baichang.android.architecture.entity.NewsDetailData;
import com.baichang.android.architecture.event.MQ;
import rx.Subscriber;
import rx.functions.Action0;

public class NewsDetailActivity extends BaseActivity {

  @BindView(R.id.news_detail_web)
  WebView mWebView;
  @BindView(R.id.news_detail_bar)
  ProgressBar mProgress;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_news_detail);
    ButterKnife.bind(this);
    initData(getIntent());
  }

  private void initData(Intent intent) {
    int id = intent.getExtras().getInt(MQ.ACTION_NEWS_ID);
    Api api = new ApiWrapper();
    api.getDetail(id).doOnSubscribe(new Action0() {
      @Override
      public void call() {
        mProgress.setVisibility(View.VISIBLE);
      }
    }).subscribe(new Subscriber<NewsDetailData>() {
      @Override
      public void onCompleted() {
        mProgress.setVisibility(View.GONE);
      }

      @Override
      public void onError(Throwable e) {
        Log.e("CID", e.getMessage());
      }

      @Override
      public void onNext(NewsDetailData newsDetailData) {
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadData(newsDetailData.body, "text/html;charset=UTF-8", "utf-8");
      }
    });
  }
}

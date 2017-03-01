package com.baichang.android.architecture.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.baichang.android.architecture.R;
import com.baichang.android.architecture.common.BaseActivity;
import com.baichang.android.architecture.event.MQ;
import com.baichang.android.architecture.news.present.INewsDetailPresent;
import com.baichang.android.architecture.news.present.INewsDetailPresentImpl;
import com.baichang.android.architecture.news.view.INewsDetailView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NewsDetailActivity extends BaseActivity implements INewsDetailView {

  @BindView(R.id.news_detail_web)
  WebView mWebView;
  @BindView(R.id.news_detail_bar)
  ProgressBar mProgress;

  private INewsDetailPresent mPresent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_news_detail);
    ButterKnife.bind(this);
    initView();
    initPresent(getIntent());
    //initData(getIntent());
  }

  private void initPresent(Intent intent) {
    int id = intent.getExtras().getInt(MQ.ACTION_NEWS_ID);
    mPresent = new INewsDetailPresentImpl(id, this);
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

  private void initView() {
    mWebView.setWebViewClient(new WebViewClient());
    mWebView.getSettings().setJavaScriptEnabled(true);
  }

//  private void initData(Intent intent) {
//    int id = intent.getExtras().getInt(MQ.ACTION_NEWS_ID);
//    API api = new APIWrapper();
//    api.getDetail(id).doOnSubscribe(new Action0() {
//      @Override
//      public void call() {
//        mProgress.setVisibility(View.VISIBLE);
//      }
//    }).subscribe(new Subscriber<NewsDetailData>() {
//      @Override
//      public void onCompleted() {
//        mProgress.setVisibility(View.GONE);
//      }
//
//      @Override
//      public void onError(Throwable e) {
//        Log.e("CID", e.getMessage());
//      }
//
//      @Override
//      public void onNext(NewsDetailData newsDetailData) {
//        mWebView.setWebViewClient(new WebViewClient());
//        mWebView.getSettings().setJavaScriptEnabled(true);
//        mWebView.loadData(newsDetailData.body, "text/html;charset=UTF-8", "utf-8");
//      }
//    });
//  }


  @Override
  public void showProgressBar() {
    mProgress.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideProgressBar() {
    mProgress.setVisibility(View.GONE);
  }

  @Override
  public void showMessage(String msg) {
    showToast(msg);
  }

  @Override
  public void showWebView(String body) {
    // image auto
    Document doc = Jsoup.parse(body);
    Elements img = doc.getElementsByTag("img");
    if (img.size() != 0) {
      for (Element e_Img : img) {
        e_Img.attr("width", "100%");
        e_Img.attr("height", "auto");
      }
    }
    String newBody = doc.toString();
    mWebView.loadData(newBody, "text/html;charset=UTF-8", "utf-8");
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (mWebView.canGoBack()){
      mWebView.goBack();
      return true;
    }else {
      return super.onKeyDown(keyCode, event);
    }
  }
}

package com.baichang.android.architecture.news.model.impl;

import android.util.SparseArray;
import com.baichang.android.architecture.common.API;
import com.baichang.android.architecture.common.APIWrapper;
import com.baichang.android.architecture.common.BaseData;
import com.baichang.android.architecture.news.entity.NewsDetailData;
import com.baichang.android.architecture.news.entity.NewsStoriesData;
import com.baichang.android.architecture.news.model.NewsInteraction;
import java.util.ArrayList;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by iCong on 2017/2/28.
 *
 * C is a Coder
 */

public class NewsInteractionImpl implements NewsInteraction {

  private static API api;
  private static SparseArray<Subscription> map;

  public NewsInteractionImpl() {
    if (api == null) {
      api = new APIWrapper();
    }
    if (map == null) {
      map = new SparseArray<>();
    }
  }

  public static final int NEWS_DETAIL = 1;
  public static final int NEWS_LIST = 2;

  @Override public void getNewsList(final BaseListener<ArrayList<NewsStoriesData>> listener) {
    Subscription subscription =
        api.getList().subscribe(new Subscriber<BaseData<NewsStoriesData>>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            listener.error(e.getMessage());
          }

          @Override public void onNext(BaseData<NewsStoriesData> data) {
            listener.success(data.stories);
          }
        });

    map.put(NEWS_LIST, subscription);
  }

  @Override public void getNewsDetail(int newsId, final BaseListener<String> listener) {
    Subscription subscription = api.getDetail(newsId).subscribe(new Subscriber<NewsDetailData>() {
      @Override public void onCompleted() {

      }

      @Override public void onError(Throwable e) {
        listener.error(e.getMessage());
      }

      @Override public void onNext(NewsDetailData newsDetailData) {
        listener.success(newsDetailData.body);
      }
    });
    map.put(NEWS_DETAIL, subscription);
  }

  @Override public void cancel(int key) {
    map.get(key).unsubscribe();
  }
}

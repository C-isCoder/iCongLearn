package com.baichang.android.architecture.news.model;

import com.baichang.android.architecture.common.Api;
import com.baichang.android.architecture.common.ApiWrapper;
import com.baichang.android.architecture.common.BaseData;
import com.baichang.android.architecture.entity.NewsStoriesData;
import rx.Subscriber;

/**
 * Created by iCong on 2017/2/28.
 *
 * C is a Coder
 */

public class INewsInteractionImpl implements INewsInteraction {

  @Override
  public void getJokeList(final IJokeListener listener) {
    Api api = new ApiWrapper();
    api.getList().subscribe(new Subscriber<BaseData<NewsStoriesData>>() {
      @Override
      public void onCompleted() {

      }

      @Override
      public void onError(Throwable e) {
        listener.error(e.getMessage());
      }

      @Override
      public void onNext(BaseData<NewsStoriesData> data) {
        listener.success(data.stories);
      }
    });
  }
}

package com.baichang.android.architecture.common;

import com.baichang.android.architecture.news.entity.NewsDetailData;
import com.baichang.android.architecture.news.entity.NewsStoriesData;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by iCong on 2017/2/28.
 *
 * C is a Coder
 */

public class APIWrapper implements API {

  private static Retrofit RETROFIT;

  public APIWrapper() {
    if (RETROFIT == null) {
      RETROFIT = new Retrofit.Builder()
          .baseUrl(APIConstants.API_DEFAULT_HOST)
          .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
          .addConverterFactory(GsonConverterFactory.create())
          .build();
    }
  }

  @Override
  public Observable<BaseData<NewsStoriesData>> getList() {
    return RETROFIT.create(API.class).getList().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  @Override
  public Observable<NewsDetailData> getDetail(@Path("id") int id) {
    return RETROFIT.create(API.class).getDetail(id).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

}

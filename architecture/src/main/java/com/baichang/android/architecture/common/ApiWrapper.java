package com.baichang.android.architecture.common;

import com.baichang.android.architecture.entity.NewsDetailData;
import com.baichang.android.architecture.entity.NewsStoriesData;
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

public class ApiWrapper implements Api {

  @Override
  public Observable<BaseData<NewsStoriesData>> getList() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(APIConstants.API_DEFAULT_HOST)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    return retrofit.create(Api.class).getList().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  @Override
  public Observable<NewsDetailData> getDetail(@Path("id") int id) {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(APIConstants.API_DEFAULT_HOST)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    return retrofit.create(Api.class).getDetail(id).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}

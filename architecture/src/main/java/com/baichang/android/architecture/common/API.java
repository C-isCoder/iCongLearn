package com.baichang.android.architecture.common;

import com.baichang.android.architecture.news.entity.NewsDetailData;
import com.baichang.android.architecture.news.entity.NewsStoriesData;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by iCong on 2017/2/28.
 *
 * C is a Coder
 */

public interface API {

  @GET("latest") Observable<BaseData<NewsStoriesData>> getList();

  @GET("{id}") Observable<NewsDetailData> getDetail(@Path("id") int id);
}

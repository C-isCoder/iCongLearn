package com.baichang.android.architecture.news.model;

import com.baichang.android.architecture.common.IBaseInteraction;
import com.baichang.android.architecture.news.entity.NewsStoriesData;
import java.util.ArrayList;

/**
 * Created by iCong on 2017/2/28.
 *
 * C is a Coder
 */

public interface NewsInteraction extends IBaseInteraction {

  void getNewsList(NewsInteraction.BaseListener<ArrayList<NewsStoriesData>> listener);

  void getNewsDetail(int newsId, NewsInteraction.BaseListener<String> listener);

  void cancel(int key);
}

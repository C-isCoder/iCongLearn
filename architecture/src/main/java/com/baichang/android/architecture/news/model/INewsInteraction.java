package com.baichang.android.architecture.news.model;

import com.baichang.android.architecture.common.IBaseInteraction;
import com.baichang.android.architecture.entity.NewsStoriesData;
import java.util.ArrayList;

/**
 * Created by iCong on 2017/2/28.
 *
 * C is a Coder
 */

public interface INewsInteraction extends IBaseInteraction {

  void getNewsList(INewsInteraction.BaseListener<ArrayList<NewsStoriesData>> listener);

  void getNewsDetail(int newsId, INewsInteraction.BaseListener<String> listener);

  void cancel(int key);
}

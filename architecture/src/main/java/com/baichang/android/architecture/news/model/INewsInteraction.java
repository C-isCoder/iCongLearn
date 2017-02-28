package com.baichang.android.architecture.news.model;

import com.baichang.android.architecture.entity.NewsStoriesData;
import java.util.ArrayList;

/**
 * Created by iCong on 2017/2/28.
 *
 * C is a Coder
 */

public interface INewsInteraction {

  interface IJokeListener {

    void success(ArrayList<NewsStoriesData> list);

    void error(String error);
  }

  void getJokeList(INewsInteraction.IJokeListener listener);
}

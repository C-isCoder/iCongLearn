package com.baichang.android.architecture.news.entity;

import com.google.gson.annotations.Expose;
import java.util.List;

/**
 * Created by iCong on 2017/2/28.
 *
 * C is a Coder
 */

public class NewsStoriesData {

  @Expose
  public String title;
  @Expose
  public String ga_prefix;
  @Expose
  public List<String> images;
  @Expose
  public int type;
  @Expose
  public int id;
}

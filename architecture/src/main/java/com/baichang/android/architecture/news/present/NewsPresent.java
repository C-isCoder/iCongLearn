package com.baichang.android.architecture.news.present;

import android.support.v7.widget.RecyclerView;
import com.baichang.android.architecture.common.IBasePresent;

/**
 * Created by iCong on 2017/2/28.
 *
 * C is a Coder
 */

public interface NewsPresent extends IBasePresent {

  void attachRecyclerView(RecyclerView view);
}

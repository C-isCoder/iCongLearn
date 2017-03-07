package com.baichang.android.architecture.news.present.impl;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.baichang.android.architecture.common.BaseEventData;
import com.baichang.android.architecture.common.FLAG;
import com.baichang.android.architecture.mq.NewsMQ;
import com.baichang.android.architecture.news.adapter.NewsAdapter;
import com.baichang.android.architecture.news.adapter.NewsAdapter.ItemOnClickListener;
import com.baichang.android.architecture.news.entity.NewsStoriesData;
import com.baichang.android.architecture.news.model.NewsInteraction;
import com.baichang.android.architecture.news.model.impl.NewsInteractionImpl;
import com.baichang.android.architecture.news.present.NewsPresent;
import com.baichang.android.architecture.news.view.NewsView;
import java.util.ArrayList;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by iCong on 2017/2/28.
 *
 * C is a Coder
 */

public class NewsPresentImpl implements NewsPresent,
    NewsInteraction.BaseListener<ArrayList<NewsStoriesData>>, ItemOnClickListener, NewsMQ {

  private NewsView mView;
  private NewsInteraction mInteraction;
  private NewsAdapter mAdapter;
  private ArrayList<NewsStoriesData> mList;
  private static String mTitle;

  public NewsPresentImpl(NewsView view) {
    mView = view;
    mInteraction = new NewsInteractionImpl();
    mList = new ArrayList<>();
    mAdapter = new NewsAdapter(mList);
    mAdapter.setItemOnClickListener(this);
    EventBus.getDefault().register(this);
  }

  public NewsPresentImpl() {
  }

  @Override
  public void onDestroy() {
    mInteraction.cancel(NewsInteractionImpl.NEWS_LIST);
    mView = null;
    //EventBus.getDefault().unregister(this);
  }

  @Override
  public void onStart() {
    mView.showProgressBar();
    mInteraction.getNewsList(this);
  }

  @Override
  public void attachRecyclerView(RecyclerView view) {
    view.setAdapter(mAdapter);
  }

  @Override
  public void success(ArrayList<NewsStoriesData> list) {
    mView.hideProgressBar();
    if (!mList.isEmpty()) {
      mList.clear();
    }
    mList.addAll(list);
    mAdapter.notifyDataSetChanged();
    mTitle = list.get(0).title;
    //BaseEventData eventData = new BaseEventData(FLAG.EVENT_NEWS_TITLE, list.get(0).title);
    //EventBus.getDefault().post(eventData);
  }

  @Override
  public void error(String error) {
    mView.hideProgressBar();
    mView.showMessage(error);
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void doEvent(BaseEventData<String, String> eventData) {
    if (eventData.type == FLAG.EVENT_NEWS_TITLE) {
      mView.showMessage(eventData.value);
      Log.e("EventBus", eventData.value);
    }
  }

  @Override
  public void onClickItem(int position) {
    mView.gotoDetail(mList.get(position).id);
  }
  // 实现MQ接口
  @Override
  public String getTitle() {
    return mTitle;
  }
}

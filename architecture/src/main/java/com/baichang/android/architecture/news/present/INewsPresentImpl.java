package com.baichang.android.architecture.news.present;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.baichang.android.architecture.adapter.NewsAdapter;
import com.baichang.android.architecture.adapter.NewsAdapter.ItemOnClickListener;
import com.baichang.android.architecture.common.BaseEventData;
import com.baichang.android.architecture.entity.NewsStoriesData;
import com.baichang.android.architecture.event.MQ;
import com.baichang.android.architecture.news.NewsDetailActivity;
import com.baichang.android.architecture.news.model.INewsInteraction;
import com.baichang.android.architecture.news.model.INewsInteractionImpl;
import com.baichang.android.architecture.news.view.INewsView;
import java.util.ArrayList;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by iCong on 2017/2/28.
 *
 * C is a Coder
 */

public class INewsPresentImpl implements INewsPresent,
    INewsInteraction.BaseListener<ArrayList<NewsStoriesData>>, ItemOnClickListener {

  private INewsView mView;
  private INewsInteraction mInteraction;
  private NewsAdapter mAdapter;
  private ArrayList<NewsStoriesData> mList;

  public INewsPresentImpl(INewsView view) {
    mView = view;
    mInteraction = new INewsInteractionImpl();
    mList = new ArrayList<>();
    mAdapter = new NewsAdapter(mList);
    mAdapter.setItemOnClickListener(this);
    EventBus.getDefault().register(this);
  }

  @Override
  public void onDestroy() {
    mInteraction.cancel(INewsInteractionImpl.NEWS_LIST);
    mView = null;
    EventBus.getDefault().unregister(this);
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
    BaseEventData eventData = new BaseEventData(MQ.EVENT_NEWS_TITLE, list.get(0).title);
    EventBus.getDefault().post(eventData);
  }

  @Override
  public void error(String error) {
    mView.hideProgressBar();
    mView.showMessage(error);
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void doEvent(BaseEventData<String, String> eventData) {
    if (eventData.type == MQ.EVENT_NEWS_TITLE) {
      mView.showMessage(eventData.value);
      Log.e("EventBus", eventData.value);
    }
  }

  @Override
  public void ItemOnClick(int position) {
    mView.gotoDetail(mList.get(position).id);
  }
}

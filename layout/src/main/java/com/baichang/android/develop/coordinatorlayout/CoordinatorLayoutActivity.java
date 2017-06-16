package com.baichang.android.develop.coordinatorlayout;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import com.baichang.android.develop.R;
import com.baichang.android.develop.coordinatorlayout.adapter.CenterLayoutManager;
import com.baichang.android.develop.coordinatorlayout.adapter.MyAdapter;
import com.baichang.android.develop.coordinatorlayout.utils.LrcInfo;
import com.baichang.android.develop.coordinatorlayout.utils.LrcParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CoordinatorLayoutActivity extends AppCompatActivity implements OnClickListener {

  private static final String TAG = CoordinatorLayoutActivity.class.getSimpleName();

  private FloatingActionButton fab;
  private RecyclerView rvList;

  private boolean isStart = false;
  private MediaPlayer player;
  private Timer timer;
  private static LrcInfo mLrcInfo;
  private List<Long> mKeyList;
  private Handler myHandler = new Handler() {
    @Override public void handleMessage(Message msg) {
      if (player == null) {
        return;
      }
      long current = player.getCurrentPosition();
      if (mKeyList.contains(current)) {
        int position = mKeyList.indexOf(current);
        rvList.smoothScrollToPosition(position);
        MyAdapter adapter = (MyAdapter) rvList.getAdapter();
        adapter.setSelected(position);
      }
    }
  };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_coordinator_layout);
    setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    initPlayer();
    initData();
    initView();
  }

  private void initData() {
    if (mLrcInfo == null) {
      try {
        mLrcInfo = new LrcParser().parser(getResources().openRawResource(R.raw.fairy_lrc));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    if (mKeyList == null) {
      mKeyList = new ArrayList<>();
      mKeyList.addAll(mLrcInfo.getInfos().keySet());
    }
  }

  private void initPlayer() {
    timer = new Timer();
    player = MediaPlayer.create(this, R.raw.fairy);
    player.setLooping(true);
  }

  private void initView() {
    rvList = (RecyclerView) findViewById(R.id.recycler_view);
    rvList.setLayoutManager(new CenterLayoutManager(this));
    String[] lrcs = new String[mLrcInfo.getInfos().size()];
    rvList.setAdapter(new MyAdapter(mLrcInfo.getInfos().values().toArray(lrcs)));
    fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(this);
    findViewById(R.id.fab_back).setOnClickListener(this);
  }

  @Override public void onClick(View view) {
    switch (view.getId()) {
      case R.id.fab_back:
        finish();
        break;
      default:
        if (!isStart) {
          fab.setSelected(true);
          timer.schedule(task, 1L, 1L);
          isStart = true;
          if (!player.isPlaying()) {
            player.start();
          }
        } else {
          fab.setSelected(false);
          isStart = false;
          timer.cancel();
          player.pause();
        }
    }
  }

  private TimerTask task = new TimerTask() {
    @Override public void run() {
      myHandler.sendEmptyMessage(0);
    }
  };

  @Override protected void onDestroy() {
    super.onDestroy();
    myHandler.removeMessages(0);
    player.stop();
    player.release();
    player = null;
  }
}

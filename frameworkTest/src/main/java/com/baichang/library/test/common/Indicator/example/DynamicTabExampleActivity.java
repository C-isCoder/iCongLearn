package com.baichang.library.test.common.Indicator.example;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.baichang.android.widget.magicIndicator.MagicIndicator;
import com.baichang.android.widget.magicIndicator.ViewPagerHelper;
import com.baichang.android.widget.magicIndicator.buildins.commonnavigator.CommonNavigator;
import com.baichang.android.widget.magicIndicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.baichang.android.widget.magicIndicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.baichang.android.widget.magicIndicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.baichang.android.widget.magicIndicator.buildins.commonnavigator.titles.ClipPagerTitleView;
import com.baichang.library.test.R;
import com.baichang.library.test.base.CommonActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DynamicTabExampleActivity extends CommonActivity {
  private static final String[] CHANNELS = new String[] {
      "CUPCAKE", "DONUT", "ECLAIR", "GINGERBREAD", "HONEYCOMB", "ICE_CREAM_SANDWICH", "JELLY_BEAN",
      "KITKAT", "LOLLIPOP", "M", "NOUGAT"
  };
  private List<String> mDataList = new ArrayList<String>(Arrays.asList(CHANNELS));
  private ExamplePagerAdapter mExamplePagerAdapter = new ExamplePagerAdapter(mDataList);

  private ViewPager mViewPager;
  private MagicIndicator mMagicIndicator;
  private CommonNavigator mCommonNavigator;

  private Toast mToast;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dynamic_tab_example_layout);

    mViewPager = (ViewPager) findViewById(R.id.view_pager);
    mViewPager.setAdapter(mExamplePagerAdapter);

    mMagicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator1);
    mMagicIndicator.setBackgroundColor(Color.parseColor("#d43d3d"));
    mCommonNavigator = new CommonNavigator(this);
    mCommonNavigator.setSkimOver(true);
    mCommonNavigator.setAdapter(new CommonNavigatorAdapter() {

      @Override public int getCount() {
        return mDataList.size();
      }

      @Override public IPagerTitleView getTitleView(Context context, final int index) {
        ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
        clipPagerTitleView.setText(mDataList.get(index));
        clipPagerTitleView.setTextColor(Color.parseColor("#f2c4c4"));
        clipPagerTitleView.setClipColor(Color.WHITE);
        clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            mViewPager.setCurrentItem(index);
          }
        });
        return clipPagerTitleView;
      }

      @Override public IPagerIndicator getIndicator(Context context) {
        return null;
      }
    });
    mMagicIndicator.setNavigator(mCommonNavigator);
    ViewPagerHelper.bind(mMagicIndicator, mViewPager);

    mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
  }

  public void randomPage(View view) {
    mDataList.clear();
    int total = new Random().nextInt(CHANNELS.length);
    for (int i = 0; i <= total; i++) {
      mDataList.add(CHANNELS[i]);
    }

    mCommonNavigator.notifyDataSetChanged();    // must call firstly
    mExamplePagerAdapter.notifyDataSetChanged();

    mToast.setText("" + mDataList.size() + " page");
    mToast.show();
  }
}

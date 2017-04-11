package com.baichang.android.develop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.baichang.android.develop.bootomnavigation.BottomNavigationActivity;
import com.baichang.android.develop.coordinatorlayout.CoordinatorLayoutActivity;
import com.baichang.android.develop.nodatalayout.NoDataBaseActivity;
import com.baichang.android.develop.recyclerviewSnapHelper.SnapHelperActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void coordinatior(View view) {
    startActivity(new Intent(this, CoordinatorLayoutActivity.class));
  }

  public void bootomnavigation(View view) {
    startActivity(new Intent(this, BottomNavigationActivity.class));
  }

  public void nodatalayout(View view) {
    startActivity(new Intent(this, NoDataBaseActivity.class));
  }

  public void recyclerview(View view) {
    startActivity(new Intent(this, SnapHelperActivity.class));
  }
}

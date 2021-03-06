package com.baichang.android.develop.bootomnavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import com.baichang.android.develop.R;

public class BottomNavigationActivity extends AppCompatActivity {

  private TextView mTextMessage;

  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
      new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
          switch (item.getItemId()) {
            case R.id.navigation_home:
              mTextMessage.setText(R.string.title_home);
              return true;
            case R.id.navigation_store:
              mTextMessage.setText(R.string.title_store);
              return true;
            case R.id.navigation_interaction:
              mTextMessage.setText(R.string.title_interaction);
              return true;
            case R.id.navigation_me:
              mTextMessage.setText(R.string.title_me);
              return true;
          }
          return false;
        }
      };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bottom_navigation);

    mTextMessage = (TextView) findViewById(R.id.message);
    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
  }
}

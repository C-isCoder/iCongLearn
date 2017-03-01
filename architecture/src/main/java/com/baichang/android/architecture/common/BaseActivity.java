package com.baichang.android.architecture.common;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by iCong on 2017/2/28.
 *
 * C is a Coder
 */

public class BaseActivity extends AppCompatActivity {

  public void showToast(String msg) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
  }
}

package com.baichang.android.develop.vectorDrawable;

import android.annotation.TargetApi;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build.VERSION_CODES;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.baichang.android.develop.R;

public class LollipopActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lollipop);
  }

  @TargetApi(VERSION_CODES.LOLLIPOP) public void anim(View view) {
    ImageView imageView = (ImageView) view;
    AnimatedVectorDrawable drawable =
        (AnimatedVectorDrawable) getDrawable(R.drawable.fivestar_anim_vector);
    imageView.setImageDrawable(drawable);
    if (drawable != null) {
      drawable.start();
    }
  }
}

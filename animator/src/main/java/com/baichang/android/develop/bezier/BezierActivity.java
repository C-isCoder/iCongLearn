package com.baichang.android.develop.bezier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.baichang.android.develop.R;

public class BezierActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bezier);
  }

  public void secondBezier(View view) {
    startActivity(new Intent(this, SecondBezierActivity.class));
  }

  public void thirdBezier(View view) {
    startActivity(new Intent(this, ThirdBezierActivity.class));
  }

  public void drawPadBezier(View view) {
    startActivity(new Intent(this, DrawPadActivity.class));
  }

  public void pathMorphingBezier(View view) {
    startActivity(new Intent(this, PathMorphingActivity.class));
  }

  public void waveBezier(View view) {
    startActivity(new Intent(this, WaveBezierActivity.class));
  }

  public void pathBezier(View view) {
    startActivity(new Intent(this, PathBezierActivity.class));
  }
}

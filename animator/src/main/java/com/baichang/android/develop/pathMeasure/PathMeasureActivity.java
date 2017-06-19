package com.baichang.android.develop.pathMeasure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.baichang.android.develop.R;

public class PathMeasureActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_path_measure);
  }

  public void pathMeasure(View view) {
    startActivity(new Intent(this, PathTracingActivity.class));
  }

  public void pathPaint(View view) {
    startActivity(new Intent(this, PathPaintActivity.class));
  }
  public void pathPosTan(View view) {
    startActivity(new Intent(this, PathPosTanActivity.class));
  }
}

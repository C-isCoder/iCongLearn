package com.baichang.android.develop.bezier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.baichang.android.develop.R;

public class BezierActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_bezier);
   }

   public void secondBezier(View view) {
      startActivity(new Intent(this, SecondBezierActivity.class));
   }
}

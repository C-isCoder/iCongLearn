package com.baichang.android.develop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.baichang.android.develop.advance.ValueAnimatorActivity;
import com.baichang.android.develop.bezier.BezierActivity;
import com.baichang.android.develop.springanmiator.SpringAnimatorActivity;
import com.baichang.android.develop.vectorDrawable.VectorActivity;

public class MainActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
   }

   public void value(View view) {
      startActivity(new Intent(this, ValueAnimatorActivity.class));
   }

   public void spring(View view) {
      startActivity(new Intent(this, SpringAnimatorActivity.class));
   }

   public void vector(View view) {
      startActivity(new Intent(this, VectorActivity.class));
   }
   public void bezier(View view) {
      startActivity(new Intent(this, BezierActivity.class));
   }
}

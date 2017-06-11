package com.baichang.android.develop.vectorDrawable;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import com.baichang.android.develop.R;

public class VectorActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_verctor);
   }

   public void anim(View view) {
      ImageView imageView = (ImageView) view;
      Drawable drawable = imageView.getDrawable();
      if (drawable instanceof Animatable) {
         ((Animatable) drawable).start();
      }
   }
}

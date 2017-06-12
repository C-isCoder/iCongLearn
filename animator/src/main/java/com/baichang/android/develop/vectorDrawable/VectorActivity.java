package com.baichang.android.develop.vectorDrawable;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
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

   public void startToL(View view){
      if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP){
         startActivity(new Intent(this,LollipopActivity.class));
      }else {
         Toast.makeText(this,"该设备不支持，路径动画 L plus",Toast.LENGTH_SHORT).show();
      }
   }
}

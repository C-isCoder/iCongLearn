package com.baichang.android.develop.eg;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import com.baichang.android.develop.R;

public class Main2Activity extends AppCompatActivity {

  private ImageView i1;
  private ImageView i2;
  private ImageView i3;
  private ImageView i4;
  private float i1Current = 200;
  private float i2Current = 300;
  private float i3Current = 400;
  private float i4Current = 500;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main2);
//    initView();
//    startAnimator(i4, i4Current).addListener(new AnimatorListenerAdapter() {
//      @Override
//      public void onAnimationEnd(Animator animation) {
//        startAnimator(i3, i3Current).addListener(new AnimatorListenerAdapter() {
//          @Override
//          public void onAnimationEnd(Animator animation) {
//            startAnimator(i2, i2Current).addListener(new AnimatorListenerAdapter() {
//              @Override
//              public void onAnimationEnd(Animator animation) {
//                startAnimator(i1, i1Current);
//              }
//            });
//          }
//        });
//      }
//    });
  }

  private void initView() {
    i1 = (ImageView) findViewById(R.id.image_view_1);
    i2 = (ImageView) findViewById(R.id.image_view_2);
    i3 = (ImageView) findViewById(R.id.image_view_3);
    i4 = (ImageView) findViewById(R.id.image_view_4);
  }

  private ObjectAnimator startAnimator(View view, float current) {
    ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", -current, current);
    animator.setDuration(2000);
    animator.start();
    view.setVisibility(View.VISIBLE);
    return animator;
  }

}

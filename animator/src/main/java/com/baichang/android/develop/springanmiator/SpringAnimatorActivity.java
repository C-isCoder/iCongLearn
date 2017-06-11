package com.baichang.android.develop.springanmiator;

import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.baichang.android.develop.R;

/***
 * SpringAnimator 示例
 *
 * SpringAnimator 官方弹性动画效果
 */
public class SpringAnimatorActivity extends AppCompatActivity {

  private ImageView imageView;
  private TextView tvView;
  private View view;
  private SpringAnimation springAnimationImageView;
  private SpringAnimation springAnimationTextView;
  private SpringAnimation springAnimationView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_spring_animator);
    imageView = (ImageView) findViewById(R.id.image_view);
    tvView = (TextView) findViewById(R.id.text_view);
    view = findViewById(R.id.view);

    initSpring();
  }

  private void initSpring() {
    springAnimationImageView = new SpringAnimation(imageView, SpringAnimation.TRANSLATION_Y, 0);
    // DampingRatio 设置弹性的频率 越高弹的次数越多。
    springAnimationImageView.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
    // Stiffness 设置弹性的速度  越高弹的越快。
    springAnimationImageView.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);

    springAnimationView = new SpringAnimation(view, SpringAnimation.ROTATION_X, 0);
    springAnimationView.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
    springAnimationView.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);

    springAnimationTextView = new SpringAnimation(tvView, SpringAnimation.TRANSLATION_X, 0);
    springAnimationTextView.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
    springAnimationTextView.getSpring().setStiffness(SpringForce.STIFFNESS_HIGH);
  }

  public void go(View view) {
    springAnimationImageView.cancel();
    // 动画起始的位置，默认是 imageView 当前的位置，会造成没有效果。 一般是 imageView 要移动的范围距离。
    springAnimationImageView.setStartValue(-800);
    springAnimationImageView.start();

    springAnimationView.cancel();
    springAnimationView.setStartValue(1000);
    springAnimationView.start();

    springAnimationTextView.cancel();
    springAnimationTextView.setStartValue(100);
    springAnimationTextView.start();

  }
}

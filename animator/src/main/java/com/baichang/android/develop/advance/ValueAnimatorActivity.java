package com.baichang.android.develop.advance;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import com.baichang.android.develop.R;

public class ValueAnimatorActivity extends AppCompatActivity {

  private ImageView ivImage;
  private PointAnimator pointAnimator;
  private ImageView ivArrow;
  private float currentCount = 0;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_value_animator);
    initView();
    //ObjectAnimator();
    //xmlAnimator();
    //ColorEvaluator();
    ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
    int heapSize = manager.getMemoryClass();
    Log.e("内存：", String.valueOf(heapSize));
    //ViewPropertyAnimator();
  }

  private void initView() {
    ivImage = (ImageView) findViewById(R.id.iv_image);
    pointAnimator = (PointAnimator) findViewById(R.id.point_animator);
    ivArrow = (ImageView) findViewById(R.id.iv_arrow);
    ivArrow.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View view) {
        currentCount += 90;
        ivArrow.animate()
            .scaleX(2f)
            .scaleY(2f)
            .rotation(currentCount)
            .setDuration(300)
            .setListener(new AnimatorListenerAdapter() {
              @Override public void onAnimationEnd(Animator animation) {
                ivArrow.animate().scaleX(1f).scaleY(1f).setDuration(300);
              }
            });
      }
    });
  }

  private void ValueAnimator() {
    ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f, 0f, 1f, 0f, 1f);
    //动画时长
    animator.setDuration(5000);
    //动画更新监听
    animator.addUpdateListener(new AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator valueAnimator) {
        //每次动画更新变化的值
        float value = (float) valueAnimator.getAnimatedValue();
        ivImage.setAlpha(value);
      }
    });
    //延迟播放
    animator.setStartDelay(2000);
    //循环播放次数
    animator.setRepeatCount(10);
    //循环播放模式
    //RESTART重新播放,REVERSE倒叙播放
    animator.setRepeatMode(ValueAnimator.RESTART);
    //执行动画
    animator.start();
  }

  private void ObjectAnimator() {
    //透明
    ObjectAnimator aAlpha = ObjectAnimator.ofFloat(ivImage, "alpha", 1f, 0f, 1f);
    //旋转
    ObjectAnimator aRotation = ObjectAnimator.ofFloat(ivImage, "rotation", 0f, 360f);
    //平移
    float currentX = ivImage.getTranslationX();
    ObjectAnimator aTranslationX =
        ObjectAnimator.ofFloat(ivImage, "translationX", -1000f, currentX);
    //缩放
    ObjectAnimator aScaleY = ObjectAnimator.ofFloat(ivImage, "scaleY", 1f, 2f, 1f);
    //playAnimator(aScaleY);
    //组合
    groupAnimator(aRotation, aAlpha, aScaleY, aTranslationX);
  }

  //播放动画
  private void playAnimator(ObjectAnimator animator) {
    animator.setDuration(5000);
    animator.setRepeatCount(1);
    //animator.setRepeatMode(ValueAnimator.REVERSE);
    animator.start();
  }

  //组合动画
  private void groupAnimator(ObjectAnimator animator1, ObjectAnimator animator2,
      ObjectAnimator animator3, ObjectAnimator animator4) {
    AnimatorSet animatorSet = new AnimatorSet();
    animatorSet.play(animator1).with(animator2).with(animator3).after(animator4);
    animatorSet.setDuration(5000);
    animatorSet.addListener(new AnimatorListenerAdapter() {
      @Override public void onAnimationStart(Animator animation) {
        Toast.makeText(ValueAnimatorActivity.this, "动画开始", Toast.LENGTH_SHORT).show();
      }

      @Override public void onAnimationEnd(Animator animation) {
        Toast.makeText(ValueAnimatorActivity.this, "动画结束", Toast.LENGTH_SHORT).show();
      }
    });
    animatorSet.start();
  }

  //xml文件动画
  private void xmlAnimator() {
    Animator animator = AnimatorInflater.loadAnimator(this, R.animator.animator);
    animator.setTarget(ivImage);
    animator.start();
  }

  //自定义动画
  private void ColorEvaluator() {
    ObjectAnimator animator =
        ObjectAnimator.ofObject(pointAnimator, "color", new ColorEvaluator(), "#0000FF", "#FF0000");
    animator.setDuration(5000);
    animator.start();
  }

  //ViewPropertyAnimator
  //3.1 API新增实现方式，效果跟ObjectAnimator一样，用来比较直观容易理解
  private void ViewPropertyAnimator() {
    //5s 变透明
    //ivImage.animate().alpha(0.5f).setDuration(5000);s
    // 移动位置
    //ivImage.animate().alpha(0.5f).x(800).y(1700).z(500).setDuration(5000)
    //    .setInterpolator(new BounceInterpolator());
    ivImage.animate().scaleX(2f).rotation(360f).setDuration(5000);
  }
}

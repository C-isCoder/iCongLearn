package com.baichang.android.develop.advance;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.baichang.android.develop.R;

/**
 * Created by iscod. Time:2017/1/21-16:31.
 */

public class PointAnimator extends View {

  private Paint mPaint;

  private Point mCurrentPoint;

  private Bitmap mBitmap;
  private float mDefault = 50f;
  //private float mDefault = 0f;

  private String color;

  public PointAnimator(Context context) {
    super(context);
  }

  public PointAnimator(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public PointAnimator(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    if (isInEditMode()) {
      return;
    }
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setColor(Color.RED);
    //mPaint.setStyle(Style.STROKE);
    mPaint.setStyle(Style.FILL);
    mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.image);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    if (isInEditMode()) {
      return;
    }
    if (mCurrentPoint == null) {
      mCurrentPoint = new Point(mDefault, mDefault);
      drawRectangle(canvas);
      startAnimator();
    } else {
      drawRectangle(canvas);
    }
  }

  private void drawRectangle(Canvas canvas) {
    RectF rectf = new RectF(mCurrentPoint.getX(), mCurrentPoint.getY(),
        mCurrentPoint.getX() + 50, mCurrentPoint.getY() + 50);
    canvas.drawRect(rectf, mPaint);
  }

  private void drawBitmap(Canvas canvas) {
    canvas.drawBitmap(mBitmap, mCurrentPoint.getX(), mCurrentPoint.getY(), mPaint);
  }

  private void startAnimator() {
//    Point startPoint = new Point(mDefault, mDefault);
//    Point endPoint = new Point(getWidth() - mDefault, getHeight() - mDefault);
    Point startPoint = new Point(getWidth() / 2, mDefault);
    Point endPoint = new Point(getWidth() / 2, getHeight() - mDefault);
    ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
    animator.addUpdateListener(new AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator valueAnimator) {
        mCurrentPoint = (Point) valueAnimator.getAnimatedValue();
        invalidate();
      }
    });
    //animator.setDuration(5000);
    //animator.start();
    ObjectAnimator animator1 = ObjectAnimator
        .ofObject(this, "color", new ColorEvaluator(), "#0000FF", "#FF0000");
    AnimatorSet animatorSet = new AnimatorSet();
    animatorSet.play(animator).with(animator1);
    animatorSet.setDuration(5000);
    //加速下落
    //animatorSet.setInterpolator(new AccelerateInterpolator(2f));
    //弹跳
    //animatorSet.setInterpolator(new BounceInterpolator());
    //自定义 减速-加速-减速
    animatorSet.setInterpolator(new DecelerateAccelerateInterpolator());
    animatorSet.start();
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
    mPaint.setColor(Color.parseColor(color));
    invalidate();
  }
}

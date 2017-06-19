package com.baichang.android.develop.pathMeasure.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by iCong on 2017/6/19.
 */

public class PathPaintView extends View {
  private Paint mPaint;

  private Path mPath;

  private float mLength;

  private float mAnimValue;

  private PathMeasure mPathMeasure;

  private PathEffect mPathEffect;

  public PathPaintView(Context context) {
    super(context);
  }

  public PathPaintView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);

    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setStrokeWidth(8);
    mPaint.setColor(Color.RED);
    mPaint.setStyle(Paint.Style.STROKE);

    mPath = new Path();

    mPathMeasure = new PathMeasure();

    mPath.moveTo(200, 200);
    mPath.lineTo(200, 400);
    mPath.lineTo(400, 200);
    mPath.close();

    mPathMeasure.setPath(mPath, true);
    mLength = mPathMeasure.getLength();

    ValueAnimator valueAnimator = ValueAnimator.ofFloat(1, 0);
    valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
    valueAnimator.setDuration(2000);
    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        mAnimValue = (float) animation.getAnimatedValue();
        // 设置一个画笔风格 Dash 虚线风格 数组传入  虚线 实现的长度 最后一个参数 是一个偏移量
        mPathEffect = new DashPathEffect(new float[] { mLength, mLength }, mLength * mAnimValue);
        mPaint.setPathEffect(mPathEffect);
        invalidate();
      }
    });
    valueAnimator.start();
  }

  public PathPaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawPath(mPath, mPaint);
  }
}

package com.baichang.android.develop.pathMeasure.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by iCong on 2017/6/19.
 */

public class PathPosTanView extends View implements View.OnClickListener {
  private Path mPath;

  private Paint mPaint;

  private float[] mPos;

  private float[] mTan;

  private PathMeasure mPathMeasure;
  private ValueAnimator mAnimator;
  private float mAnimatorValue;

  public PathPosTanView(Context context) {
    super(context);
  }

  public PathPosTanView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeWidth(5);

    mPath = new Path();
    mPath.addCircle(0, 0, 250, Path.Direction.CW);

    mPos = new float[2];
    mTan = new float[2];

    mPathMeasure = new PathMeasure();
    mPathMeasure.setPath(mPath, true);

    mAnimator = ValueAnimator.ofFloat(0, 1);
    mAnimator.setDuration(3000);
    mAnimator.setRepeatCount(ValueAnimator.INFINITE);
    mAnimator.setInterpolator(new LinearInterpolator());
    mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        mAnimatorValue = (float) animation.getAnimatedValue();
        invalidate();
      }
    });

    setOnClickListener(this);
  }

  public PathPosTanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    mPathMeasure.getPosTan(mPathMeasure.getLength() * mAnimatorValue, mPos, mTan);
    // 计算圆切线点角度
    float degree = (float) (Math.atan2(mTan[1], mTan[0]) * 180 / Math.PI);

    // 锁定画布
    canvas.save();
    // 位移画布 技巧：初始的圆心坐标是0，0 不移动画布画不全
    canvas.translate(500, 500);
    // 绘制圆
    canvas.drawPath(mPath, mPaint);
    // 当前点的坐标
    canvas.drawCircle(mPos[0], mPos[1], 10, mPaint);
    // 旋转画布旋转到 计算出来圆切线的角度，使下面的切线对应到计算的角度。
    // 这样就不用每次重新绘制切线了，可以优化性能，减少计算，算是Canvas的一个技巧
    canvas.rotate(degree);
    // 圆的切线
    canvas.drawLine(0, -250, 300, -250, mPaint);
    // 释放画布
    canvas.restore();
  }

  @Override public void onClick(View v) {
    mAnimator.start();
  }
}

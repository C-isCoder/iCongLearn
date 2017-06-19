package com.baichang.android.develop.pathMeasure.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by iCong on 2017/6/19.
 */

public class PathTracingView extends View {
  private Paint mPaint;

  private Path mDst;

  private Path mPath;

  private float mLength;

  private float mAnimValue;

  private PathMeasure mPathMeasure;

  public PathTracingView(Context context) {
    super(context);
  }

  public PathTracingView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);

    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setStrokeWidth(8);
    mPaint.setColor(Color.RED);
    mPaint.setStyle(Paint.Style.STROKE);

    mDst = new Path();
    mPath = new Path();

    mPathMeasure = new PathMeasure();
    mPath.addCircle(500, 600, 100, Path.Direction.CW);

    mPathMeasure.setPath(mPath, true);
    mLength = mPathMeasure.getLength();

    ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
    valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
    valueAnimator.setDuration(1000);
    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        mAnimValue = (float) animation.getAnimatedValue();
        invalidate();
      }
    });
    valueAnimator.start();
  }

  public PathTracingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    mDst.reset();
    // 硬件加速的BUG 不lineTO 会导致
    mDst.lineTo(0, 0);

    float stop = mLength * mAnimValue;
    // 动画前半段，start 为0 后半段，急剧接近 stop点 形成了Windows 加载圈的风格
    float start = (float) (stop - ((0.5 - Math.abs(mAnimValue - 0.5)) * mLength));
    // 获取 start - stop 路径的片段。 true 从0开始获取 false 从次的点接着获取。
    mPathMeasure.getSegment(start, stop, mDst, true);
    canvas.drawPath(mDst, mPaint);
  }
}

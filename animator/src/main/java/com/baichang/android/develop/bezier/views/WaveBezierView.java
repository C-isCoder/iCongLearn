package com.baichang.android.develop.bezier.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by iCong on 2017/6/16.
 */

public class WaveBezierView extends View implements View.OnClickListener {

  // 画笔
  private Paint mPaint;

  // 偏移值
  private int mOffset;

  // 路径
  private Path mPath;

  // 波浪的长度
  private int mWaveLength;

  // 波浪的个数
  private int mWaveCount;

  // 屏幕的宽度
  private int mScreenWidget;

  // 屏幕的高度
  private int mScreenHeight;

  // 屏幕Y轴中心点
  private int mCenterY;
  // 动画
  private ValueAnimator mValueAnimator;

  public WaveBezierView(Context context) {
    super(context);
  }

  public WaveBezierView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setColor(Color.rgb(181, 213, 255));
    mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

    mPath = new Path();

    setOnClickListener(this);
  }

  public WaveBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    Log.d("CID", "getWidth:" + getWidth() + "getHeight:" + getHeight());
  }

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    Log.d("CID", "w:" + w + "h:" + h);

    mScreenWidget = getWidth();
    mScreenHeight = getHeight();

    mCenterY = mScreenWidget / 2;
    mWaveLength = 800;
    // 计算屏幕宽度可显示 波纹的个数，偏移1.5个长度 四舍五入
    mWaveCount = (int) Math.round(mScreenWidget / mWaveLength + 1.5);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    mPath.reset();
    mPath.moveTo(-mWaveLength + mOffset, mCenterY);
    for (int i = 0; i < mWaveCount; i++) {
      mPath.quadTo(-mWaveLength * 3 / 4 + i * mWaveLength + mOffset, mCenterY + 70,
          -mWaveLength / 2 + i * mWaveLength + mOffset, mCenterY);
      mPath.quadTo(-mWaveLength / 4 + i * mWaveLength + mOffset, mCenterY - 70,
          i * mWaveLength + mOffset, mCenterY);
    }
    mPath.lineTo(mScreenWidget, mScreenHeight);
    mPath.lineTo(0, mScreenHeight);
    mPath.close();
    canvas.drawPath(mPath, mPaint);
  }

  @Override public void onClick(View v) {
    if (mValueAnimator == null) {
      mValueAnimator = ValueAnimator.ofInt(0, mWaveLength);
    }
    mValueAnimator.setInterpolator(new LinearInterpolator());
    mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
    mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        mOffset = (int) animation.getAnimatedValue();
        invalidate();
      }
    });
    mValueAnimator.setDuration(1000);
    mValueAnimator.start();
  }
}

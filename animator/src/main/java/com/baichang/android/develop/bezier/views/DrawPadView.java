package com.baichang.android.develop.bezier.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by iCong on 2017/6/16.
 */

public class DrawPadView extends View {

  private Paint mPaint;

  private Path mPath;
  // 控制点
  private float mCX, mCY;

  public DrawPadView(Context context) {
    super(context);
  }

  public DrawPadView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setStrokeWidth(4);
    mPaint.setStyle(Style.STROKE);
    mPaint.setColor(Color.RED);

    mPath = new Path();
  }

  public DrawPadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        performClick();
        mPath.reset();
        mCX = event.getX();
        mCY = event.getY();
        mPath.moveTo(mCX, mCY);
        break;
      case MotionEvent.ACTION_MOVE:
        float x = event.getX();
        float y = event.getY();
        // 取出 直线的两个中间的坐标作为 bezier曲线的终点 起点作为控制点。绘制
        float endX = (mCX + x) / 2;
        float endY = (mCY + y) / 2;
        mPath.quadTo(mCX, mCY, endX, endY);
        mCX = x;
        mCY = y;
        break;
    }
    invalidate();
    return true;
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawPath(mPath, mPaint);
  }

  @Override public boolean performClick() {
    return super.performClick();
  }
}

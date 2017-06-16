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
 * Created by iCong on 2017/6/13.
 */

public class ThirdBezierView extends View {

  // 起始点坐标
  private float mStartX, mStartY;
  // 终点坐标
  private float mEndX, mEndY;
  // 控制点1坐标
  private float mFlag1X, mFlag1Y;
  // 控制点2
  private float mFlag2X, mFlag2Y;
  // bezier paint
  private Paint mBezierPaint;

  // point paint
  private Paint mPointPaint;

  // text paint
  private Paint mTextPaint;

  // line paint
  private Paint mLinePaint;

  // 绘制容器
  private Path mPath;
  private float mRadius = 15;

  public ThirdBezierView(Context context) {
    super(context);
  }

  public ThirdBezierView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    mBezierPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mBezierPaint.setStrokeWidth(8);
    mBezierPaint.setStyle(Style.STROKE);

    mPointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPointPaint.setStrokeWidth(6);
    mPointPaint.setColor(Color.RED);
    mPointPaint.setStyle(Style.FILL);

    mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mTextPaint.setTextSize(40);
    mTextPaint.setColor(Color.BLUE);

    mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mLinePaint.setColor(Color.GREEN);
    mLinePaint.setStrokeWidth(4);
    mLinePaint.setStyle(Style.STROKE);

    mPath = new Path();
  }

  public ThirdBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    // 初始化 起始点
    mStartX = w / 5;
    mStartY = h / 2;
    // 初始化 终点
    mEndX = w * 4 / 5;
    mEndY = h / 2;
    // 初始化 控制点1
    mFlag1X = w / 2 - 100;
    mFlag1Y = h / 2 - 300;
    // 初始化 控制点2
    mFlag2X = w / 2 + 100;
    mFlag2Y = h / 2 - 300;
  }

  @Override protected void onDraw(final Canvas canvas) {
    super.onDraw(canvas);
    // patch init
    mPath.reset();
    // 1.移动到起始点
    mPath.moveTo(mStartX, mStartY);
    // 移动到 起始点 传入控制点 和终点
    mPath.cubicTo(mFlag1X, mFlag1Y, mFlag2X, mFlag2Y, mEndX, mEndY);
    // 绘制 起点和文字
    canvas.drawCircle(mStartX, mStartY, mRadius, mPointPaint);
    canvas.drawText("起点", mStartX, mStartY, mTextPaint);
    // 绘制 控制点和文字
    canvas.drawCircle(mFlag1X, mFlag1Y, mRadius, mPointPaint);
    canvas.drawText("控制点一", mFlag1X, mFlag1Y, mTextPaint);

    canvas.drawCircle(mFlag2X, mFlag2Y, mRadius, mPointPaint);
    canvas.drawText("控制点二", mFlag2X, mFlag2Y, mTextPaint);
    // 绘制 终点和文字
    canvas.drawCircle(mEndX, mEndY, mRadius, mPointPaint);
    canvas.drawText("终点", mEndX, mEndY, mTextPaint);
    // 绘制 起点和控制点的连线
    canvas.drawLine(mStartX, mStartY, mFlag1X, mFlag1Y, mLinePaint);
    // 绘制 控制点和终点的连线
    canvas.drawLine(mFlag1X, mFlag1Y, mFlag2X, mFlag2Y, mLinePaint);
    canvas.drawLine(mFlag2X, mFlag2Y, mEndX, mEndY, mLinePaint);
    // 绘制 bezier 曲线
    canvas.drawPath(mPath, mBezierPaint);
  }

  private boolean isSecondPoint = false;

  @Override public boolean onTouchEvent(MotionEvent event) {
    //多点触控
    switch (event.getAction() & MotionEvent.ACTION_MASK) {
      case MotionEvent.ACTION_DOWN:
        performClick();
        break;
      //多点判断。
      case MotionEvent.ACTION_POINTER_DOWN:
        isSecondPoint = true;
        break;
      case MotionEvent.ACTION_POINTER_UP:
        isSecondPoint = false;
        break;
      case MotionEvent.ACTION_MOVE:
        // 第一个点
        mFlag1X = event.getX(0);
        mFlag1Y = event.getY(0);
        if (isSecondPoint) {
          mFlag2X = event.getX(1);
          mFlag2Y = event.getY(1);
        }
        invalidate();
        break;
    }
    return true;
  }

  @Override public boolean performClick() {
    return super.performClick();
  }
}

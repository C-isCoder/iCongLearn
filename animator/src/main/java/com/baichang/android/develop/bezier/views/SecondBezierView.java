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

public class SecondBezierView extends View {

   // 起始点坐标
   private float mStartX, mStartY;
   // 终点坐标
   private float mEndX, mEndY;
   // 控制点坐标
   private float mFlagX, mFlagY;

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

   public SecondBezierView(Context context) {
      super(context);
   }

   public SecondBezierView(Context context, @Nullable AttributeSet attrs) {
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


   public SecondBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
   }

   @Override
   protected void onSizeChanged(int w, int h, int oldw, int oldh) {
      super.onSizeChanged(w, h, oldw, oldh);
      // 初始化 起始点
      mStartX = w / 5;
      mStartY = h / 2;
      // 初始化 终点
      mEndX = w * 4 / 5;
      mEndY = h / 2;
      // 初始化 控制点
      mFlagX = w / 2;
      mFlagY = h / 2 - 300;

   }

   @Override
   protected void onDraw(final Canvas canvas) {
      super.onDraw(canvas);
      // patch init
      mPath.reset();
      // 1.移动到起始点
      mPath.moveTo(mStartX, mStartY);
      // 2.设置bezier曲线数据 rQuadTo 和 quadTo区别是 rQuadTo是相对坐标， quadTo是绝对坐标。
      // 移动到 起始点 传入控制点 和终点
      mPath.quadTo(mFlagX, mFlagY, mEndX, mEndY);
      // 绘制 起点和文字
      canvas.drawCircle(mStartX, mStartY, mRadius, mPointPaint);
      canvas.drawText("起点", mStartX, mStartY, mTextPaint);
      // 绘制 控制点和文字
      canvas.drawCircle(mFlagX, mFlagY, mRadius, mPointPaint);
      canvas.drawText("控制点", mFlagX, mFlagY, mTextPaint);
      // 绘制 终点和文字
      canvas.drawCircle(mEndX, mEndY, mRadius, mPointPaint);
      canvas.drawText("终点", mEndX, mEndY, mTextPaint);
      // 绘制 起点和控制点的连线
      canvas.drawLine(mStartX, mStartY, mFlagX, mFlagY, mLinePaint);
      // 绘制 控制点和终点的连线
      canvas.drawLine(mFlagX, mFlagY, mEndX, mEndY, mLinePaint);
      // 绘制 bezier 曲线
      canvas.drawPath(mPath, mBezierPaint);

   }

   @Override
   public boolean onTouchEvent(MotionEvent event) {
      switch (event.getAction()) {
         case MotionEvent.ACTION_MOVE:
            mFlagX = event.getX();
            mFlagY = event.getY();
            invalidate();
            break;
      }
      return true;
   }
}

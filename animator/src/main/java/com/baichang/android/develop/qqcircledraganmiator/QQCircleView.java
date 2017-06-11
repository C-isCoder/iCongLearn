package com.baichang.android.develop.qqcircledraganmiator;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;

/**
 * Created by iCong on 2017/2/27.
 */

public class QQCircleView extends View {

  private Paint mPaint;
  // A circle center
  private float circle_A_Center = 200f;
  // A circle 直径
  private float circle_A_Radius = 15f;
  // B circle center
  private float circle_B_Center = 200f;
  // B circle 直径
  private float circle_B_Radius = 20f;
  // 最大移动距离
  private float mMaxDistance = 120f;
  // 是否超出范围
  private boolean isOutRange;
  // 是否消失
  private boolean isDisappear;
  // 存储A圆相对于B圆的两个点1和2坐标
  private PointF[] circle_A_PointFs = new PointF[2];
  // 存储B圆相对于A圆的两个点3和4坐标
  private PointF[] circle_B_PointFs = new PointF[2];
  // 贝塞尔曲线中心控制点坐标
  private PointF mControlPointF = new PointF();
  // A圆圆心坐标
  private PointF circle_A_Center_PointF = new PointF(circle_A_Center, circle_A_Center);
  // B圆圆心坐标
  private PointF circle_B_Center_PointF = new PointF(circle_B_Center, circle_B_Center);

  public QQCircleView(Context context) {
    super(context);
    init();
  }

  public QQCircleView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public QQCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mPaint.setColor(Color.RED);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    // 获得两个圆心之间临时的距离
    float distance = (float) (Math.sqrt(Math.pow(circle_B_Center_PointF.y - circle_A_Center_PointF.y, 2)) + Math
        .pow(circle_B_Center_PointF.x - circle_A_Center_PointF.x, 2));
    distance = Math.min(distance, mMaxDistance);

    // A圆随着距离变大，大小逐渐缩小的计算
    float percent = distance / mMaxDistance;
    float circle_A_Radius_percentCahnge =
        ((Number) circle_A_Radius).floatValue() + percent * (((Number) (circle_A_Radius * 0.2f)).floatValue()
            - ((Number) circle_A_Radius).floatValue());

    // A，B圆之间的偏移量
    float yOffset = circle_A_Center_PointF.y - circle_B_Center_PointF.y;
    float xOffset = circle_A_Center_PointF.x - circle_B_Center_PointF.x;

    // 求圆心两点斜率
    double lineK = 0.0;
    if (xOffset != 0f) {
      lineK = yOffset / xOffset;
    }

    // 求两个点的集合
    circle_B_PointFs = getIntersectionPoints(circle_B_Center_PointF, circle_B_Radius, lineK);
    circle_A_PointFs = getIntersectionPoints(circle_A_Center_PointF, circle_A_Radius_percentCahnge, lineK);

    //通过公式求得贝塞尔曲线控制点
    mControlPointF = new PointF((circle_A_Center_PointF.x + circle_B_Center_PointF.x) / 2.0f,
        (circle_A_Center_PointF.y + circle_B_Center_PointF.y) / 2.0f);

    canvas.save();
    if (!isDisappear) {
      if (!isOutRange) {
        Path path = new Path();
        path.moveTo(circle_A_PointFs[0].x, circle_A_PointFs[0].y);
        path.quadTo(mControlPointF.x, mControlPointF.y, circle_B_PointFs[0].x, circle_B_PointFs[0].y);
        path.lineTo(circle_B_PointFs[1].x, circle_B_PointFs[1].y);
        path.quadTo(mControlPointF.x, mControlPointF.y, circle_A_PointFs[1].x, circle_A_PointFs[1].y);
        path.close();

        canvas.drawPath(path, mPaint);
        canvas.drawCircle(circle_B_Center_PointF.x, circle_B_Center_PointF.y, circle_B_Radius, mPaint);
      }
    }
    canvas.restore();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    int action = event.getAction();
    float downX = 0f;
    float downy = 0f;
    switch (action) {
      case MotionEvent.ACTION_DOWN:
        isOutRange = false;
        isDisappear = false;
        downX = event.getX();
        downy = event.getY();
        circle_B_Center_PointF.set(downX, downy);
        invalidate();
        break;
      case MotionEvent.ACTION_MOVE:
        downX = event.getX();
        downy = event.getY();
        circle_B_Center_PointF.set(downX, downy);
        invalidate();

        // 当超过最大值时断开
        float distance = (float) Math.sqrt(Math.pow(circle_B_Center_PointF.y - circle_A_Center_PointF.y, 2) + Math
            .pow(circle_B_Center_PointF.x - circle_A_Center_PointF.x, 2));
        if (distance > mMaxDistance) {
          isOutRange = true;
          invalidate();
        }
        break;
      case MotionEvent.ACTION_CANCEL:
        if (isOutRange) {
          distance = (float) Math.sqrt(Math.pow(circle_B_Center_PointF.y - circle_A_Center_PointF.y, 2) + Math
              .pow(circle_B_Center_PointF.x - circle_A_Center_PointF.x, 2));
          if (distance > mMaxDistance) {
            isDisappear = true;
            invalidate();
          } else {
            circle_B_Center_PointF.set(circle_A_Center_PointF.x, circle_A_Center_PointF.y);
            invalidate();
          }
        } else {
          final PointF tempMovePointF = new PointF(circle_B_Center_PointF.x, circle_B_Center_PointF.y);
          ValueAnimator animator = ValueAnimator.ofFloat(1.0f);
          animator.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
              float percent = animation.getAnimatedFraction();
              PointF pointF = new PointF(
                  ((Number) (tempMovePointF.x)).floatValue() + (((Number) circle_A_Center_PointF.x).floatValue()
                      - ((Number) (tempMovePointF.x)).floatValue()) * percent,
                  ((Number) (tempMovePointF.y)).floatValue() + ((Number) (circle_A_Center_PointF.y)).floatValue()
                      - ((Number) (tempMovePointF.y)).floatValue() * percent);
              circle_B_Center_PointF.set(pointF.x, pointF.y);
              invalidate();
            }
          });
          animator.setInterpolator(new OvershootInterpolator(4));
          animator.setDuration(500);
          animator.start();
        }
        break;
    }

    return true;
  }

  private static PointF[] getIntersectionPoints(PointF pointF, float radius, Double lineK) {
    PointF[] points = new PointF[2];

    float radian, xOffset = 0, yOffset = 0;
    if (lineK != null) {
      radian = (float) Math.atan(lineK);
      xOffset = (float) (Math.sin(radian) * radius);
      yOffset = (float) (Math.cos(radian) * radius);
    } else {
      xOffset = radius;
      yOffset = 0;
    }
    points[0] = new PointF(pointF.x + xOffset, pointF.y - yOffset);
    points[1] = new PointF(pointF.x - xOffset, pointF.y + yOffset);

    return points;
  }


}

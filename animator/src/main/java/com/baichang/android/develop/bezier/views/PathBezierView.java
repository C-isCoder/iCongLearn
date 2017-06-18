package com.baichang.android.develop.bezier.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.baichang.android.develop.bezier.utils.PathEvaluator;

/**
 * Created by iCong on 2017/6/18.
 */

public class PathBezierView extends View implements View.OnClickListener {
  private int mStartX, mStartY;

  private int mEndX, mEndY;

  private int mFlagX, mFlagY;

  private int mMoveX, mMoveY;

  private Paint mLinePaint;

  private Paint mCirclePaint;

  private Path mPath;
  private PointF mFlagPoint;
  private PointF mStartPoint;
  private PointF mEndPoint;
  private ValueAnimator mValueAnimator;

  public PathBezierView(Context context) {
    super(context);
  }

  public PathBezierView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  private void init() {
    mStartX = 150;
    mStartY = 200;

    mFlagX = 500;
    mFlagY = 0;

    mEndX = 700;
    mEndY = 800;

    mMoveX = mStartX;
    mMoveY = mStartY;

    mFlagPoint = new PointF(mFlagX, mFlagY);
    mStartPoint = new PointF(mStartX, mStartY);
    mEndPoint = new PointF(mEndX, mEndY);

    mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mCirclePaint.setColor(Color.RED);

    mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mLinePaint.setStyle(Paint.Style.STROKE);
    mLinePaint.setStrokeWidth(4);

    mPath = new Path();

    setOnClickListener(this);
  }

  public PathBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    mPath.reset();
    mPath.moveTo(mStartX, mStartY);
    mPath.quadTo(mFlagX, mFlagY, mEndX, mEndY);
    canvas.drawCircle(mMoveX, mMoveY, 30, mCirclePaint);
    canvas.drawCircle(mStartX, mStartY, 20, mCirclePaint);
    canvas.drawCircle(mEndX, mEndY, 20, mCirclePaint);
    canvas.drawPath(mPath, mLinePaint);
  }

  @Override public void onClick(final View v) {
    if (mValueAnimator == null) {
      mValueAnimator =
          ValueAnimator.ofObject(new PathEvaluator(mFlagPoint), mStartPoint, mEndPoint);
    }
    mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        PointF value = (PointF) animation.getAnimatedValue();
        mMoveX = (int) value.x;
        mMoveY = (int) value.y;
        invalidate();
      }
    });
    mValueAnimator.setDuration(600);
    mValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
    mValueAnimator.start();
  }
}

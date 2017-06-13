package com.baichang.android.develop.recycleritemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.baichang.android.develop.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerItemDecorationActivity extends AppCompatActivity {

   private List<String> mList = new ArrayList<>();

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_recyceler);
      initData();

      RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
      recyclerView.setAdapter(new Adapter());
//      recyclerView.addItemDecoration(new ItemDecoration() {
//         @Override
//         public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
//            outRect.top = 1;
//         }
//      });
      recyclerView.addItemDecoration(new ColorItemDecoration());
   }

   private void initData() {
      for (int i = 0; i < 100; i++) {
         mList.add("Text" + (i + 1));
      }
   }


   private class Adapter extends RecyclerView.Adapter<Holder> {

      @Override
      public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
         TextView view = new TextView(parent.getContext());
         view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
         view.setTextSize(14);
         view.setPadding(20, 20, 20, 20);
         view.setBackgroundColor(Color.WHITE);
         view.setTextColor(Color.BLACK);
         return new Holder(view);
      }

      @Override
      public void onBindViewHolder(Holder holder, int position) {
         ((TextView) holder.itemView).setText(mList.get(position));
      }

      @Override
      public int getItemCount() {
         return mList.size();
      }
   }

   class Holder extends ViewHolder {

      Holder(View itemView) {
         super(itemView);
      }
   }

   private class ColorItemDecoration extends ItemDecoration {

      private float mDividerHeight;
      private Paint mPaint;

      @Override
      public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
         super.getItemOffsets(outRect, view, parent, state);
         // 第一个ItemView 不需要在上面绘制
         if (parent.getChildAdapterPosition(view) != 0) {
            outRect.top = 1;
            mDividerHeight = 2;
         }
      }

      @Override
      public void onDraw(Canvas c, RecyclerView parent, State state) {
         mPaint = new Paint();
         mPaint.setColor(Color.RED);
         mPaint.setAntiAlias(true);
         int childCount = parent.getChildCount();
         for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(view);
            // 第一个ItemView 不需要绘制
            if (index == 0) {
               continue;
            }
            float dividerTop = view.getTop() - mDividerHeight;
            float diverLeft = parent.getPaddingLeft();
            float dividerBottom = view.getTop();
            float dividerRight = parent.getWidth() - parent.getPaddingRight();
            c.drawRect(diverLeft, dividerTop, dividerRight, dividerBottom, mPaint);
         }
      }
   }

   private class TimeLineItemDecoration extends ItemDecoration {

      private Paint mPaint;
      // ItemVIew 左边的间距
      private float mOffseLeft;
      // ItemView 右边的间距
      private float mOffsetTop;
      // 时间轴节点的半径
      private float mNodeRadius;

      public TimeLineItemDecoration(Context context) {
         mPaint = new Paint();
         mPaint.setAntiAlias(true);
         mPaint.setColor(Color.RED);
         // 获取ItemView 左右距离 也可以通过工具 dp2px
         mOffseLeft = context.getResources().getDimension(R.dimen.timeline_item_offset_left);
         mNodeRadius = context.getResources().getDimension(R.dimen.timeline_item_node_radius);
      }

      @Override
      public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
         super.getItemOffsets(outRect, view, parent, state);
         if (parent.getChildAdapterPosition(view)!=0){
            // 硬编码1px
            outRect.top = 1;
            mOffsetTop = 1;
         }
         outRect.left = (int) mOffseLeft;
      }
   }
}

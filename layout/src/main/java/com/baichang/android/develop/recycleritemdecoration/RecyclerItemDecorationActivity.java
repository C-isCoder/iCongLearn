package com.baichang.android.develop.recycleritemdecoration;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baichang.android.develop.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerItemDecorationActivity extends AppCompatActivity {

   private List<Book> mList = new ArrayList<>();

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_recyceler);
      initData();

      RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
      recyclerView.setAdapter(new Adapter());
     /* recyclerView.addItemDecoration(new ItemDecoration() {
         @Override
         public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            outRect.top = 1;
         }
      });*/
      //recyclerView.addItemDecoration(new ColorItemDecoration());
      //recyclerView.addItemDecoration(new TimeLineItemDecoration(this));
      recyclerView.addItemDecoration(new HotSaleItemDecoration());
   }

   private void initData() {
      mList.add(new Book("Go语言编程", "1"));
      mList.add(new Book("第一行代码", "2"));
      mList.add(new Book("从小工到专家", "3"));
      mList.add(new Book("Android高级编程", "4"));
      mList.add(new Book("Kotlin编程入门", "5"));
      mList.add(new Book("Java编程思想", "6"));
      mList.add(new Book("群英专神兵利器", "7"));
      mList.add(new Book("Android开发艺术探索", "8"));
      mList.add(new Book("第二行代码", "9"));
   }


   private class Adapter extends RecyclerView.Adapter<Holder> {

      final int[] IMAGE_RES = new int[]{
         R.mipmap.book_huochetou,
         R.mipmap.book_jieyouzahuodian,
         R.mipmap.book_renmin,
         R.mipmap.book_wangyangming,
         R.mipmap.book_tensoflow,
      };

      @Override
      public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
         return new Holder(LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_book, parent, false));
      }

      @Override
      public void onBindViewHolder(Holder holder, int position) {
         Book book = mList.get(position);
         holder.tvNumber.setText(book.getNumber());
         holder.tvName.setText(book.getName());
         holder.ivImage.setImageResource(IMAGE_RES[position % IMAGE_RES.length]);
      }

      @Override
      public int getItemCount() {
         return mList.size();
      }
   }

   class Holder extends ViewHolder {

      private TextView tvName;
      private TextView tvNumber;
      private ImageView ivImage;

      Holder(View itemView) {
         super(itemView);
         tvName = (TextView) itemView.findViewById(R.id.name);
         tvNumber = (TextView) itemView.findViewById(R.id.number);
         ivImage = (ImageView) itemView.findViewById(R.id.image);
      }
   }

   /**
    * 自定义一个 ItemDecoration 通常要根据需要，复写它的 3 个方法。
    * getItemOffsets 撑开 ItemView 上、下、左、右四个方向的空间
    * onDraw 在 ItemView 内容之下绘制图形
    * onDrawOver 在 ItemView 内容之上绘制图形。
    */
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
      private float mOffsetsLeft;
      // ItemView 右边的间距
      private float mOffsetsTop;
      // 时间轴节点的半径
      private float mNodeRadius;

      public TimeLineItemDecoration(Context context) {
         mPaint = new Paint();
         mPaint.setAntiAlias(true);
         mPaint.setColor(Color.RED);
         mPaint.setStrokeWidth(2);
         // 获取ItemView 左边偏移的距离 也可以通过工具 dp2px
         mOffsetsLeft = context.getResources().getDimension(R.dimen.timeline_item_offset_left);
         // 圆半径
         mNodeRadius = context.getResources().getDimension(R.dimen.timeline_item_node_radius);
      }

      @Override
      public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
         super.getItemOffsets(outRect, view, parent, state);
         if (parent.getChildAdapterPosition(view) != 0) {
            // 硬编码1px
            outRect.top = 1;
            // itemView 顶部偏移
            mOffsetsTop = 1;
         }
         outRect.left = (int) mOffsetsLeft;
      }

      @Override
      public void onDraw(Canvas c, RecyclerView parent, State state) {
         super.onDraw(c, parent, state);
         int childCount = parent.getChildCount();
         for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);

            int index = parent.getChildAdapterPosition(view);
            float dividerTop = view.getTop() - mOffsetsTop;
            if (index == 0) {
               dividerTop = view.getTop();
            }

            float dividerLeft = parent.getPaddingLeft();
            float dividerBottom = view.getBottom();

            float centerX = dividerLeft + mOffsetsLeft / 2;
            float centerY = dividerTop + (dividerBottom - dividerTop) / 2;

            float upLineTopX = centerX;
            float upLineTopY = dividerTop;
            float upLineBottomX = centerX;
            float upLineBottomY = centerY - mNodeRadius;

            //绘制上半部轴线
            c.drawLine(upLineTopX, upLineTopY, upLineBottomX, upLineBottomY, mPaint);

            //绘制时间轴结点
            if (index % 2 == 0) {
               mPaint.setStyle(Style.STROKE);
               c.drawCircle(centerX, centerY, mNodeRadius, mPaint);
            } else {
               mPaint.setStyle(Style.FILL);
               c.drawCircle(centerX, centerY, mNodeRadius, mPaint);
            }

            float downLineTopX = centerX;
            float downLineTopY = centerY + mNodeRadius;
            float downLineBottomX = centerX;
            float downLineBottomY = dividerBottom;

            //绘制上半部轴线
            c.drawLine(downLineTopX, downLineTopY, downLineBottomX, downLineBottomY, mPaint);
         }
      }
   }

   private class HotSaleItemDecoration extends ItemDecoration {

      private Bitmap icon;
      private Paint mPaint;

      HotSaleItemDecoration() {
         icon = BitmapFactory.decodeResource(getResources(), R.mipmap.hotsale);
         mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
      }

      @Override
      public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
         super.getItemOffsets(outRect, view, parent, state);
         if (parent.getChildAdapterPosition(view) != 0) {
            outRect.top = 2;
         }
      }

      @Override
      public void onDraw(Canvas c, RecyclerView parent, State state) {
         super.onDraw(c, parent, state);
      }

      @Override
      public void onDrawOver(Canvas c, RecyclerView parent, State state) {
         super.onDrawOver(c, parent, state);

         int childCount = parent.getChildCount();
         for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(childView);
            if (index < 3) {
               c.drawBitmap(icon, parent.getPaddingLeft()
                     + childView.getPaddingLeft() + dp2px(20),
                  childView.getTop(), mPaint);
            }
         }
      }
   }

   class Book {

      private String name;
      private String number;

      public String getName() {
         return name;
      }

      public void setName(String name) {
         this.name = name;
      }

      public String getNumber() {
         return number;
      }

      public void setNumber(String number) {
         this.number = number;
      }

      public Book(String name, String number) {
         this.name = name;
         this.number = number;
      }
   }

   private int dp2px(float dpValue) {
      // 根据手机的分辨率将dp的单位转成px(像素)
      final float scale = getResources().getDisplayMetrics().density;
      return (int) (dpValue * scale + 0.5f);
   }
}

package com.baichang.android.develop.recycleritemdecoration;

import android.graphics.Color;
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
      recyclerView.addItemDecoration(new ItemDecoration() {
         @Override
         public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            outRect.top = 1;
         }
      });
   }

   private void initData() {
      for (int i = 0; i < 10; i++) {
         mList.add("Text" + i);
      }
   }


   private class Adapter extends RecyclerView.Adapter<Holder> {

      @Override
      public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
         TextView view = new TextView(parent.getContext());
         view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
         view.setTextSize(14);
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
}

package com.baichang.android.develop.recycleritemdecoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.baichang.android.develop.R;
import java.util.ArrayList;
import java.util.List;

public class CityIndexItemDecorationActivity extends AppCompatActivity {
  private List<CityEntity> mList = new ArrayList<>();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_city_index_item_decoration);
    initData();
    initView();
  }

  private void initView() {
    RecyclerView rvList = (RecyclerView) findViewById(R.id.recycler_view);
    rvList.setAdapter(new CityAdapter());
    rvList.addItemDecoration(new CityItemDecoration());
  }

  private class CityAdapter extends RecyclerView.Adapter<CityHolder> {

    @Override public CityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new CityHolder(
          LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false));
    }

    @Override public void onBindViewHolder(CityHolder holder, int position) {
      CityEntity entity = mList.get(position);
      holder.tvName.setText(entity.getName());
    }

    @Override public int getItemCount() {
      return mList.size();
    }
  }

  class CityItemDecoration extends RecyclerView.ItemDecoration {
    private int mIndexHeight = dp2px(32);
    private Paint mbgPaint;
    private Paint mTextPaint;
    private int mLeftMargin = dp2px(16);

    CityItemDecoration() {
      super();
      mbgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
      mbgPaint.setColor(Color.rgb(76, 153, 182));

      mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
      mTextPaint.setColor(Color.WHITE);
      mTextPaint.setTextSize(dp2px(16));
    }

    @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
        RecyclerView.State state) {
      super.getItemOffsets(outRect, view, parent, state);
      int position = parent.getChildAdapterPosition(view);
      if (position == 0 || isFirstInGroup(position)) {
        outRect.top = mIndexHeight;
      }
    }

    //判断是不是组中的第一个位置
    //根据前一个组名，判断当前是否为新的组
    private boolean isFirstInGroup(int position) {
      if (position == 0) {
        return true;
      } else {
        int prevGroupId = mList.get(position - 1).getId();
        int groupId = mList.get(position).getId();
        return prevGroupId != groupId;
      }
    }

    @Override public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
      super.onDrawOver(c, parent, state);
      final int itemCount = state.getItemCount();
      final int childCount = parent.getChildCount();
      final int left = parent.getLeft() + parent.getPaddingLeft();
      final int right = parent.getRight() - parent.getPaddingRight();
      //标记上一个item对应的Group
      String preGroupName;
      //当前item对应的Group
      String currentGroupName = null;
      for (int i = 0; i < childCount; i++) {
        View view = parent.getChildAt(i);
        int position = parent.getChildAdapterPosition(view);
        preGroupName = currentGroupName;
        currentGroupName = mList.get(position).getProvince();
        if (currentGroupName == null || TextUtils.equals(currentGroupName, preGroupName)) continue;
        int viewBottom = view.getBottom();
        //top 决定当前顶部第一个悬浮Group的位置
        float top = Math.max(mIndexHeight, view.getTop());
        if (position + 1 < itemCount) {
          //获取下个GroupName
          String nextGroupName = mList.get(position + 1).getProvince();
          //下一组的第一个View接近头部
          if (!currentGroupName.equals(nextGroupName) && viewBottom < top) {
            top = viewBottom;
          }
        }
        //根据top绘制group
        c.drawRect(left, top - mIndexHeight, right, top, mbgPaint);
        //文字居中显示
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        float baseLine = top - (mIndexHeight - (fm.bottom - fm.top)) / 2 - fm.bottom;
        //Rect textRect = new Rect();
        //mTextPaint.getTextBounds(currentGroupName, 0, currentGroupName.length() - 1, textRect);
        //float baseLine = (mIndexHeight + textRect.height()) / 2;
        c.drawText(currentGroupName, left + mLeftMargin, baseLine, mTextPaint);
      }
    }
  }

  class CityHolder extends RecyclerView.ViewHolder {
    private TextView tvName;

    CityHolder(View itemView) {
      super(itemView);
      tvName = (TextView) itemView.findViewById(R.id.item_city_tv_name);
    }
  }

  class CityEntity {
    private String name;
    private String province;
    private int id;

    CityEntity(int id, String province, String name) {
      this.name = name;
      this.province = province;
      this.id = id;
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getProvince() {
      return province;
    }

    public void setProvince(String province) {
      this.province = province;
    }
  }

  private int dp2px(float dpValue) {
    // 根据手机的分辨率将dp的单位转成px(像素)
    final float scale = getResources().getDisplayMetrics().density;
    return (int) (dpValue * scale + 0.5f);
  }

  private void initData() {
    mList.add(new CityEntity(1, "山东省", "济南市"));
    mList.add(new CityEntity(1, "山东省", "烟台市"));
    mList.add(new CityEntity(1, "山东省", "青岛市"));
    mList.add(new CityEntity(1, "山东省", "威海市"));
    mList.add(new CityEntity(1, "山东省", "蓬莱市"));
    mList.add(new CityEntity(1, "山东省", "德州市"));
    mList.add(new CityEntity(1, "山东省", "泰安市"));
    mList.add(new CityEntity(1, "山东省", "莱芜市"));
    mList.add(new CityEntity(1, "山东省", "临沂市"));
    mList.add(new CityEntity(2, "河北省", "衡水市"));
    mList.add(new CityEntity(2, "河北省", "廊坊市"));
    mList.add(new CityEntity(2, "河北省", "石家庄"));
    mList.add(new CityEntity(2, "河北省", "邯郸市"));
    mList.add(new CityEntity(3, "山西省", "太原市"));
    mList.add(new CityEntity(3, "山西省", "运城市"));
    mList.add(new CityEntity(3, "山西省", "大同市"));
    mList.add(new CityEntity(4, "河南省", "南阳市"));
    mList.add(new CityEntity(4, "河南省", "郑州市"));
    mList.add(new CityEntity(4, "河南省", "平顶山市"));
    mList.add(new CityEntity(5, "江苏省", "南京市"));
    mList.add(new CityEntity(5, "江苏省", "宿迁市"));
    mList.add(new CityEntity(5, "江苏省", "无锡市"));
    mList.add(new CityEntity(5, "江苏省", "徐州市"));
    mList.add(new CityEntity(5, "江苏省", "苏州市"));
    mList.add(new CityEntity(5, "江苏省", "常州市"));
    mList.add(new CityEntity(5, "江苏省", "淮阴市"));
    mList.add(new CityEntity(5, "江苏省", "盐城市"));
    mList.add(new CityEntity(5, "江苏省", "扬州市"));
    mList.add(new CityEntity(5, "江苏省", "镇江市"));
    mList.add(new CityEntity(5, "江苏省", "松江市"));
    mList.add(new CityEntity(5, "江苏省", "南通市"));
  }
}

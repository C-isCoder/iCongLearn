package com.baichang.android.develop.nodatalayout;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import com.baichang.android.develop.R;
import com.baichang.android.develop.nodatalayout.StateLinearLayout.OnRefreshListener;

public class NoDataBaseActivity extends FragmentActivity implements View.OnClickListener {

  private View mNoDataView;
  private Button btnRefresh;
  private StateLinearLayout layout;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_no_data_base);
    findViewById(R.id.btn_error).setOnClickListener(this);
    layout = (StateLinearLayout) findViewById(R.id.view);
    layout.setOnRefreshListener(new OnRefreshListener() {
      @Override public void onRefresh() {
        layout.loadNormalView();
      }
    });
  }

  @Override public void onClick(View v) {
    layout.loadNoDataView();
    //    mNoDataView = getLayoutInflater().inflate(R.layout.no_data_layout, null);
    //    setContentView(mNoDataView);
    //    btnRefresh = (Button) mNoDataView.findViewById(R.id.btn_refresh);
    //    btnRefresh.setOnClickListener(new OnClickListener() {
    //      @Override
    //      public void onClick(View v) {
    //        setContentView(R.layout.activity_no_data_base);
    //        findViewById(R.id.btn_error).setOnClickListener(this);
    //      }
    //    });

  }
}

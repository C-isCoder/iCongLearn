package com.example.database.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.database.R;
import com.example.database.entity.User;
import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;
import io.realm.RealmList;

/**
 * Created by iCong on 2017/3/6.
 */

public class MainAdapter extends RealmBaseAdapter<User> {

  public MainAdapter(OrderedRealmCollection<User> list) {
    super(list);
  }

  public void setData(OrderedRealmCollection<User> list) {
    super.updateData(list);
  }

  @Override
  public View getView(int i, View view, ViewGroup viewGroup) {
    Holder holder;
    if (view == null) {
      holder = new Holder();
      view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout, viewGroup, false);
      holder.tvId = (TextView) view.findViewById(R.id.item_tv_id);
      holder.tvName = (TextView) view.findViewById(R.id.item_tv_name);
      holder.tvAge = (TextView) view.findViewById(R.id.item_tv_age);
      holder.tvSessionId = (TextView) view.findViewById(R.id.item_tv_sid);
      view.setTag(holder);
    } else {
      holder = (Holder) view.getTag();
    }
    if (adapterData != null) {
      User item = adapterData.get(i);
      holder.tvId.setText(String.valueOf(item.getId()));
      holder.tvAge.setText(String.valueOf(item.getAge()));
      holder.tvSessionId.setText(String.valueOf(item.getSessionId()));
      holder.tvName.setText(item.getName());
    }
    return view;
  }

  private class Holder {

    private TextView tvId;
    private TextView tvName;
    private TextView tvAge;
    private TextView tvSessionId;
  }
}

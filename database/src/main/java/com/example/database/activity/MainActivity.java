package com.example.database.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.database.R;
import com.example.database.adapter.MainAdapter;
import com.example.database.entity.User;
import io.realm.Realm;
import io.realm.Realm.Transaction;
import io.realm.Realm.Transaction.OnError;
import io.realm.Realm.Transaction.OnSuccess;
import io.realm.RealmAsyncTask;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener {

  private TextView tvResult;

  private Realm mRealm;
  private RealmAsyncTask mRealmAsyncTask;
  private List<User> mTestList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initTestData();
    initDatabase();
    initView();
  }

  // init view
  private void initView() {
    findViewById(R.id.btn_add).setOnClickListener(this);
    findViewById(R.id.btn_delete).setOnClickListener(this);
    findViewById(R.id.btn_find).setOnClickListener(this);
    findViewById(R.id.btn_change).setOnClickListener(this);
    tvResult = (TextView) findViewById(R.id.tv_result);
    ((ListView) findViewById(R.id.list_view)).setAdapter(new MainAdapter(queryAll()));
  }

  // init test data
  private void initTestData() {
    if (mTestList == null) {
      mTestList = new ArrayList<>();
    }
    User user1 = new User();
    user1.setId(0);
    user1.setName("iCong");
    user1.setAge(27);
    user1.setSessionId(10010);
    User user2 = new User();
    user2.setId(1);
    user2.setName("QiQi");
    user2.setAge(27);
    user2.setSessionId(10011);
    User user3 = new User();
    user3.setId(2);
    user3.setName("Baby");
    user3.setAge(18);
    user3.setSessionId(10012);
    mTestList.add(user1);
    mTestList.add(user2);
    mTestList.add(user3);
  }

  // init db
  private void initDatabase() {
    mRealm = Realm.getDefaultInstance();
//    mRealm.addChangeListener(new RealmChangeListener<Realm>() {
//      @Override
//      public void onChange(Realm element) {
//        mAdapter.notifyDataSetChanged();
//      }
//    });
    // auto update data
    mRealm.setAutoRefresh(true);
    clearDatabase();
    // init User db data
    mRealmAsyncTask = mRealm.executeTransactionAsync(new Transaction() {
      @Override
      public void execute(Realm realm) {
        for (User user : mTestList) {
          User realmUser = realm.createObject(User.class);
          realmUser.setId(mTestList.indexOf(user));
          realmUser.setName(user.getName());
          realmUser.setAge(user.getAge());
          realmUser.setSessionId(user.getSessionId());
        }
      }
    }, new OnSuccess() {
      @Override
      public void onSuccess() {
        Log.e("database", "success");
      }
    }, new OnError() {
      @Override
      public void onError(Throwable error) {
        Log.e("database", "error: " + error.getMessage());
      }
    });
  }

  /**
   * clear db
   */
  private void clearDatabase() {
    mRealm.beginTransaction();
    mRealm.deleteAll();
    mRealm.commitTransaction();
  }

  /**
   * add User
   *
   * @param user new User
   */
  private void add(User user) {
    mRealm.beginTransaction();
    mRealm.insert(user);
    mRealm.commitTransaction();
  }

  /**
   * db query first User
   */
  private User queryFirst() {
    return mRealm.where(User.class).findFirst();
  }

  /**
   * db query last User
   */
  private User queryLast() {
    List<User> users = queryAll();
    return users == null ? null : users.get(users.size() - 1);
  }

  /**
   * new User
   */
  private User createUser() {
    User aUser = new User();
    aUser.setName("add");
    aUser.setAge(12);
    aUser.setId(mTestList.size());
    aUser.setSessionId(10013);
    return aUser;
  }

  /**
   * db query all User
   */
  private RealmResults<User> queryAll() {
    return mRealm.where(User.class).findAll();
  }

  /**
   * db delete User
   */
  private void delete(User user) {
    user.deleteFromRealm();
  }

  /**
   * db delete first User
   */
  private void deleteFirst() {
    mRealm.beginTransaction();
    RealmResults<User> results = mRealm.where(User.class).findAll();
    results.deleteFirstFromRealm();
    mRealm.commitTransaction();
  }

  /**
   * db delete last User
   */
  private void deleteLast() {
    mRealm.beginTransaction();
    RealmResults<User> results = mRealm.where(User.class).findAll();
    results.deleteLastFromRealm();
    mRealm.commitTransaction();
  }

  /**
   * db update User
   */
  private boolean update(int id, String name) {
    boolean result;
    mRealm.beginTransaction();
    RealmResults<User> users = mRealm.where(User.class).equalTo("id", id).findAll();
    if (users.isEmpty()) {
      result = false;
    } else {
      for (User _user : users) {
        _user.setName(name);
      }
      result = true;
    }
    mRealm.commitTransaction();
    return result;
  }

  /**
   * db query id User
   *
   * @param id user id
   * @return result list
   */
  private RealmResults<User> query(int id) {
    RealmResults<User> users;
    mRealm.beginTransaction();
    users = mRealm.where(User.class).equalTo("id", id).findAll();
    mRealm.commitTransaction();
    return users;
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_add:
        add(createUser());
        printResult("add user\n" + queryLast());
        break;
      case R.id.btn_delete:
        deleteLast();
        printResult("delete last user success");
        break;
      case R.id.btn_find:
        List<User> users = query(3);
        if (users.isEmpty()) {
          printResult("no query id = 3 User");
        } else {
          printResult("query all User form id = 3\n" + users.toString());
        }
        break;
      case R.id.btn_change:
        if (update(3, "Change")) {
          printResult("update id = 3 User name update to Change success");
        } else {
          printResult("no query User");
        }
        break;
    }
  }


  /**
   * change result on UI
   */
  private void printResult(String text) {
    tvResult.setText("执行结果:" + text);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mRealmAsyncTask.cancel();
    mRealm.removeAllChangeListeners();
    mRealm.close();
  }
}

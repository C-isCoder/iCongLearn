package com.example.database.activity;

import android.content.SharedPreferences;
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
import com.example.database.utils.Util;
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

  private static final String TAG = MainActivity.class.getSimpleName();
  private TextView tvResult;

  private Realm mRealm;
  private RealmAsyncTask mRealmAsyncTask;
  private SharedPreferences mPreferences;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mPreferences = getSharedPreferences(MainActivity.class.getSimpleName(), MODE_PRIVATE);
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

  // init db
  private void initDatabase() {
    mRealm = Realm.getDefaultInstance();
    // auto update data
    mRealm.setAutoRefresh(true);
    // init User db data
    if (mPreferences.getBoolean("first", false)) {
      Log.e(TAG, "true");
      return;
    }
    mRealmAsyncTask = mRealm.executeTransactionAsync(new Transaction() {
      @Override public void execute(Realm realm) {
        ArrayList<User> users = Util.getTestData();
        for (User user : users) {
          User realmUser = realm.createObject(User.class);
          realmUser.setId(users.indexOf(user));
          realmUser.setName(user.getName());
          realmUser.setAge(user.getAge());
          realmUser.setSessionId(user.getSessionId());
        }
      }
    }, new OnSuccess() {
      @Override public void onSuccess() {
        printResult("init db success");
        mPreferences.edit().putBoolean("first", true).apply();
      }
    }, new OnError() {
      @Override public void onError(Throwable error) {
        printResult("init db error: " + error.toString());
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
   * db update User
   */
  private boolean update(String name, String modify) {
    boolean result;
    mRealm.beginTransaction();
    RealmResults<User> users = mRealm.where(User.class).equalTo("name", name).findAll();
    if (users.isEmpty()) {
      result = false;
    } else {
      for (User _user : users) {
        _user.setName(modify);
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

  /**
   * db query id User
   *
   * @param name user name
   * @return result list
   */
  private RealmResults<User> query(String name) {
    RealmResults<User> users;
    mRealm.beginTransaction();
    users = mRealm.where(User.class).equalTo("name", name).findAll();
    mRealm.commitTransaction();
    return users;
  }

  @Override public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_add:
        User user = Util.createUser();
        User last = queryLast();
        if (last == null) {
          user.setId(0);
        } else {
          user.setId(last.getId() + 1);
        }
        add(user);
        printResult("add user\n" + queryLast());
        break;
      case R.id.btn_delete:
        deleteLast();
        printResult("delete last user success");
        break;
      case R.id.btn_find:
        List<User> users = query("add");
        if (users.isEmpty()) {
          printResult("no query name = add User");
        } else {
          printResult("query all User form name = add\n" + users.toString());
        }
        break;
      case R.id.btn_change:
        if (update("add", "Change")) {
          printResult("update name = add User name update to Change success");
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

  @Override protected void onDestroy() {
    super.onDestroy();
    if (mRealmAsyncTask != null) {
      mRealmAsyncTask.cancel();
    }
    mRealm.close();
  }
}

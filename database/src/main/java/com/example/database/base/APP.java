package com.example.database.base;

import android.app.Application;
import android.util.Log;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by iCong on 2017/3/6.
 */

public class APP extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Realm.init(this);
    RealmConfiguration configuration = new RealmConfiguration.Builder()
        .deleteRealmIfMigrationNeeded().build();
    Realm.setDefaultConfiguration(configuration);
    Log.i("database", "APP-onCreate");
  }
}

package com.example.database.utils;

import com.example.database.entity.User;
import java.util.ArrayList;

/**
 * Created by iCong on 2017/3/7.
 */
public class Util {

  private static ArrayList<User> users;

  public static ArrayList<User> getTestData() {
    if (users == null) {
      users = new ArrayList<>();
    }
    users.clear();
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
    users.add(user1);
    users.add(user2);
    users.add(user3);
    return users;
  }

  /**
   * new User
   */
  public static User createUser() {
    User aUser = new User();
    aUser.setName("add");
    aUser.setAge(12);
    aUser.setSessionId(10013);
    return aUser;
  }
}

package com.example.database.entity;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created by iCong on 2017/3/6.
 */

public class User extends RealmObject {

  @Required // 非空字段
  private String name;
  private int age;
  //@Ignore // 不存储
  private int sessionId;
  //@PrimaryKey // 主键 默认加索引
  private int id;

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

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getSessionId() {
    return sessionId;
  }

  public void setSessionId(int sessionId) {
    this.sessionId = sessionId;
  }

  @Override public String toString() {
    return "\nName: " + name + " Age: " + age + " Id: " + id + " SessionId: " + sessionId;
  }
}

package com.task.github.models.user;

public class UserItem {
  private String login;
  private int id;
  private String name;

  //region getters-setters
  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
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
  //endregion
}

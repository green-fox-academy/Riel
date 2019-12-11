package com.task.github.models;

import com.task.github.models.commit.CommitItem;

import java.util.List;

public class Result {
  private String login;
  private String name;
  private List<CommitItem> commits;

  public Result (String login, String name, List<CommitItem> commits){
    this.login = login;
    this.name = name;
    this.commits = commits;
  }
}

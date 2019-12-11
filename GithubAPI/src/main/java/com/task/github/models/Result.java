package com.task.github.models;

import com.task.github.models.commit.CommitItem;
import com.task.github.models.user.UserItem;

import java.util.List;

public class Result {
  private UserItem user;
  private List<CommitItem> commits;

  public Result (UserItem user, List<CommitItem> commits){
    this.user = user;
    this.commits = commits;
  }

  //region getters
  public UserItem getUser() {
    return user;
  }

  public List<CommitItem> getCommits() {
    return commits;
  }
  //endregion
}

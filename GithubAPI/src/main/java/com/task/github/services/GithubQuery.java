package com.task.github.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.task.github.enums.QueryTypes;
import com.task.github.models.commit.CommitItem;
import com.task.github.models.fork.ForkItem;
import com.task.github.models.user.UserItem;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

public class GithubQuery {

  ConnectionService service = new ConnectionService();

  public List<CommitItem> getCommitsData(String ownerName, String repoName) throws IOException {
    // Commits:
    // https://developer.github.com/v3/repos/commits/#list-commits-on-a-repository
    // GET /repos/:owner/:repo/commits

    String commitLink = "https://api.github.com/repos/" + ownerName +"/" + repoName + "/commits";
    List<CommitItem> items = service.getQueryItems(commitLink, QueryTypes.COMMIT);
    return items;
  }

  public List<ForkItem> getForksData(String ownerName, String repoName) throws IOException {
    // Forks:
    // https://developer.github.com/v3/repos/forks/
    // GET /repos/:owner/:repo/forks

    String forkLink = "https://api.github.com/repos/" + ownerName + "/" + repoName + "/forks";
    List<ForkItem> items = service.getQueryItems(forkLink, QueryTypes.FORK);
    return items;
  }

  public UserItem getUserData(String loginName) throws IOException {
    // Single user:
    // https://developer.github.com/v3/users/#get-a-single-user
    // GET /users/:username

    String requestLink = "https://api.github.com/users/" + loginName;
    HttpURLConnection connection = service.buildConnection(requestLink, "GET");
    String content = service.readContent(connection);

    UserItem user =  service.getMapper().reader()
            .forType(new TypeReference<UserItem>() {})
            .readValue(content);

    return user;
  }
}

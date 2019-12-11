package com.task.github.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.github.models.commit.CommitItem;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class CommitService {

  public List<CommitItem> getCommits(String userName, String repoName) throws IOException {
    // GET /repos/:owner/:repo/commits

    String requestLink = "https://api.github.com/repos/" + userName +"/" + repoName + "/commits";
    List<CommitItem> items = new ArrayList<>();
    for (int i = 0; i < 2; i++) {
      String paginationData = ConnectionService.buildPaginationData(i+1, 100);
      HttpURLConnection connection = ConnectionService.buildConnection(requestLink + paginationData);
      String content = ConnectionService.buildContent(connection);
      ObjectMapper mapper = ConnectionService.buildMapper();

      items.addAll(mapper.reader()
              .forType(new TypeReference<List<CommitItem>>() {})
              .readValue(content));
    }

    return items;
  }
}

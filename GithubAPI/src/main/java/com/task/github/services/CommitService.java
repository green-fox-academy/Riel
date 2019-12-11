package com.task.github.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.github.models.commit.CommitItem;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommitService {

  public List<CommitItem> getCommits(String ownerName, String repoName) throws IOException {
    // Commits:
    // https://developer.github.com/v3/repos/commits/#list-commits-on-a-repository
    // GET /repos/:owner/:repo/commits


    // TODO: remove code duplication here
    String commitLink = "https://api.github.com/repos/" + ownerName +"/" + repoName + "/commits";
    List<CommitItem> items = new ArrayList<>();

    int page = 1;
    int itemPerPage = 100;
    boolean hasMore = true;

    while(hasMore) {
      String requestLink = commitLink + ConnectionService.buildPaginationData(page, itemPerPage);
      HttpURLConnection connection = ConnectionService.buildConnection(requestLink);
      String content = ConnectionService.buildContent(connection);
      ObjectMapper mapper = ConnectionService.buildMapper();

      List<CommitItem> newItems = mapper.reader()
              .forType(new TypeReference<List<CommitItem>>() {})
              .readValue(content);

      items.addAll(newItems);

      page ++;
      hasMore = (newItems.size() == itemPerPage);
    }

    return items;
  }
}

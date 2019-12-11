package com.task.github.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.github.models.fork.ForkItem;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

@Service
public class ForkService {

  public List<ForkItem> getForks(String ownerName, String repoName) throws IOException {
    // Forks:
    // https://developer.github.com/v3/repos/forks/
    // GET /repos/:owner/:repo/forks
    String forkLink = "https://api.github.com/repos/" + ownerName + "/" + repoName + "/forks";
    List<ForkItem> items = new ArrayList<>();

    int page = 1;
    int itemPerPage = 100;
    boolean hasMore = true;

    while(hasMore) {
      String requestLink = forkLink + ConnectionService.buildPaginationData(page, itemPerPage);
      HttpURLConnection connection = ConnectionService.buildConnection(requestLink);
      String content = ConnectionService.buildContent(connection);
      ObjectMapper mapper = ConnectionService.buildMapper();

      List<ForkItem> newItems = mapper.reader()
              .forType(new TypeReference<List<ForkItem>>() {})
              .readValue(content);

      items.addAll(newItems);

      page ++;
      hasMore = (newItems.size() == itemPerPage);
    }

    return items;
  }
}

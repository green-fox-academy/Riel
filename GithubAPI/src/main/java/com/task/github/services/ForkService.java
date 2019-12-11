package com.task.github.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.github.models.fork.ForkItem;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class ForkService {

  private final String FORK_LINK = "https://api.github.com/repos/green-fox-academy/level-test-basic-exam/forks";

  public List<ForkItem> getForks() throws IOException {
    // GET /repos/:owner/:repo/forks

    List<ForkItem> items = new ArrayList<>();

    // TODO : make logic adaptive for results
    for (int i = 0; i < 2; i++) {
      HttpURLConnection connection = ConnectionService.buildConnection(FORK_LINK + ConnectionService.buildPaginationData(i+1, 100));
      String content = ConnectionService.buildContent(connection);
      ObjectMapper mapper = ConnectionService.buildMapper();

      items.addAll(mapper.reader()
              .forType(new TypeReference<List<ForkItem>>() {})
              .readValue(content));
    }

    return items;
  }
}

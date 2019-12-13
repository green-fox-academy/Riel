package com.task.github.services;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.github.enums.QueryTypes;
import com.task.github.models.commit.CommitItem;
import com.task.github.models.fork.ForkItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class ConnectionService {

  ObjectMapper mapper = new ObjectMapper();

  public ConnectionService() {
    buildMapper();
  }

  public ObjectMapper getMapper() {
    return mapper;
  }

  protected <T> List<T> getQueryItems (String link, QueryTypes queryType) throws IOException {
    List<T> items = new ArrayList<>();

    int page = 1;
    int itemPerPage = 100;
    boolean hasMore = true;

    while(hasMore) {
      String requestLink = link + buildPaginationData(page, itemPerPage);
      HttpURLConnection connection = buildConnection(requestLink, "GET");
      String content = readContent(connection);

      TypeReference reference = null;
      if (queryType == QueryTypes.COMMIT){
        reference = new TypeReference<List<CommitItem>>() {};
      } else if (queryType == QueryTypes.FORK) {
        reference = new TypeReference<List<ForkItem>>(){};
      } else {
        throw new InvalidParameterException("Parameter is invalid: " + queryType.name());
      }


      List<T> newItems = mapper.reader().forType(reference).readValue(content);
      items.addAll(newItems);

      page ++;
      hasMore = (newItems.size() == itemPerPage);
    }

    return items;
  }

  protected HttpURLConnection buildConnection (String link, String method) throws IOException {
    URL url = new URL(link);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod(method);
    String accessData = buildAccessData();
    String encodedAuth = Base64.getEncoder().encodeToString((accessData).getBytes(StandardCharsets.UTF_8));
    String basicAuth = "Basic " + encodedAuth;
    connection.setRequestProperty ("Authorization", basicAuth);
    return connection;
  }

  protected String readContent(HttpURLConnection connection) throws IOException {
    StringBuilder content;
    InputStream stream = connection.getInputStream();
    try (BufferedReader input = new BufferedReader(new InputStreamReader(stream))) {
      String line;
      content = new StringBuilder();
      while ((line = input.readLine()) != null) {
        content.append(line);
        content.append(System.lineSeparator());
      }
    } finally {
      connection.disconnect();
    }

    return content.toString();
  }

  protected void buildMapper(){
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
  }

  private String buildAccessData(){
    String gitUserName = System.getenv("GITHUB_USERNAME");
    String gitPassword = System.getenv("GITHUB_PASSWORD");
    return gitUserName + ":" + gitPassword;
  }

  private String buildPaginationData(int index, int itemsPerPage) {
    return "?page=" + index + "&per_page=" + itemsPerPage;
  }
}

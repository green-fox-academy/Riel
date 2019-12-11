package com.task.github.services;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class ConnectionService {

  protected static HttpURLConnection buildConnection (String link) throws IOException {
    URL url = new URL(link);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    String accessdata = buildAccessData();
    String encodedAuth = Base64.getEncoder().encodeToString((accessdata).getBytes(StandardCharsets.UTF_8));
    String basicAuth = "Basic " + encodedAuth;
    connection.setRequestProperty ("Authorization", basicAuth);
    return connection;
  }

  protected static String buildContent(HttpURLConnection connection) throws IOException {
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

  protected static ObjectMapper buildMapper(){
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    return mapper;
  }

  protected static String buildAccessData(){
    String gitUserName = System.getenv("GITHUB_USERNAME");
    String gitPassword = System.getenv("GITHUB_PASSWORD");
    return gitUserName + ":" + gitPassword;
  }

  protected static String buildPaginationData(int index, int itemsPerPage) {
    return "?page=" + index + "&per_page=" + itemsPerPage;
  }
}

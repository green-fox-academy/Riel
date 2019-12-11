package com.task.github.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.github.models.user.UserItem;

import java.io.IOException;
import java.net.HttpURLConnection;

public class UserService {

  public UserItem getUser(String login) throws IOException {
    // GET /users/:username

    String requestLink = "https://api.github.com/users/" + login;
    HttpURLConnection connection = ConnectionService.buildConnection(requestLink);
    String content = ConnectionService.buildContent(connection);
    ObjectMapper mapper = ConnectionService.buildMapper();

    UserItem user =  mapper.reader()
            .forType(new TypeReference<UserItem>() {})
            .readValue(content);

    return user;
  }
}

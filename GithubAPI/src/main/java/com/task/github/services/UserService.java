package com.task.github.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.github.models.user.UserItem;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;

@Service
public class UserService {

  public UserItem getUser(String loginName) throws IOException {
    // Single user:
    // https://developer.github.com/v3/users/#get-a-single-user
    // GET /users/:username

    String requestLink = "https://api.github.com/users/" + loginName;
    HttpURLConnection connection = ConnectionService.buildConnection(requestLink);
    String content = ConnectionService.buildContent(connection);
    ObjectMapper mapper = ConnectionService.buildMapper();

    UserItem user =  mapper.reader()
            .forType(new TypeReference<UserItem>() {})
            .readValue(content);

    return user;
  }
}

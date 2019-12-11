package com.task.github;

import com.task.github.models.Result;
import com.task.github.models.commit.CommitItem;
import com.task.github.services.CommitService;
import com.task.github.services.ForkService;
import com.task.github.services.UserService;
import com.task.github.models.fork.ForkItem;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class GithubHandler implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(GithubHandler.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    ForkService forkService = new ForkService();
    UserService userService = new UserService();
    CommitService commitService = new CommitService();


    // Get all forks:
    List<ForkItem> forks = forkService.getForks();

    // Get all user logins who forked:
    List<String> ownerLogins = forks.stream().map(item -> item.getOwner().getLogin()).collect(Collectors.toList());

    // Get all relevant commits
    List<Result> resulst = new ArrayList<>();

    for (String login: ownerLogins) {
      String name = userService.getUser(login).getName();
      List<CommitItem> commits = commitService.getCommits(login, "level-test-basic-exam");
      Result result = new Result(login, name, commits);
      resulst.add(result);
    }

    // Collect necessary information:

    System.out.println("Process finished");
  }
}
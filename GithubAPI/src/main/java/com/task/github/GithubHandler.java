package com.task.github;

import com.task.github.models.Result;
import com.task.github.models.commit.CommitItem;
import com.task.github.models.user.UserItem;
import com.task.github.services.*;
import com.task.github.models.fork.ForkItem;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class GithubHandler implements CommandLineRunner {

  private ForkService forkService;
  private UserService userService;
  private CommitService commitService;
  private final String GFA_USER = "green-fox-academy";
  private final String LEVEL_TEST_REPO = "level-test-basic-exam";

  public GithubHandler(ForkService forkService, UserService userService, CommitService commitService){
    this.forkService = forkService;
    this.userService = userService;
    this.commitService = commitService;
  }

  public static void main(String[] args) {
    SpringApplication.run(GithubHandler.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    // Get all forks:
    List<ForkItem> forks = forkService.getForks(GFA_USER, LEVEL_TEST_REPO);

    // Get all user logins who forked:
    List<String> ownerLoginNames = forks.stream().map(item -> item.getOwner().getLogin()).collect(Collectors.toList());

    // Get all relevant commits
    List<Result> results = new ArrayList<>();

    // TODO: REMOVE THIS AFTER DEBUGGING: ------------
    // FOR DEBUGGING:
    // List<String> shortList = ownerLoginNames.subList(0,30);
    // ------------------------------------------------

    for (String login: ownerLoginNames) {
      UserItem user = userService.getUser(login);
      List<CommitItem> commits = commitService.getCommits(login, LEVEL_TEST_REPO);
      Result result = new Result(user, commits);
      results.add(result);
      System.out.println("Item processed: " + login);
    }

    // Collect necessary information:
    DataCollectorService collector = new DataCollectorService();
    String dataToExport = collector.processResults(results);
    FileIOHandler exporter = new FileIOHandler();
    exporter.saveToFile(dataToExport);
  }
}
package com.task.github.services;

import com.task.github.models.Result;
import com.task.github.models.commit.CommitItem;
import com.task.github.models.fork.ForkItem;
import com.task.github.models.user.UserItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LevelTestBasicExamQuery {
  private final String GFA_USER = "green-fox-academy";
  private final String LEVEL_TEST_REPO = "level-test-basic-exam";
  private GithubQuery queryService;

  public LevelTestBasicExamQuery() {
    this.queryService = new GithubQuery();
  }

  public void runQuery() throws IOException {
    // Get all forks:
    List<ForkItem> forks = queryService.getForksData(GFA_USER, LEVEL_TEST_REPO);

    // Get all user login names who forked the repository:
    List<String> ownerLoginNames = forks.stream().map(item -> item.getOwner().getLogin()).collect(Collectors.toList());

    // Get all relevant commits
    List<Result> results = new ArrayList<>();

    // TODO: REMOVE THIS AFTER DEBUGGING: ------------
    // FOR DEBUGGING:
    // ownerLoginNames = ownerLoginNames.subList(0,10);
    // ------------------------------------------------

    for (String login: ownerLoginNames) {
      UserItem user = queryService.getUserData(login);
      List<CommitItem> commits = queryService.getCommitsData(login, LEVEL_TEST_REPO);
      Result result = new Result(user, commits);
      results.add(result);

      System.out.println("Item processed: " + login);
    }

    // Collect necessary information:
    DataCollector collector = new DataCollector();
    String dataToExport = collector.processLevelTestBasicExamResults(results);
    FileIOHandler exporter = new FileIOHandler();
    exporter.saveToFile(dataToExport);

    System.out.println("Please make sure that you keep and commit the published reports!!!");
  }
}

package com.task.github.services;

import com.task.github.models.Result;
import com.task.github.models.commit.Commit;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class DataCollector {
  public String processLevelTestBasicExamResults (List<Result> results){
    String sep = ";";
    StringBuilder builder = new StringBuilder();
    int GFACommitNumber = 6;      // Number of commits created by GFA members

    // Report header
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please add your name: ");
    String reporterName = scanner.next();

    builder.append("Report created by: " + sep +  reporterName);
    builder.append("\n");

    builder.append("Report created at: " + sep + LocalDate.now());
    builder.append("\n");

    System.out.println("Please specify code version: ");
    String version = scanner.next();
    builder.append("Software version: " + sep + version);
    builder.append("\n");

    builder.append("Find logic here: " + sep + "https://github.com/green-fox-academy/riel");
    builder.append("\n");

    builder.append("Number of forks:" + sep + results.size());

    builder.append("\n");
    builder.append("\n");


    // Report data:
    builder.append("Login name" + sep + "Name" + sep +
            "Email" + sep + "Contributed" + sep + "Number of commits" + sep +
            "Last commit date" + sep + "Last commit author name" + sep +
            "Last commit author mail");
    builder.append("\n");

    for (Result result : results) {
      String login = result.getUser().getLogin();
      String userName = result.getUser().getName();
      String email = result.getUser().getEmail();
      int numberOfCommits = result.getCommits().size() - GFACommitNumber;
      boolean contributed = numberOfCommits > 0;

      Commit lastCommit = result.getCommits().get(0).getCommit();
      String lastDate = contributed ? lastCommit.getAuthor().getDate() : "";
      String lastCommitAuthorName = contributed ? lastCommit.getAuthor().getName() : "";
      String lastCommitAuthorMail = contributed ? lastCommit.getAuthor().getEmail() : "";
      builder.append(login + sep + (userName != null ? userName : "") + sep + (email!=null ? email : "")  + sep
              + contributed + sep + (contributed ? numberOfCommits : "") + sep + lastDate + sep +
              lastCommitAuthorName + sep + lastCommitAuthorMail);
      builder.append("\n");
    }

    return builder.toString();
  }
}

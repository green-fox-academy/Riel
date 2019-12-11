package com.task.github.services;

import com.task.github.models.Result;
import com.task.github.models.commit.Commit;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class DataCollectorService {
  public String processResults (List<Result> results){
    String sep = ";";
    StringBuilder builder = new StringBuilder();
    int GFACommitNumber = 6;

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
    builder.append("\n");

    builder.append("loging name" + sep + "name" + sep +
            "email" + sep + "contributed" + sep +
            "last commit date" + sep + "last commit author name" + sep +
            "last commit author mail");
    builder.append("\n");

    for (Result result : results) {
      String login = result.getUser().getLogin();

      // TODO: for name and email - do not display null!
      String userName = result.getUser().getName();
      String email = result.getUser().getEmail();
      boolean contributed = result.getCommits().size() > GFACommitNumber;

      Commit lastCommit = result.getCommits().get(0).getCommit();
      String lastDate = contributed ? lastCommit.getAuthor().getDate() : "";
      String lastCommitAuthorName = contributed ? lastCommit.getAuthor().getName() : "";
      String lastCommitAuthorMail = contributed ? lastCommit.getAuthor().getEmail() : "";
      builder.append(login + sep + userName + sep + email + sep + contributed + sep + lastDate + sep + lastCommitAuthorName + sep + lastCommitAuthorMail);
      builder.append("\n");
    }
    return builder.toString();
  }
}

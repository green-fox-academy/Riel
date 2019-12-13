package com.task.github;

import com.task.github.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class GithubHandler implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(GithubHandler.class, args);
  }

  @Override
  public void run(String... args) {
    // This report was requested by the marketing to see statistics
    // about the usage of level-test-basic-exam repository
    createLevelTestBasicExamReport();
  }

  private void createLevelTestBasicExamReport() {
    LevelTestBasicExamQuery query = new LevelTestBasicExamQuery();
    try{
      query.runQuery();
    } catch (IOException e){
      System.out.println("Error happened during queries to Github API: " + e.getMessage());
    }
  }
}
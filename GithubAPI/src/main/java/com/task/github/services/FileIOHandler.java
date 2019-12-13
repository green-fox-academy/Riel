package com.task.github.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileIOHandler {
  public void saveToFile(String dataToExport){
    String fileName = "results/results_" + LocalDate.now() + ".csv";
    Path filePath = Paths.get(fileName);

    try{
      List<String> text = new ArrayList<>();
      text.add(dataToExport);
      Files.write(filePath, text);
    } catch (IOException e) {
      System.out.println("Error happened during data export:" + e.getMessage());
      return;
    }

    System.out.println("Data saved successfully to: " + fileName);
  }
}

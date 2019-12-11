package com.task.github.models.commit;

public class Commit {
  private Author author;
  private Commiter commiter;
  private String message;

  //region getters-setters
  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public Commiter getCommiter() {
    return commiter;
  }

  public void setCommiter(Commiter commiter) {
    this.commiter = commiter;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
  //endregion
}

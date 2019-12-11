package com.task.github.models.fork;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForkItem {
  private String name;
  private String full_name;
  private boolean fork;
  private int forks_count;
  private int open_issues_count;
  private boolean has_issues;
  private boolean has_projects;
  private boolean has_wiki;
  private boolean has_pages;
  private boolean has_downloads;
  private boolean archived;
  private boolean disabled;
  private String visibility;
  private String pushed_at;
  private String created_at;
  private String updated_at;
  private Owner owner;

  //region getter-setter
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFull_name() {
    return full_name;
  }

  public void setFull_name(String full_name) {
    this.full_name = full_name;
  }

  public boolean isFork() {
    return fork;
  }

  public void setFork(boolean fork) {
    this.fork = fork;
  }

  public int getForks_count() {
    return forks_count;
  }

  public void setForks_count(int forks_count) {
    this.forks_count = forks_count;
  }

  public int getOpen_issues_count() {
    return open_issues_count;
  }

  public void setOpen_issues_count(int open_issues_count) {
    this.open_issues_count = open_issues_count;
  }

  public boolean isHas_issues() {
    return has_issues;
  }

  public void setHas_issues(boolean has_issues) {
    this.has_issues = has_issues;
  }

  public boolean isHas_projects() {
    return has_projects;
  }

  public void setHas_projects(boolean has_projects) {
    this.has_projects = has_projects;
  }

  public boolean isHas_wiki() {
    return has_wiki;
  }

  public void setHas_wiki(boolean has_wiki) {
    this.has_wiki = has_wiki;
  }

  public boolean isHas_pages() {
    return has_pages;
  }

  public void setHas_pages(boolean has_pages) {
    this.has_pages = has_pages;
  }

  public boolean isHas_downloads() {
    return has_downloads;
  }

  public void setHas_downloads(boolean has_downloads) {
    this.has_downloads = has_downloads;
  }
  public boolean isArchived() {
    return archived;
  }

  public void setArchived(boolean archived) {
    this.archived = archived;
  }

  public boolean isDisabled() {
    return disabled;
  }

  public void setDisabled(boolean disabled) {
    this.disabled = disabled;
  }

  public String getVisibility() {
    return visibility;
  }

  public void setVisibility(String visibility) {
    this.visibility = visibility;
  }

  public String getPushed_at() {
    return pushed_at;
  }

  public void setPushed_at(String pushed_at) {
    this.pushed_at = pushed_at;
  }

  public String getCreated_at() {
    return created_at;
  }

  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  public String getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(String updated_at) {
    this.updated_at = updated_at;
  }

  public Owner getOwner() {
    return owner;
  }

  public void setOwner(Owner owner) {
    this.owner = owner;
  }
  //endregion
}

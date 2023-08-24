package com.prmorais.crudspring.enums;

public enum Category {
  BACKEND("Back-end"), FRONTEND("Front-end");

  final String value;

  Category(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }
}

package com.prmorais.crudspring.enums;

public enum Status {
  ACTIVE("Ativo"), INACTIVE("Inativo");

  final String value;

  Status(String value) {
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

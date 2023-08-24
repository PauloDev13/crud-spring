package com.prmorais.crudspring.exception;

public class RecordNotFoundException extends RuntimeException{

  public RecordNotFoundException(Long id) {
    super("Registro não encontrado com o ID: " + id);
  }
}

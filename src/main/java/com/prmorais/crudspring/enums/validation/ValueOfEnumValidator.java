package com.prmorais.crudspring.enums.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Stream;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {
  private List<String> acceptedValues;
  @Override
  public void initialize(ValueOfEnum annotation) {
    acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
        .map(Enum::toString)
        .toList();
  }

  @Override
  public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
    if (value == null || "".equals(value.toString().trim())) {
      return true;
    }
    if (!acceptedValues.contains(value.toString())) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate("Só é aceito os valores " + acceptedValues)
          .addConstraintViolation();
      return false;
    }
    return true;
  }
}

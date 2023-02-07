package com.example.restaurante3.validator;

public interface Validator<T> {
    void validate(T t) throws ValidatorException;
}

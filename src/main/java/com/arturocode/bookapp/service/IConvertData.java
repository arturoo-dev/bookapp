package com.arturocode.bookapp.service;

public interface IConvertData {
    <T> T convertData(String json, Class<T> tClass);
}

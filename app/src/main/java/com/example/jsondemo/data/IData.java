package com.example.jsondemo.data;

import com.google.gson.stream.JsonReader;

import java.io.IOException;

public interface IData<T> {
    void read(JsonReader reader) throws IOException, IllegalStateException;
}

package com.example.jsondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jsondemo.data.Fruit;
import com.example.jsondemo.data.Result;
import com.google.gson.stream.JsonReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Result<Fruit>();
    }
}

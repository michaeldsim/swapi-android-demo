package com.michaeldavidsim.swapi_android_demo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cont = findViewById(R.id.cont);
        cont.setOnClickListener(e -> {
            startActivity(new Intent(this, HomeActivity.class));
        });
    }
}

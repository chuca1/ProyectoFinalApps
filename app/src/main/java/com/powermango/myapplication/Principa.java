package com.powermango.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Principa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principa);
    }
    public void nivel1(View view) {
        Intent intent = new Intent(Principa.this, MainActivity.class);
        startActivity(intent);
    }

}
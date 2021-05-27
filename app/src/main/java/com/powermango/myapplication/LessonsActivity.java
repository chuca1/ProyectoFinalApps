package com.powermango.myapplication;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

import static com.powermango.myapplication.Constants.*;

public class LessonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        int lessonType = getIntent().getExtras().getInt("lesson");

        switch (LessonType.valueOf(lessonType)) {
            case REGLA_GENERAL:
                Toast.makeText(getApplicationContext(), "Regla general", Toast.LENGTH_SHORT).show();
                break;
            case DIPTONGO_HIATO:
                Toast.makeText(getApplicationContext(), "Diptongo hiato", Toast.LENGTH_SHORT).show();
                break;
            case CASOS_ESPECIALES:
                Toast.makeText(getApplicationContext(), "Casos especiales", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
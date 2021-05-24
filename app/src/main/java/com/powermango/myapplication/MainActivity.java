package com.powermango.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView checkedTextView;
    EditText respEditText;
    Button botonCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Log.i("info", "Botón presionado");

        checkedTextView = findViewById(R.id.conf_checked);
        botonCheck = findViewById(R.id.check);

        botonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respEditText = findViewById(R.id.espacio);
                String respString = respEditText.getText().toString();
                if("Océano".equals(respString) || "océano".equals(respString)){
                    Toast.makeText(getApplicationContext(), "BUENA!!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "MÁS SUERTE LA PRÓXIMA", Toast.LENGTH_LONG).show();
                }
            }
        });*/

    }
}
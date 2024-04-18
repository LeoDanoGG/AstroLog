package com.prueba.astrolog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Aplicación para guardar registos astronómicos
 * Esta pantalla tiene un ListView para mostrar los registros.
 */
public class MainActivity extends AppCompatActivity {

    ListView AstroList;
    Button addButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.astroAdd);
        AstroList = findViewById(R.id.AstroListView);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AddLog = new Intent(MainActivity.this, AddLog.class);
                startActivity(AddLog);
            }
        });
    }
}
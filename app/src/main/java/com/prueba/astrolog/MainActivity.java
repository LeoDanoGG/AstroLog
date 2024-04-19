package com.prueba.astrolog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Aplicación para guardar registos astronómicos
 * Esta pantalla tiene un ListView para mostrar los registros.
 */
public class MainActivity extends AppCompatActivity {

    ListView AstroList;
    ArrayList<AstroItem> AstroLista = new ArrayList<>();
    Button addButton;
    AstroLogAdapter adapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.astroAdd);
        AstroList = findViewById(R.id.AstroListView);
        // Agregar log de prueba
        AstroLista.add(new AstroItem(R.drawable.estrella, "El Sol", new Date()));
        AstroLista.add(new AstroItem(R.drawable.cometa, "Cometa Halley", new Date()));

        // Vincular la vista de cada fila a los datos
        adapter = new AstroLogAdapter(this, R.layout.astrolog_item, AstroLista);

        // Vincular el adapta a la vista del listado
        AstroList.setAdapter(adapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AddLog = new Intent(MainActivity.this, AddLog.class);
                startActivity(AddLog);
            }
        });
    }
}
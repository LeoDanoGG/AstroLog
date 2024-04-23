package com.prueba.astrolog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    TextView removeAlert;
    Astro_List AstroLista;
    Button addButton;
    AstroLogAdapter adapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.astroAdd);
        AstroList = findViewById(R.id.AstroListView);
        removeAlert = findViewById(R.id.RemoveAlert);
        LoadLogs();
        AddNewLog();
        // Vincular la vista de cada fila a los datos
        adapter = new AstroLogAdapter(this, R.layout.astrolog_item, AstroLista.items);
        // Vincular el adapta a la vista del listado
        AstroList.setAdapter(adapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AddLog = new Intent(MainActivity.this, AddLog.class);
                startActivity(AddLog);
            }
        });
        AstroList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtener el elemento seleccionado
                String elemento = AstroLista.items.get(position).name;
                removeAlert.setText(elemento + " ha sido eliminado");
                // Eliminar el registro
                AstroLista.items.remove(position);
                // Notificar al adaptador del cambio
                adapter.notifyDataSetChanged();

                return true; // Indica que se ha manejado el evento
            }
        });
        AstroList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HideWarn();
            }
        });
    }
    public void HideWarn() {
        removeAlert.setText("");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SaveLogs();
    }
        public void AddNewLog() {
        Intent intent = getIntent();
        Bundle parametros = getIntent().getExtras();
        if (parametros != null) {
            Date newDay = (Date) parametros.getSerializable("Day");
            AstroLista.items.add(new AstroItem(intent.getIntExtra("Icon",R.drawable.estrella), intent.getStringExtra("Name"), newDay));
            SaveLogs();
        }
    }
    public void SaveLogs() {
        String json = AstroLista.ToJSON();
        SharedPreferences preferences = getSharedPreferences("Registros", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("conversaciones", json);
        editor.commit();
    }
    public void LoadLogs() {
        SharedPreferences preferences = getSharedPreferences("Registros", Context.MODE_PRIVATE);
        String json = preferences.getString("conversaciones", null);
        if (json == null) {
            AstroLista = new Astro_List();
        } else {
            AstroLista = Astro_List.ToJava(json);
        }
    }
}
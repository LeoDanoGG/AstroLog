package com.prueba.astrolog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
    TextView titleLog;
    TextView scrollAlert;
    TextView removeAlert;
    Astro_List AstroLista;
    Button addButton;
    AstroLogAdapter adapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Referencias
        addButton = findViewById(R.id.astroAdd);
        AstroList = findViewById(R.id.AstroListView);
        titleLog = findViewById(R.id.TitleTextView);
        scrollAlert = findViewById(R.id.ScrollAlertTextView);
        removeAlert = findViewById(R.id.RemoveAlert);
        // Métodos de control de los registros
        LoadLogs();
        ShowLogCount();
        AddNewLog();
        // Vincular la vista a los datos
        adapter = new AstroLogAdapter(this, R.layout.astrolog_item, AstroLista.items);
        // Vincular el adapter a la vista del listado
        AstroList.setAdapter(adapter);
        // Cambio de pantalla
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AddLog = new Intent(MainActivity.this, AddLog.class);
                startActivity(AddLog);
            }
        });
        // Cuando dejas pulsado un item
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
                SaveLogs();
                ShowLogCount();
                return true; // Indica que se ha manejado el evento
            }
        });
        // Cuando haces un click corto en cada item
        AstroList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                scrollAlert.setText("Fecha: " + AstroLista.items.get(i).fecha
                + "\nTipo: " + AstroLista.items.get(i).type);
                HideWarn();
            }
        });
    }
    /**
     * Muestra el número de registros y avisa si tienes que hacer scroll vertical
     */
    public void ShowLogCount() {
        titleLog.setText("Has registrado " + AstroLista.items.size() + " astros");
        if (AstroLista.items.size() > 7) scrollAlert.setText("Sube y baja en la lista para observar todos los registros");
        else scrollAlert.setText("");
    }
    /**
     * Oculta el mensaje de "registro eliminado"
     */
    public void HideWarn() {
        removeAlert.setText("");
    }

    /**
     * Se asegura de guardar los cambios en los registros al cambiar de pantalla
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SaveLogs();
    }

    /**
     * Agrega el nuevo registro y actualiza la lista
     */
        public void AddNewLog() {
        Intent intent = getIntent();
        if (intent.getStringExtra("Name") != null) {
            Date newDay = new Date(intent.getLongExtra("Date", 0L));
            AstroLista.items.add(new AstroItem(intent.getIntExtra("Icon",R.drawable.estrella), intent.getStringExtra("Name"), intent.getStringExtra("Type"), newDay));
            SaveLogs();
            ShowLogCount();
        }
    }

    /**
     * Guarda los cambios realizados en la lista
     */
    public void SaveLogs() {
        String json = AstroLista.ToJSON();
        SharedPreferences preferences = getSharedPreferences("Registros", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("conversaciones", json);
        editor.commit();
    }

    /**
     * Carga los datos guardados
     */
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
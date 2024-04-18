package com.prueba.astrolog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TimePicker;

/**
 * Aplicación para guardar registos astronómicos
 * Esta pantalla es para agregar un registro.
 */
public class AddLog extends AppCompatActivity {

    ImageButton[] AstroIcons = new ImageButton[8];
    Boolean[] IconSelected = new Boolean[8];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_log);
        // Date Picker
        DatePicker dateDP = findViewById(R.id.dateDP);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dateDP.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                // Hacer algo con la fecha elegida
                }
            });
        }
        // Time Picker
        TimePicker timeTP = findViewById(R.id.timeTP);
        timeTP.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
            // Hacer algo con la hora elegida
            }
        });
    }
}
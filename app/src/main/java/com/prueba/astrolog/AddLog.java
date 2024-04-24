package com.prueba.astrolog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Date;

/**
 * Aplicación para guardar registos astronómicos
 * Esta pantalla es para agregar un registro.
 */
public class AddLog extends AppCompatActivity {

    EditText LogName;
    TextView IconHint;
    String[] hint = new String[8];
    ImageButton[] AstroIcons = new ImageButton[8];
    int[] Icon = new int[8];
    Boolean[] IconSelected = new Boolean[8];
    Boolean correct = false;
    Date calendarDay = new Date();
    Button AddToLog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_log);
        // Nombre del astro
        LogName = findViewById(R.id.LogName);
        LogName.requestFocus();
        LogName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                // Si pulsa enter cierra el teclado
                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    imm.hideSoftInputFromWindow(LogName.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
        // Iconos
        AstroIcons[0] = findViewById(R.id.starButton);
        AstroIcons[1] = findViewById(R.id.moonButton);
        AstroIcons[2] = findViewById(R.id.planetButton);
        AstroIcons[3] = findViewById(R.id.sateliteButton);
        AstroIcons[4] = findViewById(R.id.cometButton);
        AstroIcons[5] = findViewById(R.id.nebulosaButton);
        AstroIcons[6] = findViewById(R.id.constellationButton);
        AstroIcons[7] = findViewById(R.id.shootingStarButton);
        IconHint = findViewById(R.id.TypeLogTextView);
        // Pistas
        hint[0] = "Estrella";
        hint[1] = "Luna";
        hint[2] = "Planeta";
        hint[3] = "Satélite";
        hint[4] = "Cometa/Asteroide";
        hint[5] = "Nebulosa";
        hint[6] = "Constelación";
        hint[7] = "Estrella fugaz";
        // Fotos
        Icon[0] = R.drawable.estrella;
        Icon[1] = R.drawable.luna;
        Icon[2] = R.drawable.planeta;
        Icon[3] = R.drawable.satelite;
        Icon[4] = R.drawable.cometa;
        Icon[5] = R.drawable.nube_cosmica;
        Icon[6] = R.drawable.constellation;
        Icon[7] = R.drawable.estrella_fugaz;
        // Botones
        for (int b = 0; b < AstroIcons.length; b++) {
            final int LogType = b;
            AstroIcons[b].setOnClickListener(view -> IconManager(LogType));
        }
        // Date Picker
        DatePicker dateDP = findViewById(R.id.dateDP);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dateDP.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                // Hacer algo con la fecha elegida
                    calendarDay.setDate(day);
                    calendarDay.setMonth(month);
                    calendarDay.setYear(year);
                }
            });
        }
        // Time Picker
        TimePicker timeTP = findViewById(R.id.timeTP);
        timeTP.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
            // Hacer algo con la hora elegida
                calendarDay.setHours(i);
                calendarDay.setMinutes(i1);
            }
        });
        // Botón de agregar
        AddToLog = findViewById(R.id.AddButton);
        AddToLog.setOnClickListener(view -> AddLogManager());
    }
    public void AddLogManager() {
        IconHint.setText("Dia " + calendarDay.getDate() + " Mes " + calendarDay.getMonth() + "Año " + calendarDay.getYear());
        if (!correct) IconHint.setText("Selecciona un icono antes de guardar");
        else if (LogName == null) IconHint.setText("Tienes que guardarlo con nombre");
        else AddLog();

    }
    public void AddLog() {
        int draw = IsAnyTrue(IconSelected);
        //AstroItem nuevo = new AstroItem(AstroIcons[Icon].getDrawingCacheBackgroundColor(),LogName.getText().toString(), calendarDay);
        Intent GoBack = new Intent(AddLog.this, MainActivity.class);
        int imagen = Icon[draw];
        GoBack.putExtra("Icon", imagen);
        GoBack.putExtra("Name", LogName.getText().toString());
        Bundle bundle = new Bundle();
        bundle.putSerializable("Day", calendarDay);
        GoBack.putExtras(bundle);
        startActivity(GoBack);
    }
    public int IsAnyTrue(Boolean[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i]) {
                return i; // Devuelve el índice tan pronto como encuentres un valor true en el array
            }
        }
        return -1; // Si no se encuentra ningún valor true en el array, devuelve -1
    }
    public void IconManager(int icon) {
        for (int i = 0; i < AstroIcons.length; i++) {
            IconSelected[i] = false;
            if (!IconSelected[i]) AstroIcons[i].setBackgroundResource(R.drawable.roundcorner);
        }
        IconSelected[icon] = true;
        if (IconSelected[icon]) {
            AstroIcons[icon].setBackgroundResource(R.drawable.selected);
            IconHint.setText(hint[icon]);
            correct = true;
        }
        else {
            AstroIcons[icon].setBackgroundResource(R.drawable.roundcorner);
            correct = false;
        }
    }
}
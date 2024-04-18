package com.prueba.astrolog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

/**
 * Aplicación para guardar registos astronómicos
 * Esta pantalla es para agregar un registro.
 */
public class AddLog extends AppCompatActivity {

    EditText LogName;
    ImageButton[] AstroIcons = new ImageButton[8];
    Boolean[] IconSelected = new Boolean[8];
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_log);
        LogName = findViewById(R.id.LogName);
        // Asegúrate de que el EditText tenga el foco
        LogName.requestFocus();
        LogName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN || keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    // Aquí puedes realizar la acción que desees al pulsar Enter
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
    public void IconManager(int icon) {
        for (int i = 0; i < AstroIcons.length; i++) {
            IconSelected[i] = false;
            if (!IconSelected[i]) AstroIcons[i].setBackgroundResource(R.drawable.roundcorner);
        }
        IconSelected[icon] = true;
        if (IconSelected[icon]) AstroIcons[icon].setBackgroundResource(R.drawable.selected);
        else AstroIcons[icon].setBackgroundResource(R.drawable.roundcorner);
    }
}
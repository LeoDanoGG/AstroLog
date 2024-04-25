package com.prueba.astrolog;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class AstroLogAdapter extends ArrayAdapter {
    Context context;
    int idLayoutItem;
    List<AstroItem> item;

    public AstroLogAdapter(@NonNull Context context, int idLayoutItem, @NonNull List<AstroItem> item) {
        super(context, idLayoutItem, item);
        this.context = context;
        this.idLayoutItem = idLayoutItem;
        this.item = item;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Crear vista de esta fila
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(idLayoutItem, parent, false);
        // Astro en la lista
        AstroItem item = this.item.get(position);
        // Poner el nombre
        TextView nombreTextView = convertView.findViewById(R.id.AstroNameTextView);
        nombreTextView.setText(item.name);
        // Poner la foto
        ImageView fotoImageView = convertView.findViewById(R.id.AstroImageView);
        fotoImageView.setImageDrawable(context.getDrawable(item.image));
        // Mostrar fecha
        TextView fechaTextView = convertView.findViewById(R.id.AstroTimeTextView);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(item.fecha); // Asignar el año del registro como el año del calendar
        int year = calendar.get(Calendar.YEAR);
        // Obtener la franja horaria
        TimeZone gmt = TimeZone.getDefault();
        int gmtLocal = gmt.getRawOffset();
        long gmtLog = item.fecha.getTime() - gmtLocal; // Adaptar la franja horaria
        item.fecha.setTime(gmtLog);
        calendar.setTimeZone(gmt);
        // Configurar el formato de la fecha
        if (year >= 2000) {
            // Si es a partir del 2000
            SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy HH:mm");
            String fecha = format.format(item.fecha);
            fechaTextView.setText(fecha);
        } else {
            // Si es anterior al 2000
            SimpleDateFormat format = new SimpleDateFormat("dd MMMM yy HH:mm");
            String fecha = format.format(item.fecha);
            fechaTextView.setText(fecha);
        }
        return convertView;
    }
}

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
import java.util.List;

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

        // Persona en esta posición
        AstroItem item = this.item.get(position);

        // Poner el nombre
        TextView nombreTextView = convertView.findViewById(R.id.AstroNameTextView);
        nombreTextView.setText(item.name);

        // Poner la foto
        /*try {
            ImageView fotoImageView = convertView.findViewById(R.id.AstroImageView);
            fotoImageView.setImageDrawable(context.getDrawable(item.image));
        } catch (Exception e) {
            ImageView fotoImageView = convertView.findViewById(R.id.AstroImageView);
            fotoImageView.setImageDrawable(context.getDrawable(R.drawable.cometa));
        }*/

        // Mostrar fecha
        TextView fechaTextView = convertView.findViewById(R.id.AstroTimeTextView);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String fecha = format.format(item.fecha);
        fechaTextView.setText(fecha);
        return convertView;
    }
}

package com.prueba.astrolog;

import android.media.Image;

import java.util.Date;

public class AstroItem {
    public int image;
    public String name;
    public Date fecha;

    public AstroItem(int image, String name, Date fecha) {
        this.image = image;
        this.name = name;
        this.fecha = fecha;
    }
}

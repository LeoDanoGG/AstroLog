package com.prueba.astrolog;

import android.media.Image;
import java.util.Date;

public class AstroItem {
    public int image;
    public String name;
    public String type;
    public Date fecha;

    public AstroItem(int image, String name, String type, Date fecha) {
        this.image = image;
        this.name = name;
        this.type = type;
        this.fecha = fecha;
    }
        public String GetName() {
            return this.name;
    }
}

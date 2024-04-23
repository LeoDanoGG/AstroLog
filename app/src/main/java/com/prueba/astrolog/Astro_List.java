package com.prueba.astrolog;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Date;

public class Astro_List {
    public ArrayList<AstroItem> items;

    public Astro_List() {
        items  = new ArrayList<>();
        // Agregar log de prueba
        items.add(new AstroItem(R.drawable.estrella, "El Sol", new Date()));
        items.add(new AstroItem(R.drawable.cometa, "Cometa Halley", new Date()));
    }
    public String ToJSON() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }
    public static Astro_List ToJava(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Astro_List.class);
    }
}

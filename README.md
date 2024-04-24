![Build Status](https://cdn6.aptoide.com/imgs/1/e/2/1e20de3284b3cdcc2ca2a12d81b76641_icon.png?w=128)
# **ASTRO LOG**
##### *Una app para los fans del cosmos.*
##### Developer: *Dano GG*
---
Una app que registra los astros que registres en el firmamento.
> La app está desarrollada en `Android Studio` utilizando lenguaje `Java`.
Se compone de dos pantallas: la pantalla inicial, donde puedes ver la lista de registros que tienes guardada (`Log activity`) y la pantalla de agregar un registro (`AddLog activity`).

Los registros que guardes se mostrarán con su nombre, tipo de astro y la fecha de guardado.
Tienes la opción de registrar astros como: 
|![Build Status](https://cdn-icons-png.freepik.com/256/15142/15142734.png?semt=ais_hybrid)|![Build Status](https://icons.iconarchive.com/icons/microsoft/fluentui-emoji-3d/256/Comet-3d-icon.png)|
| ------- | ------- |
| Estrellas :star: | Asteroides o cometas ☄️|
| Lunas 🌕| Nebulosas ☁️|
| Planetas 🪐| Constelaciones ✨|
| Satélites 🛰️| Estrellas fugaces 🌠 |

Los registros se guardan mediante una clase bastante sencilla llamada `AstroItem`.
```
public class AstroItem {
    // Atributos
    public int image;
    public String name;
    public Date fecha;
    // Constructor
    public AstroItem(int image, String name, Date fecha) {
        this.image = image;
        this.name = name;
        this.fecha = fecha;
    }
}
```
La fecha de cada objeto ==AstroLog== se guarda creando un `new Date()`
y después editándole mediante un `TimePicker` y un `DatePicker`.

| TimePicker (dentro del `xml`)| DatePicker (dentro del `xml`)|
| ------- | ------- |
| Modifica la hora | Modifica la fecha |
| Formato de 12 horas (hora, minutos) | Formato europeo (día, mes y año) |

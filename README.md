![Build Status](https://cdn6.aptoide.com/imgs/1/e/2/1e20de3284b3cdcc2ca2a12d81b76641_icon.png?w=128)
# **ASTRO LOG**
##### *An app for cosmos enthusiasts*.
##### Developer: *Dano GG*
---
An app that logs the celestial bodies you spot in the sky.
> The app is developed in `Android Studio` using the `Java` language.

It consists of two screens: the main screen, where you can view your saved list of entries (`Log activity`) and the screen to add a new entry (`AddLog activity`).

## Astro-Log
The entries you save will be displayed with their name, type of celestial body, and the date they were saved.
You have the option to register objects such as: 

|![Build Status](https://cdn-icons-png.freepik.com/256/15142/15142734.png?semt=ais_hybrid)|![Build Status](https://icons.iconarchive.com/icons/microsoft/fluentui-emoji-3d/256/Comet-3d-icon.png)|
| ------- | ------- |
| Stars :star: | Asteroids or comets ☄️|
| Moons 🌕| Nebulae ☁️|
| Planets 🪐| Constellations ✨|
| Satellites 🛰️| Shooting stars 🌠 |

## AstroItem Object
Entries are saved using a fairly simple class called `AstroItem`.

```java
public class AstroItem {
    // Attributes
    public int image;
    public String name;
    public Date date;
    // Constructor
    public AstroItem(int image, String name, Date date) {
        this.image = image;
        this.name = name;
        this.date = date;
    }
}

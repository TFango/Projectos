package Trabajo.Inventario;

import Trabajo.Personajes.Personaje;

public interface Objeto {
     String nombre();
     int peso();
     boolean esTemporal();
     TiposDeObjetos tipo();

     void usar(Personaje personaje);

}



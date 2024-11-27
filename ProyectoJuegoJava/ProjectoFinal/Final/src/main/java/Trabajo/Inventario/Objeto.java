package Trabajo.Inventario;

import Trabajo.Personajes.Personaje;

public interface Objeto {
     String nombre();
     int peso();
     boolean esTemporal();
     TiposDeObjetos tipo();
     boolean estaUsado();
     void marcarComoUsado();

     void usar(Personaje personaje);

}



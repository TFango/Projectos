package Trabajo.Inventario;

import Trabajo.Personajes.Personaje;

public class Pocion extends Objeto{
    public Pocion(){
        super(TiposDeObjetos.POCION.getNombre(), TiposDeObjetos.POCION.getPeso(), TiposDeObjetos.POCION.esTemporal(), TiposDeObjetos.POCION);
    }

    @Override
    public void usar(Personaje personaje) {
        personaje.curar(this.getTipo().getEfecto());
        System.out.println(personaje.getNombre() + " ha usado una pocion.");
    }
}

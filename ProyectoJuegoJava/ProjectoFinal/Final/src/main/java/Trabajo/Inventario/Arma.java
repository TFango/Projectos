package Trabajo.Inventario;

import Trabajo.Personajes.Personaje;

public class Arma extends Objeto {
    public Arma() {
        super(TiposDeObjetos.ARMA.getNombre(), TiposDeObjetos.ARMA.getPeso(), TiposDeObjetos.ARMA.esTemporal(), TiposDeObjetos.ARMA);
    }

    @Override
    public void usar(Personaje personaje) {
        personaje.aumentarFuerza(this.getTipo().getEfecto());
        System.out.println(personaje.getNombre() + " ha usado un arma.");
    }
}

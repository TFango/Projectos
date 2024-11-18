package Trabajo.Inventario;

import Trabajo.Personajes.Personaje;

public class Armadura extends Objeto{
    public Armadura() {
        super(TiposDeObjetos.ARMADURA.getNombre(), TiposDeObjetos.ARMADURA.getPeso(), TiposDeObjetos.ARMADURA.esTemporal, TiposDeObjetos.ARMADURA);
    }

    @Override
    public void usar(Personaje personaje) {
        personaje.aumentarDefensa(this.getTipo().getEfecto());
        System.out.println(personaje.getNombre() + " ha aumentado la defensa.");
    }
}

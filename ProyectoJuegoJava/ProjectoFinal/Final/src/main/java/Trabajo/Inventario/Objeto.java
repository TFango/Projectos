package Trabajo.Inventario;

import Trabajo.Personajes.Personaje;

public abstract class Objeto {
    protected String nombre;
    protected int peso;
    protected boolean esTemporal;

    public Objeto(String nombre, int peso, boolean esTemporal) {
        this.nombre = nombre;
        this.peso = peso;
        this.esTemporal = esTemporal;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract void usar(Personaje personaje);

    public boolean EsTemporal() {
        return esTemporal;
    }
}

package Trabajo.Inventario;

import Trabajo.Personajes.Personaje;

public abstract class Objeto {
    protected String nombre;
    protected int peso;
    protected boolean esTemporal;
    private TiposDeObjetos tipo;

    public Objeto(String nombre, int peso, boolean esTemporal, TiposDeObjetos tipo) {
        this.nombre = nombre;
        this.peso = peso;
        this.esTemporal = esTemporal;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract void usar(Personaje personaje);

    public boolean EsTemporal() {
        return esTemporal;
    }

    public TiposDeObjetos getTipo() {
        return tipo;
    }

    public int getPeso() {
        return peso;
    }

    public boolean isEsTemporal() {
        return esTemporal;
    }
}


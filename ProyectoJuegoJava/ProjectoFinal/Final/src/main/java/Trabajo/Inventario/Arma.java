package Trabajo.Inventario;

import Trabajo.Personajes.Personaje;

public class Arma implements Objeto {
    private String nombre;
    private int peso;
    private boolean esTemporal;
    private TiposDeObjetos tipo;

    public Arma() {
        this.nombre = TiposDeObjetos.ARMA.getNombre();
        this.peso = TiposDeObjetos.ARMA.getPeso();
        this.esTemporal = TiposDeObjetos.ARMA.esTemporal();
        this.tipo = TiposDeObjetos.ARMA;
    }
    @Override
    public String nombre() {
        return this.nombre;
    }

    @Override
    public int peso() {
        return this.peso;
    }

    @Override
    public boolean esTemporal() {
        return this.esTemporal;
    }

    @Override
    public TiposDeObjetos tipo() {
        return this.tipo;
    }
    @Override
    public void usar(Personaje personaje) {
        personaje.aumentarFuerza(this.tipo().getEfecto());
        System.out.println(personaje.getNombre() + " ha usado un arma.");
    }
}

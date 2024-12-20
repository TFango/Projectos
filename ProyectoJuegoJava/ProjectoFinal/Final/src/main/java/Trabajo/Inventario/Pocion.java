package Trabajo.Inventario;

import Trabajo.Personajes.Personaje;

public class Pocion implements Objeto{
    private String nombre;
    private int peso;
    private boolean esTemporal;
    private TiposDeObjetos tipo;
    private boolean usado;


    public Pocion() {
        this.nombre = TiposDeObjetos.POCION.getNombre();
        this.peso = TiposDeObjetos.POCION.getPeso();
        this.esTemporal = TiposDeObjetos.POCION.esTemporal();
        this.tipo = TiposDeObjetos.POCION;
        this.usado = false;
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
    public boolean estaUsado() {return usado;}

    @Override
    public void marcarComoUsado() {this.usado = true;}

    @Override
    public void usar(Personaje personaje) {
        personaje.curar(this.tipo().efecto);
    }


}

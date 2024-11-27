package Trabajo.Inventario;

import Trabajo.Personajes.Personaje;

public class Armadura implements Objeto{
    private String nombre;
    private int peso;
    private boolean esTemporal;
    private TiposDeObjetos tipo;
    private boolean usado;

    public Armadura() {
        this.nombre = TiposDeObjetos.ARMADURA.getNombre();
        this.peso = TiposDeObjetos.ARMADURA.getPeso();
        this.esTemporal = TiposDeObjetos.ARMADURA.esTemporal();
        this.tipo = TiposDeObjetos.ARMADURA;
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
        personaje.aumentarDefensa(this.tipo().getEfecto());
    }
}

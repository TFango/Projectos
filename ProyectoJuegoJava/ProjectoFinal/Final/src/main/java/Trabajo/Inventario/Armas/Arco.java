package Trabajo.Inventario.Armas;

import Trabajo.Inventario.Objeto;
import Trabajo.Inventario.TiposDeObjetos;
import Trabajo.Personajes.Personaje;

public class Arco implements Objeto {
    private String nombre;
    private int peso;
    private boolean esTemporal;
    private TiposDeObjetos tipo;
    private boolean usado;

    public Arco() {
        this.nombre = TiposDeObjetos.ARCO.getNombre();
        this.peso = TiposDeObjetos.ARCO.getPeso();
        this.esTemporal = TiposDeObjetos.ARCO.esTemporal();
        this.tipo = TiposDeObjetos.ARCO;
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
    public boolean estaUsado(){return usado;}

    @Override
    public void marcarComoUsado(){this.usado = true;}

    @Override
    public void usar(Personaje personaje) {
        personaje.aumentarFuerza(this.tipo().getEfecto());
    }
}

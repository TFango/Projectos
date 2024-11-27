package Trabajo.Inventario.Armas;

import Trabajo.Inventario.Objeto;
import Trabajo.Inventario.TiposDeObjetos;
import Trabajo.Personajes.Personaje;

public class Espada implements Objeto {
    private String nombre;
    private int peso;
    private boolean esTemporal;
    private TiposDeObjetos tipo;
    private boolean usado;

    public Espada() {
        this.nombre = TiposDeObjetos.ESPADA.getNombre();
        this.peso = TiposDeObjetos.ESPADA.getPeso();
        this.esTemporal = TiposDeObjetos.ESPADA.esTemporal();
        this.tipo = TiposDeObjetos.ESPADA;
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

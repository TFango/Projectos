package Trabajo.Inventario.Armas;

import Trabajo.Inventario.Objeto;
import Trabajo.Inventario.TiposDeObjetos;
import Trabajo.Personajes.Personaje;

public class Varita implements Objeto {
    private String nombre;
    private int peso;
    private boolean esTemporal;
    private TiposDeObjetos tipo;
    private boolean usado;

    public Varita() {
        this.nombre = TiposDeObjetos.VARITA.getNombre();
        this.peso = TiposDeObjetos.VARITA.getPeso();
        this.esTemporal = TiposDeObjetos.VARITA.esTemporal();
        this.tipo = TiposDeObjetos.VARITA;
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

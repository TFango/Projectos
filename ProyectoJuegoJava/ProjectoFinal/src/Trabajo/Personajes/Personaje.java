package Trabajo.Personajes;

import Trabajo.Inventario.Inventario;
import Trabajo.Inventario.Objeto;

public abstract class Personaje {
    protected String nombre;
    protected int nivel;
    protected int salud;
    public int fuerza;
    protected int defensa;
    protected int velocidad;
    protected Inventario inventario;

    public Personaje(String nombre, int nivel, int salud, int fuerza, int defensa, int velocidad) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.salud = salud;
        this.fuerza = fuerza;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.inventario = new Inventario(10);
    }

    // --------------
    // HACER DAÑO
    // --------------

    public abstract void atacar(Personaje personaje);

    // --------------
    // RECIBIR DAÑO
    // --------------

    public void recibirDaño(int cantidad){
        this.salud -= cantidad;
        if(this.salud < 0){
            this.salud = 0;
        }
    }

    // --------------
    // USAR OBJETO
    // --------------

    public void usarObjeto(Objeto objeto){
        objeto.usar(this);
        inventario.removerObjeto(objeto);
    }

    // --------------
    // SUBIR NIVEL
    // --------------

    public void subirNivel(){
        this.nivel++;
        this.fuerza += 5;
        this.defensa += 3;
        this.salud += 10;
        this.velocidad += 2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public Inventario getInventario() {
        return inventario;
    }
}

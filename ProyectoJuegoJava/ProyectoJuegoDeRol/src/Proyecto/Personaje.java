package Proyecto;

import java.io.Serializable;


public abstract class Personaje implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String nombre;
    protected int nivel;
    protected int salud;
    protected int mana;
    protected int fuerza;
    protected int defensa;
    protected int velocidad;
    protected Inventario inventario;

    //----------------------
    //Constructor
    //----------------------

    public Personaje(String nombre, int nivel, int salud, int mana, int fuerza, int defensa, int velocidad) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.salud = salud;
        this.mana = mana;
        this.fuerza = fuerza;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.inventario = new Inventario(10);
    }

    //----------------------
    //Atacar
    //----------------------

    public void atacar(Personaje enemigo){
        int daño = this.fuerza - enemigo.defensa;
        if(daño > 0){
            enemigo.recibirDaño(daño);
        }
    }

    //----------------------
    //Recibir daño
    //----------------------

    public void recibirDaño(int cantidad) {
        this.salud -= cantidad;
        if(this.salud <= 0) this.salud=0;
    }

    //----------------------
    //Usar un objeto
    //----------------------

    public void usarObjeto(Objeto objeto) {
        objeto.usar(this);
        inventario.removerObjeto(objeto);
    }

    //----------------------
    //Subir de Nivel
    //----------------------

    public void subirNivel() {
        this.nivel++;

        this.fuerza += 5;
        this.defensa += 3;
        this.salud += 10;
        this.mana += 5;
        this.velocidad += 2;
    }

    //----------------------
    //Getters
    //----------------------


    public String getNombre() {
        return nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public int getSalud() {
        return salud;
    }

    public int getMana() {
        return mana;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
}

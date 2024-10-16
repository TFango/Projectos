package Trabajo.Personajes;

import Trabajo.Inventario.Inventario;
import Trabajo.Inventario.Objeto;

public abstract class Personaje {
    protected String nombre;
    protected int nivel;
    protected int salud;
    protected int fuerza;
    protected int defensa;
    protected int velocidad;
    protected Inventario inventario;

    protected int aumentarFuerzaTemporal = 0;
    protected int aumentarDefensaTemporal = 0;

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
        System.out.println(nombre + " ha subido de nivel, ahora es nivel: " + nivel);
    }

    public void aumentarFuerza(int incremento){
        this.aumentarFuerzaTemporal = incremento;
        this.fuerza += incremento;
        System.out.println(nombre + " ha aumentado su fuerza, ahora cuenta con " + fuerza + " de fuerza.");
    }

    public void aumentarDefensa(int incremento){
        this.aumentarDefensaTemporal = incremento;
        this.defensa += incremento;
        System.out.println(nombre + " ha aumentado su defensa, ahora cuenta con " + defensa + " de defensa.");
    }

    public void curar(int incremento){
        this.salud += incremento;
        System.out.println(nombre + " se ha curado " + incremento + " salud total: " + salud);
    }

    public void revertirCambiosTemporales(){
        this.fuerza -= aumentarFuerzaTemporal;
        this.defensa -= aumentarDefensaTemporal;
        aumentarDefensaTemporal = 0;
        aumentarFuerzaTemporal = 0;
        System.out.println("Los incrementos de fuerza y defensa de " + nombre + " han sido revertidos.");
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

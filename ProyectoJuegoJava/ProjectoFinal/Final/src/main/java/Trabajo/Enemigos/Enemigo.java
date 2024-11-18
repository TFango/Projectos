package Trabajo.Enemigos;

import java.util.Random;

public class Enemigo {
    private String nombre;
    private int vida;
    private int ataque;
    private int defensa;
    private int experiencia;
    private int velocidad;
    private int saludMaxima = 100;

    public Enemigo(String nombre, int vida, int ataque, int defensa, int experiencia, int velocidad) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.experiencia = experiencia;
        this.velocidad = velocidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int atacar(){
        Random random = new Random();
        return ataque + random.nextInt(5);
    }

    public void recibirDaño(int daño){
        int dañoRecibido = Math.max(daño - defensa, 0);
        vida -= dañoRecibido;
        System.out.println(nombre + " recibio " + dañoRecibido + " de daño");
    }

    public String obtenerBarraSalud() {
        int longitudBarra = 20; // Longitud de la barra de salud
        int saludActual = (int) ((double) vida / saludMaxima * longitudBarra);
        String barraSalud = "|";
        for (int i = 0; i < longitudBarra; i++) {
            if (i < saludActual) {
                barraSalud += "#";
            } else {
                barraSalud += "-";
            }
        }
        barraSalud += "|";
        return barraSalud;
    }


    public boolean estaVivo(){
        return vida > 0;
    }
}


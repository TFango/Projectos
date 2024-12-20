package Trabajo.Personajes;

import Trabajo.Enemigos.Enemigo;

public class Guerrero extends Personaje {
    private int resistencia;
    private int fuerzaExtra;


    public Guerrero(String nombre) {
        super(nombre, 1, 120, 50, 20, 7);
        this.resistencia = 40;
        this.fuerzaExtra = 10;
    }

    @Override
    public void atacar(Enemigo enemigo) {
        enemigo.recibirDaño(this.fuerza);
    }


    public int getResistencia() {
        return resistencia;
    }

    public int getFuerzaExtra() {
        return fuerzaExtra;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public void setFuerzaExtra(int fuerzaExtra) {
        this.fuerzaExtra = fuerzaExtra;
    }
}


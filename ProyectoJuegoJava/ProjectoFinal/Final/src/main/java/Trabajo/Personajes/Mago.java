package Trabajo.Personajes;

import Trabajo.Enemigos.Enemigo;

public class Mago extends Personaje {
    private static final long serialVersionUID = 1l;
    private int poderMagico;
    private int manaExtra;

    public Mago(String nombre) {
        super(nombre, 1, 80, 10, 8, 6);
        this.poderMagico = 30;
        this.manaExtra = 60;
    }

    @Override
    public void atacar(Enemigo enemigo) {
        enemigo.recibirDaño(this.poderMagico);
    }

    public void lanzarHechizo(Personaje enemigo) {
        if(manaExtra >= 10) {
            enemigo.recibirDaño(this.poderMagico * 2);
            this.manaExtra -= 10;
        }else {
            System.out.println(nombre + " no tiene suficiente mana para lanzar un hechizo ");
        }
    }

    public int getPoderMagico() {
        return poderMagico;
    }

    public int getManaExtra() {
        return manaExtra;
    }

    public void setPoderMagico(int poderMagico) {
        this.poderMagico = poderMagico;
    }

    public void setManaExtra(int manaExtra) {
        this.manaExtra = manaExtra;
    }
}


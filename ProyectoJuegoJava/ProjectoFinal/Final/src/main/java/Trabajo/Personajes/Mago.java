package Trabajo.Personajes;

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
    public void atacar(Personaje enemigo) {
        enemigo.recibirDaño(this.poderMagico);
    }

    public void lanzarHechizo(Personaje enemigo) {
        enemigo.recibirDaño(this.poderMagico * 2);
        this.manaExtra -= 10;
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

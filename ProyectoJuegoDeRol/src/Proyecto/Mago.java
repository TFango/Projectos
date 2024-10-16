package Proyecto;


public class Mago extends Personaje {
    private static final long serialVersionUID = 1l;

    private int poderMagico;
    private int manaExtra;

    public Mago(String nombre) {
        super(nombre, 1, 80, 100, 10, 5, 7);
        this.poderMagico = 20;
        this.manaExtra = 15;
    }

    public void ataqueMagico(Personaje enemigo) {
        if (this.mana > 10) {
            this.mana -= 10;
            int dañoMagico = (this.poderMagico + this.manaExtra) - enemigo.defensa;
            enemigo.recibirDaño(dañoMagico);
        } else {
            System.out.println("No cuentas con suficiente mana para realizar un ataque magico podereso.");
        }
    }

    public int getPoderMagico() {
        return poderMagico;
    }

    public int getManaExtra() {
        return manaExtra;
    }
}

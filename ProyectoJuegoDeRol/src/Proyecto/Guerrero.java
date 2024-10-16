package Proyecto;

public class Guerrero extends Personaje {
    private static final long serialVersionUID = 1l;

    private int resistencia;
    private int fuerzaExtra;

    public Guerrero(String nombre) {
        super(nombre, 1, 100, 20, 15, 10, 5);
        this.resistencia = 10;
        this.fuerzaExtra = 10;
    }

    public void ataqueEspecial(Personaje enemigo) {
        int dañoEspecial = (this.fuerza + this.fuerzaExtra) - enemigo.defensa;
        if(dañoEspecial > 0) {
            enemigo.recibirDaño(dañoEspecial);
        }
    }

    public int getResistencia() {
        return resistencia;
    }

    public int getFuerzaExtra() {
        return fuerzaExtra;
    }
}

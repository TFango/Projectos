package Proyecto;

public class Arquero extends Personaje {
    private static final long serialVersionUID = 1l;

    private int precision;
    private int agilidad;

    public Arquero(String nombre) {
        super(nombre, 1, 90, 50, 12, 8, 20);
        this.precision = 15;
        this.agilidad = 12;
    }


    public void dispararFlechaRapida(Personaje enemigo) {
        int dañoFlechaRapida = this.fuerza + this.precision * 2;
        enemigo.recibirDaño(dañoFlechaRapida);
    }

    public int getPrecision() {
        return precision;
    }

    public int getAgilidad() {
        return agilidad;
    }
}

package Trabajo.Personajes;

public class Arquero extends Personaje {

    private static final long serialVersionUID = 1l;
    private int precision;
    private int agilidad;


    public Arquero(String nombre) {
        super(nombre, 1, 90, 15, 10, 12);
        this.precision = 20;
        this.agilidad = 25;
    }

    @Override
    public void atacar(Personaje enemigo) {
        enemigo.recibirDaño(this.fuerza);
    }

    public void dispararFlechaRapida(Personaje enemigo) {
        enemigo.recibirDaño(this.fuerza + this.precision);
    }

    public int getPrecision() {
        return precision;
    }

    public int getAgilidad() {
        return agilidad;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public void setAgilidad(int agilidad) {
        this.agilidad = agilidad;
    }
}

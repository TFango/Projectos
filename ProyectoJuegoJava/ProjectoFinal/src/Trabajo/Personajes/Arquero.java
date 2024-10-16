package Trabajo.Personajes;

public class Arquero extends Personaje {
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
}

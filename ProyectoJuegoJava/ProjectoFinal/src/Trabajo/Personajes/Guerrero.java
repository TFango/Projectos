package Trabajo.Personajes;

public class Guerrero extends Personaje {
    private int resistencia;
    private int fuerzaExtra;

    public Guerrero(String nombre) {
        super(nombre, 1, 120, 50, 20, 7);
        this.resistencia = 40;
        this.fuerzaExtra = 10;
    }

    @Override
    public void atacar(Personaje enemigo) {
        enemigo.recibirDaño(this.fuerza);
    }

    public void atacarEspecial(Personaje enemigo) {
        enemigo.recibirDaño(this.fuerza + this.fuerzaExtra);
    }

    public int getResistencia() {
        return resistencia;
    }

    public int getFuerzaExtra() {
        return fuerzaExtra;
    }
}

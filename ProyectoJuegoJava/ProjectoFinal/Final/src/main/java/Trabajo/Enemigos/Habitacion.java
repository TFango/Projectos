package Trabajo.Enemigos;

public class Habitacion {
    private boolean esJefe;
    private Enemigo enemigo;

    public Habitacion(boolean esJefe) {
        this.esJefe = esJefe;
        if (esJefe) {
            this.enemigo = new Enemigo("Jefe", 100, 20, 10, 50, 1000, 50);
        } else {
            this.enemigo = new Enemigo("Enemigo", 50, 10, 5, 20,20,40);
        }
    }

    public boolean tieneEnemigo() {
        return enemigo != null && enemigo.estaVivo();
    }

    public Enemigo getEnemigo() {
        return enemigo;
    }
}


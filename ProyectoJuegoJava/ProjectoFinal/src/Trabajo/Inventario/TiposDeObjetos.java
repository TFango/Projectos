package Trabajo.Inventario;

import Trabajo.Personajes.Personaje;

public enum TiposDeObjetos {
    ARMA("Espada", 5, 10, true){
        @Override
        public void usar(Personaje personaje) {
            personaje.aumentarFuerza(this.efecto);
            this
        }
    };
    private final String nombre;
    private final int peso;
    private final int efecto;
    private final boolean esTemporal;

    TiposDeObjetos(String nombre, int peso, int efecto, boolean esTemporal) {
        this.nombre = nombre;
        this.peso = peso;
        this.efecto = efecto;
        this.esTemporal = esTemporal;
    }

    public boolean esTemporal() {
        return esTemporal;
    }

    public abstract void usar(Personaje personaje);

    public String getNombre() {
        return nombre;
    }
}

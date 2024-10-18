package Trabajo.Inventario;

import Trabajo.Personajes.Personaje;

/**
 * Creacion de los tipos de objetos, la idea es que, las armas y armaduras sean temporales
 * Osea que solo sirvan para una sola batalla y despues de esto se eliminen.
 * Al contrario de la pocion, que permitara usarse cuando desee.
 */
public enum TiposDeObjetos {

    /**
     * Se crea un tipo objeto "arma" el cual sera temporal.
     */
    ARMA("Espada", 5, 10, true){
        @Override
        public void usar(Personaje personaje) {
            personaje.aumentarFuerza(ARMA.efecto);
            System.out.println(personaje.getNombre() + " ha usado un arma y aumenta su daño por esta batalla");
        }
    },

    ARMADOS("EspadaDos",5,15,true){
        @Override
        public void usar(Personaje personaje) {
            personaje.aumentarFuerza(ARMADOS.efecto);
            System.out.println(personaje.getNombre() + " ha usado un arma y aumenta su daño por esta batalla.");
        }
    },
    /**
     * Se crea otro tipo de objeto "armadura" el cual sera temporal.
     */
    ARMADURA("Armadura", 15, 20, true){
        @Override
        public void usar(Personaje personaje) {
            personaje.aumentarDefensa(ARMADURA.efecto);
            System.out.println(personaje.getNombre() + " ha usado una armadura y aumenta su defensa por esta batalla.");
        }
    },
    /**
     * Se crea otro tipo de objeto "pocion" el cual no sera temporal.
     */
    POCION("Pocion de vida", 0, 20, false){
        @Override
        public void usar(Personaje personaje) {
            personaje.curar(POCION.efecto);
            System.out.println(personaje.getNombre() + " ha usado una pocion de vida y se ha curado, vida total: " + personaje.getSalud());
        }
    };

    /**
     * Sirve para poder inicializar cada tipo de objeto con sus caracteristicas propias.
     */

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

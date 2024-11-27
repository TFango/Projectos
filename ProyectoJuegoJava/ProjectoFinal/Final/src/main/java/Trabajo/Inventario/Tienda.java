package Trabajo.Inventario;

import Trabajo.Personajes.Personaje;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Tienda {
    private Set<TiposDeObjetos> catalogo;
    private Map<Objeto, ObjetoDisponible> objetosDisponibles;
    private Personaje personaje;

    public Tienda(Personaje personaje) {
        this.catalogo = new HashSet<>();
        this.objetosDisponibles = new HashMap<>();
        this.personaje = personaje;


        //----------------
        //TIPOS DE OBJETOS
        //----------------

        Arma arma = new Arma();
        Armadura armadura = new Armadura();
        Pocion pocion = new Pocion();

        //-----------------
        //PRECIOS Y STOCK
        //-----------------

        objetosDisponibles.put(arma, new ObjetoDisponible(TiposDeObjetos.ARMA, 100, 2));
        objetosDisponibles.put(armadura, new ObjetoDisponible(TiposDeObjetos.ARMADURA, 75, 3));
        objetosDisponibles.put(pocion, new ObjetoDisponible(TiposDeObjetos.POCION, 35, 5));
    }

    public static class ObjetoDisponible {
        private final TiposDeObjetos tipo;
        private final int precio;
        private int stock;

        public ObjetoDisponible(TiposDeObjetos tipo, int precio, int stock) {
            this.tipo = tipo;
            this.precio = precio;
            this.stock = stock;
        }

        public TiposDeObjetos getTipo() {
            return tipo;
        }

        public int getPrecio() {
            return precio;
        }

        public int getStock() {
            return stock;
        }

        public void reducirStock() {
            if(stock > 0) {
                stock--;
            }
        }

        @Override
        public String toString() {
            return tipo.getNombre() + " (Precio: " + precio + ", Stock: " + stock + ")";
        }
    }

    //----------------
    //MOSTRAR CATALOGO
    //----------------

    public void mostrarCatalogo() {
        System.out.println("Bienvenido a la tienda: ");
        personaje.mostrarOro();
        int i = 1;
        for(Objeto objeto : objetosDisponibles.keySet()) {
            System.out.println(i + ". " + objeto.nombre() + " - Precio: " + objetosDisponibles.get(objeto).getPrecio());
            i++;
        }
    }

    //---------------
    //COMPRAR OBJETOS
    //---------------

    public void comprarObjetos(Personaje personaje, Objeto tipoObjeto) {
        ObjetoDisponible objeto = objetosDisponibles.get(tipoObjeto);

        if (objeto != null && personaje.getOro() >= objeto.getPrecio() && objeto.getStock() > 0) {
            personaje.reducirOro(objeto.getPrecio());
            personaje.getInventario().agregarObjetos(tipoObjeto);
            objeto.reducirStock();
            System.out.println(personaje.getNombre() + " ha comprado " + tipoObjeto.nombre());
        } else {
            System.out.println("No tienes suficiente oro para comprar " + tipoObjeto.nombre() + " o no está disponible.");
        }
    }

    //--------------------------
    // OBTENER INDICE DEL OBJETO
    //--------------------------

    public Objeto ObjetoPorIndice(int indice) {
        int i = 1;
        for(Objeto objeto : objetosDisponibles.keySet()) {
            if(i == indice) {
                return objeto;
            }
            i++;
        }
        return null;
    }

    //---------------------------------------
    // VERIFICAR SI EL OBJETO ESTA DISPONIBLE
    //---------------------------------------

    public boolean estaDisponible(TiposDeObjetos tipo) {
        return objetosDisponibles.containsKey(tipo);
    }
}



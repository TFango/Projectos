package Trabajo.Inventario;

import Trabajo.Inventario.Armas.Arco;
import Trabajo.Inventario.Armas.Espada;
import Trabajo.Inventario.Armas.Varita;
import Trabajo.Personajes.Arquero;
import Trabajo.Personajes.Guerrero;
import Trabajo.Personajes.Mago;
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

        Espada espada = new Espada();
        Varita varita = new Varita();
        Arco arco = new Arco();
        Armadura armadura = new Armadura();
        Pocion pocion = new Pocion();

        //-----------------
        //PRECIOS Y STOCK
        //-----------------

        objetosDisponibles.put(espada, new ObjetoDisponible(TiposDeObjetos.ESPADA, 100, 2));
        objetosDisponibles.put(varita, new ObjetoDisponible(TiposDeObjetos.VARITA, 100, 2));
        objetosDisponibles.put(arco, new ObjetoDisponible(TiposDeObjetos.ARCO, 100, 2));
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
        for(Map.Entry<Objeto, ObjetoDisponible> entry : objetosDisponibles.entrySet()){
            Objeto objeto = entry.getKey();
            ObjetoDisponible objetoDisponible = entry.getValue();

            if(esObjetoValidoParaPersonaje(personaje, objeto)){
                System.out.println("Objeto: " + objeto.nombre() + " - Precio: " + objetoDisponible.getPrecio() + " monedas - Stock: " + objetoDisponible.getStock());
            }
        }
    }

    private boolean esObjetoValidoParaPersonaje(Personaje personaje, Objeto objeto) {
        if(personaje instanceof Guerrero){
            return objeto instanceof Espada;
        }else if (personaje instanceof Mago){
            return objeto instanceof Varita;
        } else if (personaje instanceof Arquero) {
            return objeto instanceof Arco;
        }
        return false;
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
}



package Proyecto;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class Inventario implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Objeto> objetos;
    private int capacidadMaxima;

    public Inventario(int capacidadMaxima) {
        this.objetos = new ArrayList<>();
        this.capacidadMaxima = capacidadMaxima;
    }

    public boolean agregarObjeto(Objeto objeto){
        if(objetos.size() < capacidadMaxima){
            objetos.add(objeto);
            return true;
        }
        return false;
    }

    public boolean removerObjeto(Objeto objeto){
        return objetos.remove(objeto);
    }

    public void mostrarInventario(){
        for(Objeto objeto : objetos){
            System.out.println(objeto.getNombre());
        }
    }

    public List<Objeto> getObjetos() {
        return objetos;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }
}

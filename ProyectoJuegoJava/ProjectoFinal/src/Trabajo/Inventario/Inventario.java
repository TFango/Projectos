package Trabajo.Inventario;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Inventario implements Serializable{
    private static final long serialVersionUID = 1l;
    private List<Objeto> objetos;
    private int capacidadMaxima;


    public Inventario(int capacidadMaxima) {
        this.objetos = new ArrayList<>();
        this.capacidadMaxima = capacidadMaxima;
    }

    public void agregarObjetos(Objeto objeto){
        if(objetos.size() < capacidadMaxima){
            objetos.add(objeto);
            System.out.println("Has añadido " + objeto.getNombre() + " al inventario.");
        }else {
            System.out.println("Inventario lleno, no puedes añadir mas objetos.");
        }
    }

    public void removerObjeto(Objeto objeto){
        objetos.remove(objeto);
        System.out.println("Has eliminado " + objeto.getNombre() + " del inventario.");
    }

    public void mostrarInventario(){
        System.out.println("Inventario: ");
        for(Objeto objeto : objetos){
            System.out.println("- " + objeto.getNombre());
        }
    }

    public List<Objeto> getObjetos() {
        return objetos;
    }
}

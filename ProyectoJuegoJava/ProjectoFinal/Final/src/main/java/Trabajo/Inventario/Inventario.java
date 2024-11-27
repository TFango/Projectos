package Trabajo.Inventario;

import java.util.ArrayList;
import java.util.List;

public class Inventario  {
    private List<Objeto> objetos;
    private int capacidadMaxima;


    public Inventario(int capacidadMaxima) {
        this.objetos = new ArrayList<>();
        this.capacidadMaxima = capacidadMaxima;
    }

    public void agregarObjetos(Objeto objeto) {
        if(objetos.size() < capacidadMaxima){
            objetos.add(objeto);
        }else {
            System.out.println("Inventario lleno, no puedes añadir mas objetos.");
        }
    }

    public void removerObjeto(int index){
        if(index >= 0 && index < objetos.size()){
            Objeto objeto = objetos.get(index);
            objetos.remove(index);
        }else {
            System.out.println("Indice invalido.");
        }
    }

    public void mostrarInventario(){
        if(objetos.isEmpty()){
            System.out.println("El inventario esta vacio.");
        }else {
            System.out.println("Inventario: ");
            for(Objeto objeto : objetos){
                System.out.println("- " + objeto.nombre());
            }
        }
    }

    public List<Objeto> getObjetos() {
        return objetos;
    }
}


package Trabajo.Inventario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            System.out.println("Has eliminado " + objeto.nombre()+ " al inventario.");
        }else {
            System.out.println("Indice invalido.");
        }
    }

    public void mostrarInventario() {
        if (objetos.isEmpty()) {
            System.out.println("El inventario esta vacio.");
        } else {
            System.out.println("Inventario: ");
            for (Objeto objeto : objetos) {
                if (!objeto.estaUsado()) { // Solo mostrar objetos no usados
                    System.out.println((objetos.indexOf(objeto) + 1) + "- " + objeto.nombre());
                }
            }
        }
    }

    public Objeto obtenerPocion(){
        for (int i = 0; i < objetos.size(); i++){
            Objeto objeto = objetos.get(i);
            if(objeto.tipo() == TiposDeObjetos.POCION){
                removerObjeto(i);
                return objeto;
            }
        }
        return null;
    }

    public List<Objeto> getObjetos() {
        return objetos;
    }

  //  public Map<Objeto, Integer> getIndiceObjeto() {return indiceObjeto;}
}


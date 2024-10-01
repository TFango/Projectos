package Proyecto;

import java.io.Serializable;

public abstract class Objeto implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String nombre;
    protected int peso;
    protected int valor;

    public Objeto(String nombre, int peso, int valor) {
        this.nombre = nombre;
        this.peso = peso;
        this.valor = valor;
    }

    public abstract void usar(Personaje personaje);

    public String getNombre() {
        return nombre;
    }

    public int getPeso() {
        return peso;
    }

    public int getValor() {
        return valor;
    }
}

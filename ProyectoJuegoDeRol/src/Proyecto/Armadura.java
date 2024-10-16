package Proyecto;

public class Armadura extends Objeto{
    private int proteccion;

    public Armadura(String nombre, int peso, int valor, int proteccion) {
        super(nombre, peso, valor);
        this.proteccion = proteccion;
    }

    @Override
    public void usar(Personaje personaje) {
        personaje.defensa += this.proteccion;
    }

    public int getProteccion() {
        return proteccion;
    }
}

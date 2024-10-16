package Proyecto;

public class Pocion extends Objeto{
    private int curacion;

    public Pocion(String nombre, int peso, int valor, int curacion) {
        super(nombre, peso, valor);
        this.curacion = curacion;
    }

    @Override
    public void usar(Personaje personaje) {
        personaje.salud += this.curacion;
        if(personaje.salud > 100){
            personaje.salud = 100;
        }
    }

    public int getCuracion() {
        return curacion;
    }
}

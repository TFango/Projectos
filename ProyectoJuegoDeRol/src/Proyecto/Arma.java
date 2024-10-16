package Proyecto;

public class Arma extends Objeto {
    private int daño;
    private int durabilidad;

    public Arma(String nombre, int peso, int valor, int daño, int durabilidad) {
        super(nombre, peso, valor);
        this.daño = daño;
        this.durabilidad = durabilidad;
    }



    @Override
    public void usar(Personaje personaje) {
        //Incrementa temporalmente el daño del arma, con cual, del personaje
        personaje.fuerza+= this.daño;
        this.durabilidad--;
        if(this.durabilidad <= 0){
            System.out.printf("El arma usada se ha roto.");
        }
    }

    public int getDaño() {
        return daño;
    }

    public int getDurabilidad() {
        return durabilidad;
    }
}

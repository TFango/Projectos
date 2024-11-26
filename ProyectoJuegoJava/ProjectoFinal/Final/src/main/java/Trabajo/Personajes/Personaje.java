package Trabajo.Personajes;

import Trabajo.Enemigos.Enemigo;
import Trabajo.Inventario.Inventario;
import Trabajo.Inventario.Objeto;
import Trabajo.Inventario.TiposDeObjetos;

public abstract class Personaje implements Objeto {
    public Inventario getInventario;
    protected String nombre;
    protected int nivel;
    protected int salud;
    protected int fuerza;
    protected int defensa;
    protected int velocidad;
    protected int oro;
    protected int saludMaxima = 100;
    private int combatesGanados = 0;
    protected int experiencia = 0;
    protected Inventario inventario;

    protected int aumentarFuerzaTemporal = 0;
    protected int aumentarDefensaTemporal = 0;
    private boolean pocionUsada = false;
    private boolean fuerzaAumentadaTemporalmente = false;
    private boolean defensaAumentadaTemporalmente = false;
    private boolean objetoUsado = false;

    public Personaje(String nombre, int nivel, int salud, int fuerza, int defensa, int velocidad) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.salud = salud;
        this.fuerza = fuerza;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.oro = 50;
        this.inventario = new Inventario(10);
    }

    // --------------
    // HACER DAÑO
    // --------------

    public abstract void atacar(Enemigo enemigo);

    // --------------
    // RECIBIR DAÑO
    // --------------

    public void recibirDaño(int cantidad) {
        this.salud -= cantidad;
        if (this.salud < 0) {
            this.salud = 0;
        }
    }

    // --------------
    // GANAR ORO
    // --------------

    public void ganarOro(int cantidad){
        this.oro += cantidad;
        System.out.println("Has ganado " + cantidad + " monedas de oro!.");
    }

    // --------------
    // DESCONTAR ORO
    // --------------

    public void reducirOro(int cantidad) {
        if (cantidad <= oro) {
            oro -= cantidad;
        } else {
            System.out.println("No tienes suficiente oro.");
        }
    }

    // --------------
    // USAR OBJETO
    // --------------

    public void usarObjeto(int index) {
        if (index < 0 || index >= inventario.getObjetos().size()) {
            System.out.printf("Indice no valido.");
            return;
        }
        Objeto objeto = inventario.getObjetos().get(index);
        objeto.usar(this);
    }

    // --------------
    // SUBIR NIVEL
    // --------------

    public void añadirExperiencia(int exp){
        this.experiencia += exp;
        System.out.println(nombre + " ha ganado " + exp + " puntos de experiencia. Experiencia total: " + this.experiencia);
        if(this.experiencia >= 100){
            subirNivel();
        }
    }

    public void subirNivel() {
        this.nivel++;
        this.fuerza += 5;
        this.defensa += 3;
        this.salud += 10;
        if (this.salud > saludMaxima){
            this.saludMaxima = saludMaxima;
        }
        this.velocidad += 2;
        this.experiencia -= 100;
        System.out.println(nombre + " ha subido de nivel, ahora es nivel: " + nivel);
    }

    public void aumentarFuerza(int incremento) {
        this.fuerza += incremento;
        System.out.println(nombre + " ha aumentado su fuerza, ahora cuenta con " + fuerza + " de fuerza.");
    }

    public void aumentarDefensa(int incremento) {
        this.defensa += incremento;
        System.out.println(nombre + " ha aumentado su defensa, ahora cuenta con " + defensa + " de defensa.");
    }

    public void curar(int incremento) {
        this.salud += incremento;
        if(this.salud > saludMaxima){
            this.salud = saludMaxima;
        }
        System.out.println(nombre + " se ha curado " + incremento + " salud total: " + salud);
    }

    public void revertirCambiosTemporales() {
        if(aumentarFuerzaTemporal != 0) {
            this.fuerza -= aumentarFuerzaTemporal;
            aumentarFuerzaTemporal = 0;
        }
        if(aumentarDefensaTemporal != 0) {
            this.defensa -= aumentarDefensaTemporal;
            aumentarDefensaTemporal = 0;
        }
        if (pocionUsada){
            pocionUsada = false;
        }
        objetoUsado = false;
        System.out.println("Los incrementos de fuerza y defenesa de " + nombre + " han sido revertidos.");
    }

    public String obtenerSalud() {
        return nombre + " tiene " + salud + " puntos de salud.";
    }

    public String obtenerBarraSalud() {
        int longitudBarra = 20; // Longitud de la barra de salud
        int saludActual = (int) ((double) salud / saludMaxima * longitudBarra);
        String barraSalud = "|";
        for (int i = 0; i < longitudBarra; i++) {
            if (i < saludActual) {
                barraSalud += "#";
            } else {
                barraSalud += "-";
            }
        }
        barraSalud += "|";
        return barraSalud;
    }

    public void registrarVictoria() {
        combatesGanados++;
        System.out.println(nombre + " ha ganado " + combatesGanados + " combates.");
    }



    public boolean estaVivo(){
        return salud > 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getOro() {return oro;}

    public void setOro(int oro) {this.oro = oro;}

    public void mostrarOro(){System.out.println("Tienes " + oro + " de oro.");}

    public Inventario getInventario() {
        return inventario;
    }

    public boolean isFuerzaAumentadaTemporalmente() {
        return fuerzaAumentadaTemporalmente;
    }

    public boolean isDefensaAumentadaTemporalmente() {
        return defensaAumentadaTemporalmente;
    }

    public void setAumentarFuerzaTemporal(int aumentarFuerzaTemporal) {
        this.aumentarFuerzaTemporal = aumentarFuerzaTemporal;
    }

    public void setAumentarDefensaTemporal(int aumentarDefensaTemporal) {
        this.aumentarDefensaTemporal = aumentarDefensaTemporal;
    }

    public void setFuerzaAumentadaTemporalmente(boolean fuerzaAumentadaTemporalmente) {
        this.fuerzaAumentadaTemporalmente = fuerzaAumentadaTemporalmente;
    }

    public void setDefensaAumentadaTemporalmente(boolean defensaAumentadaTemporalmente) {
        this.defensaAumentadaTemporalmente = defensaAumentadaTemporalmente;
    }

    public void setPocionUsada(boolean pocionUsada) {
        this.pocionUsada = pocionUsada;
    }

    public boolean isObjetoUsado() {
        return objetoUsado;
    }

    public void setObjetoUsado(boolean objetoUsado) {
        this.objetoUsado = objetoUsado;
    }

    @Override
    public String nombre() {return this.nombre;}

    @Override
    public int peso() {return peso();}

    @Override
    public boolean esTemporal() {return esTemporal();}

    @Override
    public TiposDeObjetos tipo() {return tipo();}

    @Override
    public boolean estaUsado() {return false;}

    @Override
    public void marcarComoUsado() {}

    @Override
    public void usar(Personaje personaje) {}
}

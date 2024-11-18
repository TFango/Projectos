package Trabajo.Enemigos;

import Trabajo.Inventario.TiposDeObjetos;
import Trabajo.Personajes.Personaje;

import java.util.Scanner;

public class Combate {
    private Personaje jugador;
    private Enemigo enemigo;
    private Scanner sc;

    public Combate(Personaje jugador, Enemigo enemigo) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("🗡️ ¡Explorando mazmorra!");

        boolean turnoJugador = jugador.getVelocidad() >= enemigo.getVelocidad();
        System.out.println("🌟 ¡Enfrentando a un enemigo en esta habitación!");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("⚔️ **¡Comienza el combate contra " + enemigo.getNombre() + "!**");

        while (jugador.estaVivo() && enemigo.estaVivo()) {
            System.out.println("✨ **Salud:**");
            System.out.println("✨ **Salud de " + jugador.getNombre() + ":** " + jugador.obtenerBarraSalud());
            System.out.println("✨ **Salud de " + enemigo.getNombre() + ":** " + enemigo.obtenerBarraSalud());
            System.out.println("━━━━━━━━━━━━━━━━━━━━━");

            if (turnoJugador) {
                System.out.println("🔵 **El jugador ataca ahora.**");
                System.out.println("🔹 Turno del jugador:");
                System.out.println("1. 🗡️ Atacar");
                System.out.println("2. 🛡️ Usar objeto.");
                System.out.print("**Elige una opción:** ");
                int opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        jugador.atacar(enemigo);
                        break;
                    case 2:
                        usarObjeto();
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } else {
                System.out.println("🔴 **Enemigo ataca ahora.**");
                System.out.println("🔻 Turno del enemigo:");
                int dañoEnemigo = enemigo.atacar();
                jugador.recibirDaño(dañoEnemigo);
                System.out.println(enemigo.getNombre() + " ha atacado y causado " + dañoEnemigo + " de daño.");
            }

            if (!enemigo.estaVivo()) {
                System.out.println("🏆 **¡Has derrotado a " + enemigo.getNombre() + "!**");
                jugador.añadirExperiencia(enemigo.getExperiencia());
                jugador.registrarVictoria();
                break;
            } else if (!jugador.estaVivo()) {
                System.out.println("Has sido derrotado.");
                break;
            }
            turnoJugador = !turnoJugador;
        }
        jugador.revertirCambiosTemporales();
        jugador.setObjetoUsado(false);
    }


    private void usarObjeto() {

        if (jugador.isObjetoUsado()) {
            System.out.println("Ya has usado este objeto en el combate. No puedes usarlo devuelta.");
            return;
        }
        System.out.println("Elige el tipo de objeto a usar: ");
        System.out.println("1. Arma");
        System.out.println("2. Armadura");
        System.out.println("3. Pocion");
        int opcion = sc.nextInt();

        TiposDeObjetos objeto;
        switch (opcion) {
            case 1:
                objeto = TiposDeObjetos.ARMA;
                break;
            case 2:
                objeto = TiposDeObjetos.ARMADURA;
                break;
            case 3:
                objeto = TiposDeObjetos.POCION;
                break;
            default:
                System.out.println("Opcion no valida.");
                return;
        }
        objeto.usar(jugador);

        jugador.setObjetoUsado(true);

        if (objeto.esTemporal()) {
            System.out.println("El objeto " + objeto.getNombre() + " es temporal y se ha descartado tras esta batalla.");
        }
    }

}



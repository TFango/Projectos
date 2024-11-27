package Trabajo.Enemigos;

import Trabajo.Inventario.Objeto;
import Trabajo.Inventario.Tienda;
import Trabajo.Inventario.TiposDeObjetos;
import Trabajo.Personajes.Personaje;

import java.util.List;
import java.util.Scanner;

public class Combate {
    private Personaje jugador;
    private Enemigo enemigo;
    private Tienda tienda;
    private Scanner sc;

    public Combate(Personaje jugador, Enemigo enemigo, Tienda tienda) {
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.tienda = new Tienda(jugador);
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
                System.out.println("3. 🏪 Comprar objeto.");
                System.out.print("**Elige una opción:** ");
                int opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        jugador.atacar(enemigo);
                        break;
                    case 2:
                        usarObjeto();
                        break;
                    case 3:
                        tienda.mostrarCatalogo();
                        System.out.println("Ingrese el indice del objeto que desea comprar: ");
                        int indice = sc.nextInt();
                        Objeto objetoComprado = tienda.ObjetoPorIndice(indice);
                        tienda.comprarObjetos(jugador, objetoComprado);
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
                int oroGanado = enemigo.getOro();
                jugador.ganarOro(oroGanado);
                System.out.println("¡Has ganado " + enemigo.getOro() + " de oro!.");
                jugador.mostrarOro();
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
        List<Objeto> objetos = jugador.getInventario().getObjetos();

        if (objetos.isEmpty()) {
            System.out.println("No tienes objetos para usar.");
            return;
        }

        while (true) {
            System.out.println("Elige el tipo de objetos a usar: ");
            for (int i = 0; i < objetos.size(); i++) {
                Objeto objeto = objetos.get(i);
                System.out.println((i + 1) + ". " + objeto.nombre());
            }
            int opcion = sc.nextInt();
            int indice = opcion - 1;

            if(indice < 0 || indice >= objetos.size()) {
                System.out.println("Indice invalido.");
                continue;
            }else {
                Objeto objetSellecionado = objetos.get(indice);
                objetSellecionado.usar(jugador);
                jugador.setObjetoUsado(true);
                jugador.getInventario().removerObjeto(indice);

                if(objetSellecionado.esTemporal()){
                    System.out.println("El objeto " + objetSellecionado.nombre() + " es temporal y se ha descartado tras esta batalla.");
                }
            }

        }
    }

}

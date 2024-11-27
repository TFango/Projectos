package Trabajo.Usuarios;

import Trabajo.Enemigos.Combate;
import Trabajo.Enemigos.Habitacion;
import Trabajo.Enemigos.Mazmorra;
import Trabajo.Inventario.Objeto;
import Trabajo.Inventario.Tienda;
import Trabajo.Inventario.TiposDeObjetos;
import Trabajo.Personajes.Personaje;
import Trabajo.excepciones.OpcionInvalidaException;
import Trabajo.excepciones.PersonajeException;


import java.util.List;
import java.util.Scanner;

import static Trabajo.Personajes.ManejoPersonajes.crearPersonajeDemo;


public class JuegoDemo {
    private Scanner sc;

    public JuegoDemo(Scanner sc) {
        this.sc = sc;
    }

    Personaje personajeActualDemo = null;

    public void iniciarJuegoDemo() {
        boolean continuar = true;

        try {
            personajeActualDemo = crearPersonajeDemo(sc);
        } catch (PersonajeException e) {
            System.out.println("Error: " + e.getMessage());
        }

        if (personajeActualDemo == null) {
            boolean explorando = true;

            while (explorando) {
                mostrarSubmenuExploracion();
                System.out.println("Seleccione una opcion: ");
                String opcionExploracion = sc.nextLine();
                Tienda tienda = new Tienda(personajeActualDemo);

                switch (opcionExploracion) {
                    case "1":
                        Mazmorra mazmorra = new Mazmorra(1);
                        boolean mazmorraCompletada = false;

                        while (mazmorra.getPisoActual() < mazmorra.getCantidadPisos()) {
                            List<Habitacion> habitaciones = mazmorra.getHabitacionesDelPisoActual();

                            for (Habitacion habitacion : habitaciones) {
                                if (habitacion.tieneEnemigo()) {

                                    Combate combate = new Combate(personajeActualDemo, habitacion.getEnemigo(), tienda);
                                    combate.iniciar();

                                    if (!personajeActualDemo.estaVivo()) {
                                        System.out.println("Has sido derrotado. Fin de la exploracion.");
                                        explorando = false;
                                        personajeActualDemo = null;
                                        System.out.println("Personaje eliminado.");
                                        break;
                                    }
                                }
                            }
                            if (mazmorra.avanzarPiso()) {
                                System.out.println("Has avanzado al siguiente piso.");
                            } else {
                                mazmorraCompletada = true;
                                break;
                            }
                        }
                        if (mazmorraCompletada) {
                            System.out.println("━━━━━━━━━━━━━━━━━━━━━");
                            System.out.println("🏆 **Haz completado la mazmorra. ¡Felicidades!**");
                            personajeActualDemo = null;
                            System.out.println("Personaje eliminado");
                        }
                        explorando = false;
                        break;
                    case "2":
                        personajeActualDemo.getInventario().mostrarInventario();
                        break;
                    case "3":
                        System.out.println("Ingrese el indice del objeto a usar: ");
                        try {
                            int index = Integer.parseInt(sc.nextLine());
                            if (index >= 0 && index < personajeActualDemo.getInventario().getObjetos().size()) {
                                Objeto objetoUsado = personajeActualDemo.getInventario().getObjetos().get(index);

                                if (objetoUsado != null) {
                                    if (!objetoUsado.estaUsado()) {

                                        if (objetoUsado.tipo() == TiposDeObjetos.ARMA) {
                                            System.out.println("Usaste un arma. Fuerza aumentada temporalmente.");
                                            personajeActualDemo.setFuerza(personajeActualDemo.getFuerza() + objetoUsado.tipo().getEfecto());
                                            objetoUsado.marcarComoUsado();
                                        } else if (objetoUsado.tipo() == TiposDeObjetos.ARMADURA) {
                                            System.out.println("Usaste una armadura. Defensa aumentada temporalmente.");
                                            personajeActualDemo.setDefensa(personajeActualDemo.getDefensa() + objetoUsado.tipo().getEfecto());
                                            objetoUsado.marcarComoUsado();
                                        } else if (objetoUsado.tipo() == TiposDeObjetos.POCION) {
                                            System.out.println("Usaste una pocion. Salud restaurada.");
                                            personajeActualDemo.setSalud(personajeActualDemo.getSalud() + objetoUsado.tipo().getEfecto());
                                            objetoUsado.marcarComoUsado();
                                        } else {
                                            System.out.println("Objeto no valido.");
                                        }
                                    } else {
                                        System.out.println("Objeto no encontrado en el inventario.");
                                    }
                                }
                            } else {
                                throw new OpcionInvalidaException("Indice invalido.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada no valida. Ingrese un numero.");
                        } catch (OpcionInvalidaException e) {
                            System.out.println("ERROR: " + e.getMessage());
                        }
                        break;
                    case "4":
                        boolean tiendaAbierta = true;
                        while (tiendaAbierta) {
                            tienda.mostrarCatalogo();
                            System.out.println("Ingrese el indice del objeto que desea comprar o ingrese 'salir' para salir de la tienda: ");
                            String opcionTienda = sc.nextLine();

                            if (opcionTienda.equals("salir")) {
                                tiendaAbierta = false;
                                System.out.println("Saliendo de la tienda...");
                            } else {
                                try {
                                    int indice = Integer.parseInt(opcionTienda);
                                    Objeto objeto = tienda.ObjetoPorIndice(indice);

                                    if (objeto != null) {
                                        tienda.comprarObjetos(personajeActualDemo, objeto);
                                    } else {
                                        throw new OpcionInvalidaException("Indice no valido.");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Entrada no valida. Ingrese un numero.");
                                } catch (OpcionInvalidaException e) {
                                    System.out.println("ERROR: " + e.getMessage());
                                }
                            }
                        }
                        break;
                    case "5":
                        System.out.println("Saliendo de la demo...");
                        return;
                    default:
                        System.out.println("Opcion no valida.");
                        break;
                }
            }
        }
    }

    private static void mostrarSubmenuExploracion() {
        mostrarCarga();
        System.out.println("╔═══════════════════════════╗");
        System.out.println("║        EXPLORANDO         ║");
        System.out.println("╠═══════════════════════════╣");
        System.out.println("║  1. Explorar Mazmorra     ║");
        System.out.println("║  2. Mostrar inventario    ║");
        System.out.println("║  3. Usar objeto           ║");
        System.out.println("║  4. Ver tienda            ║");
        System.out.println("║  5. Salir                 ║");
        System.out.println("╚═══════════════════════════╝");
        mostrarSeparador();
    }

    private static void mostrarSeparador() {
        System.out.println("☆".repeat(40));
    }

    private static void mostrarCarga() {
        System.out.print("Cargando");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.print(".");
        }
        System.out.println();
    }

    private static void mostrarCargaDos() {
        System.out.print("Cargando");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.print(".");
        }
        System.out.println();
    }
}

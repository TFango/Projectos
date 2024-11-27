package Trabajo.Usuarios;

import Trabajo.Enemigos.Combate;
import Trabajo.Enemigos.Habitacion;
import Trabajo.Enemigos.Mazmorra;
import Trabajo.Inventario.*;
import Trabajo.Personajes.Personaje;
import Trabajo.excepciones.OpcionInvalidaException;
import Trabajo.excepciones.PersonajeException;

import java.util.List;
import java.util.Scanner;

import static Trabajo.Personajes.ManejoPersonajes.crearPersonaje;
import static Trabajo.Personajes.ManejoPersonajes.cargarPersonaje;

public class JuegoCompleto {
    private Scanner sc;

    public JuegoCompleto(Scanner sc) {
        this.sc = sc;
    }

    Personaje personajeActual = null;

    public void iniciarJuegoCompleto() {
        boolean continuar = true;

        while (continuar) {
            mostrarMenuGestionRPG();
            System.out.println("Seleccione una opcion: ");
            int opcionRPG = Integer.parseInt(sc.nextLine());

            switch (opcionRPG) {
                case 1:
                    try {
                        personajeActual = crearPersonaje(sc);
                    } catch (PersonajeException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        personajeActual = cargarPersonaje(sc);
                    } catch (PersonajeException e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;
                case 3:
                    explorarMazmorras();
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }

    private void mostrarMenuGestionRPG() {
        mostrarCargaDos();
        System.out.println("╔═════════════════════════════════════╗");
        System.out.println("║       Sistema de Gestión de RPG     ║");
        System.out.println("╠═════════════════════════════════════╣");
        System.out.println("║  1. 🌟 Crear nuevo personaje        ║");
        System.out.println("║  2. ⚔️ Cargar personaje existente   ║");
        System.out.println("║  3. 🌌 Explorar mazmorras           ║");
        System.out.println("║  4. 🚪 Salir                        ║");
        System.out.println("╚═════════════════════════════════════╝");
        mostrarSeparador();
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



    public void explorarMazmorras() {
        if (personajeActual != null) {
            boolean explorando = true;
            Tienda tienda = new Tienda(personajeActual);

            while (explorando) {
                mostrarSubmenuExploracion();
                System.out.println("Sellecione una opcion: ");
                String opcionExploracion = sc.nextLine();

                switch (opcionExploracion) {
                    case "1":
                        Mazmorra mazmorra = new Mazmorra(1);
                        boolean mazmorraCompletada = false;

                        while (!mazmorraCompletada) {
                            List<Habitacion> habitaciones = mazmorra.getHabitacionesDelPisoActual();
                            for (Habitacion habitacion : habitaciones) {
                                if (habitacion.tieneEnemigo()) {

                                    Combate combate = new Combate(personajeActual, habitacion.getEnemigo(), tienda);
                                    combate.iniciar();

                                    if (!personajeActual.estaVivo()) {
                                        System.out.println("Has sido derrotado. Fin de la exploracion.");
                                        explorando = false;
                                        break;
                                    }
                                }
                            }

                            if (mazmorra.avanzarPiso()) {
                                System.out.println("Haz avanzado al siguiente piso.");
                            } else {
                                mazmorraCompletada = true;
                                break;
                            }
                        }
                        if (mazmorraCompletada && personajeActual.estaVivo()) {
                            System.out.println("━━━━━━━━━━━━━━━━━━━━━");
                            System.out.println("🏆 **Haz completado la mazmorra. ¡Felicidades!**");
                        }
                        explorando = false;
                        break;

                    case "2":
                        personajeActual.getInventario().mostrarInventario();
                        break;
                    case "3":
                        System.out.println("Ingrese el indice del objeto a usar: ");
                        try {
                            int index = Integer.parseInt(sc.nextLine());
                            if (index >= 0 && index < personajeActual.getInventario().getObjetos().size()) {
                                Objeto objeoUsado = personajeActual.getInventario().getObjetos().get(index);

                                if (objeoUsado != null) {
                                    if (objeoUsado.tipo() == TiposDeObjetos.ARMA) {
                                        System.out.println("Usaste un arma. Fuerza aumentanda temporalmente.");
                                        personajeActual.setFuerza(personajeActual.getFuerza() + objeoUsado.tipo().getEfecto());
                                        objeoUsado.marcarComoUsado();
                                    } else if (objeoUsado.tipo() == TiposDeObjetos.ARMADURA) {
                                        System.out.println("Usaste una armadura. Defensa aumentada temporalmente.");
                                        personajeActual.setDefensa(personajeActual.getDefensa() + objeoUsado.tipo().getEfecto());
                                        objeoUsado.marcarComoUsado();
                                    } else if (objeoUsado.tipo() == TiposDeObjetos.POCION) {
                                        System.out.println("Usaste una pocion. Salud restaurada.");
                                        personajeActual.setSalud(personajeActual.getSalud() + objeoUsado.tipo().getEfecto());
                                        objeoUsado.marcarComoUsado();
                                    } else {
                                        System.out.println("Objeto no valido.");
                                    }
                                } else {
                                    System.out.println("Objeto no encontrado en el inventario.");
                                }
                            } else {
                                throw new OpcionInvalidaException("Indice invalido.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada no valida. Ingrese un numero.");
                        } catch (OpcionInvalidaException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case "4":
                        boolean tiendaAbierta = true;
                        while (tiendaAbierta) {
                            tienda.mostrarCatalogo();
                            System.out.println("Ingrese el indice del objeto a comprar o ingrese 'salir' para salir de la tienda.");
                            String opcionTienda = sc.nextLine();

                            if (opcionTienda.equalsIgnoreCase("salir")) {
                                tiendaAbierta = false;
                                System.out.println("Saliendo de la tienda...");
                            } else {
                                try {
                                    int indice = Integer.parseInt(opcionTienda);
                                    Objeto objeto = tienda.ObjetoPorIndice(indice);

                                    if (objeto != null) {
                                        tienda.comprarObjetos(personajeActual, objeto);
                                    } else {
                                        throw new OpcionInvalidaException("Indice no valido.");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Entrada no valida. Ingrese un numero.");
                                } catch (OpcionInvalidaException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        }
                        break;
                    case "5":
                        System.out.println("╔══════════════════════════════════════════╗");
                        System.out.println(ROJO + "║ Saliendo del juego. ¡Hasta luego!        ║" + RESET);
                        System.out.println("╚══════════════════════════════════════════╝");
                        explorando = false;
                        break;
                    default:
                        System.out.println("╔══════════════════════════════════════════╗");
                        System.out.println("║ Opción no válida. Intente de nuevo.      ║");
                        System.out.println("╚══════════════════════════════════════════╝");
                        break;
                }
                if (explorando) {
                    System.out.println("\nPresiona ENTER para continuar...");
                    sc.nextLine();
                }
            }
        }
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




    String RESET = "\u001B[0m";
    final String VERDE = "\u001B[32m";
    final String ROJO = "\u001B[31m";
    final String AMARILLO = "\u001B[33m";
    final String AZUL = "\u001B[34m";


}

package org.example;

import Trabajo.Personajes.Arquero;
import Trabajo.Personajes.Guerrero;
import Trabajo.Personajes.Mago;
import Trabajo.Personajes.Personaje;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            System.out.println("Seleccione una opcion: ");
            String opcion = sc.nextLine();
            Personaje personajeActual = null;

            switch (opcion) {
                case "1":
                    personajeActual = crearPersonaje(sc);
                    break;
                case "2":
                    personajeActual = cargarPersonaje(sc);
                    break;
                case "3":
                    borrarPersonaje(sc);
                    break;
                case "4":
                    System.out.println("Saliendo del juego. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
            if (continuar) {
                System.out.println("\nPresiona ENTER para continuar...");
                sc.nextLine();
            }
        }
        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("=====================================");
        System.out.println("       Sistema de Gestión de RPG      ");
        System.out.println("=====================================");
        System.out.println("1. Crear nuevo personaje");
        System.out.println("2. Cargar personaje existente");
        System.out.println("3. Borrar personaje");
        System.out.println("4. Explorar mazmorra");
        System.out.println("5. Salir");
        System.out.println("=====================================");
    }

    private static Personaje crearPersonaje(Scanner sc) {
        System.out.println("Ingrese el nombre del personaje: ");
        String nombre = sc.nextLine();

        //Seleccion de clase
        System.out.println("Seleccione la clase del personaje: ");
        System.out.println("1. Guerrero.");
        System.out.println("2. Mago.");
        System.out.println("3. Arquero.");
        String claseSeleccionada = sc.nextLine();

        Personaje nuevoPersonaje;
        switch (claseSeleccionada) {
            case "1":
                nuevoPersonaje = new Guerrero(nombre);
                break;
            case "2":
                nuevoPersonaje = new Mago(nombre);
                break;
            case "3":
                nuevoPersonaje = new Arquero(nombre);
                break;
            default:
                System.out.println("Opcion no valida, creando guerrero por defecto.");
                nuevoPersonaje = new Guerrero(nombre);
                break;
        }
        System.out.println("Ingresa el nombre del archivo a guardar.");
        String nombreArchivo = sc.nextLine();

        guardarPersonaje(nuevoPersonaje, nombreArchivo);
        System.out.println("Personaje creado y guardado correctamente.");

        return nuevoPersonaje;
    }

    private static void guardarPersonaje(Personaje nuevoPersonaje, String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(nuevoPersonaje);
        } catch (IOException e) {
            System.out.println("Error al guardar el personaje: " + e.getMessage());
        }
    }

    private static Personaje cargarPersonaje(Scanner scanner) {
        System.out.println("Ingresa el nombre del archivo del personaje a cargar: ");
        String nombreArchivo = scanner.nextLine();

        Personaje personajeCargado = cargarPersonajeDesdeArchivo(nombreArchivo);
        if (personajeCargado != null) {
            System.out.println("Personaje cargado con exito: " + personajeCargado);
        } else {
            System.out.println("Personaje no encontrado");
        }

        return personajeCargado;
    }

    private static Personaje cargarPersonajeDesdeArchivo(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return (Personaje) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el personaje: " + e.getMessage());
            return null;
        }
    }

    private static void borrarPersonaje(Scanner scanner) {
        System.out.println("Ingresa el nombre del archivo del personaje a borrar: ");
        String archivo = scanner.nextLine();

        File file = new File(archivo);
        if (file.delete()) {
            System.out.println("Personaje borrado con exito.");
        } else {
            System.out.println("No se pudo borrar el personaje. Asegurate de que el archivo exista");
        }
    }
}
package org.example;

import Trabajo.Personajes.Arquero;
import Trabajo.Personajes.Guerrero;
import Trabajo.Personajes.Mago;
import Trabajo.Personajes.Personaje;
import org.json.JSONObject;

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

    /**
     * Metodo encargado de guardar el personaje creado en una archivo JSON
     */
    public static void guardarPersonaje(Personaje personaje, String nombreArchivo) {
        JSONObject json = new JSONObject(); //Creacion de un objeto JSON, en este se almacenaran los datos del personaje

        //Se agregan los atributos que tienen en comun todos los personajes.
        json.put("nombre", personaje.getNombre());
        json.put("nivel", personaje.getNivel());
        json.put("salud", personaje.getSalud());
        json.put("fuerza", personaje.getFuerza());
        json.put("defensa", personaje.getDefensa());
        json.put("velocidad", personaje.getVelocidad());
        json.put("tipo", personaje.getClass().getSimpleName()); //Se añade el tipo de personaje ya sea (Guerrero, Arquero, Mago)

        /**
         * Se almacenan los atributos en especifico, se verifica el tipo de personaje pora guardar sus atributos
         * Se usa un instanceof para determinar el tipo de personaje
         */
        if (personaje instanceof Guerrero) {
            Guerrero guerrero = (Guerrero) personaje;
            json.put("resistencia", guerrero.getResistencia());
            json.put("fuerzaExtra", guerrero.getFuerzaExtra());
        } else if (personaje instanceof Mago) {
            Mago mago = (Mago) personaje;
            json.put("poderMagico", mago.getPoderMagico());
            json.put("manaExtra", mago.getManaExtra());
        } else if (personaje instanceof Arquero) {
            Arquero arquero = (Arquero) personaje;
            json.put("precision", arquero.getPrecision());
            json.put("agilidad", arquero.getAgilidad());
        }

        /**
         * Se guardar el JSONObject en un archivo
         */

        try (FileWriter file = new FileWriter(nombreArchivo)) {
            file.write(json.toString()); //Usado para escribir el objeto JSON en un archivo especifico
            System.out.println("Personaje guardado en: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("ERROR al guardar el personaje" + e.getMessage());
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

    /**
     *Se encarga de leer el archivo JSON y crear un objeto Personaje a partir de los datos almacenados
     */
    public static Personaje cargarPersonajeDesdeArchivo(String nombreArchivo) {
        String contenido;

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            contenido = reader.readLine();
        } catch (IOException e) {
            System.out.println("ERROR al cargar el personaje: " + e.getMessage());
            return null;
        }
        //"BufferedReader" se utiliza para leer el contenido del archivo, en este caso se lee una linea que tendra el contenido del JSON completo.

        JSONObject json = new JSONObject(contenido); //Creacion del JSONObject
        /**
         * A partir de los datos, se determina que tipo de personaje se esta creando y se inicializan sus atributos
         */
        String nombre = json.getString("nombre");//Se extrae el nombre
        String tipo = json.getString("tipo");//Se extrae el tipo

        Personaje personaje = null;
        switch (tipo) {
            case "Guerrero":
                personaje = new Guerrero(nombre);
                ((Guerrero) personaje).setResistencia(json.getInt("resistencia")); // Asegúrate de tener un método para esto
                ((Guerrero) personaje).setFuerzaExtra(json.getInt("fuerzaExtra")); // Asegúrate de tener un método para esto
                break;
            case "Mago":
                personaje = new Mago(nombre);
                ((Mago) personaje).setPoderMagico(json.getInt("poderMagico"));
                ((Mago) personaje).setManaExtra(json.getInt("manaExtra"));
                break;
            case "Arquero":
                personaje = new Arquero(nombre);
                // Establecer atributos adicionales
                ((Arquero) personaje).setPrecision(json.getInt("presicion"));
                ((Arquero) personaje).setAgilidad(json.getInt("agilidad"));
                break;
            default:
                System.err.println("Tipo de personaje desconocido: " + tipo);
                break;
        }
        if(personaje != null){
            personaje.setNivel(json.getInt("nivel"));
            personaje.setSalud(json.getInt("salud"));
            personaje.setFuerza(json.getInt("fuerza"));
            personaje.setDefensa(json.getInt("defensa"));
            personaje.setVelocidad(json.getInt("velocidad"));
        }
        return personaje;


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
package Trabajo.Personajes;

import Trabajo.Inventario.*;
import Trabajo.Inventario.Armadura;
import Trabajo.Inventario.Armas.Arco;
import Trabajo.Inventario.Armas.Espada;
import Trabajo.Inventario.Armas.Varita;
import Trabajo.excepciones.OpcionInvalidaException;
import Trabajo.excepciones.PersonajeException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManejoPersonajes {

    public static Personaje crearPersonaje(Scanner sc) throws PersonajeException {
        mostrarSeparador();
        mostrarCarga();
        System.out.println("╔═════════════════════════════════════╗");
        System.out.println("║       Crear Nuevo Personaje         ║");
        System.out.println("╚═════════════════════════════════════╝");

        System.out.print("Ingrese el nombre del personaje: ");
        String nombre = sc.nextLine();

        if (nombre.isEmpty()) {
            throw new PersonajeException("El nombre del personaje no puede estar vacio.");
        }
        // Selección de clase
        Personaje nuevoPersonaje = null;
        while ((nuevoPersonaje == null)) {
            try {
                System.out.println("\nSeleccione la clase del personaje: ");
                System.out.println("1. Guerrero");
                System.out.println("2. Mago");
                System.out.println("3. Arquero");
                System.out.print("Opción: ");
                String claseSeleccionada = sc.nextLine();

                switch (claseSeleccionada) {
                    case "1":
                        nuevoPersonaje = new Guerrero(nombre);
                        nuevoPersonaje.getInventario().agregarObjetos(new Espada());
                        break;
                    case "2":
                        nuevoPersonaje = new Mago(nombre);
                        nuevoPersonaje.getInventario().agregarObjetos(new Varita());
                        break;
                    case "3":
                        nuevoPersonaje = new Arquero(nombre);
                        nuevoPersonaje.getInventario().agregarObjetos(new Arco());
                        break;
                    default:
                        throw new OpcionInvalidaException("╔═════════════════════════════════════╗" + "\n" + "║           Opción no válida.         ║" + "\n" + "╚═════════════════════════════════════╝");
                }
            } catch (OpcionInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }


        nuevoPersonaje.getInventario().agregarObjetos(new Armadura());
        nuevoPersonaje.getInventario().agregarObjetos(new Pocion());

        System.out.print("\nIngresa el nombre del archivo para guardar: ");
        String nombreArchivo = sc.nextLine() + ".json";

        boolean personajeGuardadoConExito = guardarPersonaje(nuevoPersonaje, nombreArchivo);
        if (personajeGuardadoConExito) {
            System.out.println("╔═════════════════════════════════════╗");
            System.out.println("║     Personaje creado y guardado     ║");
            System.out.println("╚═════════════════════════════════════╝");
        }

        return nuevoPersonaje;
    }


    /**
     * Metodo encargado de guardar el personaje creado en una archivo JSON
     */

    public static boolean guardarPersonaje(Personaje personaje, String nombreArchivo) {
        JSONObject json = new JSONObject(); //Creacion de un objeto JSON, en este se almacenaran los datos del personaje

        if (personaje instanceof Guerrero) {
            Guerrero guerrero = (Guerrero) personaje;
            if (!personaje.getInventario().contieneTipo(TiposDeObjetos.ESPADA)) {
                personaje.getInventario().agregarObjetos(new Espada());
            }
        } else if (personaje instanceof Mago) {
            Mago mago = (Mago) personaje;
            if (!personaje.getInventario().contieneTipo(TiposDeObjetos.VARITA)) {
                personaje.getInventario().agregarObjetos(new Varita());
            }
        } else if (personaje instanceof Arquero) {
            Arquero arquero = (Arquero) personaje;
            if (!personaje.getInventario().contieneTipo(TiposDeObjetos.ARCO)) {
                personaje.getInventario().agregarObjetos(new Arco());
            }
        }

        //Se agregan los atributos que tienen en comun todos los personajes.
        json.put("nombre", personaje.getNombre());
        json.put("nivel", personaje.getNivel());
        json.put("salud", personaje.getSalud());
        json.put("fuerza", personaje.getFuerza());
        json.put("defensa", personaje.getDefensa());
        json.put("velocidad", personaje.getVelocidad());
        json.put("tipo", personaje.getClass().getSimpleName()); //Se añade el tipo de personaje ya sea (Guerrero, Arquero, Mago)

        List<JSONObject> objetosJSON = new ArrayList<>();
        for (Objeto objeto : personaje.getInventario().getObjetos()) {
            JSONObject objetoJSON = new JSONObject();
            objetoJSON.put("nombre", objeto.nombre());
            objetoJSON.put("peso", objeto.peso());
            objetoJSON.put("esTemporal", objeto.esTemporal());
            objetoJSON.put("tipo", objeto.tipo().name());
            objetosJSON.add(objetoJSON);
        }
        json.put("inventario", objetosJSON);

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
            return true;
        } catch (IOException e) {
            System.out.println("ERROR al guardar el personaje" + e.getMessage());
            return false;
        }
    }

    public static Personaje cargarPersonaje(Scanner scanner) throws PersonajeException {
        mostrarSeparador();
        mostrarCarga();
        System.out.println("Ingresa el nombre del archivo del personaje a cargar: ");
        String nombreArchivo = scanner.nextLine();

        if (nombreArchivo.isEmpty()) {
            throw new PersonajeException("El nombre del archivo no puede estar vacio");
        }

        Personaje personajeCargado = cargarPersonajeDesdeArchivo(nombreArchivo);
        if (personajeCargado != null) {
            System.out.println("Personaje cargado con exito: " + personajeCargado);
        } else {
            System.out.println("Personaje no encontrado");
        }
        mostrarSeparador();

        return personajeCargado;
    }

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
                ((Guerrero) personaje).setResistencia(json.getInt("resistencia"));
                ((Guerrero) personaje).setFuerzaExtra(json.getInt("fuerzaExtra"));
                break;
            case "Mago":
                personaje = new Mago(nombre);
                ((Mago) personaje).setPoderMagico(json.getInt("poderMagico"));
                ((Mago) personaje).setManaExtra(json.getInt("manaExtra"));
                break;
            case "Arquero":
                personaje = new Arquero(nombre);
                ((Arquero) personaje).setPrecision(json.getInt("precision"));
                ((Arquero) personaje).setAgilidad(json.getInt("agilidad"));
                break;
            default:
                System.err.println("Tipo de personaje desconocido: " + tipo);
                break;
        }
        if (personaje != null) {
            personaje.setNivel(json.getInt("nivel"));
            personaje.setSalud(json.getInt("salud"));
            personaje.setFuerza(json.getInt("fuerza"));
            personaje.setDefensa(json.getInt("defensa"));
            personaje.setVelocidad(json.getInt("velocidad"));
        }

        Inventario inventario = personaje.getInventario();
        inventario.getObjetos().clear();

        for (Object obj : json.getJSONArray("inventario")) {
            JSONObject objetoJSON = (JSONObject) obj;
            String tipoObjeto = objetoJSON.getString("tipo");
            Objeto objeto = null;

            switch (tipoObjeto) {
                case "ESPADA":
                    objeto = new Espada();
                    break;
                case "VARITA":
                    objeto = new Varita();
                    break;
                case "ARCO":
                    objeto = new Arco();
                    break;
                case "ARMADURA":
                    objeto = new Armadura();
                    break;
                case "POCION":
                    objeto = new Pocion();
                    break;
            }
            if (objeto != null) {
                inventario.agregarObjetos(objeto);
            }
        }

        return personaje;
    }

    public static Personaje crearPersonajeDemo(Scanner sc) throws PersonajeException {
        mostrarSeparador();
        mostrarCarga();
        System.out.println("╔═════════════════════════════════════╗");
        System.out.println("║       Creando Personaje Demo        ║");
        System.out.println("╚═════════════════════════════════════╝");

        System.out.print("Ingrese el nombre del personaje: ");
        String nombre = sc.nextLine();

        if (nombre.isEmpty()) {
            throw new PersonajeException("El nombre del personaje no puede estar vacio.");
        }
        // Selección de clase
        Personaje nuevoPersonajeDemo = null;
        nuevoPersonajeDemo = new Mago(nombre);

        nuevoPersonajeDemo.getInventario().agregarObjetos(new Varita());
        nuevoPersonajeDemo.getInventario().agregarObjetos(new Armadura());
        nuevoPersonajeDemo.getInventario().agregarObjetos(new Pocion());

        nuevoPersonajeDemo.getInventario().mostrarInventario();


        System.out.println("╔═════════════════════════════════════╗");
        System.out.println("║          Personaje creado           ║");
        System.out.println("╚═════════════════════════════════════╝");

        return nuevoPersonajeDemo;
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

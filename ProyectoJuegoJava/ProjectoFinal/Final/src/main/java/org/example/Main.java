package org.example;

import Trabajo.Enemigos.Combate;
import Trabajo.Enemigos.Habitacion;
import Trabajo.Enemigos.Mazmorra;
import Trabajo.Inventario.*;
import Trabajo.Personajes.*;
import Trabajo.excepciones.OpcionInvalidaException;
import Trabajo.excepciones.PersonajeException;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws PersonajeException {

        Administrador admin = new Administrador("admin","123");
        Personaje personajeActual = null;
        Personaje personajeActualDemo = null;
        String usuario = "Admin";
        String contraseña = "123";

        String RESET = "\u001B[0m";
        final String VERDE = "\u001B[32m";
        final String ROJO = "\u001B[31m";
        final String AMARILLO = "\u001B[33m";
        final String AZUL = "\u001B[34m";

        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            menuInicial();
            System.out.println("Seleccione una opcion: ");
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    try {
                        personajeActualDemo = crearPersonajeDemo(sc);
                    } catch (PersonajeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    if (personajeActualDemo != null) {
                        boolean explorando = true;
                        while (explorando) {
                            mostrarSubmenuExploracion();
                            System.out.println("Seleccione una opcion: ");
                            String opcionExploracion = sc.nextLine();


                            switch (opcionExploracion) {
                                case "1":
                                    Mazmorra mazmorra = new Mazmorra(1);
                                    boolean mazmorraCompletada = false;

                                    while (mazmorra.getPisoActual() < mazmorra.getCantidadPisos()) {
                                        List<Habitacion> habitaciones = mazmorra.getHabitacionesDelPisoActual();
                                        for (Habitacion habitacion : habitaciones) {
                                            if (habitacion.tieneEnemigo()) {

                                                Combate combate = new Combate(personajeActualDemo, habitacion.getEnemigo());
                                                combate.iniciar();

                                                if (!personajeActualDemo.estaVivo()) {
                                                    System.out.println("Has sido derrotado. Fin de la exploración.");
                                                    explorando = false;
                                                    personajeActualDemo = null;
                                                    System.out.println("Personaje eliminado");
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
                                        mostrarSubmenuExploracion();
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
                                                if (objetoUsado.tipo() == TiposDeObjetos.ARMA) {
                                                    System.out.println("Usaste un arma. Fuerza aumentada temporalmente.");
                                                    personajeActualDemo.setFuerza(personajeActualDemo.getFuerza() + objetoUsado.tipo().getEfecto());
                                                    personajeActualDemo.getInventario().removerObjeto(index);
                                                } else if (objetoUsado.tipo() == TiposDeObjetos.ARMADURA) {
                                                    System.out.println("Usaste una armadura. Defensa aumentada temporalmente.");
                                                    personajeActualDemo.setDefensa(personajeActualDemo.getDefensa() + objetoUsado.tipo().getEfecto());
                                                    personajeActualDemo.getInventario().removerObjeto(index);
                                                } else if (objetoUsado.tipo() == TiposDeObjetos.POCION) {
                                                    System.out.println("Usaste una pocion. Salud restaurada.");
                                                    personajeActualDemo.setSalud(personajeActualDemo.getSalud() + objetoUsado.tipo().getEfecto());
                                                } else {
                                                    System.out.println("Objeto no valido.");
                                                }
                                            } else {
                                                System.out.println("objeto no encontrado en el inventario.");
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
                            }
                        }
                    }
                    break;
                case "2":
                    mostrarMenu();
                    System.out.println("Seleccione una opcion: ");
                    String opcion2 = sc.nextLine();

                    switch (opcion2) {
                        case "1":
                            try {
                                personajeActual = crearPersonaje(sc);
                            } catch (PersonajeException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                            break;
                        case "2":
                            try {
                                personajeActual = cargarPersonaje(sc);
                            } catch (PersonajeException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                            break;
                        case "3":
                            if (personajeActual != null) {
                                boolean explorando = true;
                                while (explorando) {
                                    mostrarSubmenuExploracion();
                                    System.out.printf("Seleccione una opcion: ");
                                    String opcionExploracion = sc.nextLine();


                                    switch (opcionExploracion) {
                                        case "1":
                                            Mazmorra mazmorra = new Mazmorra(3);
                                            boolean mazmorraCompletada = false;

                                            while (mazmorra.getPisoActual() < mazmorra.getCantidadPisos()) {
                                                List<Habitacion> habitaciones = mazmorra.getHabitacionesDelPisoActual();
                                                for (Habitacion habitacion : habitaciones) {
                                                    if (habitacion.tieneEnemigo()) {

                                                        Combate combate = new Combate(personajeActual, habitacion.getEnemigo());
                                                        combate.iniciar();

                                                        if (!personajeActual.estaVivo()) {
                                                            System.out.println("Has sido derrotado. Fin de la exploración.");
                                                            explorando = false;
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
                                                    Objeto objetoUsado = personajeActual.getInventario().getObjetos().get(index);

                                                    if (objetoUsado != null) {
                                                        if (objetoUsado.tipo() == TiposDeObjetos.ARMA) {
                                                            System.out.println("Usaste un arma. Fuerza aumentada temporalmente.");
                                                            personajeActual.setFuerza(personajeActual.getFuerza() + objetoUsado.tipo().getEfecto());
                                                            personajeActual.getInventario().removerObjeto(index);
                                                        } else if (objetoUsado.tipo() == TiposDeObjetos.ARMADURA) {
                                                            System.out.println("Usaste una armadura. Defensa aumentada temporalmente.");
                                                            personajeActual.setDefensa(personajeActual.getDefensa() + objetoUsado.tipo().getEfecto());
                                                            personajeActual.getInventario().removerObjeto(index);
                                                        } else if (objetoUsado.tipo() == TiposDeObjetos.POCION) {
                                                            System.out.println("Usaste una pocion. Salud restaurada.");
                                                            personajeActual.setSalud(personajeActual.getSalud() + objetoUsado.tipo().getEfecto());
                                                        } else {
                                                            System.out.println("Objeto no valido.");
                                                        }
                                                    } else {
                                                        System.out.println("objeto no encontrado en el inventario.");
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
                                        case "5":
                                            System.out.println("╔══════════════════════════════════════════╗");
                                            System.out.println(ROJO + "║ Saliendo del juego. ¡Hasta luego!        ║" + RESET);
                                            System.out.println("╚══════════════════════════════════════════╝");
                                            continuar = false;
                                            break;
                                        default:
                                            System.out.println("╔══════════════════════════════════════════╗");
                                            System.out.println("║ Opción no válida. Intente de nuevo.      ║");
                                            System.out.println("╚══════════════════════════════════════════╝");
                                            break;
                                    }
                                    if (continuar) {
                                        System.out.println("\nPresiona ENTER para continuar...");
                                        sc.nextLine();
                                    }

                                }
                                sc.close();
                            }

                    }
                case "3":
                    int intentos = 0;
                    while(intentos < 3){
                        System.out.println("Porfavor ingrese el usuario...");
                        String usuarioIngresado = sc.nextLine();
                        System.out.println("Porfavor ingrese la contraseña...");
                        String contraseñaIngresada = sc.nextLine();

                        if(admin.autenticar(usuarioIngresado,contraseñaIngresada)){
                            System.out.println("Autenticacion exisitosa. Bienvenido, administrador.");
                            mostrarMenuAdministrador(admin,sc);
                            break;
                        }else {
                            intentos++;
                            System.out.println("Usuario o contraseña incorrectos. Intetos restantes: " + (3-intentos));
                        }
                    }

                    if(intentos >= 3){
                        System.out.println("Demasiados intentos fallidos. Saliendo del modo Administrador.");
                        break;
                    }


                    break;
            }
        }
    }


    private static void menuInicial() {
        System.out.println("Bienvenido al juego");
        System.out.println("Como desea entrar?");
        System.out.println("1. Jugar Demo");
        System.out.println("2. Jugar juego completo");
        System.out.println("3. Administrador.");
    }

    public static void mostrarMenuAdministrador(Administrador admin, Scanner sc) throws PersonajeException {
        while (true) {
            System.out.println("Opciones del administrador: ");
            System.out.println("1. Ver todos los personajes." );
            System.out.println("2. Borrar un personaje.");
            System.out.println("3. Modificar nombre de un personaje.");
            System.out.println("4. Salir.");

            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    admin.mostrarArchivosPersonajes();
                    break;
                case "2":
                    borrarPersonaje(sc);
                    break;
                case "3":
                    System.out.println("Ingrese el nombre del personaje a renombrar: ");
                    String nombre = sc.nextLine();
                    System.out.println("Ingrese el nuevo nombre del personaje: ");
                    String nuevoNombre = sc.nextLine();
                    admin.renombrarPersonaje(nombre,nuevoNombre);
                    break;
            }
        }
    }

    private static void mostrarMenu() {
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
        System.out.println("║  4. Salir                 ║");
        System.out.println("╚═══════════════════════════╝");
        mostrarSeparador();
    }

    private static Personaje crearPersonajeDemo(Scanner sc) throws PersonajeException {
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

        nuevoPersonajeDemo.getInventario().agregarObjetos(new Arma());
        nuevoPersonajeDemo.getInventario().agregarObjetos(new Armadura());
        nuevoPersonajeDemo.getInventario().agregarObjetos(new Pocion());

        nuevoPersonajeDemo.getInventario().mostrarInventario();


        System.out.println("╔═════════════════════════════════════╗");
        System.out.println("║          Personaje creado           ║");
        System.out.println("╚═════════════════════════════════════╝");

        return nuevoPersonajeDemo;
    }


    private static Personaje crearPersonaje(Scanner sc) throws PersonajeException {
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
                        break;
                    case "2":
                        nuevoPersonaje = new Mago(nombre);
                        break;
                    case "3":
                        nuevoPersonaje = new Arquero(nombre);
                        break;
                    default:
                        throw new OpcionInvalidaException("╔═════════════════════════════════════╗" + "\n" + "║           Opción no válida.         ║" + "\n" + "╚═════════════════════════════════════╝");
                }
            } catch (OpcionInvalidaException e) {
                System.out.println(e.getMessage());
            }
        }

        nuevoPersonaje.getInventario().agregarObjetos(new Arma());
        nuevoPersonaje.getInventario().agregarObjetos(new Armadura());
        nuevoPersonaje.getInventario().agregarObjetos(new Pocion());

        nuevoPersonaje.getInventario().mostrarInventario();

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

    private static Personaje cargarPersonaje(Scanner scanner) throws PersonajeException {
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

    /**
     * Se encarga de leer el archivo JSON y crear un objeto Personaje a partir de los datos almacenados
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
                // Establecer atributos adicionales
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
                case "ARMA":
                    objeto = new Arma();
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

    private static void borrarPersonaje(Scanner scanner) throws PersonajeException {
        mostrarSeparador();
        mostrarCarga();
        System.out.println("Ingresa el nombre del archivo del personaje a borrar: ");
        String archivo = scanner.nextLine();

        if (archivo.isEmpty()) {
            throw new PersonajeException("El nombre del archivo no puede estar vacio.");
        }

        File file = new File(archivo);
        if (file.delete()) {
            System.out.println("Personaje borrado con exito.");
        } else {
            System.out.println("No se pudo borrar el personaje. Asegurate de que el archivo exista");
        }
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

package Trabajo.Usuarios;

import Trabajo.excepciones.PersonajeException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Administrador {
    private String usuario;
    private String contraseña;
    private Scanner sc;

    public Administrador(Scanner sc) {
        this.usuario = "admin";
        this.contraseña = "contraseña";
        this.sc = sc;
    }



    public void iniciarAdministrador() throws PersonajeException {
        int intentos = 0;
        while (intentos < 3) {
            System.out.println("Porfavor ingrese el usuario...");
            String usuarioIngresada = sc.nextLine();
            System.out.println("Porfavor ingrese la contraseña...");
            String contraseñaIngresada = sc.nextLine();

            if (autenticar(usuarioIngresada, contraseñaIngresada)) {
                System.out.println("Autenticacion exitosa. Bienvenido, administrador.");
                mostrarMenuAdministrador(sc);
                break;
            } else {
                intentos++;
                System.out.println("Usuario o contraseñas incorrectos. Intentos restantes: " + (3 - intentos));
            }
        }

        if (intentos >= 3) {
            System.out.println("Demasiados intentos fallidos. Saliendo del modo Administrador.");
            return;
        }
    }

    private static void mostrarMenuAdministrador(Scanner sc) throws PersonajeException {
        while (true) {
            System.out.println("Opciones del administrador: ");
            System.out.println("1. Ver todos los personajes.");
            System.out.println("2. Borrar un personaje.");
            System.out.println("3. Modificar nombre de un personaje.");
            System.out.println("4. Salir.");

            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    mostrarArchivosPersonajes();
                    break;
                case "2":
                    borrarPersonaje(sc);
                    break;
                case "3":
                    System.out.println("Ingrese el nombre del personaje a renombrar: ");
                    String nombre = sc.nextLine();
                    System.out.println("Ingrese el nuevo nombre del personaje: ");
                    String nuevoNombre = sc.nextLine();
                    renombrarPersonaje(nombre, nuevoNombre);
                    break;
                case "4":
                    System.out.println("Saliendo del modo Administrador.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        }
    }


    public boolean autenticar(String usuario, String contra) {
        return this.usuario.equals(usuario) && this.contraseña.equals(contra);
    }

    public static void mostrarArchivosPersonajes() {
        String rutaDirectorio = System.getProperty("user.dir");
        //Obtiene la ruta del directorio actual del proyecto
        File directorio = new File(rutaDirectorio);
        //Se crea un File con la ruta del directorio raiz.

        if (directorio.exists() && directorio.isDirectory()) {
            //Se verifica si el directorio existe y es efectivamente un directorio.
            File[] archivos = directorio.listFiles((dir, name) -> name.endsWith(".json"));
            //Se usa un filtro de archivos para obtener unicamente aquellos archivos que terminar con .json

            if (archivos != null && archivos.length > 0) {
                System.out.println("Archivos de personajes encontrados:");
                for (File archivo : archivos) {
                    System.out.println("- " + archivo.getName());
                }
            } else {
                System.out.println("No se encontraron archivos JSON en el directorio.");
            }
        } else {
            System.out.println("La ruta especificada no es válida.");
        }
    }

    public static void renombrarPersonaje(String nombreActual, String nuevoNombre) {
        //Creacion de dos File
        File archivoActual = new File(nombreActual + ".json"); //Archivo actual
        File archivoNuevo = new File(nuevoNombre + ".json"); //Nuevo nombre

        if(archivoActual.exists()){
            if(archivoNuevo.exists()){
                System.out.println("Ya existe un archivo con ese nombre. Elija otro.");
                return;
            }

            if(archivoActual.renameTo(archivoNuevo)){
                System.out.println("Archivo renombrado con exito.");
                actualizarNombreEnJSON(archivoNuevo.getPath(), nuevoNombre);
            }else {
                System.out.println("Error al renombrar el archivo.");
            }
        }else {
            System.out.println("El archivo no existe");
        }
    }

    public static void actualizarNombreEnJSON(String rutaArchivo, String nuevoNombre) {
        try {
            // Leer el contenido del archivo JSON
            String contenido = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
            JSONObject jsonPersonaje = new JSONObject(contenido);

            // Actualizar el atributo "nombre"
            jsonPersonaje.put("nombre", nuevoNombre);

            // Guardar los cambios en el archivo
            Files.write(Paths.get(rutaArchivo), jsonPersonaje.toString(4).getBytes());
            System.out.println("Nombre en JSON actualizado con éxito.");
        } catch (IOException e) {
            System.out.println("Error al actualizar el JSON: " + e.getMessage());
        }
    }

    public static void borrarPersonaje(Scanner scanner) throws PersonajeException {
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

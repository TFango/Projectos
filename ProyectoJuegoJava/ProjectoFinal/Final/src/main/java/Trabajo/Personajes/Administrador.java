package Trabajo.Personajes;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Administrador {
    private String usuario;
    private String contraseña;

    public Administrador(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
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

        // Verificar que el archivo exista
        if (archivoActual.exists()) {
            // Renombrar el archivo
            if (archivoActual.renameTo(archivoNuevo)) {
                System.out.println("Archivo renombrado con éxito.");

                // Actualizar el contenido del archivo JSON
                actualizarNombreEnJSON(archivoNuevo.getPath(), nuevoNombre);
            } else {
                System.out.println("Error al renombrar el archivo.");
            }
        } else {
            System.out.println("El archivo del personaje no existe.");
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
}

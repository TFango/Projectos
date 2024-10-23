package Trabajo.GuardadoPersonajes;

import java.io.File;

public class borrarPersonajes {
    public static void borrarArchivo(String archivo) {
        File file = new File(archivo);
        if (file.delete()) {
            System.out.println("El archivo " + archivo + " ha sido borrado.");
        } else {
            System.out.println("No se pudo borrar el archivo " + archivo);
        }
    }
}

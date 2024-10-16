package Proyecto;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class guardarPersonaje {
    public static void guardarPersonaje(Personaje personaje, String archivo) {
        try (FileOutputStream fileOut = new FileOutputStream(archivo);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(personaje);
            System.out.println("Personaje guardado en " + archivo);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}

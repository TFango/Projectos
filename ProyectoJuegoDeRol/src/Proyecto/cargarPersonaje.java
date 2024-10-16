package Proyecto;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class cargarPersonaje {
    public static Personaje cargarPersonaje(String archivo) {
        Personaje personaje = null;
        try (FileInputStream fileIn = new FileInputStream(archivo);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            personaje = (Personaje) in.readObject();
            System.out.println("Personaje cargado desde " + archivo);
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
        return personaje;
    }
}

package Trabajo.GuardadoPersonajes;

import Trabajo.Personajes.Personaje;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class cargarPersonajes {
    private static final String archivoPersonajes = "personajes.json";
    private Gson gson = new Gson();

    public List<Personaje> cargarPersonajes(){
        try(FileReader reader = new FileReader(archivoPersonajes)){
            Type tipoLista = new TypeToken<List<Personaje>> () {}.getType();
            return gson.fromJson(reader, tipoLista);
        }catch (IOException e){
            System.out.println("Error al cargar personajes desde el archivo.");
            return null;
        }
    }

    public Personaje cargarPersonajesPorNombre(String nombre){
        List<Personaje> personajes = cargarPersonajes();
        if(personajes != null){
            for(Personaje p : personajes){
                if(p.getNombre().equals(nombre)){
                    System.out.println("Personaje " + nombre + "cargado.");
                    return p;
                }
            }
            System.out.println("Personaje " + nombre + " no cargado.");
        }
        return null;
    }
}

package Trabajo.Enemigos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mazmorra {
    private List<List<Habitacion>> pisos;
    private int pisoActual;

    public Mazmorra(int cantidadPisos) {
        this.pisos = new ArrayList<>();
        Random random = new Random();

        for(int i=0; i<cantidadPisos; i++) {
            List<Habitacion> habitaciones = new ArrayList<>();
            int cantidadHabitaciones = random.nextInt(3) + 3;
            for(int j=0; j<cantidadHabitaciones; j++) {
                habitaciones.add(new Habitacion(j == cantidadHabitaciones - 1));
            }
            pisos.add(habitaciones);
        }
        this.pisoActual = 0;
    }

    public int getPisoActual() {
        return pisoActual;
    }

    public int getCantidadPisos() { return pisos.size();}

    public List<Habitacion> getHabitacionesDelPisoActual() { return pisos.get(pisoActual);}

    public boolean avanzarPiso(){
        if(pisoActual<pisos.size() -1) {
            pisoActual++;
            return true;
        }
        return false;
    }
}

package Trabajo.Usuarios;

import Trabajo.excepciones.PersonajeException;

import java.util.Scanner;

public class MenuInicial {
    private Scanner sc;

    public MenuInicial(Scanner sc) {
        this.sc = sc;
    }

    public void mostrarMenu() throws PersonajeException {
        int opcion;

        while (true) {
            System.out.println("Bienvenido al juego");
            System.out.println("Como desea entrar?");
            System.out.println("1. Jugar Demo");
            System.out.println("2. Jugar juego completo");
            System.out.println("3. Administrador");
            System.out.println("Sellecione una opcion: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            }catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un numero");
                continue;
            }

            switch (opcion) {
                case 1:
                    JuegoDemo juegoDemo = new JuegoDemo(sc);
                    juegoDemo.iniciarJuegoDemo();
                    break;
                case 2:
                    JuegoCompleto juegoCompleto = new JuegoCompleto(sc);
                    juegoCompleto.iniciarJuegoCompleto();
                    break;
                case 3:
                    Administrador administrador = new Administrador(sc);
                    administrador.iniciarAdministrador();
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }
}

package org.example;

import Trabajo.Usuarios.MenuInicial;
import Trabajo.excepciones.PersonajeException;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws PersonajeException {

        Scanner sc = new Scanner(System.in);
        MenuInicial menuInicial = new MenuInicial(sc);
        menuInicial.mostrarMenu();

    }
}

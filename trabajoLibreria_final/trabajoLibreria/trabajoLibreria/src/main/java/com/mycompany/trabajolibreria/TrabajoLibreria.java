package com.mycompany.trabajolibreria;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TrabajoLibreria {

    public static void main(String[] args) {

        Boolean variableDeControl = false;

        while (!variableDeControl) {
            try {
                System.out.print(" 1. Añadir libro \n 2. Buscar \n 3. Mostar libros \n 4. Añadir unidades\n 5. Salir\nQue quieres hacer: ");
                Integer eleccion = InputOutput.introducirInteger();

                if (eleccion == 1) {

                    System.out.print("ID: ");
                    int id = InputOutput.introducirInteger();

                    InputOutput.introducirString();

                    System.out.print("Nombre: ");
                    String nombre = InputOutput.introducirString();

                    System.out.print("Autor: ");
                    String autor = InputOutput.introducirString();

                    System.out.print("Cuantas unidades quieres añadir: ");
                    int unidades = InputOutput.introducirInteger();

                    libreria.añadirLibros(id, nombre, autor, unidades);

                } else if (eleccion == 2) {
                    System.out.println();
                    System.out.print("1. Buscar por titulo\n2. Buscar por autor\n3.Salir\nQue quieres?: ");
                    int eleccion1 = InputOutput.introducirInteger();

                    Boolean variableDeControl1 = false;

                    while (!variableDeControl1) {
                        if (eleccion1 == 1) {
                            System.out.println();
                            InputOutput.introducirString();
                            System.out.print("Dime el titulo del libro que quieres buscar: ");
                            String titulo = InputOutput.introducirString();
                            libreria.buscarTitulo(titulo);
                            variableDeControl1 = true;
                        } else if (eleccion1 == 2) {
                            InputOutput.introducirString();
                            System.out.print("Dime el autor del libro que quieres buscar: ");
                            String autor = InputOutput.introducirString();
                            libreria.buscarAutor(autor);
                            variableDeControl1 = true;
                        } else if (eleccion1 == 3) {
                            System.out.println("Saliendo!");
                            variableDeControl1 = true;
                            System.out.println();
                        } else {
                            System.out.println("Introduce un numero del 1 al 3");
                            variableDeControl1 = true;
                        }
                    }
                } else if (eleccion == 3) {

                    libreria.mostrarLibros();

                } else if (eleccion == 4) {
                    InputOutput.introducirString();
                    System.out.print("A que libro quieres añadir: ");
                    String libroSeleccionado = InputOutput.introducirString();
                    System.out.print("Cuantas unidades quieres añadir: ");
                    int unidadesAgregar = InputOutput.introducirInteger();
                    libreria.anadirUd(libroSeleccionado, unidadesAgregar);

                } else if (eleccion == 5) {

                    System.out.println("Saliendo!");
                    variableDeControl = true;

                }
                else{
                    System.out.println("Introduce un numero del 1 al 5");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes introducir un número entero.");
                InputOutput.introducirString();
            }
        }

    }
}
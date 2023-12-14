package com.mycompany.trabajolibreria;

import java.util.ArrayList;

public class libreria {

    public static ArrayList<libros> estanteria = new ArrayList<>();

    public static void añadirLibros(int id, String nombre, String autor, int unidades) {

        for (libros libro : estanteria) {

            if (libro.getId() == id) {
                System.out.println("No se ha podido añadir el libro. Ya existe un libro con ese ID");
                return; 
            }
        }

        libros libroNuevo = new libros(id, nombre, autor, unidades);
        estanteria.add(libroNuevo);
        System.out.println();
        System.out.println("ID: " + libroNuevo.getId() + " Titulo: " + libroNuevo.getNombre() + " Autor: " + libroNuevo.getAutor() + " Unidades: " + libroNuevo.getUnidades());
        System.out.println();
    }

    public static void buscarTitulo(String titulo) {
        for (libros libro : estanteria) {
            if (libro.getNombre().equals(titulo)) {
                System.out.println();
                System.out.println("Libro encontrado: ");
                System.out.println("ID: " + libro.getId() + " Titulo: " + libro.getNombre() + " Autor:" + libro.getAutor() + " Unidades disponibles: " + libro.getUnidades());
                System.out.println();
            } else {
                System.out.println("El libro " + titulo + " no existe.");
                System.out.println();
            }
        }
    }

    public static void buscarAutor(String autor) {
        for (libros libro : estanteria) {
            if (libro.getAutor().equals(autor)) {
                System.out.println("Autor encontrado: ");
                System.out.println("ID: " + libro.getId() + " Titulo: " + libro.getNombre() + " Autor:" + libro.getAutor() + " Unidades disponibles: " + libro.getUnidades());
            } else {
                System.out.println();
                System.out.println("El autor " + autor + " no existe.");
                System.out.println();
            }
        }
    }

    public static void mostrarLibros() {
        for (libros libroActual : estanteria) {
            int idLibro = libroActual.getId();
            String nombreLibro = libroActual.getNombre();
            String autorLibro = libroActual.getAutor();
            int udLibro = libroActual.getUnidades();
            System.out.println("ID: " + idLibro + " Nombre: " + nombreLibro + " Autor: " + autorLibro + " Unidades: " + udLibro);
            System.out.println();
        }
    }

    public static void anadirUd(String libroSeleccionado, int unidadesAgregar) {
        for (libros libro : estanteria) {
            if (libro.getNombre().equals(libroSeleccionado)) {
                int unidadesNuevas = libro.getUnidades() + unidadesAgregar;
                System.out.println("Unidades: " + unidadesNuevas);
                libro.setUnidades(unidadesNuevas);
            }
        }
    }
}

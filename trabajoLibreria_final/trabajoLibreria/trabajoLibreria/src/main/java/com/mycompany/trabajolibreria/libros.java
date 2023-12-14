package com.mycompany.trabajolibreria;

public class libros {
    private int id;
    private String nombre;
    private String autor;
    private int unidades;

    public libros(int id, String nombre, String autor, int unidades) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.unidades = unidades;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

}

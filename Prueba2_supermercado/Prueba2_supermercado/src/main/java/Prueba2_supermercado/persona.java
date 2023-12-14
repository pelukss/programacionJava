package Prueba2_supermercado;

import java.util.ArrayList;

public class persona {
    private  String nombre;
    private  String dni;
    private  ArrayList<Double> carrito;

    public persona(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
        this.carrito = new ArrayList<>();
    }

    public  String getNombre() {
        return nombre;
    }

    public  String getDni() {
        return dni;
    }

    public  ArrayList<Double> getCarrito() {
        return carrito;
    }

    public  void agregarCarrito( Double valor){
        carrito.add(valor);
    }

    public void mergeCarritos(Double productosCarrito){
        carrito.add(productosCarrito);
    }
}
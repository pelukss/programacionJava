package Prueba2_supermercado;

public class productoEnCesta {
        Double id;
        private Double precio;
        private int cantidad;

        public productoEnCesta(Double id, Double precio, int cantidad) {
            this.id = id;
            this.precio = precio;
            this.cantidad = cantidad;
        }

        public void incrementarCantidad() {
            this.cantidad++;
        }

        public double calcularTotal() {
            return this.precio * this.cantidad;
        }

        public int getCantidad() {
            return cantidad;
        }
    }
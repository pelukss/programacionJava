package Prueba2_supermercado;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
//import java.nio.file.Paths;


public class index {
    private static ArrayList<persona> listaPersonas = new ArrayList<>();
    private static persona personaActual;

    public static void main(String[] args) {
        File archivo = new File("Prueba2_supermercado/Prueba2_supermercado/Productos.xlsx");

        Scanner scanner = new Scanner(System.in);

        boolean variableControl = true;
        while (variableControl) {
            System.out.println();
            System.out.print("1. Mostrar todo\n2. Comprar\n3. Salir\nQue quieres hacer: ");
            int eleccion = scanner.nextInt();

            if (eleccion == 1) {
                System.out.println();
                mostrarTodos(archivo);
            } else if (eleccion == 2) {
                scanner.nextLine();
                System.out.print("Como te llamas?: ");
                String nombrePersona = scanner.nextLine();

                String dniPersona = " ";

                Boolean variableDeControl = true;
                while (variableDeControl) {
                    System.out.print("Cual es tu DNI?: ");
                    dniPersona = scanner.nextLine();

                    if (dniPersona.length() != 9) {
                        System.out.println("DNI no valido");
                    } else {
                        variableDeControl = false;
                    }
                }

                personaActual = new persona(nombrePersona, dniPersona);
                personaActual = comprobarPersonas(personaActual);

                comprar(archivo, personaActual);

            } else if (eleccion == 3) {
                variableControl = false;
            } else {
                System.out.println("Escribe algo valido");
            }
        }

        sumarPrecios(archivo);

        // Convertir listaPersonas a JSON
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String listaPersonasJson = objectMapper.writeValueAsString(listaPersonas);
            String rutaArchivoJson = "C:\\Users\\sorro\\Desktop\\programacionLucasH\\Prueba2_supermercado\\Prueba2_supermercado\\src\\main\\java\\Prueba2_supermercado\\listaPersonas.json";

            // Guardar el JSON en un archivo
            Files.write(Paths.get(rutaArchivoJson), listaPersonasJson.getBytes());

            System.out.println("JSON guardado en: " + rutaArchivoJson);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void mostrarTodos(File archivo) {
        try (FileInputStream fileInputStream = new FileInputStream(archivo);
             Workbook workBook = new XSSFWorkbook(fileInputStream)) {
            Sheet hoja = workBook.getSheetAt(0);

            for (Row fila : hoja) {
                for (Cell celda : fila) {
                    if (celda.getColumnIndex() == 0 || celda.getColumnIndex() == 1) {
                        Cell valor = celda;
                        System.out.print(valor + "\t");
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("404 something went wrong");
        }
    }

    public static void comprar(File archivo, persona personaActual) {
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        mostrarTodos(archivo);
        System.out.println();

        Boolean variableControlWhileCompra = true;
        while (variableControlWhileCompra) {
            System.out.print("ID de la compra (o '0' para volver al menú principal): ");
            Double idCompra = scanner.nextDouble();

            if (idCompra == 0) {
                break;
            }

            try (FileInputStream fileInputStream = new FileInputStream(archivo);
                 Workbook workBook = new XSSFWorkbook(fileInputStream)) {
                Sheet hoja = workBook.getSheetAt(0);

                boolean primeraFila = true;

                for (Row fila : hoja) {
                    if (primeraFila) {
                        primeraFila = false;
                        continue;
                    }

                    Cell celda = fila.getCell(1);

                    if (celda != null) {
                        Double valor = celda.getNumericCellValue();

                        if (valor.equals(idCompra)) {
                            System.out.println("ID correcto, añadiendo al carrito...");
                            personaActual.agregarCarrito(valor);
                        }
                    }
                }
            } catch (Exception h) {
                System.out.println("Introduce algo válido :(");
            }
        }
    }

    public static persona comprobarPersonas(persona personaActual) {
        boolean personaEncontrada = false;
        for (persona personaCreada : listaPersonas) {
            if (personaCreada.getNombre().equals(personaActual.getNombre())
                    && personaCreada.getDni().equals(personaActual.getDni())) {
                personaEncontrada = true;
                return personaCreada;
            }
        }

        if (!personaEncontrada) {
            listaPersonas.add(personaActual);
        }

        return personaActual;
    }

    public static void sumarPrecios(File archivo) {
        try (FileInputStream fileInputStream = new FileInputStream(archivo);
                Workbook workBook = new XSSFWorkbook(fileInputStream)) {
            Sheet hoja = workBook.getSheetAt(0);

            for (persona personaActual : listaPersonas) {
                boolean primeraFila = true; // Reiniciar primeraFila para cada persona

                List<productoEnCesta> productosEnCesta = new ArrayList<>();

                for (Row fila : hoja) {
                    if (primeraFila) {
                        primeraFila = false;
                        continue;
                    }

                    Cell celdaID = fila.getCell(1);
                    Cell celdaPrecio = fila.getCell(3);

                    if (celdaID != null && celdaPrecio != null) {
                        Double id = celdaID.getNumericCellValue();
                        Double precio = celdaPrecio.getNumericCellValue();

                        if (personaActual.getCarrito().contains(id)) {
                            productoEnCesta producto = encontrarProductoEnCesta(productosEnCesta, id);

                            if (producto == null) {
                                productosEnCesta.add(new productoEnCesta(id, precio,
                                        contarProductos(personaActual.getCarrito(), id)));
                            } else {
                                producto.incrementarCantidad();
                            }
                        }
                    }
                }

                double totalPrecios = 0.0;

                System.out.println("El carrito de " + personaActual.getNombre() + " contiene:");
                for (productoEnCesta producto : productosEnCesta) {
                    System.out.println("ID: " + producto.id + " x" + producto.getCantidad());
                    totalPrecios += producto.calcularTotal();
                }

                System.out.println("Total: " + totalPrecios + " euros.");
                System.out.println();
            }
        } catch (Exception j) {
            System.out.println("Error al sumar precios");
        }
    }

    public static int contarProductos(List<Double> carrito, Double id) {
        return Collections.frequency(carrito, id);
    }

    public static productoEnCesta encontrarProductoEnCesta(List<productoEnCesta> productosEnCesta, Double id) {
        //recorre los productos en la cesta
        for (productoEnCesta producto : productosEnCesta) {
            //si el id esta en la cesta devuelve el producto que es el id del carrito
            if (producto.id.equals(id)) {
                return producto;
            }
        }
        return null;
    }
}
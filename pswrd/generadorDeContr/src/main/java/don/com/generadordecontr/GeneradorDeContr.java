package don.com.generadordecontr;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class GeneradorDeContr {
    // Declarar variables
    private static ArrayList<Character> letrasPermitidas = new ArrayList<>();
    private static ArrayList<Integer> numerosPermitidos = new ArrayList<>();
    private static ArrayList<String> simbolosPermitidos = new ArrayList<>();
    private static String contrasena;
    private static String nombreCuenta;
    private static String cuentaBorrar;


    public static void main(String[] args) {
        File archivo = new File("C:\\Users\\sorro\\Desktop\\excel.xlsx");

        // crear scanner
        Scanner scanner = new Scanner(System.in);

        // Bucle for para añadir todas las letras al array
        for (char letra = 'a'; letra <= 'z'; letra++) {
            letrasPermitidas.add(letra);
        }

        // Bucle for para añadir todas los numeros al array
        for (int numero = 0; numero <= 9; numero++) {
            numerosPermitidos.add(numero);
        }

        // array para los simbolos
        simbolosPermitidos.add("#");
        simbolosPermitidos.add("$");
        simbolosPermitidos.add("%");
        simbolosPermitidos.add("&");
        simbolosPermitidos.add("*");

        // MENU
        System.out.print(
                "1. Crear contraseña aleatoria\n2. Borrar cuenta\n3. Añadir cuenta y contraseña\n \nQue quieres hacer: ");
        int eleccion = scanner.nextInt();
        System.out.println();

        // condicionales de eleccion
        if (eleccion == 1) {
            System.out.print("Nombre de la cuenta: ");
            scanner.nextLine();
            nombreCuenta = scanner.nextLine();
            constructorPswd();
            escribirExcel(contrasena, nombreCuenta, archivo);
        } else if (eleccion == 2) {
            mostrarTodos(archivo);
            System.out.print("Nombre de cuenta que quieres borrar: ");
            scanner.nextLine();
            cuentaBorrar = scanner.nextLine();
            borrarCuenta(cuentaBorrar, archivo);
        } else if (eleccion == 3) {

        }

        scanner.close();
    }

    public static char letraAleatoria() {
        // Crea una instancia de la clase Random
        Random random = new Random();

        // coge un indice aleatorio
        int indiceAleatorio = random.nextInt(letrasPermitidas.size());
        // coge el valor de ese indice
        char letraRandom = letrasPermitidas.get(indiceAleatorio);
        return letraRandom;
    }

    public static int numeroAleatorio() {
        // Crea una instancia de la clase Random
        Random random = new Random();

        // consigue un indice del array
        int indiceAleatorio = random.nextInt(numerosPermitidos.size());
        // coge el valor de ese indice
        int numeroRandom = numerosPermitidos.get(indiceAleatorio);
        return numeroRandom;
    }

    public static String simboloAleatorio() {
        // Crea una instancia de la clase Random
        Random random = new Random();

        // consigue un indice del array
        int indiceAleatorio = random.nextInt(simbolosPermitidos.size());
        // coge el valor de ese indice
        String simboloRandom = simbolosPermitidos.get(indiceAleatorio);
        return simboloRandom;
    }

    public static String constructorPswd() {
        // crear y almacenar cada numero
        int primerNumero = numeroAleatorio();
        int segundoNumero = numeroAleatorio();
        int tercerNumero = numeroAleatorio();

        // crea y almacena cada letra
        char primeraLetra = letraAleatoria();
        char segundaLetra = letraAleatoria();
        char terceraLetra = letraAleatoria();
        char cuartaLetra = letraAleatoria();
        char quintaLetra = letraAleatoria();

        // crea y almacena cada simbolo
        String primerSimbolo = simboloAleatorio();
        String segundoSimbolo = simboloAleatorio();

        // Concatenar los números como cadenas de texto
        contrasena = String.valueOf(primerNumero) + String.valueOf(segundoNumero) + String.valueOf(tercerNumero)
                + primeraLetra + segundaLetra + terceraLetra + cuartaLetra + quintaLetra + primerSimbolo
                + segundoSimbolo;
        return contrasena;
    }

    public static void mostrarTodos(File archivo) {
        // carga el archivo
        try (FileInputStream fileInputStream = new FileInputStream(archivo);
                Workbook workBook = new XSSFWorkbook(fileInputStream)) {
            org.apache.poi.ss.usermodel.Sheet hoja = workBook.getSheetAt(0);

            for (Row fila : hoja) {

                for (Cell celda : fila) {

                    Cell valor = celda;
                    System.out.print(valor + " ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("404 something went wrong");
        }
    }

    public static void escribirExcel(String contrasena, String nombreCuenta, File archivo) {

        try (FileInputStream fileInputStream = new FileInputStream(archivo);
                Workbook workBook = new XSSFWorkbook(fileInputStream)) {
            org.apache.poi.ss.usermodel.Sheet hoja = workBook.getSheetAt(0);

            int ultimaFila = hoja.getLastRowNum() + 2;

            if (ultimaFila == -1) {
                ultimaFila = 1;
            }

            Row fila = hoja.createRow(ultimaFila);

            Cell celdaNombreCuenta = fila.createCell(1);
            celdaNombreCuenta.setCellValue(nombreCuenta);

            // Ir a la siguiente fila para la contraseña
            ultimaFila++;
            Row filaContrasena = hoja.createRow(ultimaFila);
            Cell celdaContrasena = filaContrasena.createCell(1);
            celdaContrasena.setCellValue(contrasena);

            try (FileOutputStream fileOut = new FileOutputStream(archivo)) {
                workBook.write(fileOut);
            }

            System.out.println(contrasena);
            System.out.println("Datos escritos en el archivo Excel existente.");

        } catch (Exception e) {
            System.out.println("catch de escritura makina");
            e.printStackTrace();
        }
    }

    public static void borrarCuenta(String cuentaBorrar, File archivo){
        try (FileInputStream fileInputStream = new FileInputStream(archivo);
                Workbook workBook = new XSSFWorkbook(fileInputStream)) {
            Sheet hoja = workBook.getSheetAt(0);
            Row fila = hoja.getRow(1);
            Cell celda = fila.getCell(1);

            if(celda != null){
                String valor = celda.getStringCellValue();
                if(valor == cuentaBorrar){
                    celda.setCellValue("");
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(archivo)) {
                workBook.write(fileOut);
            }


        } catch (Exception e) {
            System.out.println("404 something went wrong");
        }
    }

}

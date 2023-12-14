package com.mycompany.trabajolibreria;

import java.util.Scanner;



public class InputOutput {

    private static Scanner scanner = new Scanner(System.in);

    public static String introducirString(){
        return scanner.nextLine();
    }

    public static Integer introducirInteger(){
        return scanner.nextInt();
    }
}

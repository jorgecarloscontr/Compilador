/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import Funciones.Lexico;
import Funciones.Sintactico;
import java.util.Scanner;

/**
 *
 * @author jorge
 */
public class Compilador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingresa una cadena: ");
        String cadena= entrada.nextLine();
        /*
        Lexico lexico = new Lexico(cadena);
        while(lexico.fin()){
            lexico.automata();
        }*/
        Sintactico analisis=new Sintactico(cadena);
        analisis.ejercicio1();
    }
    
}

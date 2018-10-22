/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import Controlador.Contr_Main;
import Funciones.Sintactico;
import Vista.Main;
import java.awt.Dimension;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jorge
 */
public class Compilador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Main ventana = new Main();
//        ventana.setVisible(true);
//        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
        
        
        
        
//        Scanner entrada = new Scanner(System.in);
//        System.out.print("Ingresa una cadena: ");
//        String cadena= entrada.nextLine();
        
//        Lexico lexico = new Lexico(cadena);
//        while(lexico.fin()){
//            lexico.automata();
//        }
        
        
//        Sintactico analisis=new Sintactico(cadena);
//        analisis.cargar_datos();
//        analisis._sintactico();
//        try {
//            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//            try {
//                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex1) {
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex1);
//            }
//        }
        Main m = new Main();
        m.setSize(new Dimension(1200, 800));
        m.setVisible(true);
        m.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    }
    
}

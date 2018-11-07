/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;

/**
 *
 * @author jorge
 */
public class Semantico {
    static List<String> listaErrores=new ArrayList<>();
    static TablaSimbolos tablaSimbolos=new TablaSimbolos();;
    
    private String cadena;
    private Sintactico analisis;
    public Semantico(JScrollPane jScrollPane2){
        analisis=new Sintactico(jScrollPane2);
        analisis.cargar_datos();
    }
    public void run(String cadena){
        tablaSimbolos.inicializar();
        listaErrores.clear();
        this.cadena=cadena;
        analisis.inicializar(this.cadena);
        analisis._sintactico();
        Nodo raiz=analisis.getRaiz();
        raiz.validaTipos(tablaSimbolos);
        tablaSimbolos.getListaTabla().forEach((e) -> {
            System.out.println(e.info());
        });
        System.out.println("\nLista de errores");
        listaErrores.forEach((e)->{
            System.out.println(e);
        });
    }
}

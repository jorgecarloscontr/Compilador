/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import Controlador.Contr_Main;
import Controlador.FuncTableModel;
import Controlador.VarTableModel;
import Controlador.TableRender;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author jorge
 */
public class Semantico {
    static List<String> listaErrores=new ArrayList<>();
    static TablaSimbolos tablaSimbolos=new TablaSimbolos();
    private JTable jTableVar;
    private JTable jTableFunc;
    private VarTableModel varTableModel;
    private FuncTableModel funcTableModel;
    private String header= "#include <conio.h>\n" +
                            "#include <stdlib.h>\n" +
                            "#include <cstdio>\n" +
                            "#include <iostream>\n" +
                            "using namespace std;\n";

    private String cadena;
    private Sintactico analisis;
    public Semantico(JScrollPane jScrollPane2,JTable v,JTable f){
        analisis=new Sintactico(jScrollPane2);
        analisis.cargar_datos();
        jTableVar=v;
        jTableFunc=f;
        varTableModel=new VarTableModel();
        varTableModel.updateTable(tablaSimbolos.getListaTabla());
        funcTableModel=new FuncTableModel();
        funcTableModel.updateTable(tablaSimbolos.getListaTabla());
        jTableVar.setModel(varTableModel);
        jTableFunc.setModel(funcTableModel);
        jTableVar.setDefaultRenderer(Object.class, new TableRender());
        jTableFunc.setDefaultRenderer(Object.class, new TableRender());
    }
    public void run(String cadena) throws IOException{
        tablaSimbolos.inicializar();
        listaErrores.clear();
        this.cadena=cadena;
        analisis.inicializar(this.cadena);
        analisis._sintactico();
        Nodo raiz=analisis.getRaiz();
        if(raiz!=null){
            raiz.validaTipos(tablaSimbolos);
            tablaSimbolos.getListaTabla().forEach((e) -> {
                System.out.println(e.info());
            });
            System.out.println("\nLista de errores");
            listaErrores.forEach((e)->{
                System.out.println(e);
            });
            if(listaErrores.isEmpty()){
                    FileWriter fichero = new FileWriter("codigo.cpp");
                    PrintWriter pw =  new PrintWriter(fichero);
                    pw.print(header+cadena.replaceAll("print", "cout<<"));
                    pw.close();
                    fichero.close();
                    PrintWriter out= new PrintWriter("Generador.bat");                    
                    out.println("@echo off");
                    out.println("title Compilador");
                    out.println("CD C:\\Users\\jorge\\Documents\\NetBeansProjects\\Compilador");
                    out.println("C:\\MinGW\\bin\\g++ -c codigo.cpp");
                    out.println("C:\\MinGW\\bin\\g++ -o codigo codigo.cpp");
                    out.println("codigo.exe");
                    out.println("@ECHO.");
                    out.println("@pause");
                    out.println("exit");
                    out.close();
                    Runtime rt=Runtime.getRuntime();
                    rt.exec("cmd /c start \"\" Generador.bat");

            }
        }
        varTableModel.updateTable(tablaSimbolos.getListaTabla());
        varTableModel.fireTableDataChanged();
        funcTableModel.updateTable(tablaSimbolos.getListaTabla());
        funcTableModel.fireTableDataChanged();
    }
    public String getOutput(){
        String output=analisis.getInfo_pila()+"\n\nLista de errores: \n";
        for(String s:listaErrores){
            output=output+s+"\n";
        }
        return output;
    }
}

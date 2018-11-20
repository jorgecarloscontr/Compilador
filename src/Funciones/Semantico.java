/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import Controlador.FuncTableModel;
import Controlador.VarTableModel;
import Controlador.TableRender;
import java.util.ArrayList;
import java.util.List;
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
    public void run(String cadena){
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
        }
        varTableModel.updateTable(tablaSimbolos.getListaTabla());
        varTableModel.fireTableDataChanged();
        funcTableModel.updateTable(tablaSimbolos.getListaTabla());
        funcTableModel.fireTableDataChanged();
    }
    public String getOutput(){
        String output=analisis.getInfo_pila()+"Lista de errores: \n";
        for(String s:listaErrores){
            output=output+s+"\n";
        }
        return output;
    }
}

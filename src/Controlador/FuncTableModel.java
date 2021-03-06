/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Funciones.ElementoTabla;
import Funciones.Funcion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jorge
 */
public class FuncTableModel extends AbstractTableModel {
    private List<Funcion> data;
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Funcion getData = data.get(rowIndex);
            switch(columnIndex){
                case 0: return getData.getSimbolo();
                case 1: String t;
                        switch(getData.getTipo()){
                            case 'i':
                                t="int";break;
                            case 'f':
                                t="float";break;
                            case 'v':
                                t="void";break;
                            default: t="N";
                        }
                        return t;
                case 2: return getData.getParametros();
                default: return "";
            }
    }
    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "Identificador";
            case 1: return "Tipo";
            case 2: return "Parametros";
            default: return "";
        }
    }
    public void updateTable(List<ElementoTabla> lista){
        data=new ArrayList<>();
        lista.forEach((e) -> {
            if(e.isFucnion()){
                data.add((Funcion) e);
            }else{
            }
        });
    }
}

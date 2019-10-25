/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge
 */
public class ElementoTabla {
    String simbolo;
    char tipo;   

    public String getSimbolo() {
        return simbolo;
    }

    public char getTipo() {
        return tipo;
    }
    

    public boolean isVariable(){
        return false;
    }
    public boolean isVarLocal(){
        return false;
    }
    public boolean isFucnion(){
        return false;
    }
    public String info(){
        return "Simbolo: "+simbolo+" Tipo"+tipo;
    }
    
}
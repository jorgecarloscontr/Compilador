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

class Variable extends ElementoTabla{
    private final boolean local;
    private final String ambito;
    public Variable(char tipo,String simbolo,String ambito){
        this.tipo=tipo;
        this.simbolo=simbolo;
        this.ambito=ambito;
        this.local=(!this.ambito.equals(""));
    }
    @Override
    public boolean isVarLocal(){
        return local;
    }
    @Override
    public boolean isVariable(){
        return true;
    } 
    public String getAmbito() {
        return ambito;
    }
    @Override
    public String info(){
        return "Simbolo: "+simbolo+" Tipo: "+tipo+" ambito: "+ambito+" local: "+local;
    }
}

class Funcion extends ElementoTabla{
    private final String parametros;
    @Override
    public boolean isFucnion(){
        return true;
    }
    public Funcion(char tipo, String simbolo, String parametros){
            this.simbolo= simbolo;
            this.tipo= tipo;
            this.parametros= parametros;
    }
    @Override
    public String info(){
    return "Simbolo: "+simbolo+" Tipo: "+tipo+" parametros: "+parametros;
    }
    public List<String> getParametros(){
        String aux="";
        List<String> lista=new ArrayList<>();
        for(char c:parametros.toCharArray()){
            if(c==32){
                lista.add(aux);
                aux="";
            }else{
                aux=aux+c;
            }
        }
        return lista;
    }
};
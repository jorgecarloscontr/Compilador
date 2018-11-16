/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

/**
 *
 * @author jorge
 */
public class Variable extends ElementoTabla{
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
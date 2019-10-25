/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pila;

import Funciones.Nodo;

/**
 *
 * @author jorge
 */
public class NoTerminal extends ElementoPila{
    private String regla;
    private Nodo nodo;
    public NoTerminal(int id, String regla,Nodo nodo){
        this.id=id;
        this.regla=regla;
        this.nodo=nodo;
        
    }
    @Override
    public void muestra() {
        System.out.println("id_regla: "+this.id+" regla: "+this.regla);
    }

    @Override
    public int getid() {
        return this.id;
    }
    public String getregla(){
        return this.regla;
    }

    public Nodo getNodo() {
        return nodo;
    }

    public void setNodo(Nodo nodo) {
        this.nodo = nodo;
    }
    
}

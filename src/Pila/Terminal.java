/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pila;

/**
 *
 * @author jorge
 */
public class Terminal extends ElementoPila{
    private String simbolo;
    public Terminal(int id,String simbolo){
        this.id=id;
        this.simbolo=simbolo;
    }
    public Terminal(int id){
        this.id=id;
    }

    @Override
    public void muestra() {
        System.out.println("id: "+this.id+" Simbolo: "+this.simbolo);
    }   

    @Override
    public int getid() {
        return this.id;
    }
    public String getsimbolo(){
        return simbolo;
    }
}

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
public class Estado extends ElementoPila{
    
    public Estado(int id){
        this.id=id;
    }

    @Override
    public void muestra() {
        System.out.println("id "+this.id);
    }

    @Override
    public int getid() {
        return this.id;
    }
}

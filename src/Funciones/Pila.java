/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author jorge
 */
public class Pila {
    Stack pila;
    public Pila(){
        pila=new Stack<>();
    }
    public void muestra(){
        Iterator iterador=pila.iterator();
        while(iterador.hasNext()){
            System.out.println((int)iterador.next());
        }
        
    }
}

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
public class Sintactico {
   private Stack pila;
   int fila,columna,accion;
   boolean aceptacion;
   boolean cancelar;
   Lexico lexico;
   int[][] tablaLR2={
       {2,0,0,1},
       {0,0,-1,0},
       {0,3,-3,0},
       {2,0,0,4},
       {0,0,-2,0}
    };
    int[] idReglas= { 3, 3};
    int[] lonReglas= {3, 1};   
   
   public Sintactico(String cadena){
       pila= new Stack<>();
       aceptacion=false;
       cancelar=false;
       lexico= new Lexico(cadena);
   }
   public void ejercicio1(){
       pila.push(2);
       pila.push(0);
       lexico.automata();
       while (!aceptacion && !cancelar) {
           muestra();
           fila = (int)pila.peek();
           columna = lexico.getestado();
           accion = tablaLR2[fila][columna];
           int aux = 0;
           if (accion > 0) {
               pila.add(columna);
               pila.add(accion);
               lexico.automata();
               System.out.println();
           } else {
               if (accion < 0) {
                   if(accion==-1){
                    aceptacion=true;
                    System.out.println("cadena admitida");
                   }else{
                   aux = (lonReglas[Math.abs(accion + 2)]) * 2;                  
                   for (int i = 0; i < aux; i++) {
                       pila.pop();
                   }
                   fila = (int)pila.peek();
                   columna = idReglas[Math.abs(accion + 2)]; //el no terminal que representa a E
                   accion = tablaLR2[fila][columna];
                   //transici�n
                   pila.push(columna);
                   pila.push(accion);}
                   muestra();
               } else {
                 //  muestra(pila.iterator());
                   cancelar = true;
                   System.out.println("cadena no admitida");
               }
           }
       }
   }   
   public void muestra(){
       for(Object d: pila.toArray())
           System.out.print((int)d);
       System.out.println();
   }
}

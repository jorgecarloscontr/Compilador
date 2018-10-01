/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import Pila.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author jorge
 */

public class Sintactico {
   private Stack<ElementoPila> pila;
   int fila,columna,accion;
   boolean aceptacion;
   boolean cancelar;
   Lexico lexico;
   int[][] tablaLR;
   int[][] tablaLR2={
       {2,0,0,1},
       {0,0,-1,0},
       {0,3,-3,0},
       {2,0,0,4},
       {0,0,-2,0}
    };
    int[] idReglas;
    int[] lonReglas; 
    String[] strReglas;

   
    public Sintactico(String cadena) {
        pila = new Stack<>();
        aceptacion = false;
        cancelar = false;
        lexico = new Lexico(cadena);
    }
    public void ejercicio1() {
        pila.push(new Terminal(2));
        pila.push(new Estado(0));
        lexico.automata();
        muestra();
        while (!aceptacion && !cancelar) {
            fila = pila.peek().getid();
            columna = lexico.getestado();
            accion = tablaLR2[fila][columna];
            int aux = 0;
            if (accion > 0) {
                pila.add(new Terminal(columna, lexico.getSimbolo()));
                pila.add(new Estado(accion));
                muestra();
                lexico.automata();
            } else {
                if (accion < 0) {
                    if (accion == -1) {
                        aceptacion = true;
                        System.out.println("cadena admitida");
                    } else {
                        aux = (lonReglas[Math.abs(accion + 2)]) * 2;
                        for (int i = 0; i < aux; i++) {
                            pila.pop();
                        }
                        fila = pila.peek().getid();
                        columna = idReglas[Math.abs(accion + 2)]; //el no terminal que representa a E
                        accion = tablaLR2[fila][columna];
                        //transici�n
                        pila.push(new NoTerminal(columna, strReglas[0]));
                        pila.push(new Estado(accion));
                        muestra();
                    }
                } else {
                    cancelar = true;
                    System.out.println("cadena no admitida");
                    muestra();
                }
            }
        }
    }
    public void _sintactico() {
        pila.push(new Terminal(2));
        pila.push(new Estado(0));
        lexico.automata();
        muestra();
        while (!aceptacion && !cancelar) {
            fila = pila.peek().getid();
            columna = lexico.getestado();
            accion = tablaLR[fila][columna];
            int aux = 0;
            if (accion > 0) {
                pila.add(new Terminal(columna, lexico.getSimbolo()));
                pila.add(new Estado(accion));
                muestra();
                lexico.automata();
            } else {
                if (accion < 0) {
                    if (accion == -1) {
                        aceptacion = true;
                        System.out.println("cadena admitida");
                    } else {
                        aux = (lonReglas[Math.abs(accion + 2)]) * 2;
                        for (int i = 0; i < aux; i++) {
                            pila.pop();
                        }
                        fila = pila.peek().getid();
                        columna = idReglas[Math.abs(accion + 2)]; //el no terminal que representa a E
                        //transicion
                        pila.push(new NoTerminal(columna, strReglas[Math.abs(accion + 2)]));
                        accion = tablaLR[fila][columna];
                        pila.push(new Estado(accion));
                        muestra();
                    }
                } else {
                    cancelar = true;
                    System.out.println("cadena no admitida");
                    muestra();
                }
            }
        }
    }
    public void muestra() {
        for (Object d : pila.toArray()) {
            if (Terminal.class.isInstance(d)) {
                if (((Terminal) d).getsimbolo() != null) {
                    System.out.print(((Terminal) d).getsimbolo());
                } else {
                    System.out.print(((Terminal) d).getid());
                }
            } else if (Estado.class.isInstance(d)) {
                System.out.print(((Estado) d).getid());
            } else {
                System.out.print(((NoTerminal) d).getregla());
            }
        }
        System.out.println();
    }
    public void cargar_datos() {
        BufferedReader mbuffer;
        FileReader fichero;
        int nreglas=0,aux=0,aux2=0,cont=0;
        try {
            fichero = new FileReader("src/Funciones/compilador.lr");
            mbuffer = new BufferedReader(fichero);
            String text = mbuffer.readLine();
            nreglas=Integer.parseInt(text);
            idReglas=new int[nreglas];
            lonReglas=new int[nreglas];
            strReglas=new String[nreglas];
            for(int i=0;i<nreglas;i++){
                text=mbuffer.readLine();
                asignar_datos(text,i);
            }
            text=mbuffer.readLine();
            aux=(int)retornar_vector(text).get(0);
            tablaLR=new int[aux][(int)retornar_vector(text).get(1)];
            while(cont<aux){
                text=mbuffer.readLine();
                for(Object element:retornar_vector(text).toArray()){
                    tablaLR[cont][aux2]=(int)element;
                    aux2++;
                }
                aux2=0;
                cont++;
            }
            fichero.close();
            mbuffer.close();            
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo");
        }
    }
    public void asignar_datos(String datos,int indice){//asigna los valores de idReglas, longReglas y strReglas
        int cont=0,i=0;
        while(cont<2){
            if(datos.charAt(i)==9){
                if(cont==0)
                    this.idReglas[indice]=Integer.parseInt(datos.substring(0,i));
                else
                    this.lonReglas[indice]=Integer.parseInt(datos.substring(0,i));
                datos=datos.substring(i+1,datos.length());
                i=-1;
                cont++;
            }
            i++;
        }
        this.strReglas[indice]=datos;
    }
    public List retornar_vector(String datos){//retorna una lista de enteros por linea
        int i=0;
        List lista=new ArrayList<>();
        while(i<datos.length()){
            if(datos.charAt(i)==9){
                lista.add(Integer.parseInt(datos.substring(0, i)));
                datos=datos.substring(i+1,datos.length());
                i=-1;
            }
            i++;
        }
        lista.add(Integer.parseInt(datos));
        return lista;
    }
}
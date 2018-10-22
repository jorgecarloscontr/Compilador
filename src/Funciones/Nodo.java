/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import Pila.ElementoPila;
import Pila.NoTerminal;
import Pila.Terminal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author jorge
 */
public abstract class Nodo{
    String simbolo;
    Nodo padre;
    double x,y,distancia;
    List<Nodo> hijos;
    boolean visit;
    public Nodo(){
        this.x=0;
        this.y=0;
        this.padre=null;
        this.hijos=new ArrayList<>();
        this.simbolo="";
        this.visit=false;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }
    public boolean isVisit() {
        return visit;
    }

    public void setVisit(boolean visit) {
        this.visit = visit;
    }
    public Nodo getprimero(){
        if(!hijos.isEmpty()){
            return hijos.get(0);
        }else{
            return null;
        }
    }
    public Nodo getultimo(){
        if(!hijos.isEmpty()){
            return hijos.get(hijos.size()-1);
        }else{
            return null;
        }
    }
    public Nodo getPadre() {
        return padre;
    }
    
    public void addhijos(Nodo h){
        hijos.add(h);
    }

    public List<Nodo> getHijos() {
        return hijos;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
}

class Programa extends Nodo{
    public Programa(Stack<ElementoPila> pila){
        pila.pop();
        Nodo tmp=((NoTerminal)pila.pop()).getNodo();
        tmp.setPadre(this);
        this.setSimbolo("Programa");
        this.addhijos(tmp);
    }
} 
class Definiciones extends Nodo{
    public Definiciones(int longitud,Stack<ElementoPila> pila){
        this.setSimbolo("Definiciones");
        if(longitud!=0){
            pila.pop();
            Nodo tmp=((NoTerminal)pila.pop()).getNodo();
            tmp.setPadre(this);
            this.addhijos(tmp);
            pila.pop();
            Nodo tmp1=((NoTerminal)pila.pop()).getNodo();
            tmp1.setPadre(this);
            this.addhijos(tmp1);
        }
    }
}
class Definicion extends Nodo{
    public Definicion(Stack<ElementoPila> pila){
        pila.pop();
        Nodo tmp=((NoTerminal)pila.pop()).getNodo();
        tmp.setPadre(this);
        this.setSimbolo("Definicion");
        this.addhijos(tmp);
    }   
}

class DefVar extends Nodo{
    DefVar(Stack<ElementoPila> pila){
        pila.pop();
        pila.pop();
        pila.pop();
        Nodo tmp=((NoTerminal)pila.pop()).getNodo();
        tmp.setPadre(this);
        this.addhijos(tmp);
        pila.pop();
        Identificador ident = new Identificador(((Terminal)pila.pop()).getsimbolo(),this);
        this.addhijos(ident);
        pila.pop();
        Tipo tipo=new Tipo(((Terminal)pila.pop()).getsimbolo(),this);
        this.addhijos(tipo);
        this.simbolo="DefVar";
    }
}
class ListaVar extends Nodo{
    public ListaVar(int longitud,Stack<ElementoPila> pila){
        this.simbolo="ListaVar";
        if(longitud!=0){
            pila.pop();
            Nodo tmp=((NoTerminal)pila.pop()).getNodo();
            tmp.setPadre(this);
            this.addhijos(tmp);
            pila.pop();
            Identificador ident = new Identificador(((Terminal)pila.pop()).getsimbolo(),this);
            this.addhijos(ident);
            pila.pop();
            Simbolo s=new Simbolo(((Terminal)pila.pop()).getsimbolo(),this);  
            this.addhijos(s);
        }
    }
}
class Simbolo extends Nodo{
    public Simbolo(String simbolo,Nodo padre){
        this.simbolo=simbolo;
        this.setPadre(padre);
    } 
}


class DefFunc extends Nodo{
    public DefFunc(Stack<ElementoPila> pila){
        this.simbolo="DefFunc";
        pila.pop();
        Nodo bloq=((NoTerminal)pila.pop()).getNodo();
        bloq.setPadre(this);
        this.addhijos(bloq);
        pila.pop();
        pila.pop();
        pila.pop();
        Nodo parametros=((NoTerminal)pila.pop()).getNodo();
        parametros.setPadre(this);
        this.addhijos(parametros);
        pila.pop();
        pila.pop();
        pila.pop();
        Nodo identificador=new Identificador(((Terminal)pila.pop()).getsimbolo(),this);
        this.addhijos(identificador);
        pila.pop();
        Nodo tipo=new Tipo(((Terminal)pila.pop()).getsimbolo(),this);
        this.addhijos(tipo);
    }
}
class Parametros extends Nodo{
    public Parametros(int longitud,Stack<ElementoPila> pila){
        this.simbolo="Parametros";
        if(longitud!=0){
            pila.pop();
            Nodo listaparam=((NoTerminal)pila.pop()).getNodo();
            listaparam.setPadre(this);
            this.addhijos(listaparam);
            pila.pop();
            Nodo identificador=new Identificador(((Terminal)pila.pop()).getsimbolo(),this);
            this.addhijos(identificador);
            pila.pop();
            Nodo tipo=new Tipo(((Terminal)pila.pop()).getsimbolo(),this);
            this.addhijos(tipo);
        }
    }
}
class ListaParam extends Nodo{
    public ListaParam(int tam,Stack<ElementoPila> pila){
        this.simbolo="ListaParam";
        if(tam!=0){
            pila.pop();
            Nodo listaparam= ((NoTerminal)pila.pop()).getNodo();
            listaparam.setPadre(this);
            this.addhijos(listaparam);
            pila.pop();
            Nodo identificador=new Identificador(((Terminal)pila.pop()).getsimbolo(),this);
            this.addhijos(identificador);
            pila.pop();
            Tipo tipo=new Tipo(((Terminal)pila.pop()).getsimbolo(),this);
            this.addhijos(tipo);
            pila.pop();
            Simbolo s=new Simbolo(((Terminal)pila.pop()).getsimbolo(),this);  
            this.addhijos(s);
        }
    }
}
class BloqFunc extends Nodo{//--------------------------------------llaves
    public BloqFunc(Stack<ElementoPila> pila){
        this.simbolo="BloqFunc";
        pila.pop();
        pila.pop();
        pila.pop();
        Nodo deflocales=((NoTerminal)pila.pop()).getNodo();
        deflocales.setPadre(this);
        this.addhijos(deflocales);
        pila.pop();
        pila.pop();
    }
}
class DefLocales extends Nodo{
    public DefLocales(int tam,Stack<ElementoPila> pila){
        this.simbolo="DefLocales";
        if (tam!=0){
            pila.pop();
            Nodo deflocales=(DefLocales)((NoTerminal)pila.pop()).getNodo();
            deflocales.setPadre(this);
            this.addhijos(deflocales);
            pila.pop();
            Nodo deflocal=(DefLocal)((NoTerminal)pila.pop()).getNodo(); 
            deflocal.setPadre(this);
            this.addhijos(deflocal);
        }       
    }
}
class DefLocal extends Nodo{
    public DefLocal(Stack<ElementoPila> pila){
        this.simbolo="DefLocal";
        pila.pop();
        Nodo tmp=((NoTerminal)pila.pop()).getNodo();
        tmp.setPadre(this);
        this.addhijos(tmp);
    }
}
class Sentencias extends Nodo{
    public Sentencias(int tam,Stack<ElementoPila> pila){
        if(tam>0){
            pila.pop();
            Nodo sentencias=((NoTerminal)pila.pop()).getNodo();
            sentencias.setPadre(this);
            this.addhijos(sentencias);
            pila.pop();
            Nodo sentencia=((NoTerminal)pila.pop()).getNodo();
            sentencia.setPadre(this);
            this.addhijos(sentencia);
        }
    } 
}
class Sentencia extends Nodo{
    Identificador identificador;
    Expresion expresion;
    SentenciaBloque sentenciabloque;
    Bloque bloque;
    ValorRegresa valorregresa;
    Nodo llamadafunc;
    Nodo otro;
    Nodo s;
    public Sentencia(int tam,Stack<ElementoPila> pila){
        this.simbolo="Sentencia";
        switch(tam){
            case 2:
                pila.pop();
                s=new Simbolo(((Terminal)pila.pop()).getsimbolo(),this);  
                this.addhijos(s);
                pila.pop();
                llamadafunc=((NoTerminal)pila.pop()).getNodo();
                llamadafunc.setPadre(this);
                this.addhijos(llamadafunc);
                break;
            case 3:
                pila.pop();
                s=new Simbolo(((Terminal)pila.pop()).getsimbolo(),this);  
                this.addhijos(s);
                pila.pop();
                valorregresa=(ValorRegresa)((NoTerminal)pila.pop()).getNodo();
                valorregresa.setPadre(this);
                this.addhijos(valorregresa);
                pila.pop();
                s=new Simbolo(((Terminal)pila.pop()).getsimbolo(),this);  
                this.addhijos(s);
                break;
            case 4:
                pila.pop();
                s=new Simbolo(((Terminal)pila.pop()).getsimbolo(),this);  
                this.addhijos(s);
                pila.pop();
                expresion=(Expresion)((NoTerminal)pila.pop()).getNodo();
                expresion.setPadre(this);
                this.addhijos(expresion);
                pila.pop();
                s=new Simbolo(((Terminal)pila.pop()).getsimbolo(),this);  
                this.addhijos(s);
                pila.pop();
                identificador=new Identificador(((Terminal)pila.pop()).getsimbolo(),this);
                identificador.setPadre(this);
                this.addhijos(identificador);
                break;
            case 5:
                pila.pop();
                bloque=(Bloque)((NoTerminal)pila.pop()).getNodo();
                bloque.setPadre(this);
                this.addhijos(bloque);
                pila.pop();
                pila.pop();
                pila.pop();
                expresion=(Expresion)((NoTerminal)pila.pop()).getNodo();
                expresion.setPadre(this);
                this.addhijos(expresion);
                pila.pop();
                pila.pop();
                pila.pop();
                s=new Simbolo(((Terminal)pila.pop()).getsimbolo(),this);  
                this.addhijos(s);
                break;
            case 6:
                pila.pop();
                otro=((NoTerminal)pila.pop()).getNodo();
                otro.setPadre(this);
                this.addhijos(otro);
                pila.pop();
                sentenciabloque=(SentenciaBloque)((NoTerminal)pila.pop()).getNodo();
                sentenciabloque.setPadre(this);
                this.addhijos(sentenciabloque);
                pila.pop();
                pila.pop();
                pila.pop();
                expresion=(Expresion)((NoTerminal)pila.pop()).getNodo();
                expresion.setPadre(this);
                this.addhijos(expresion);
                pila.pop();
                pila.pop();
                pila.pop();
                s=new Simbolo(((Terminal)pila.pop()).getsimbolo(),this);  
                this.addhijos(s);
                break;           
        }
    }
}

class Otro extends Nodo{
    public Otro(int tam,Stack<ElementoPila> pila){
        this.simbolo="Otro";
        if(tam>0){
            pila.pop();
            Nodo sentenciabloque=((NoTerminal)pila.pop()).getNodo();
            sentenciabloque.setPadre(this);
            this.addhijos(sentenciabloque);
            pila.pop();
            Nodo s=new Simbolo(((Terminal)pila.pop()).getsimbolo(),this);  
            this.addhijos(s);
        }
    }
}

class Bloque extends Nodo{
    public Bloque(Stack<ElementoPila> pila){
        this.simbolo="Bloque";
        pila.pop();
        pila.pop();
        pila.pop();
        Nodo sentencia=((NoTerminal)pila.pop()).getNodo();
        sentencia.setPadre(this);
        this.addhijos(sentencia);
        pila.pop();
        pila.pop();
    }
}
class ValorRegresa extends Nodo{
    public ValorRegresa(int tam,Stack<ElementoPila> pila){
        this.simbolo="ValorRegresa";
        if(tam!=0){
            pila.pop();
            Nodo expresion=((NoTerminal)pila.pop()).getNodo();
            expresion.setPadre(this);
            this.addhijos(expresion);
        }
    }
}
class Argumentos extends Nodo{
    public Argumentos(int tam, Stack<ElementoPila> pila){
        this.simbolo="Argumentos";
        if(tam>0){
            pila.pop();
            Nodo listaArgumentos=((NoTerminal)pila.pop()).getNodo();
            listaArgumentos.setPadre(this);
            this.addhijos(listaArgumentos);
            pila.pop();
            Nodo expresion=((NoTerminal)pila.pop()).getNodo();
            expresion.setPadre(this);
            this.addhijos(expresion);
        }
    }
}
class ListaArgumentos extends Nodo{
    public ListaArgumentos(int tam, Stack<ElementoPila> pila){
        this.simbolo="ListaArgumentos";
        if(tam>0){
            pila.pop();
            Nodo listaArgumentos=((NoTerminal)pila.pop()).getNodo();
            listaArgumentos.setPadre(this);
            this.addhijos(listaArgumentos);
            pila.pop();
            Nodo expresion=((NoTerminal)pila.pop()).getNodo();
            expresion.setPadre(this);
            this.addhijos(expresion);
            pila.pop();
            Nodo s=new Simbolo(((Terminal)pila.pop()).getsimbolo(),this);  
            this.addhijos(s);
        }
    }
}

class Entero extends Nodo{
    public Entero(String simbolo,Nodo padre){
        this.simbolo=simbolo;
        this.padre=padre;
    }
}
class Real extends Nodo{
    public Real(String simbolo,Nodo padre){
        this.simbolo=simbolo;
        this.padre=padre;
    }
}
class Cadena extends Nodo{
    public Cadena(String simbolo,Nodo padre){
        this.simbolo=simbolo;
        this.padre=padre;
    }
}

class Termino extends Nodo{
    public Termino(int nregla,Stack<ElementoPila> pila){
        this.simbolo="Termino";
        switch(nregla){
            case 35:
                pila.pop();
                Nodo llamadafunc=((NoTerminal)pila.pop()).getNodo();
                llamadafunc.setPadre(this);
                this.addhijos(llamadafunc);
                break;
            case 36:
                pila.pop();
                Nodo identificador=new Identificador(((Terminal)pila.pop()).getsimbolo(),this);
                this.addhijos(identificador);
                break;
            case 37:
                pila.pop();
                Nodo entero=new Entero(((NoTerminal)pila.pop()).getNodo().simbolo,this);
                this.addhijos(entero);
                break;
            case 38: 
                pila.pop();
                Nodo real=new Real(((NoTerminal)pila.pop()).getNodo().simbolo,this);
                this.addhijos(real);
                break;
            case 39:
                pila.pop();
                Nodo cadena=new Cadena(((NoTerminal)pila.pop()).getNodo().simbolo,this);
                this.addhijos(cadena);
                break;
        }
    }
}
class LlamadaFunc extends Nodo{
    public LlamadaFunc(Stack<ElementoPila> pila){
        pila.pop();
        pila.pop();
        pila.pop();
        Nodo argumentos=((NoTerminal)pila.pop()).getNodo();
        argumentos.setPadre(this);
        this.addhijos(argumentos);
        pila.pop();
        pila.pop();
        pila.pop();
        Nodo identificador=new Identificador(((Terminal)pila.pop()).getsimbolo(),this);
        this.addhijos(identificador);
    }
}
class SentenciaBloque extends Nodo{
    public SentenciaBloque(Stack<ElementoPila> pila){
        pila.pop();
        Nodo sig=((NoTerminal)pila.pop()).getNodo();
        sig.setPadre(this);
        this.addhijos(sig);
    }
}

class Expresion extends Nodo{
    Nodo izq, der;
    Nodo s;
    public Expresion(int nregla,Stack<ElementoPila> pila){
        this.simbolo="Expresion";
        switch(nregla){
            case 43:
                pila.pop();
                pila.pop();
                pila.pop();
                izq=((NoTerminal)pila.pop()).getNodo();
                izq.setPadre(this);
                this.addhijos(izq);
                pila.pop();
                pila.pop();
                break;
            case 44:
                pila.pop();
                der=((NoTerminal)pila.pop()).getNodo();
                der.setPadre(this);
                this.addhijos(der);
                pila.pop();
                s=new Simbolo(((Terminal)pila.pop()).getsimbolo(),this);
                this.addhijos(s);
                break;
            case 45:
                pila.pop();
                der=((NoTerminal)pila.pop()).getNodo();
                der.setPadre(this);
                this.addhijos(der);
                pila.pop();
                s=new Simbolo(((Terminal)pila.pop()).getsimbolo(),this);
                this.addhijos(s);
                break;
            case 46: 
                pila.pop();
                der=((NoTerminal)pila.pop()).getNodo();
                der.setPadre(this);
                this.addhijos(der);
                pila.pop();
                s=new Simbolo(((Terminal)pila.pop()).getsimbolo(),this);
                this.addhijos(s);
                pila.pop();
                izq=((NoTerminal)pila.pop()).getNodo();
                izq.setPadre(this);
                this.addhijos(izq);
                break;
            case 47:
                pila.pop();
                der=((NoTerminal)pila.pop()).getNodo();
                der.setPadre(this);
                this.addhijos(der);
                pila.pop();
                s=new Simbolo(((Terminal)pila.pop()).getsimbolo(),this);
                this.addhijos(s);
                pila.pop();
                izq=((NoTerminal)pila.pop()).getNodo();
                izq.setPadre(this);
                this.addhijos(izq);
                break;
            case 48:
                pila.pop();
                der=((NoTerminal)pila.pop()).getNodo();
                der.setPadre(this);
                this.addhijos(der);
                pila.pop();
                s=new Simbolo(((Terminal)pila.pop()).getsimbolo(),this);
                this.addhijos(s);
                pila.pop();
                izq=((NoTerminal)pila.pop()).getNodo();
                izq.setPadre(this);
                this.addhijos(izq);
                break;
            case 49:
                pila.pop();
                der=((NoTerminal)pila.pop()).getNodo();
                der.setPadre(this);
                this.addhijos(der);
                pila.pop();
                s=new Simbolo(((Terminal)pila.pop()).getsimbolo(),this);
                this.addhijos(s);
                pila.pop();
                izq=((NoTerminal)pila.pop()).getNodo();
                izq.setPadre(this);
                this.addhijos(izq);
                break;
            case 50:
                pila.pop();
                der=((NoTerminal)pila.pop()).getNodo();
                der.setPadre(this);
                this.addhijos(der);
                pila.pop();
                s=new Simbolo(((Terminal)pila.pop()).getsimbolo(),this);
                this.addhijos(s);
                pila.pop();
                izq=((NoTerminal)pila.pop()).getNodo();
                izq.setPadre(this);
                this.addhijos(izq);
                break;
            case 51:
                pila.pop();
                der=((NoTerminal)pila.pop()).getNodo();
                der.setPadre(this);
                this.addhijos(der);
                pila.pop();
                s=new Simbolo(((Terminal)pila.pop()).getsimbolo(),this);
                this.addhijos(s);
                izq=((NoTerminal)pila.pop()).getNodo();
                izq.setPadre(this);
                this.addhijos(izq);
                break;
            case 52:
                pila.pop();
                Nodo termino=((NoTerminal)pila.pop()).getNodo();   
                termino.setPadre(this);
                this.addhijos(termino);
                break;
        }
    }
}

class Tipo extends Nodo{
    public Tipo(String simbolo,Nodo padre){
        this.simbolo=simbolo;
        this.padre=padre;
    }
}

class  Identificador extends Nodo{
    public Identificador(String simbolo,Nodo padre){
        this.simbolo=simbolo;
        this.setPadre(padre);
    }
}



 
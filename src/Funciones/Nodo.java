/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import Pila.ElementoPila;
import Pila.NoTerminal;
import java.util.Stack;

/**
 *
 * @author jorge
 */
public abstract class Nodo{
    String simbolo;
    Nodo sig;
}

class Programa extends Nodo{
    public Programa(Stack<ElementoPila> pila){
        sig=((NoTerminal)pila.pop()).getNodo();
    }    
}
class Definiciones extends Nodo{
    public Definicion izq;
    public Definiciones der;
    public Definiciones(int nregla,Stack<ElementoPila> pila){
        if(nregla!=0){
            pila.pop();
            izq=(Definicion) ((NoTerminal)pila.pop()).getNodo();
            pila.pop();
            der=(Definiciones) ((NoTerminal)pila.pop()).getNodo();
        }else{
            izq=null;
            der=null;
        }
    }
}
class Definicion extends Nodo{
    public Definicion(Stack<ElementoPila> pila){
        pila.pop();
        this.sig=((NoTerminal)pila.pop()).getNodo();
    }   
}

class DefVar extends Nodo{
    public Tipo tipo;
    public Identificador identificador;
    public ListaVar listavar;
    public DefVar(Stack<ElementoPila> pila){
        pila.pop();
        pila.pop();
        pila.pop();
        this.listavar=(ListaVar) ((NoTerminal)pila.pop()).getNodo();
        pila.pop();
        this.tipo=(Tipo) ((NoTerminal)pila.pop()).getNodo();
        pila.pop();
        this.identificador=(Identificador) ((NoTerminal)pila.pop()).getNodo();
        this.sig=null;
    }
}
class ListaVar extends Nodo{
    public Identificador identificador;
    public ListaVar listavar;
    public ListaVar(int longitud,Stack<ElementoPila> pila){
        this.sig=null;
        this.simbolo=null;
        if(longitud!=0){
            pila.pop();
            this.listavar=(ListaVar) ((NoTerminal)pila.pop()).getNodo();
            pila.pop();
            this.identificador=(Identificador) ((NoTerminal)pila.pop()).getNodo();
            pila.pop();
            pila.pop();
        }else{
            this.identificador=null;
            this.listavar=null;
        }
    }
}
class DefFunc extends Nodo{
    public Tipo tipo; 
    public Identificador identificador;
    public Parametros parametros;
    public BloqFunc bloqfunc;
    public DefFunc(Stack<ElementoPila> pila){
        pila.pop();
        this.bloqfunc=(BloqFunc)((NoTerminal)pila.pop()).getNodo();
        pila.pop();
        pila.pop();
        pila.pop();
        this.parametros=(Parametros)((NoTerminal)pila.pop()).getNodo();
        pila.pop();
        pila.pop();
        pila.pop();
        this.identificador=(Identificador)((NoTerminal)pila.pop()).getNodo();
        pila.pop();
        this.tipo=(Tipo)((NoTerminal)pila.pop()).getNodo();
    }
}
class Parametros extends Nodo{
    public Tipo tipo;
    public Identificador identificador;
    public ListaParam listaparam;
    public Parametros(int longitud,Stack<ElementoPila> pila){
        if(longitud!=0){
            pila.pop();
            this.listaparam=(ListaParam)((NoTerminal)pila.pop()).getNodo();
            pila.pop();
            this.identificador=(Identificador)((NoTerminal)pila.pop()).getNodo();
            pila.pop();
            this.tipo=(Tipo)((NoTerminal)pila.pop()).getNodo();
        }else{
            this.tipo=null;
            this.identificador=null;
            this.listaparam=null;
        }
    }
}
class ListaParam extends Nodo{
    public Tipo tipo;
    public Identificador identificador;
    public ListaParam listaparam;
    
    public ListaParam(int tam,Stack<ElementoPila> pila){
        if(tam!=0){
            pila.pop();
            this.listaparam= (ListaParam)((NoTerminal)pila.pop()).getNodo();
            pila.pop();
            this.identificador=(Identificador)((NoTerminal)pila.pop()).getNodo();
            pila.pop();
            this.tipo=(Tipo)((NoTerminal)pila.pop()).getNodo();
        }else{
            tipo=null;
            identificador=null;
            listaparam=null;
        }
    }
}
class BloqFunc extends Nodo{
    public DefLocales deflocales;
    public BloqFunc(Stack<ElementoPila> pila){
        pila.pop();
        deflocales=(DefLocales)((NoTerminal)pila.pop()).getNodo();
    }
}
class DefLocales extends Nodo{
    public DefLocal deflocal;
    public DefLocales deflocales;
    public DefLocales(int tam,Stack<ElementoPila> pila){
        if (tam!=0){
            pila.pop();
           deflocal=(DefLocal)((NoTerminal)pila.pop()).getNodo();
            pila.pop();
            deflocales=(DefLocales)((NoTerminal)pila.pop()).getNodo(); 
        }else{
            deflocal=null;
            deflocales=null;
        }        
    }
}
class DefLocal extends Nodo{
    public DefLocal(Stack<ElementoPila> pila){
        pila.pop();
        this.sig=((NoTerminal)pila.pop()).getNodo();
    }
}

 


class Tipo extends Nodo{
    public Tipo(String simbolo){
        this.simbolo=simbolo;
        this.sig=null;
    }
}
abstract class Expresion extends Nodo{
    public Expresion izq, der;
}
class  Identificador extends Expresion{
    public Identificador(String simbolo, Nodo sig){
        this.simbolo=simbolo;
        this.sig=sig;
    }
}



 
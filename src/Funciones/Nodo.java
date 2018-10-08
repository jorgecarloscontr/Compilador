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
        pila.pop();
        sig=((NoTerminal)pila.pop()).getNodo();
    }
}
class Definiciones extends Nodo{
    Nodo izq;
    Nodo der;
    public Definiciones(int longitud,Stack<ElementoPila> pila){
        if(longitud!=0){
            pila.pop();
            izq=((NoTerminal)pila.pop()).getNodo();
            pila.pop();
            der=((NoTerminal)pila.pop()).getNodo();
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
    Nodo tipo;
    Nodo identificador;
    Nodo listavar;
    DefVar(Stack<ElementoPila> pila){
        pila.pop();
        pila.pop();
        pila.pop();
        this.listavar=((NoTerminal)pila.pop()).getNodo();
        pila.pop();
        this.identificador=((NoTerminal)pila.pop()).getNodo();
        pila.pop();
        this.tipo=(Tipo) ((NoTerminal)pila.pop()).getNodo();
        this.sig=null;
    }
}
class ListaVar extends Nodo{
    Nodo identificador;
    Nodo listavar;
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
    Tipo tipo; 
    Identificador identificador;
    Parametros parametros;
    BloqFunc bloqfunc;
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
    Tipo tipo;
    Identificador identificador;
    ListaParam listaparam;
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
    Tipo tipo;
    Identificador identificador;
    ListaParam listaparam;
    public ListaParam(int tam,Stack<ElementoPila> pila){
        if(tam!=0){
            pila.pop();
            this.listaparam= (ListaParam)((NoTerminal)pila.pop()).getNodo();
            pila.pop();
            this.identificador=(Identificador)((NoTerminal)pila.pop()).getNodo();
            pila.pop();
            this.tipo=(Tipo)((NoTerminal)pila.pop()).getNodo();
            pila.pop();
            pila.pop();
        }else{
            tipo=null;
            identificador=null;
            listaparam=null;
        }
    }
}
class BloqFunc extends Nodo{
    DefLocales deflocales;
    public BloqFunc(Stack<ElementoPila> pila){
        pila.pop();
        pila.pop();
        pila.pop();
        deflocales=(DefLocales)((NoTerminal)pila.pop()).getNodo();
        pila.pop();
        pila.pop();
    }
}
class DefLocales extends Nodo{
    DefLocal deflocal;
    DefLocales deflocales;
    public DefLocales(int tam,Stack<ElementoPila> pila){
        if (tam!=0){
            pila.pop();
            deflocales=(DefLocales)((NoTerminal)pila.pop()).getNodo();
            pila.pop();
            deflocal=(DefLocal)((NoTerminal)pila.pop()).getNodo(); 
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
class Sentencias extends Nodo{
    Nodo sentencia;
    Nodo sentencias;
    public Sentencias(int tam,Stack<ElementoPila> pila){
        if(tam>0){
            pila.pop();
            sentencias=((NoTerminal)pila.pop()).getNodo();
            pila.pop();
            sentencia=((NoTerminal)pila.pop()).getNodo();
        }else{
            sentencia=null;
            sentencias=null;
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
    public Sentencia(int tam,Stack<ElementoPila> pila){
        switch(tam){
            case 2:
                pila.pop();
                pila.pop();
                pila.pop();
                llamadafunc=((NoTerminal)pila.pop()).getNodo();
                break;
            case 3:
                pila.pop();
                pila.pop();
                pila.pop();
                valorregresa=(ValorRegresa)((NoTerminal)pila.pop()).getNodo();
                pila.pop();
                pila.pop();
                break;
            case 4:
                pila.pop();
                pila.pop();
                pila.pop();
                expresion=(Expresion)((NoTerminal)pila.pop()).getNodo();
                pila.pop();
                pila.pop();
                pila.pop();
                identificador=(Identificador)((NoTerminal)pila.pop()).getNodo();
                break;
            case 5:
                pila.pop();
                bloque=(Bloque)((NoTerminal)pila.pop()).getNodo();
                pila.pop();
                pila.pop();
                pila.pop();
                expresion=(Expresion)((NoTerminal)pila.pop()).getNodo();
                pila.pop();
                pila.pop();
                pila.pop();
                pila.pop();
                break;
            case 6:
                pila.pop();
                otro=((NoTerminal)pila.pop()).getNodo();
                pila.pop();
                sentenciabloque=(SentenciaBloque)((NoTerminal)pila.pop()).getNodo();
                pila.pop();
                pila.pop();
                pila.pop();
                expresion=(Expresion)((NoTerminal)pila.pop()).getNodo();
                pila.pop();
                pila.pop();
                pila.pop();
                pila.pop();
                break;           
        }
    }
}

class Otro extends Nodo{
    Nodo sentenciabloque;
    public Otro(int tam,Stack<ElementoPila> pila){
        if(tam>0){
            pila.pop();
            sentenciabloque=((NoTerminal)pila.pop()).getNodo();
            pila.pop();
            pila.pop();
        }else{
            sentenciabloque=null;
        }
    }

}

class Bloque extends Nodo{
    public Bloque(Stack<ElementoPila> pila){
        pila.pop();
        pila.pop();
        pila.pop();
        sig=((NoTerminal)pila.pop()).getNodo();
        pila.pop();
        pila.pop();
    }
}
class ValorRegresa extends Nodo{
    Nodo expresion;
    public ValorRegresa(int tam,Stack<ElementoPila> pila){
       if(tam!=0){
           pila.pop();
           expresion=((NoTerminal)pila.pop()).getNodo();
       }else{
           sig=null;expresion=null;
       }
    }
}
class Argumentos extends Nodo{
    Nodo listaArgumentos;
    Nodo expresion;
    public Argumentos(int tam, Stack<ElementoPila> pila){
        if(tam>0){
            pila.pop();
            listaArgumentos=((NoTerminal)pila.pop()).getNodo();
            pila.pop();
            expresion=((NoTerminal)pila.pop()).getNodo();
        }else{
            listaArgumentos=null;
            expresion=null;
        }
    }
}
class ListaArgumentos extends Nodo{
    Nodo listaArgumentos;
    Nodo expresion;
    public ListaArgumentos(int tam, Stack<ElementoPila> pila){
        if(tam>0){
            pila.pop();
            listaArgumentos=((NoTerminal)pila.pop()).getNodo();
            pila.pop();
            expresion=((NoTerminal)pila.pop()).getNodo();
            pila.pop();
            pila.pop();
        }else{
            listaArgumentos=null;
            expresion=null;
        }
    }
}

class Entero extends Nodo{
    public Entero(String simbolo){
        this.simbolo=simbolo;
    }
}
class Real extends Nodo{
    public Real(String simbolo){
        this.simbolo=simbolo;
    }
}
class Cadena extends Nodo{
    public Cadena(String simbolo){
        this.simbolo=simbolo;
    }
}

class Termino extends Nodo{
    Nodo identificador;
    Nodo llamadafunc;
    Nodo entero;
    Nodo real;
    Nodo cadena;
    public Termino(int nregla,Stack<ElementoPila> pila){
        switch(nregla){
            case 35:
                pila.pop();
                llamadafunc=((NoTerminal)pila.pop()).getNodo();
                break;
            case 36:
                pila.pop();
                identificador=((NoTerminal)pila.pop()).getNodo();
                break;
            case 37:
                pila.pop();
                entero=new Entero(((NoTerminal)pila.pop()).getNodo().simbolo);
                break;
            case 38: 
                pila.pop();
                real=new Real(((NoTerminal)pila.pop()).getNodo().simbolo);
                break;
            case 39:
                pila.pop();
                cadena=new Cadena(((NoTerminal)pila.pop()).getNodo().simbolo);
                break;
        }
    }
}
class LlamadaFunc extends Nodo{
    Nodo argumentos;
    Nodo identificador;
    public LlamadaFunc(Stack<ElementoPila> pila){
        pila.pop();
        pila.pop();
        pila.pop();
        argumentos=((NoTerminal)pila.pop()).getNodo();
        pila.pop();
        pila.pop();
        pila.pop();
        identificador=((NoTerminal)pila.pop()).getNodo();
    }
}
class SentenciaBloque extends Nodo{
    
    public SentenciaBloque(Stack<ElementoPila> pila){
        pila.pop();
        sig=((NoTerminal)pila.pop()).getNodo();
    }
}

class Expresion extends Nodo{
    Nodo izq, der;
    Nodo opsuma,opnot,opmul,oprela,opigualdad,opand,opor,termino;
    public Expresion(int nregla,Stack<ElementoPila> pila){
        switch(nregla){
            case 43:
                pila.pop();
                pila.pop();
                pila.pop();
                izq=((NoTerminal)pila.pop()).getNodo();
                pila.pop();
                pila.pop();
                break;
            case 44:
                pila.pop();
                der=((NoTerminal)pila.pop()).getNodo();
                pila.pop();
                opsuma=((NoTerminal)pila.pop()).getNodo();
                break;
            case 45:
                pila.pop();
                der=((NoTerminal)pila.pop()).getNodo();
                pila.pop();
                opnot=((NoTerminal)pila.pop()).getNodo();
                break;
            case 46: 
                pila.pop();
                der=((NoTerminal)pila.pop()).getNodo();
                pila.pop();
                opmul=((NoTerminal)pila.pop()).getNodo();
                pila.pop();
                izq=((NoTerminal)pila.pop()).getNodo();
                break;
            case 47:
                pila.pop();
                der=((NoTerminal)pila.pop()).getNodo();
                pila.pop();
                opsuma=((NoTerminal)pila.pop()).getNodo();
                pila.pop();
                izq=((NoTerminal)pila.pop()).getNodo();
                break;
            case 48:
                pila.pop();
                der=((NoTerminal)pila.pop()).getNodo();
                pila.pop();
                oprela=((NoTerminal)pila.pop()).getNodo();
                pila.pop();
                izq=((NoTerminal)pila.pop()).getNodo();
                break;
            case 49:
                pila.pop();
                der=((NoTerminal)pila.pop()).getNodo();
                pila.pop();
                opigualdad=((NoTerminal)pila.pop()).getNodo();
                pila.pop();
                izq=((NoTerminal)pila.pop()).getNodo();
                break;
            case 50:
                pila.pop();
                der=((NoTerminal)pila.pop()).getNodo();
                pila.pop();
                opand=((NoTerminal)pila.pop()).getNodo();
                pila.pop();
                izq=((NoTerminal)pila.pop()).getNodo();
                break;
            case 51:
                pila.pop();
                der=((NoTerminal)pila.pop()).getNodo();
                pila.pop();
                opor=((NoTerminal)pila.pop()).getNodo();
                pila.pop();
                izq=((NoTerminal)pila.pop()).getNodo();
                break;
            case 52:
                pila.pop();
                termino=((NoTerminal)pila.pop()).getNodo();      
        }
    }
}

class Tipo extends Nodo{
    public Tipo(String simbolo){
        this.simbolo=simbolo;
        this.sig=null;
    }
}

class  Identificador extends Nodo{
    public Identificador(String simbolo){
        this.simbolo=simbolo;
        this.sig=null;
    }
}



 
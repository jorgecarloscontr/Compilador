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
    public void validaTipos(TablaSimbolos tbl){
        if(!hijos.isEmpty()){
            int n=hijos.size();
            while(n>0){
                hijos.get(n-1).validaTipos(tbl);
                n--;
            }
        }
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
        Identificador ident = new Identificador(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);
        this.addhijos(ident);
        pila.pop();
        Tipo tipo=new Tipo(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);
        this.addhijos(tipo);
        this.simbolo="DefVar";
    }
    @Override
    public void validaTipos(TablaSimbolos tbl){
        tbl.agrega(this);
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
            Identificador ident = new Identificador(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);
            this.addhijos(ident);
            pila.pop();
            Simbolo s=new Simbolo(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);  
            this.addhijos(s);
        }
    }
}
class Simbolo extends Nodo{
    int numLinea;
    public Simbolo(int numLinea,String simbolo,Nodo padre){
        this.numLinea=numLinea;
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
        Nodo identificador=new Identificador(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);
        this.addhijos(identificador);
        pila.pop();
        Nodo tipo=new Tipo(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);
        this.addhijos(tipo);
    }
    @Override
    public void validaTipos(TablaSimbolos tbl){
        tbl.agrega(this);
        if(!hijos.isEmpty()){
            int n=hijos.size();
            while(n>0){
                hijos.get(n-1).validaTipos(tbl);
                n--;
            }
        }
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
            Nodo identificador=new Identificador(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);
            this.addhijos(identificador);
            pila.pop();
            Nodo tipo=new Tipo(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);
            this.addhijos(tipo);
        }
    }
    @Override
    public void validaTipos(TablaSimbolos tbl){
        tbl.agrega(this);
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
            Nodo identificador=new Identificador(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);
            this.addhijos(identificador);
            pila.pop();
            Tipo tipo=new Tipo(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);
            this.addhijos(tipo);
            pila.pop();
            Simbolo s=new Simbolo(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);  
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
        this.simbolo="Sentencias";
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
    int tam;
    public Sentencia(int tam,Stack<ElementoPila> pila){
        this.simbolo="Sentencia";
        this.tam=tam;
        switch(tam){
            case 2:
                pila.pop();
                s=new Simbolo(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);  
                this.addhijos(s);
                pila.pop();
                llamadafunc=((NoTerminal)pila.pop()).getNodo();
                llamadafunc.setPadre(this);
                this.addhijos(llamadafunc);
                break;
            case 3:
                pila.pop();
                s=new Simbolo(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);  
                this.addhijos(s);
                pila.pop();
                valorregresa=(ValorRegresa)((NoTerminal)pila.pop()).getNodo();
                valorregresa.setPadre(this);
                this.addhijos(valorregresa);
                pila.pop();
                s=new Simbolo(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);  
                this.addhijos(s);
                break;
            case 4:
                pila.pop();
                s=new Simbolo(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);  
                this.addhijos(s);
                pila.pop();
                expresion=(Expresion)((NoTerminal)pila.pop()).getNodo();
                expresion.setPadre(this);
                this.addhijos(expresion);
                pila.pop();
                s=new Simbolo(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);  
                this.addhijos(s);
                pila.pop();
                identificador=new Identificador(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);
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
                s=new Simbolo(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);  
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
                s=new Simbolo(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);  
                this.addhijos(s);
                break;           
        }
    }
    @Override
    public void validaTipos(TablaSimbolos tbl){
        boolean bandera=false;
        int n=hijos.size();
        while(n>0){
            hijos.get(n-1).validaTipos(tbl);
            if(Expresion.class.isInstance(hijos.get(n-1))){
                validar(tbl);
                bandera=true;
            }
            n--;
        }
        if(!bandera){
            validar(tbl);
        }
    }
    public void validar(TablaSimbolos tbl){
        char tipo;
        switch(tam){
            case 4:
                Nodo p=padre;
                while(!p.getSimbolo().equals("DefFunc")){
                    p=p.getPadre();
                }
                if(Semantico.tablaSimbolos.varLocalDefinida(this.getultimo().getSimbolo(),p.getHijos().get(p.getHijos().size()-2).getSimbolo())){
                    tipo=tbl.tipoVariableLocal(this.getultimo().getSimbolo(),p.getHijos().get(p.getHijos().size()-2).getSimbolo());
                    if(tipo!=((Expresion)this.getHijos().get(1)).getTipo())
                        Semantico.listaErrores.add("Error en la linea "+((Identificador)this.getultimo()).numLinea+": Asignacion en la variable '"+this.getultimo().getSimbolo()+"' incorrecta");
                }else{
                    if(Semantico.tablaSimbolos.varGlobalDefinida(this.getultimo().getSimbolo())){
                        tipo=tbl.tipoVariableGlobal(this.getultimo().getSimbolo());
                        if(tipo!=((Expresion)this.getHijos().get(1)).getTipo())
                            Semantico.listaErrores.add("Error en la linea "+((Identificador)this.getultimo()).numLinea+": Asignacion en la variable '"+this.getultimo().getSimbolo()+"' incorrecta");
                    }else
                        Semantico.listaErrores.add("Error en la linea "+((Identificador)this.getultimo()).numLinea+": Variable '"+this.getultimo().getSimbolo()+"' no definida");
                }
                break;
            case 6:
                if(((Expresion)this.getHijos().get(2)).getTipo()!='b'){
                    Semantico.listaErrores.add("Error en la linea "+((Simbolo)this.getultimo()).numLinea+": Los parametros del if no son booleanos");
                }
                break;
            case 5:
                if(((Expresion)this.getHijos().get(1)).getTipo()!='b'){
                    Semantico.listaErrores.add("Error en la linea "+((Simbolo)this.getultimo()).numLinea+": Los parametros del while no son booleanos");
                }
                break;
            case 3://return
                p=padre;
                while(!p.getSimbolo().equals("DefFunc")){
                    p=p.getPadre();
                }
                if(Semantico.tablaSimbolos.varLocalDefinida(this.getHijos().get(1).getultimo().getultimo().getultimo().simbolo,p.getHijos().get(2).getSimbolo())
                   ||Semantico.tablaSimbolos.varGlobalDefinida(this.getHijos().get(1).getultimo().getultimo().getultimo().simbolo)){
                    if(Semantico.tablaSimbolos.TipoFuncionDefinida(p.getHijos().get(2).getSimbolo())!=Semantico.tablaSimbolos.tipoVariableGlobal(this.getHijos().get(1).getultimo().getultimo().getultimo().simbolo) &&
                            Semantico.tablaSimbolos.TipoFuncionDefinida(p.getHijos().get(2).getSimbolo())!=Semantico.tablaSimbolos.tipoVariableLocal(this.getHijos().get(1).getultimo().getultimo().getultimo().simbolo,p.getHijos().get(2).getSimbolo())){
                        Semantico.listaErrores.add("Error en la linea "+((Simbolo)this.getultimo()).numLinea+":  El valor de retorno no es el mismo que de la funcion");
                    }
                }
                break;
            case 2: //llamada a funcion falta declarar
                if(Semantico.tablaSimbolos.funcionDefinida(this.getultimo().getultimo().simbolo)){
                    Nodo aux=this.getultimo().getprimero();
                    int cont=0;
                    char c;
                    List<String> lista= Semantico.tablaSimbolos.parametrosFuncionDefinida(this.getultimo().getultimo().simbolo);
                    while(!aux.getHijos().isEmpty()){
                        if(cont<lista.size()){
                            aux.getHijos().get(1).validaTipos(Semantico.tablaSimbolos);
                            c=Semantico.tablaSimbolos.tipoVariableLocal(lista.get(cont),this.getultimo().getultimo().simbolo);
                            if(((Expresion)aux.getHijos().get(1)).getTipo()!=c){
                                Semantico.listaErrores.add("Error en la linea "+((Identificador)this.getultimo().getultimo()).numLinea+": Lammada a funcion '"+this.getultimo().getultimo().simbolo+ "' incorrecta, el parametro "+(cont+1)+" deve ser de tipo "+ c);
                            }
                        }
                        aux=aux.getprimero();
                        cont++;
                    }
                    if(cont!=lista.size()){
                        Semantico.listaErrores.add("Error en la linea "+((Identificador)this.getultimo().getultimo()).numLinea+": Lammada a funcion '"+this.getultimo().getultimo().simbolo+ "'  contiene una cantidad de parametros incorrectos");
                    }
                }else{
                    if(!this.getultimo().getultimo().simbolo.equals("print"))
                        Semantico.listaErrores.add("Error en la linea "+((Identificador)this.getultimo().getultimo()).numLinea+": Funcion "+this.getultimo().getultimo().simbolo+" no delarada");
                }
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
            Nodo s=new Simbolo(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);  
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
            Nodo s=new Simbolo(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);  
            this.addhijos(s);
        }
    }
}

class Entero extends Nodo{
    int numLinea;
    public Entero(int numLinea,String simbolo,Nodo padre){
        this.numLinea=numLinea;
        this.simbolo=simbolo;
        this.padre=padre;
    }
}
class Real extends Nodo{
    int numLinea;
    public Real(int numLinea,String simbolo,Nodo padre){
        this.numLinea=numLinea;
        this.simbolo=simbolo;
        this.padre=padre;
    }
}
class Cadena extends Nodo{
    int numLinea;
    public Cadena(int numLinea,String simbolo,Nodo padre){
        this.numLinea=numLinea;
        this.simbolo=simbolo;
        this.padre=padre;
    }
}

class Termino extends Nodo{
    int nregla;
    public Termino(int nregla,Stack<ElementoPila> pila){
        this.simbolo="Termino";
        this.nregla=nregla;
        switch(nregla){
            case 35:
                pila.pop();
                Nodo llamadafunc=((NoTerminal)pila.pop()).getNodo();
                llamadafunc.setPadre(this);
                this.addhijos(llamadafunc);
                break;
            case 36:
                pila.pop();
                Nodo identificador=new Identificador(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);
                this.addhijos(identificador);
                break;
            case 37:
                pila.pop();
                Nodo entero=new Entero(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);
                this.addhijos(entero);
                break;
            case 38: 
                pila.pop();
                Nodo real=new Real(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);
                this.addhijos(real);
                break;
            case 39:
                pila.pop();
                Nodo cadena=new Cadena(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);
                this.addhijos(cadena);
                break;
        }
    }
    char dimeTipo() {
        if (nregla==35){
            if(Semantico.tablaSimbolos.funcionDefinida(this.getprimero().getultimo().simbolo)){
                Nodo p=padre;
                while(!p.getSimbolo().equals("DefFunc")){
                    p=p.getPadre();
                }
                Nodo aux=this.getprimero().getprimero();
                int cont=0;
                char c;
                List<String> lista= Semantico.tablaSimbolos.parametrosFuncionDefinida(this.getprimero().getultimo().simbolo);
                while(!aux.getHijos().isEmpty()){
                    if(cont<lista.size()){
                        aux.getHijos().get(1).validaTipos(Semantico.tablaSimbolos);
                        c=Semantico.tablaSimbolos.tipoVariableLocal(lista.get(cont),this.getprimero().getultimo().simbolo);
                        if(((Expresion)aux.getHijos().get(1)).getTipo()!=c){
                            Semantico.listaErrores.add("Error en la linea "+((Identificador)this.getprimero().getultimo()).numLinea+": Lammada a funcion '"+this.getprimero().getultimo().simbolo+ "' incorrecta, el parametro "+(cont+1)+" deve ser de tipo "+ c);
                        }
                    }
                    aux=aux.getprimero();
                    cont++;
                }
                if(cont!=lista.size()){
                    Semantico.listaErrores.add("Error en la linea "+((Identificador)this.getprimero().getultimo()).numLinea+": Lammada a funcion '"+this.getprimero().getultimo().simbolo+ "'  contiene una cantidad de parametros incorrectos");
                }
                return Semantico.tablaSimbolos.TipoFuncionDefinida(this.getprimero().getultimo().simbolo);
            }else{
                Semantico.listaErrores.add("Error en la linea "+((Identificador)this.getprimero().getultimo()).numLinea+": Funcion "+this.getprimero().getultimo().simbolo+" no delarada");
                return 'n';
            }
        }
        if (nregla==36){
            Nodo p=padre;
            while(!p.getSimbolo().equals("DefFunc")){
                p=p.getPadre();
            }
            if(Semantico.tablaSimbolos.varLocalDefinida(this.getprimero().getSimbolo(),p.getHijos().get(p.getHijos().size()-2).getSimbolo())){
                return Semantico.tablaSimbolos.tipoVariableLocal(this.getprimero().getSimbolo(),p.getHijos().get(p.getHijos().size()-2).getSimbolo());
            }else{
                if(Semantico.tablaSimbolos.varGlobalDefinida(this.getprimero().getSimbolo())){
                    return Semantico.tablaSimbolos.tipoVariableGlobal(this.getprimero().getSimbolo()); 
                }else
                    Semantico.listaErrores.add("Error en la linea "+((Identificador)this.getprimero()).numLinea+": Variable '"+this.getprimero().simbolo+ "' no definida");
            }
            return 'n';
        }
        if (nregla==37) return 'i';
        if (nregla==38) return 'f';
        return 's';
    }
}
class LlamadaFunc extends Nodo{
    public LlamadaFunc(Stack<ElementoPila> pila){
        this.simbolo="LlamadaFunc";
        pila.pop();
        pila.pop();
        pila.pop();
        Nodo argumentos=((NoTerminal)pila.pop()).getNodo();
        argumentos.setPadre(this);
        this.addhijos(argumentos);
        pila.pop();
        pila.pop();
        pila.pop();
        Nodo identificador=new Identificador(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);
        this.addhijos(identificador);
    }
}
class SentenciaBloque extends Nodo{
    public SentenciaBloque(Stack<ElementoPila> pila){
        this.simbolo="SentenciaBloque";
        pila.pop();
        Nodo sig=((NoTerminal)pila.pop()).getNodo();
        sig.setPadre(this);
        this.addhijos(sig);
    }
}

class Expresion extends Nodo{
    Nodo izq, der;
    Nodo s;
    int nregla,numLineas;
    char tipo;
    public Expresion(int nregla,Stack<ElementoPila> pila){
        this.simbolo="Expresion";
        this.nregla=nregla;
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
                s=new Simbolo(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);
                this.addhijos(s);
                break;
            case 45:
                pila.pop();
                der=((NoTerminal)pila.pop()).getNodo();
                der.setPadre(this);
                this.addhijos(der);
                pila.pop();
                s=new Simbolo(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);
                this.addhijos(s);
                break;
            case 46: 
                pila.pop();
                der=((NoTerminal)pila.pop()).getNodo();
                der.setPadre(this);
                this.addhijos(der);
                pila.pop();
                s=new Simbolo(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);
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
                s=new Simbolo(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);
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
                s=new Simbolo(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);
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
                s=new Simbolo(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);
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
                s=new Simbolo(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);
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
                s=new Simbolo(((Terminal)pila.peek()).getLinea(),((Terminal)pila.pop()).getsimbolo(),this);
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
    @Override
    public void validaTipos(TablaSimbolos tbl){
        int n=hijos.size();
        while(n>0){
            hijos.get(n-1).validaTipos(tbl);
            n--;
        }
        validar(tbl);
    }
    public void validar(TablaSimbolos tbl){
        switch(nregla){
            case 43:
                tipo=((Expresion)this.getHijos().get(1)).getTipo();
                break;
            case 44:
                tipo=((Expresion)this.getultimo()).getTipo();
                break;
            case 45:
                tipo=((Expresion)this.getultimo()).getTipo();
                break;
            case 46:
                if(((Expresion)this.getprimero()).getTipo()==((Expresion)this.getultimo()).getTipo()){
                    tipo=((Expresion)this.getprimero()).getTipo();
                }else{
                    Semantico.listaErrores.add("Error en la linea "+((Simbolo)this.getHijos().get(1)).numLinea+": Expresion no admitida: "+((Expresion)this.getprimero()).getTipo()+" opmult "+((Expresion)this.getultimo()).getTipo());
                    tipo='n';
                }
                break;
            case 47:
                if(((Expresion)this.getprimero()).getTipo()==((Expresion)this.getultimo()).getTipo()){
                    tipo=((Expresion)this.getprimero()).getTipo();
                }else{
                    Semantico.listaErrores.add("Error en la linea "+((Simbolo)this.getHijos().get(1)).numLinea+": Expresion no admitida: "+((Expresion)this.getprimero()).getTipo()+" opsum "+((Expresion)this.getultimo()).getTipo());
                    tipo='n';
                }
                break;
            case 48:
                if(((Expresion)this.getprimero()).getTipo()==((Expresion)this.getultimo()).getTipo()){
                    tipo='b';//boolean
                }else{
                    Semantico.listaErrores.add("Error en la linea "+((Simbolo)this.getHijos().get(1)).numLinea+": Expresion no admitida: "+((Expresion)this.getprimero()).getTipo()+" oprelac "+((Expresion)this.getultimo()).getTipo());
                    tipo='n';
                }
                break;
            case 49:
                if(((Expresion)this.getprimero()).getTipo()==((Expresion)this.getultimo()).getTipo()){
                    tipo='b';//boolean
                }else{
                    Semantico.listaErrores.add("Error en la linea "+((Simbolo)this.getHijos().get(1)).numLinea+": Expresion no admitida: "+((Expresion)this.getprimero()).getTipo()+" == "+((Expresion)this.getultimo()).getTipo());
                    tipo='n';
                }
                break;
            case 50:
                if(((Expresion)this.getprimero()).getTipo()==((Expresion)this.getultimo()).getTipo()){
                    tipo=((Expresion)this.getprimero()).getTipo();
                }else{
                    Semantico.listaErrores.add("Error en la linea "+((Simbolo)this.getHijos().get(1)).numLinea+": Expresion no admitida: "+((Expresion)this.getprimero()).getTipo()+" and "+((Expresion)this.getultimo()).getTipo());
                    tipo='n';
                }
                break;
            case 51:
                if(((Expresion)this.getprimero()).getTipo()==((Expresion)this.getultimo()).getTipo()){
                    tipo=((Expresion)this.getprimero()).getTipo();
                }else{
                    Semantico.listaErrores.add("Error en la linea "+((Simbolo)this.getHijos().get(1)).numLinea+": Expresion no admitida: "+((Expresion)this.getprimero()).getTipo()+" or "+((Expresion)this.getultimo()).getTipo());
                    tipo='n';
                }
                break;
            case 52:
                tipo=((Termino)this.getprimero()).dimeTipo();
                break;

        }
    }

    public char getTipo() {
        return tipo;
    }
    
}

class Tipo extends Nodo{
    int numLinea;
    public Tipo(int numLinea,String simbolo,Nodo padre){
        this.numLinea=numLinea;
        this.simbolo=simbolo;
        this.padre=padre;
    }
    char dimeTipo() {
        if ( simbolo.equals("int")) return 'i';
        if ( simbolo.equals("float")) return 'f';
        if ( simbolo.equals("string")) return 's';
        if ( simbolo.equals("void")) return 'v';
        return 'N';
    }
}

class  Identificador extends Nodo{
    int numLinea;
    public Identificador(int numLinea,String simbolo,Nodo padre){
        this.numLinea=numLinea;
        this.simbolo=simbolo;
        this.setPadre(padre);
    }
}



 
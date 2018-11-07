/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author jorge
 */
public class TablaSimbolos {
    private List<ElementoTabla> listaTabla;
    public TablaSimbolos(){
        listaTabla=new ArrayList<>();
    }
    public void inicializar(){
        listaTabla.clear();
    }

    public List<ElementoTabla> getListaTabla() {
        return listaTabla;
    }
    
    public void agregar(ElementoTabla e){
        listaTabla.add(e);
    }
    public boolean varGlobalDefinida(String simbolo){
        Iterator it = listaTabla.iterator();
        boolean bandera=false;
        ElementoTabla e;
        while(it.hasNext() && !bandera){
            e=(ElementoTabla) it.next();
            if(e.isVariable() && e.getSimbolo().equals(simbolo) && !e.isVarLocal()){
                bandera=true;
            }
        }
        return bandera;
    };
    public char tipoVariableGlobal(String variable){
        Iterator it = listaTabla.iterator();
        boolean bandera=false;
        ElementoTabla e;
        while(it.hasNext() && !bandera){
            e=(ElementoTabla) it.next();
            if(e.isVariable() && !e.isVarLocal()){
                if(e.getSimbolo().equals(variable))
                    return e.getTipo();
            }
        }
        return 'n';
    }
    public char tipoVariableLocal(String variable, String funcion){
        Iterator it = listaTabla.iterator();
        boolean bandera=false;
        ElementoTabla e;
        while(it.hasNext() && !bandera){
            e=(ElementoTabla) it.next();
            if(e.isVariable() && e.isVarLocal()){
                if(((Variable)e).getAmbito().equals(funcion) && e.getSimbolo().equals(variable))
                    return e.getTipo();
            }
        }
        return 'n';
    }
    public boolean funcionDefinida(String simbolo){
        Iterator it = listaTabla.iterator();
        boolean bandera=false;
        ElementoTabla e;
        while(it.hasNext() && !bandera){
            e=(ElementoTabla) it.next();
            if(e.isFucnion() && e.getSimbolo().equals(simbolo)){
                bandera=true;
            }
        }
        return bandera;  
    }
    public char TipoFuncionDefinida(String simbolo){
        Iterator it = listaTabla.iterator();
        boolean bandera=false;
        ElementoTabla e;
        while(it.hasNext() && !bandera){
            e=(ElementoTabla) it.next();
            if(e.isFucnion() && e.getSimbolo().equals(simbolo)){
                return e.getTipo();
            }
        }
        return 'n';  
    }
    public List<String> parametrosFuncionDefinida(String simbolo){
        Iterator it = listaTabla.iterator();
        boolean bandera=false;
        ElementoTabla e;
        while(it.hasNext() && !bandera){
            e=(ElementoTabla) it.next();
            if(e.isFucnion() && e.getSimbolo().equals(simbolo)){
                return ((Funcion)e).getParametros();
            }
        }
        return null;  
    }
    public boolean varLocalDefinida(String variable, String funcion){
        Iterator it = listaTabla.iterator();
        boolean bandera=false;
        ElementoTabla e;
        while(it.hasNext() && !bandera){
            e=(ElementoTabla) it.next();
            if(e.isVariable() && e.isVarLocal()){
                if(((Variable)e).getAmbito().equals(funcion) && e.getSimbolo().equals(variable))
                    bandera=true;
            }
        }
        return bandera;
    }
    public void agrega(DefVar defVar){
        if(!defVar.getHijos().isEmpty()){
            char tipo=((Tipo)defVar.getHijos().get(2)).dimeTipo();
            String simbolo;
            Nodo tmp;
            if(defVar.getPadre().getSimbolo().equals("Definicion")){//si es global
                tmp=(Nodo)defVar;
                while(!tmp.getHijos().isEmpty()){
                    simbolo=tmp.getHijos().get(1).getSimbolo();
                    if(varGlobalDefinida(simbolo)){
                        Semantico.listaErrores.add("Variable "+simbolo+" ya habia sido definida");
                    }else{
                        ElementoTabla elemento=new Variable(tipo, simbolo, "");
                        agregar(elemento);
                    }
                    tmp=tmp.getprimero();
                }
            }else{//si es varLocal
                Nodo p=defVar.getPadre();
                while(!p.getSimbolo().equals("DefFunc")){
                    p=p.getPadre();
                }
                tmp=(Nodo)defVar;
                while(!tmp.getHijos().isEmpty()){
                    simbolo=tmp.getHijos().get(1).getSimbolo();
                    if(varLocalDefinida(simbolo,p.getHijos().get(p.getHijos().size()-2).getSimbolo()))
                        Semantico.listaErrores.add("Variable "+simbolo+" ya habia sido definida");
                    else{
                        ElementoTabla elemento=new Variable(tipo,simbolo,p.getHijos().get(p.getHijos().size()-2).getSimbolo());
                        agregar(elemento);
                    }
                    tmp=tmp.getprimero();
                }
            }
        }
    }
    void agrega(Parametros parametros){
        String ambito=parametros.getPadre().getHijos().get(2).getSimbolo();
        char tipo;
        String simbolo;
        Nodo tmp=(Nodo)parametros;
        while(!tmp.getHijos().isEmpty()){
            simbolo=tmp.getHijos().get(1).getSimbolo();
            tipo=((Tipo)tmp.getHijos().get(2)).dimeTipo();
            if(varLocalDefinida(simbolo,ambito))
                Semantico.listaErrores.add("Variable "+simbolo+" ya habia sido definida");
            else{
                ElementoTabla elemento=new Variable(tipo,simbolo,ambito);
                agregar(elemento);
            }
            tmp=tmp.getprimero();
        }
        
    }
    public void agrega( DefFunc defFunc){
        String sparametros="";
        Nodo parametros=(defFunc.getHijos().get(1));
        String simbolo=defFunc.getHijos().get(2).getSimbolo();
        char tipo=((Tipo)defFunc.getultimo()).dimeTipo();

        while(!parametros.getHijos().isEmpty()){
            sparametros=sparametros+parametros.getHijos().get(1).getSimbolo()+" ";
            parametros=parametros.getHijos().get(0);
        }
        if(funcionDefinida(simbolo)){
            Semantico.listaErrores.add("La funcion "+simbolo+" ya habia sido definida");
        }else{
            ElementoTabla elemento=new Funcion(tipo,simbolo,sparametros);
            agregar(elemento);
        }
    }    
}

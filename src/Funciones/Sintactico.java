/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import Pila.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
    private static final int Ancho=130;
    private static final int Alto=70;
    private static int max=0;
    private Nodo raiz;
    private JScrollPane jScrollPane2;
    private JPanel panel;

    public Nodo getRaiz() {
        return raiz;
    }
    
    public Sintactico(String cadena) {
        pila = new Stack<>();
        aceptacion = false;
        cancelar = false;
        lexico = new Lexico(cadena);
    }
    public Sintactico(JScrollPane jScrollPane2) {
        pila = new Stack<>();
        aceptacion = false;
        cancelar = false;
        raiz=null;
        this.jScrollPane2=jScrollPane2;
        panel=new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D)g;
                g2.setFont(new Font("Arial",Font.TRUETYPE_FONT,18));
                if(raiz!=null){
                    elementos(g,raiz,0);
                }
            }
        };
        panel.setBackground(Color.WHITE);
        jScrollPane2.setViewportView(panel);
    }
    public void inicializar(String cadena){
        pila.removeAllElements();
        aceptacion=false;
        max=0;
        raiz=null;
        lexico=new Lexico(cadena);
        cancelar=false;
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
                        pila.push(new NoTerminal(columna, strReglas[0],null));
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
        Nodo nd=null;
        muestra();
        raiz=null;
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
                        aux = Math.abs(accion + 2)+1;
                        if(aux==1){
                            nd=new Programa(pila);
                            raiz=nd;
                        }else if(aux==2 || aux==3){
                            nd= new Definiciones(lonReglas[aux-1],pila);
                        }else if(aux==4 || aux==5){
                            nd= new Definicion(pila);
                        }else if(aux==6){
                            nd= new DefVar(pila);
                        }else if(aux==7 || aux==8){
                            nd= new ListaVar(lonReglas[aux-1],pila);
                        }else if(aux==9){
                            nd= new DefFunc(pila);
                        }else if(aux==10 || aux==11){
                            nd= new Parametros(lonReglas[aux-1],pila);
                        }else if(aux==12 || aux==13){
                            nd= new ListaParam(lonReglas[aux-1],pila);
                        }else if(aux==14){
                            nd= new BloqFunc(pila);
                        }else if(aux==15 || aux==16){
                            nd= new DefLocales(lonReglas[aux-1],pila);
                        }else if(aux==17 || aux==18){
                            nd= new DefLocal(pila);
                        }else if(aux==19 || aux==20){
                            nd= new Sentencias(lonReglas[aux-1],pila);
                        }else if(aux==21 || aux==22 || aux==23 || aux==24 || aux==25){
                            nd= new Sentencia(lonReglas[aux-1],pila);
                        }else if(aux==26 || aux==27){
                            nd= new Otro(lonReglas[aux-1],pila);
                        }else if(aux==28){
                            nd= new Bloque(pila);
                        }else if(aux==29 || aux==30){
                            nd= new ValorRegresa(lonReglas[aux-1],pila);
                        }else if(aux==31 || aux==32){
                            nd= new Argumentos(lonReglas[aux-1],pila);
                        }else if(aux==33 || aux==34){
                            nd= new ListaArgumentos(lonReglas[aux-1],pila);
                        }else if(aux==35 || aux==36 || aux==37 || aux==38 || aux==39){
                            nd= new Termino(aux,pila);
                        }else if(aux==40){
                            nd= new LlamadaFunc(pila);
                        }else if(aux==41 || aux==42){
                            nd= new SentenciaBloque(pila);
                        }else if(aux==43||aux==44||aux==45||aux==46||aux==47||aux==48||aux==49||aux==50||aux==51||aux==52){
                            nd= new Expresion(aux,pila);
                        }
                        fila = pila.peek().getid();
                        columna = idReglas[Math.abs(accion + 2)]; //el no terminal que representa a E
                        //transicion
                        pila.push(new NoTerminal(columna, strReglas[Math.abs(accion + 2)],nd));
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
        if(raiz!=null){
            ncapa(0,raiz);
            System.out.println(max);
            int cont=0;
            generar_arbol(raiz);
            while(cont<max){
                List<Nodo> capa1=new ArrayList<>();
                capan(0, cont+1,raiz, capa1);
                capa1.forEach((iter) -> {
                    System.out.print(iter.getX()+"  ");
                });
                System.out.println();
                cont++;
            }    
            pintar(raiz);
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
    //************************************************Generar y ajustar el arbol para graficas
    void  ncapa(int n,Nodo aux){
        n+=1;
        for(Nodo i:aux.getHijos()){
            ncapa(n,i);
        }
        if(max<n){
            max=n;
        }
    }    
    void capan(int n,int s,Nodo aux,List<Nodo> lista){
        n+=1;
        for(Nodo i:aux.getHijos()){
            capan(n,s,i,lista);
        }
        if(n==s){
            lista.add(aux);
        }
    }
    void reajustar(int capa,boolean bandera){
        List<Nodo> lista=new ArrayList<>();
        capan(0, capa,raiz, lista);
        Nodo padre;
        int i=0,e;
        double tmp,tmp1,media;
        while(i<lista.size()){
            if(!lista.get(i).getHijos().isEmpty()){
                if(lista.get(i).getprimero().isVisit()){
                    tmp=lista.get(i).getX();
                    lista.get(i).setX((lista.get(i).getultimo().getX()-lista.get(i).getprimero().getX())/2+lista.get(i).getprimero().getX());
                    if(lista.get(i).getX()!=tmp){
                        padre=lista.get(i).getPadre();
                        if(i-1>-1){
                            if(lista.get(i-1).getPadre().equals(padre) && lista.get(i-1).getHijos().isEmpty()){
                                media=tmp-lista.get(i-1).getX();
                                lista.get(i-1).setX(lista.get(i).getX()-media);
                                lista.get(i-1).setDistancia(lista.get(i).getX()-media);
                            }
                        }
                        e=i+1;
                        tmp=lista.get(i).getDistancia();
                        while(e<lista.size()){
                            media=(lista.get(e).getX()-tmp);
                            tmp1=lista.get(e).getX();
                            lista.get(e).setX(media+lista.get(i).getultimo().getX());
                            media=lista.get(e).getDistancia()-tmp1;
                            lista.get(e).setDistancia(media+lista.get(e).getX());
                            e++;
                        }
                    }
                    lista.get(i).setDistancia(lista.get(i).getultimo().getX());
                }
            }
            i++;
        }
    }
        
   void generar_arbol(Nodo raiz){
        raiz.setDistancia(0);
        raiz.setX(Ancho);
        raiz.setY(0);
        raiz.setVisit(true);
        Nodo p_actual;
        Graphics g=panel.getGraphics();
        Graphics2D g2 = (Graphics2D)g;
        g2.setFont(new Font("Arial",Font.TRUETYPE_FONT,18));
        Rectangle2D rectangulo;
        int i=1,iter;
        double x,tmpx,tamletra=0;
        while(i<max){//while que itera para todas las capas
            List<Nodo> lista=new ArrayList<>();
            capan(0, i+1,raiz, lista);
            p_actual=lista.get(0).getPadre();//Padre actual
            x=p_actual.getX()-Ancho;//posicion x del padre actual
            tmpx=0;
            for(Nodo t:lista){
                t.setVisit(true);
                if(!t.getPadre().equals(p_actual)){
                    p_actual=t.getPadre();
                    if(x+tamletra>p_actual.getX()-Ancho/2){
                        tmpx=(x+tamletra)-(p_actual.getX()-Ancho);
                    }
                    x=p_actual.getX()-Ancho;
                }
                rectangulo= g.getFontMetrics(g.getFont()).getStringBounds(t.getSimbolo(), g);
                tamletra=rectangulo.getWidth()/2;
                t.setY(t.getPadre().getY()+Alto);
                if(tamletra<=Ancho/2){
                    t.setX(x+Ancho+tmpx);
                    t.setDistancia(t.getX());
                    tmpx=0;
                }else{

                    if(tmpx==0){
                        t.setX(Ancho+x+tamletra);
                        System.out.println("cuando x="+x+" tamletras "+tamletra+" xn "+t.getX());
                    }else
                        t.setX(tmpx+x+tamletra+(Ancho/2));
                    tmpx=tamletra;
                    t.setDistancia(t.getX());
                }
                x=t.getX();
                if(p_actual.getultimo().equals(t)){//ver si es el ultimo hijo
                    iter=i;
                    //algoritmo que reajusta las capas anteriores
                    while(iter>0){
                        if(iter!=i)
                            reajustar(iter,false);
                        else
                            reajustar(iter,true);
                        iter--;
                    }
                }
            }
            i++;
        }
    }
    public void pintar(Nodo aux){
        panel.paintComponents(panel.getGraphics());
        panel.repaint();
        panel.setPreferredSize(new Dimension(maximox()+Ancho,maximoy()+Alto));
    }
    public int maximox(){
        ncapa(0,raiz);
        int cont=0;
        int maximo=0;
        while(cont<max){
            List<Nodo> capa1=new ArrayList<>();
            capan(0, cont+1,raiz, capa1);
            for(Nodo iter:capa1){
                if(maximo<iter.getX()){
                    maximo=(int) iter.getX();
                }
            }
            System.out.println();
            cont++;
        } 
        return maximo;
    }
    public int maximoy(){
        ncapa(0,raiz);
        int cont=0;
        int maximo=0;
        while(cont<max){
            List<Nodo> capa1=new ArrayList<>();
            capan(0, cont+1,raiz, capa1);
            for(Nodo iter:capa1){
                if(maximo<iter.getY()){
                    maximo=(int) iter.getY();
                }
            }
            System.out.println();
            cont++;
        } 
        return maximo;
    }
    public void elementos(Graphics g,Nodo aux,int n){
        n+=1;
        Graphics2D g2 = (Graphics2D)g;
        double x;
        Rectangle2D rectangulo=g.getFontMetrics(g.getFont()).getStringBounds(aux.getSimbolo(), g);
        x=aux.getX()-rectangulo.getWidth()/2;
        rectangulo.setRect(x, aux.getY(),rectangulo.getWidth(),rectangulo.getHeight() );
        g2.setColor(Color.red);
        g2.draw(rectangulo);
        g2.setColor(Color.black);
        g2.drawString(aux.getSimbolo(), (int)x, (int)(aux.getY()+18));
        if(aux.getPadre()!=null){
            g2.drawLine((int)aux.getPadre().getX(),(int)aux.getPadre().getY()+18, (int)aux.getX(), (int)aux.getY());
        }
        for(Nodo i:aux.getHijos()){
            elementos(g,i,n);
        }
    }
    
}
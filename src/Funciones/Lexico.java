package Funciones;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jorge
 */
public final class Lexico {
    private static final int identificador = 0;
    private static final int entero = 1;
    private static final int real = 2;
    private static final int cadena=3;
    private static final int tipo = 4;
    private static final int opsuma =5;
    private static final int opmul = 6;
    private static final int oprelac =7;
    private static final int opor = 8;
    private static final int opand = 9;
    private static final int opnot = 10;
    private static final int opigualdad = 11;
    private static final int punto_coma = 12;
    private static final int coma = 13;
    private static final int in_parentesis = 14;
    private static final int fn_parentesis = 15;
    private static final int in_llaves = 16;
    private static final int fn_llaves = 17;
    private static final int igualdad =18;
    private static final int opif = 19;
    private static final int opwhile = 20;
    private static final int opreturn = 21;
    private static final int opelse =22;
    private static final int opsimbolo = 23;
    private static final int error = -1;
    private String entrada;
    private int estado;
    private int ps;
    private boolean bandera;
    private int cont;
    private String tmp;
    private int aux;
    private char caracter;
    private String simbolo;   

    
    public Lexico(String cadena){
        entrada=cadena;
        estado=0;
        bandera=true; 
        cont=1;
        tmp="";
        ps=0;
        aux=0;
        if(!entrada.isEmpty())
            caracter=entrada.charAt(0);
    }
    public char sig_caracter(String cadena){
            cont++;
            return cadena.charAt(cont-1);          
    }   
    
    public int automata(){
        estado=0;
        aux=0;
        while(bandera){
            switch(estado){  
                case 0:
                    if(caracter>47 && caracter<58){//si es un numero
                        estado=1;
                    }else if(caracter==105){//i
                        estado=23;
                    }else if(caracter==102){//f
                        estado=27;
                    }else if(caracter==118){//v
                        estado=30;
                    }else if(caracter==119){//w
                        estado=33;
                    }else if(caracter==114){//r
                        estado=38;
                    }else if(caracter==101){//e
                        estado=43;
                    }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || caracter==95){//letra
                        estado=4;
                    }else if(caracter==43 || caracter==45){//suma
                        aux=5;
                        ps=1;
                        estado=-1;
                    }else if(caracter==42 || caracter==47){//multiplicacion
                        aux=6;
                        ps=1;
                        estado=-1;
                    }else if(caracter==61){//=
                        estado=7;
                    }else if(caracter==33){//!
                        estado=9;
                    }else if(caracter==60 || caracter==62){//relacional < >
                        estado=10;
                    }else if(caracter==38){//&
                        estado=12;
                    }else if(caracter==124){//|
                        estado=14;
                    }else if(caracter==40){//parentesis ( ------------------------------------------------------------------
                        aux=16;
                        ps=1;
                        estado=-1;
                    }else if(caracter==41){//)
                        aux=17;
                        ps=1;
                        estado=-1;
                    }
                    else if(caracter==123){//{
                        aux=18;
                        ps=1;
                        estado=-1;
                    }
                    else if(caracter==125){//}
                        aux=19;
                        ps=1;
                        estado=-1;
                    }
                    else if(caracter==59){//;
                        aux=20;
                        ps=1;
                        estado=-1;
                    }
                    else if(caracter==36){//$
                        aux=21;
                        ps=1;
                        estado=-1;
                    }else if(caracter==44){//,
                        aux=22;
                        ps=1;
                        estado=-1;
                    }else if(caracter==34){//"
                        estado=50;
                    }else {
                        aux=0;
                        ps=0;
                        if(caracter==32 || caracter==13 ||caracter==10 || caracter==9){
                            estado=0;
                        }else{ 
                            estado=-1;}}
                    break;
                case 1://*********************************************entero
                    if(caracter>47 && caracter<58){//numero
                        estado=1;
                    }else if(caracter==46){//putno decimal 
                        estado=2;
                    }else{
                        aux=estado;
                        estado=-1;}
                    break;
                case 2: /// q2 -> . q3
                    if(caracter>47 && caracter<58){
                        estado=3;
                    }else{
                        aux=1;
                        cont--;
                        estado=-1;}
                    break;
                case 3: //****************************************validacion real
                    if(caracter>47 && caracter<58){
                        estado=3;
                    }else{
                        aux=estado;
                        estado=-1;}
                    break;
                case 4: //****************************************validacion identificador
                    if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58) || caracter==95){
                        estado=4;
                    }else{
                        aux=estado;
                        estado=-1;
                    }
                    break;  
                case 7://validacion =
                    if(caracter==61){
                        aux=8;
                        ps=1;
                        estado=-1;
                    }else{
                        aux=estado;
                        estado=-1;
                    }
                    break;
                case 9: //validacion !
                    if(caracter==61){
                        aux=8;
                        ps=1;
                        estado=-1;
                    }else{
                       aux=estado;
                        estado=-1;
                    }
                    break;
                case 10: //validacion <>
                    if(caracter==61){
                        aux=11;
                        ps=1;
                        estado=-1;
                    }else{
                        aux=estado;
                        estado=-1;
                    }
                    break;   
                case 12: 
                    if(caracter==38){
                        aux=13;// &&
                        ps=1;
                        estado=-1;
                    }else{
                        aux=estado;
                        estado=-1;
                    }
                    break;  
                case 14:
                    if(caracter==124){//|
                        aux=15;
                        ps=1;
                        estado=-1;
                    }else{
                        aux=estado;
                        estado=-1;
                    }
                    break;
                case 23:
                    if(caracter==110){//in
                        estado=24;
                    }else{
                        if(caracter==102){//if
                            aux=23;
                            estado=37;
                        }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
                            estado=4;
                        }else{
                            aux=4;
                            estado=-1;}
                    }
                    break;
                case 24:
                    if(caracter==116){//t
                        estado=25;
                    }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
                        estado=4;
                    }else{
                        aux=4;
                        estado=-1;}
                    break;
                case 25://estado para validar tipo
                    if(caracter==32){
                        aux=26;
                        estado=-1;
                    }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
                        estado=4;
                    }else{
                        aux=4;
                        estado=-1;}
                    break;
                case 27:
                    if(caracter==108){//l
                        estado=28;
                    }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
                        estado=4;
                    }else{
                        aux=4;
                        estado=-1;}
                    break;
                case 28:
                    if(caracter==111){//o
                        estado=29;
                    }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
                        estado=4;
                    }else{
                        aux=4;
                        estado=-1;}
                    break;
                case 29:
                    if(caracter==97){//a
                        estado=24;
                    }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
                        estado=4;
                    }else{
                        aux=4;
                        estado=-1;}
                    break;
                case 30:
                    if(caracter==111){//o
                        estado=31;
                    }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
                        estado=4;
                    }else{
                        aux=4;
                        estado=-1;}
                    break;    
                case 31:
                    if(caracter==105){//i
                        estado=32;
                    }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
                        estado=4;
                    }else{
                        aux=4;
                        estado=-1;}
                    break; 
                case 32:
                    if(caracter==100){//d
                        estado=25;
                    }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
                        estado=4;
                    }else{
                        aux=4;
                        estado=-1;}
                    break;  
                case 33:
                    if(caracter==104){//h
                        estado=34;
                    }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
                        estado=4;
                    }else{
                        aux=4;
                        estado=-1;}
                    break;
                case 34:
                    if(caracter==105){//i
                        estado=35;
                    }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
                        estado=4;
                    }else{
                        aux=4;
                        estado=-1;}
                    break;
                case 35:
                    if(caracter==108){//l
                        estado=36;
                    }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
                        estado=4;
                    }else{
                        aux=4;
                        estado=-1;}
                    break;
                case 36:
                    if(caracter==101){//e////////estado while
                        aux=estado;
                        estado=37;
                    }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
                        estado=4;
                    }else{
                        aux=4;
                        estado=-1;}
                    break;
                case 37://validar distinto tipos como while return else
                    if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
                        estado=4;
                    }else{
                        if(aux==36){
                            aux=46;//-------------while
                        }else if(aux==42){
                            aux=47;//-------------return
                        }else if(aux==45) {
                            aux=48;//-------------else
                        }else{
                            aux=49;//-------------if
                        }
                        estado=-1;   
                    }
                    break;
                case 38:
                    if(caracter==101){//e
                        estado=39;
                    }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
                        estado=4;
                    }else{
                        aux=4;
                        estado=-1;}
                    break;
                case 39:
                    if(caracter==116){//t
                        estado=40;
                    }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
                        estado=4;
                    }else{
                        aux=4;
                        estado=-1;}
                    break;
                case 40:
                    if(caracter==117){//u
                        estado=41;
                    }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
                        estado=4;
                    }else{
                        aux=4;
                        estado=-1;}
                    break;
                case 41:
                    if(caracter==114){//r
                        estado=42;
                    }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
                        estado=4;
                    }else{
                        aux=4;
                        estado=-1;}
                    break;
                case 42:////////estado return
                    if(caracter==110){//n
                        aux=42;
                        estado=37;
                    }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
                        estado=4;
                    }else{
                        aux=4;
                        estado=-1;}
                    break;
                case 43:
                    if(caracter==108){//l
                        estado=44;
                    }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
                        estado=4;
                    }else{
                        aux=4;
                        estado=-1;}
                    break;
                case 44:
                    if(caracter==115){//s
                        estado=45;
                    }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
                        estado=4;
                    }else{
                        aux=4;
                        estado=-1;}
                    break;
                case 45:////////estado else
                    if(caracter==101){//e
                        aux=estado;
                        estado=37;
                    }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
                        estado=4;
                    }else{
                        aux=4;
                        estado=-1;}
                    break;
                 case 50:
                    if(caracter==34){//""
                        aux=51;
                        ps=1;
                        estado=-1;
                    }else{
                        estado=50;}
                    break;
            }
            if(estado==-1){//si el estado es de error
                if(aux!=0){//recorta la cadena a uno anterior del actual
                    System.out.println(recortar(entrada,0)+"\t\t"+tipoc(aux));
                    simbolo=recortar(entrada,0);
                    entrada=recortar(entrada,2);
                }else{//recorta la cadena en donde se quedo
                    System.out.println(recortar(entrada,1)+"\t\t"+tipoc(estado));
                    simbolo=recortar(entrada,1);
                    entrada=recortar(entrada,3);
                }
                cont=0;
                if(fin()){
                    caracter=sig_caracter(entrada);
                }else{
                    if(aux!=21){
                        entrada="$";
                        caracter=sig_caracter(entrada);

                    }
                }
                inicializar();
                bandera=false;
            }else{
                if(estado==0 ){
                    entrada=recortar(entrada,3);
                    cont=0;
                }
                if(cont<entrada.length()){//sigue con la lectura
                    caracter=sig_caracter(entrada);
                }else{//cuando se termina cadena
                    System.out.println("niinininininini");
                    if(estado!=0){
                        System.out.println(recortar(entrada,1)+"\t\t"+tipoc(estado));
                        simbolo=recortar(entrada,1);
                        bandera=false;

                    }
                    entrada="$";
                    cont=0;
                    caracter=sig_caracter(entrada);
                    inicializar();
                }
            }
        }
        bandera=true;
        return estado=tipoc1(estado);
    }

    public String getSimbolo() {
        return simbolo;
    }
    
    public void inicializar(){
        aux=0;
        ps=0;
    }
    
    public int getestado(){
        return estado;
    }
    public boolean fin(){
        return entrada.length()>0;
    }
    
    public String recortar(String cadena, int op){
        if(op==0 && ps==0){
            return cadena.substring(0, cont-1);
        }else if(op==1 || (op==0 && ps==1)){
            return cadena.substring(0, cont);
        }else if(op==2 && ps==0){
            return cadena.substring(cont-1, cadena.length());
        }
        return cadena.substring(cont, cadena.length());
    }
    public String tipoc(int estado){
        String aux="";
        switch(estado){
            case 4:
                aux="identificador";
                break;
            case 1://entero
                aux="entero";
                break;
            case 3:
                aux="real";
                break;
            case 51:
                aux="cadena";
                break;
            case 26:
                aux="tipo";
                break;
            case 25:
                aux="tipo";
                break;
            case 37:
                if(this.aux==36){
                    aux="opwhile";//while
                }else if(this.aux==42){
                    aux="opreturn";//-------------return
                }else if(this.aux==45) {
                    aux="opelse";//-------------else
                }else{
                    aux="opif";//-------------if
                }
                break;
            case 5:
                aux="opsuma";
                break;
            case 6:
                aux="opmul";
                break;
            case 10:
                aux="oprelac";
                break;
            case 15:
                aux="opor";
                break;
            case 13:
                aux="opand";
                break;
            case 9:
                aux="opnot";
                break;
            case 8:
                aux="opigualdad";
                break;
            case 20:
                aux="punto_coma";
                break;
            case 22:
                aux="coma";
                break;
            case 16:
                aux="in_parentesis";
                break;
            case 17:
                aux="fn_parentesis";
                break;
            case 18:
                aux="in_llaves";
                break;
            case 19:
                aux="fn_llaves";
                break;
            case 7:
                aux="igualdad";
                break;
            case 49:
                aux="opif";
                break;
            case 46:
                aux="opwhile";
                break;
            case 47:
                aux="opreturn";
                break;
            case 48:
                aux="opelse";
                break;
            case 21:
                aux="opsimbolo";
                break;
            case 11:
                aux="oprelac";
                break;
            default:
                if((estado>26 && estado<37) || estado==23 || estado==24 || estado==23 || (estado>37 && estado<46) ) {
                    aux="identificador";
                } else {
                    aux="error";}
                break;
        }
        this.estado=estado;
    return aux;
    }   
    
    
    public int tipoc1(int estado){
        int aux;
        switch(estado){
            case 4:
                aux=identificador;
                break;
            case 1://entero
                aux=entero;
                break;
            case 3:
                aux=real;
                break;
            case 51:
                aux=cadena;
                break;
            case 26:
                aux=tipo;
                break;
            case 25:
                aux=tipo;
                break;
            case 37:
                if(this.aux==36){
                    aux=opwhile;//while
                }else if(this.aux==42){
                    aux=opreturn;//-------------return
                }else if(this.aux==45) {
                    aux=opelse;//-------------else
                }else{
                    aux=opif;//-------------if
                }
                break;
            case 5:
                aux=opsuma;
                break;
            case 6:
                aux=opmul;
                break;
            case 10:
                aux=oprelac;
                break;
            case 15:
                aux=opor;
                break;
            case 13:
                aux=opand;
                break;
            case 9:
                aux=opnot;
                break;
            case 8:
                aux=opigualdad;
                break;
            case 20:
                aux=punto_coma;
                break;
            case 22:
                aux=coma;
                break;
            case 16:
                aux=in_parentesis;
                break;
            case 17:
                aux=fn_parentesis;
                break;
            case 18:
                aux=in_llaves;
                break;
            case 19:
                aux=fn_llaves;
                break;
            case 7:
                aux=igualdad;
                break;
            case 49:
                aux=opif;
                break;
            case 46:
                aux=opwhile;
                break;
            case 47:
                aux=opreturn;
                break;
            case 48:
                aux=opelse;
                break;
            case 21:
                aux=opsimbolo;
                break;
            case 11:
                aux=oprelac;
                break;
            default:
                if((estado>26 && estado<37) || estado==23 || estado==24 || estado==23 || (estado>37 && estado<46) ) {
                    aux=identificador;
                } else {
                    aux=error;}
                break;
        }
    return aux;
    }   
}

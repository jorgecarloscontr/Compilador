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
    private static final int identificador = 4;
    private static final int entero = 1;
    private static final int real = 3;
    private static final int cadena=51;
    private static final int tipo = 26;
    private static final int opsuma =5;
    private static final int opmul = 6;
    private static final int oprelac =10;
    private static final int opor = 15;
    private static final int opand = 13;
    private static final int opnot = 9;
    private static final int opigualdad = 8;
    private static final int punto_coma = 20;
    private static final int coma = 22;
    private static final int in_parentesis = 16;
    private static final int fn_parentesis = 17;
    private static final int in_llaves = 18;
    private static final int fn_llaves = 19;
    private static final int igualdad =7;
    private static final int opif = 49;
    private static final int opwhile = 46;
    private static final int opreturn = 47;
    private static final int opelse =48;
    private static final int opsimbolo = 21;
    private static final int error = -1;
    private String entrada;
    private int estado;
    private int ps;
    private boolean bandera;
    private int cont;
    private String tmp;
    private int aux;
    private char caracter;

    
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
                    }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123)){//letra
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
                    }else if(caracter==34){//,
                        estado=50;
                    }else {
                        aux=0;
                        ps=0;
                        if(caracter==32)
                            estado=0;
                        else 
                            estado=-1;}
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
                    if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
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
                    entrada=recortar(entrada,2);
                }else{//recorta la cadena en donde se quedo
                    System.out.println(recortar(entrada,1)+"\t\t"+tipoc(estado));
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
                if(estado==0){
                    entrada=recortar(entrada,3);
                    cont=0;
                }
                if(cont<entrada.length()){//sigue con la lectura
                    caracter=sig_caracter(entrada);
                }else{//cuando se termina cadena
                    if(estado!=0){
                        System.out.println(recortar(entrada,1)+"\t\t"+tipoc(estado));
                    }
                    entrada="$";
                    cont=0;
                    caracter=sig_caracter(entrada);
                    inicializar();
                    bandera=false;
                }
            }
        }
        bandera=true;
        return estado=tipoc1(estado);
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
            case identificador:
                aux="Identificador";
                break;
            case entero:
                aux="entero";
                break;
            case real:
                aux="real";
                break;
            case cadena:
                aux="cadena";
                break;
            case tipo:
                aux="tipo";
                break;
            case 25:
                aux="tipo";
                break;
            case 37:
                if(this.aux==36){
                    aux="while";
                }else if(this.aux==42){
                    aux="return";//-------------return
                }else if(this.aux==45) {
                    aux="else";//-------------else
                }else{
                    aux="if";//-------------if
                }
                break;
            case opsuma:
                aux="operacion suma";
                break;
            case opmul:
                aux="operacion multiplicacion";
                break;
            case oprelac:
                aux="relacional";
                break;
            case opor:
                aux="operacion or";
                break;
            case opand:
                aux="operacion and";
                break;
            case opnot:
                aux="operacion not";
                break;
            case opigualdad:
                aux="operacion igualdad";
                break;
            case punto_coma:
                aux="punto y coma";
                break;
            case coma:
                aux="coma";
                break;
            case in_parentesis:
                aux="inicio parentesis";
                break;
            case fn_parentesis:
                aux="cierre parentesis";
                break;
            case in_llaves:
                aux="inicio llaves";
                break;
            case fn_llaves:
                aux="cierre llaves";
                break;
            case igualdad:
                aux="igualdad";
                break;
            case opif:
                aux="operacion if";
                break;
            case opwhile:
                aux="operacion while";
                break;
            case opreturn:
                aux="operacion return";
                break;
            case opelse:
                aux="operacion else";
                break;
            case opsimbolo:
                aux="operacion simbolo";
                break;
            case error:
                aux="error";
                break;
            case 11:
                aux="realacional";
                break;
            default:
                if((estado>26 && estado<37) || estado==23 || estado==24 || estado==23 || (estado>37 && estado<46) ) {
                    aux="Identificador";
                } else {
                    aux="Error";}
                break;
        }
        this.estado=estado;
    return aux;
    }   
    
    
    public int tipoc1(int estado){
        int aux;
        switch(estado){
            case identificador:
                aux=0;
                break;
            case entero:
                aux=5;
                break;
            case real:
                aux=23;
                break;
            case cadena:
                aux=3;
                break;
            case tipo:
                aux=4;
                break;
            case 25:
                aux=4;
                break;
            case 37:
                if(this.aux==36){
                    aux=20;
                }else if(this.aux==42){
                    aux=21;//-------------return
                }else if(this.aux==45) {
                    aux=22;//-------------else
                }else{
                    aux=19;//-------------if
                }
                break;
            case opsuma:
                aux=1;
                break;
            case opmul:
                aux=6;
                break;
            case oprelac:
                aux=7;
                break;
            case opor:
                aux=8;
                break;
            case opand:
                aux=9;
                break;
            case opnot:
                aux=10;
                break;
            case opigualdad:
                aux=11;
                break;
            case punto_coma:
                aux=12;
                break;
            case coma:
                aux=13;
                break;
            case in_parentesis:
                aux=14;
                break;
            case fn_parentesis:
                aux=15;
                break;
            case in_llaves:
                aux=16;
                break;
            case fn_llaves:
                aux=17;
                break;
            case igualdad:
                aux=18;
                break;
            case opif:
                aux=19;
                break;
            case opwhile:
                aux=20;
                break;
            case opreturn:
                aux=21;
                break;
            case opelse:
                aux=22;
                break;
            case opsimbolo:
                aux=2;
                break;
            case error:
                aux=24;
                break;
            case 11:
                aux=6;
                break;
            default:
                if((estado>26 && estado<37) || estado==23 || estado==24 || estado==23 || (estado>37 && estado<46) ) {
                    aux=0;
                } else {
                    aux=24;}
                break;
        }
    return aux;
    }   
}

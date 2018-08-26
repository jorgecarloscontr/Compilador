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
public class Lexico {
    private static final int identificador = 4;
    private static final int entero = 1;
    private static final int real = 3;
    //private static final int cadena =
    private static final int tipo = 24;
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
    private static final int opif = 34;
    private static final int opwhile = 39;
    private static final int opreturn = 45;
    private static final int opelse =49;
    private static final int opsimbolo = 21;
    private static final int error = -1;
    private String entrada;
    private int estado;
    private boolean bandera;
    private int cont;
    private String tmp;

    
    public Lexico(String cadena){
        entrada=cadena;
        estado=0;
        bandera=true; 
        cont=0;
        tmp="";
    }
    public char sig_caracter(String cadena){
            cont++;
            return cadena.charAt(cont-1);          
    }
    public void fin_automata(){
        if(cont>entrada.length()-1){
            bandera=false;
        }
    }
    
    
    public void automata(){
        char caracter=sig_caracter(entrada);
        int numero= caracter;
        int aux=0;

        while(bandera){
            switch(estado){  
                case 0:
                    if(caracter>47 && caracter<58){//si es un numero
                        estado=1;
                    }else if(caracter==105){//i
                        estado=23;
                    }else if(caracter==102){//f
                        estado=25;
                    }else if(caracter==118){//v
                        estado=29;
                    }else if(caracter==119){//w
                        estado=35;
                    }else if(caracter==114){//r
                        estado=40;
                    }else if(caracter==101){//e
                        estado=46;
                    }else if((caracter>64 && caracter<91) || (caracter>96 && caracter<123)){//letra
                        estado=4;
                    }else if(caracter==43 || caracter==45){//suma
                        estado=5;
                    }else if(caracter==42 || caracter==47){//multiplicacion
                        estado=6;
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
                        estado=16;
                    }else if(caracter==41){//)
                        estado=17;
                    }
                    else if(caracter==123){//{
                        estado=18;
                    }
                    else if(caracter==125){//}
                        estado=19;
                    }
                    else if(caracter==59){//;
                        estado=20;
                    }
                    else if(caracter==36){//$
                        estado=21;
                    }else if(caracter==44){//,
                        estado=22;
                    }else {
                        aux=estado;
                        estado=-1;}
                    break;
                case 1://*********************************************entero
                    if(caracter>47 && caracter<58){
                        estado=1;
                    }else if(caracter==46){
                        estado=2;
                    }else if((caracter==43 || caracter==45) ||(caracter==42 || caracter==47) || caracter==61 || caracter==33 || (caracter==60 || caracter==62) || caracter==41 ||caracter==40 ||caracter==123||caracter==125
                            ||caracter==59){//s)uma multiplicacion igualdar
                        aux=estado;
                        estado=-2;                        
                    }else{
                        aux=estado;
                        estado=-1;}
                    break;
                case 2: /// q2 -> . q3
                    if(caracter>47 && caracter<58){
                        estado=3;
                    }else{
                        aux=0;
                        estado=-1;}
                    break;
                case 3: //****************************************validacion real
                    if(caracter>47 && caracter<58){
                        estado=3;
                    }else if((caracter==43 || caracter==45) ||(caracter==42 || caracter==47) || caracter==61 || caracter==33 || (caracter==60 || caracter==62) || caracter==41 ||caracter==40 ||caracter==123||caracter==125
                            ||caracter==59){
                        aux=estado;
                        estado=-2;                        
                    }else{
                        aux=estado;
                        estado=-1;}
                    break;
                case 4: //****************************************validacion cadena
                    if((caracter>64 && caracter<91) || (caracter>96 && caracter<123) || (caracter>47 && caracter<58)){
                        estado=4;
                    }else{
                        aux=estado;
                        estado=-1;}
                    break;  
                case 5://validacion suma
                    aux=estado;
                    if(caracter>47 && caracter<58){
                        aux=estado;
                        estado=-2;
                    }else{
                        estado=-1;
                    }
                    break;  
                case 6://validacion multiplicacion
                    aux=estado;
                    if(caracter>47 && caracter<58){
                        aux=estado;
                        estado=-2;
                    }else{
                        estado=-1;
                    }
                    break;
                case 7://validacion =
                    aux=estado;
                    if(caracter==61){
                        estado=8;
                    }else if((caracter>47 && caracter<58)||(caracter>64 && caracter<91) || (caracter>96 && caracter<123)){
                        estado=-2;
                    }else{
                        estado=-1;
                    }
                    break;
                case 8://validacion == !=
                    aux=estado;
                    estado=-1;
                    break;
                case 9: //validacion !
                    if(caracter==61){
                        estado=8;
                    }else{
                        aux=estado;
                        estado=-1;}
                    break;
                case 10: //validacion <>
                    if(caracter==61){
                        estado=11;
                    }else{
                        aux=estado;
                        estado=-1;}
                    break;
                case 11: //validacion <= >=
                    aux=estado;
                    estado=-1;
                    break;   
                case 12: 
                    if(caracter==38){
                        estado=13;
                    }else{
                        aux=estado;
                        estado=-1;
                    }
                    break;  
                case 13://validacion &&
                        aux=estado;
                        estado=-1;
                    break;
                case 14:
                    if(caracter==124){//|
                        estado=15;
                    }else{
                        aux=estado;
                        estado=-1;
                    }
                    break;
                case 15://validacion ||
                    aux=estado;
                    estado=-1;
                    break;
                case 16: //validacion (
                    aux=estado;
                    estado=-1;
                    break; 
                case 17: //validacion )
                    aux=estado;
                    estado=-1;
                    break; 
                case 18: //validacion {
                    aux=estado;
                    estado=-1;
                    break; 
                case 19: //validacion }
                    aux=estado;
                    estado=-1;
                    break; 
                case 20: //validacion ;
                    aux=estado;
                    estado=-1;
                    break; 
                case 21: //validacion $
                    aux=estado;
                    estado=-1;
                    break;
                case 22: //validaciion ,
                    aux=estado;
                    estado=-1;
                    break;
                case 23:
                    if(caracter==110){
                        estado=32;
                    }else{
                        if(caracter==102){
                            estado=34;
                        }else{
                            aux=0;
                            estado=4;
                        }
                    }
                    break;   
                case 24://validacion tipo
                    if(caracter==32){
                        aux=estado;
                        estado=-1;
                    }else{
                        estado=4;
                    }
                    break;
                case 25:
                    if(caracter==108){//l
                        estado=26;
                    }else{
                        aux=0;
                        estado=4;}
                    break;
                case 26:
                    if(caracter==111){//o
                        estado=27;
                    }else{
                        estado=4;}
                    break;
                case 27:
                    if(caracter==97){//a
                        estado=28;
                    }else{
                        estado=4;}
                    break;
                case 28:
                    if(caracter==116){//t
                        estado=24;
                    }else{
                        aux=estado;
                        estado=4;}
                    break;
                case 29:
                    if(caracter==111){//o
                        estado=30;
                    }else{
                        aux=estado;
                        estado=4;}
                    break;    
                case 30:
                    if(caracter==105){//i
                        estado=31;
                    }else{
                        aux=estado;
                        estado=4;}
                    break; 
                case 31:
                    if(caracter==100){//d
                        estado=24;
                    }else{
                        aux=estado;
                        estado=4;}
                    break;  
                case 32:
                    if(caracter==116){
                        estado=24;
                    }else{
                        estado=4;
                    }
                    break;
                case 34:
                    if(caracter==32){
                        aux=estado;
                        estado=-1;
                    }else{
                        estado=4;
                    }
                    break;
                case 35:
                    if(caracter==104){//h
                        estado=36;
                    }else{
                        estado=4;
                    }
                    break;
                case 36:
                    if(caracter==105){//i
                        estado=37;
                    }else{
                        estado=4;
                    }
                    break;
                case 37:
                    if(caracter==108){//l
                        estado=38;
                    }else{
                        estado=4;
                    }
                    break;
                case 38:
                    if(caracter==101){//e
                        estado=39;
                    }else{
                        estado=4;
                    }
                    break;
                case 39:
                    if(caracter==32){
                        aux=estado;
                        estado=-1;
                    }else{
                        estado=4;
                    }
                    break;
                case 40:
                    if(caracter==101){//e
                        estado=41;
                    }else{
                        estado=4;
                    }
                    break;
                case 41:
                    if(caracter==116){//t
                        estado=42;
                    }else{
                        estado=4;
                    }
                    break;
                case 42:
                    if(caracter==117){//u
                        estado=43;
                    }else{
                        estado=4;
                    }
                    break;
                case 43:
                    if(caracter==114){//r
                        estado=44;
                    }else{
                        estado=4;
                    }
                    break;
                case 44:
                    if(caracter==110){//n
                        estado=45;
                    }else{
                        estado=4;
                    }
                    break;
                case 45:
                    if(caracter==32){
                        aux=estado;
                        estado=-1;
                    }else{
                        estado=4;
                    }
                    break;
                case 46:
                    if(caracter==108){//l
                        estado=47;
                    }else{
                        estado=4;
                    }
                    break;
                case 47:
                    if(caracter==115){//s
                        estado=48;
                    }else{
                        estado=4;
                    }
                    break;
                case 48:
                    if(caracter==101){//e
                        estado=49;
                    }else{
                        estado=4;
                    }
                    break;
                case 49:
                    if(caracter==32){
                        aux=estado;
                        estado=-1;
                    }else{
                        estado=4;
                    }
                    break;
                
            }
            if(estado==-1){//si el estado es de error
                if(caracter==32){
                    System.out.println(recortar(entrada,1)+"\t\t"+tipoc(aux));
                }else{
                    System.out.println(recortar(entrada,1)+"\t\t"+tipoc(estado));
                }
                entrada=recortar(entrada,0);
                estado=0;
                cont=0;
                if(entrada.length()==0){
                    bandera=false;
                }else{
                    caracter=sig_caracter(entrada);

                }
            }else if(estado==-2){
                System.out.println(entrada.subSequence(0, cont-1)+"\t\t"+tipoc(aux));
                entrada=entrada.substring(cont-1, entrada.length());
                cont=0;
                estado=0;
                caracter=sig_caracter(entrada);

            }else{
                fin_automata();
                if(bandera)
                    caracter=sig_caracter(entrada);
                else
                    System.out.println(entrada.subSequence(0, cont)+"\t\t"+tipoc(estado));

            }
        }
    }
    
    public String recortar(String cadena, int op){
        int i=0;
        int aux=0;
        for(char a:cadena.toCharArray()){
            if(a==32 && aux==0){
                aux=i;
            }
            i++;
        }
        if(aux==0){
            if(op>0){
                return cadena;
            }
            return "" ;
        }else{
            if(op>0){
                return cadena.substring(0, aux) ;
            }
            return cadena.substring(aux+1, cadena.length()) ;
        }
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
                break;/*
            case cadena:
                aux="cadena";
                break;*/
            case tipo:
                aux="tipo";
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
            case 11:
                aux="realacional";
        }
        return aux;
    }   
}

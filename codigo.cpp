#include <conio.h>
#include <stdlib.h>
#include <cstdio>
#include <iostream>
using namespace std;
int maximo;
int minimo(int a,int b){
	if(a<b){
		return a;
	}else{
		return b;
	}
}
int main(){
	string cadena;
	cadena="Hola mundo";
	cout<<(cadena);
	int a,b;
	a=40;
	b=10;
	maximo=400;
	cout<<(minimo(a,maximo));
}
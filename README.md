# Compilador
Proyecto seminario de traductores

Herramientas utilizadas:

- jdk y jre
- Netbeans 
- MingW


Descripcion:

El compilador fue realizado en java utilizando wondows 10 como sistema operativo. Como java es multiplataforma igualmente puede ejecutarse en linux pero se debera cambia un par de lineas de codigo:

1- En el archivo 'Sintactico.java' en la funcion 'cargar_datos()', lee desde una archivo.ir las reglas del sintactico que se utilizara. Este archivo se localiza en la carpeta principal del proyecto, para windows no se tiene que modificar la ruta, pero en linux se debera cambiar las '/' por '\'.

2- En el archivo 'Semantico.java' despues de validar el codigo, en la funcion 'run()'. se genera el codigo en ensamblador, ahi se debera especifiar la ruta en donde esta intalado mingw. Tamnbien los comandos para generar el archivo.obj es diferente para linux.





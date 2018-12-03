# Compilador
Proyecto seminario de traductores

Herramientas utilizadas:

- jdk y jre
- Netbeans 
- MingW


Descripcion:
El compilador fue realizado en java utilizando windows  10 como sistema operativo. Como java es multiplataforma igualmente puede ejecutarse en linux pero se deberá cambia un par de lineas de código:

1- En el archivo 'Sintactico.java' en la función 'cargar_datos()', lee desde una archivo.ir las reglas del sintáctico que se utilizara. Este archivo se localiza en la carpeta principal del proyecto, para windows no se tiene que modificar la ruta, pero en linux se debera cambiar las '/' por '\\'.

2- En el archivo 'Semantico.java' después de validar el código, en la funcion 'run()'. se genera el código en ensamblador, ahi se debera especifiar la ruta en donde esta intalado mingw. También los comandos para generar el archivo.obj es diferente para linux.





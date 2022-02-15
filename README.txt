El proyecto esta desarrollado en java usando Intellij como IDE.

He utilizado un servidor local de tomcat en el puerto 8080 como base del proyecto. 
Al escribir en la url "http://localhost:8080/MCP/index" despues de haber desplegado el servidor nos dirigirá a una pagina simple de inicio en la que nos pedirá seleccionar un archivo para procesar. 
Los archivos deben de encontrarse en la raíz del proyecto en una carpeta llamada "logs" para que se puedan procesar de manera adecuada.
Cuando termina de procesarse la información, nos redirige a un nuevo endpoint llamado "/logs" que nos mostrará por consola un resumen del procesamiento de los datos. 
En la pagina web se nos dará a elegir entre 2 botones, el primero nos redirige al endpoint "/metrics" que nos muestra por consola los datos de las mediciones realizadas por el programa.
El segundo botón nos lleva al endpoint "/kpis" que nos muestra un resumen con los totales de los datos procesados por consola.
El programa solo puede procesar un archivo a la vez, y no mantiene datos entre archivo y archivo.
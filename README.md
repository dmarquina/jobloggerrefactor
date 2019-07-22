# Errores y mejoras en base a buenas prácticas
##### Evitar declarar atributos que no seran utilizados
-Como el atributo "initialized".

##### Cada clase debe de tener una sola responsabilidad

-Operaciones directamente sobre la base de datos.

-Clasificar donde sera guardado el logMessage.

-Contrucción del logMessage.

-Validaciones de los parámetros de entrada.

##### Nuestras clases deben estar abiertas a extensión pero cerradas a modificación

-Por ejemplo: La conexión a la base de datos.

##### Usar inyeccion de dependencias para tener un código mejor desacoplado

-Para lograr esto se pueden usar interfaces y clases que las implementen.

##### Los métodos deberian tener idealmente un solo proposito y un nombre claro que defina ese proposito

-Como por ejemplo "LogToDatabase(...)", de esta manera el uso uso de comentarios se disminuye.

##### Evitar usar variables para lograr el mismo proposito

-Como "error" como parametro y "logerror" como atributo de la clase, ambos funcionan para lo mismo.

##### Evitar usar conversiones de enteros a string innecesarios
-Como String.valueOf(t), cuando un entero es parte de la cadena este se vuelve string inmediatamente.

##### Evitar usar condicionales innecesarias
-Por ejemplo cuando se define el tipo de mensaje a base de datos. Para ello se puede utilizar "return" si se cumple alguna condición,
de esta manera se evita que se ejecute más código de forma innecesaria.


En esta tarea, utilizarás las implementaciones de pila (stack) y cola (queue) de la API de Colecciones de Java para construir un programa que determine si una página HTML está bien formateada. Al completar esta tarea, lograrás lo siguiente:

Familiarizarte con los métodos de la clase java.util.Stack y la interfaz java.util.Queue. Trabajar con un tipo de dato abstracto (en este caso, colas) utilizando únicamente la interfaz de una implementación. Aplicar lo que has aprendido sobre cómo funcionan las pilas y las colas. Nota de depuración/error: Si te encuentras con errores/bugs o no entiendes la salida que Codio te está proporcionando, por favor, publica en el Foro de Discusión y un TA (Asistente de Enseñanza) te ayudará. ¡Por favor, NO envíes correos electrónicos a Codio, ya que no revisarán los errores que estás obteniendo.

Antecedentes: Las páginas web están escritas en Lenguaje de Marcado de Hipertexto (HTML). Un archivo HTML está compuesto por texto rodeado de etiquetas, donde las etiquetas "marcan" el texto especificando su formato, diseño u otra información. Las etiquetas también pueden estar anidadas. Aquí tienes un ejemplo simple con las etiquetas resaltadas en negrita:

html Copy code

<title>Página HTML de ejemplo</title> Esto es un texto HTML. Los significados exactos de las etiquetas no son importantes (aunque si deseas saber más, puedes inscribirte en nuestro curso "Programación para la Web con JavaScript" como parte de esta serie de edX). Sin embargo, las etiquetas como y se conocen como "etiquetas abiertas" porque indican el inicio de algún formato, y las etiquetas como (con la barra diagonal hacia adelante antes de la palabra) se conocen como "etiquetas de cierre" porque indican el final del formato.
En teoría (aunque no siempre en la práctica), el HTML bien formateado requiere que las etiquetas estén "equilibradas", es decir, que las etiquetas abiertas sean emparejadas con sus etiquetas de cierre correspondientes en el orden correcto.

Por ejemplo, si ignoramos los espacios en blanco y el texto entre las etiquetas, obtendremos esto:

html Copy code

<title></title> Observa que hay cierta simetría en las etiquetas HTML, ya que cada vez que cerramos una etiqueta, coincide con la etiqueta abierta más reciente (sin cerrar).
Por ejemplo, si resaltamos las etiquetas "title", veremos que una etiqueta de cierre coincide con la última etiqueta abierta:

html Copy code

<title></title> Y en este caso, la etiqueta de cierre "body" coincide con la etiqueta abierta "body", que es la etiqueta más recientemente abierta que aún no se ha cerrado (ya que la etiqueta "b" ya está cerrada):
html Copy code

<title></title> Algunas etiquetas HTML son "auto-cerrantes" y no dependen de una etiqueta de cierre coincidente. Por ejemplo, aquí la etiqueta "br" se cierra a sí misma:
html Copy code

head>
Una etiqueta auto-cerrante es aquella que termina con el carácter de barra diagonal hacia adelante, a diferencia de una etiqueta de cierre, que comienza con uno.
¡Es fácil cometer errores en el código HTML! Lo más común es que las personas olviden cerrar etiquetas o cierren etiquetas anidadas en el orden incorrecto, como algo similar a esto:

html Copy code

<title></title> En este caso, no hay una etiqueta de cierre "head", y la etiqueta "body" se cierra en el orden incorrecto: debería ir después de la etiqueta de cierre "b".
En esta tarea, escribirás un método que determine si un archivo HTML está bien formateado utilizando una pila. Cada vez que tu código encuentre una etiqueta abierta, deberá agregarla a la pila; cuando encuentre una etiqueta de cierre, deberá quitar la etiqueta de la parte superior de la pila, y si no coinciden, sabrás que el archivo no está bien formateado. A continuación, se proporcionan más ejemplos y explicaciones.

Para comenzar, descarga htmlreader.java y htmltag.java, que contienen el código que puedes usar en esta tarea. No debes cambiar ninguna de estas implementaciones para esta tarea.

HtmlTag.java representa información sobre una sola etiqueta HTML. Los métodos que pueden ser útiles para ti son:

getElement(): Obtiene el nombre del elemento (String) especificado en esta etiqueta. isOpenTag(): Comprueba si esta es la etiqueta de apertura. Si la etiqueta es una etiqueta de cierre o una etiqueta auto-cerrante (por ejemplo,
es una etiqueta de salto de línea que no necesita ningún texto acompañante), isOpenTag devolverá false. isSelfClosing(): Comprueba si una etiqueta es auto-cerrante (por ejemplo,
). matches(HtmlTag other): Comprueba si una HtmlTag llamada other es la etiqueta de apertura/cierre coincidente con ella misma (por ejemplo, y o viceversa). En HtmlReader.java, encontrarás un método llamado getTagsFromFile que lee la ruta de un archivo HTML y lo separa en tokens. La salida es una representación del archivo HTML como una cola (queue) de HtmlTags en el orden en que se encuentran. Puedes editar este código si lo deseas, pero por favor, no modifiques HtmlTag.java.

También descarga htmlvalidator.java, que contiene el método no implementado para el código que escribirás en esta tarea.

En HtmlValidator.java, implementa el método isValidHtml. isValidHtml debería recibir como entrada una Cola (Queue) de HtmlTags y devolver una Pila (Stack) de HtmlTags que verifique la corrección de la estructura de etiquetas, de acuerdo con la especificación descrita a continuación.

El método debe implementarse de la siguiente manera:

Si el archivo HTML está bien formateado, el método debe devolver una Pila vacía. Por ejemplo:

<html><body><h1>encabezado</h1><p>párrafo</p></body></html>
En este caso, las etiquetas de cierre coinciden con las etiquetas de apertura, por lo que el HTML es válido. Cuando llegues al final del archivo/Cola, la Pila estará vacía.

Si el archivo HTML no está bien formateado, el método debe devolver la Pila en su estado actual (es decir, con sus valores actuales) en el momento en que el código determinó que las etiquetas no estaban equilibradas.

Aquí tienes algunos casos de ejemplo para considerar:

Ejemplo #1: Etiquetas cerradas en orden incorrecto

<html><body><p><b>Texto aquí</p></b></body></html>
En este caso, deberías agregar todas las etiquetas de apertura a la Pila de modo que se vea así:

<b>
<p>
<body>
Y, al encontrar una etiqueta de cierre en la Cola, deberías verificar esa Pila para ver si la coincidencia correcta está presente. La primera etiqueta de cierre que encuentras es </p>, sin embargo, la última etiqueta de apertura (en la parte superior de la Pila) es <b>. Eso es incorrecto. Tan pronto como determines que el archivo HTML no es válido, devuelve la Pila de etiquetas de apertura sin eliminar la etiqueta de apertura que no coincide. En este caso, la salida esperada sería una Pila que contiene (de abajo a arriba): <html><body><p><b>.

Ejemplo #2: Etiqueta de cierre sin etiqueta de apertura

<html><body>Correcto<br/><b>Texto</b> aquí</div></body></html>
En este caso, la primera etiqueta de cierre que encuentras (</b>) coincide con su etiqueta de apertura, pero la siguiente (</div>) no lo hace. Entonces, la salida esperada sería una Pila que contiene (de abajo a arriba): <html><body>. ¡Ten en cuenta que la etiqueta <br/> es auto-cerrante y no debe colocarse en la Pila!

Ejemplo #3: Etiqueta de apertura nunca cerrada

<html><body><b>Este es un texto
En este caso, el método llega al final del archivo/Cola y aún quedan elementos en la Pila, ya que esas etiquetas de apertura nunca se cerraron. La salida esperada sería una Pila que contiene (de abajo a arriba): <html><body><b>.

Ejemplo #4 (la parte complicada): Etiqueta de cierre sin etiqueta de apertura, todo bien hasta entonces

<html><body><p>Hola, mundo!</p></body></html></p>
Esto es similar al Ejemplo #2, excepto que ahora, cuando encuentres la etiqueta de cierre que no tiene una etiqueta de apertura, la Pila estará vacía ya que todo antes de eso coincide. Sin embargo, devolver una Pila vacía significa que el archivo está bien formateado. En este caso, debes devolver null para indicar que el archivo no está bien formateado. Piensa en cómo puedes diferenciar cuándo devolver null y cuándo devolver una Pila vacía.

Por favor, no cambies la firma del método isValid (su lista de parámetros, nombre y tipo de valor de retorno). Además, no crees archivos .java adicionales para tu solución y por favor, no modifiques HtmlTag.java. Si necesitas clases adicionales, puedes definirlas en HtmlValidator.java. Por último, asegúrate de que tu clase HtmlValidator esté en el paquete predeterminado, es decir, no debe haber una declaración "package" en la parte superior del código fuente.

Consejos útiles:

La documentación sobre los métodos de la clase Stack y la interfaz Queue en la última versión de Java está disponible en:

https://docs.oracle.com/javase/8/docs/api/java/util/Stack.html
https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html
Consulta esta documentación si necesitas ayuda para comprender los métodos disponibles para ti.

Ten en cuenta que tu método isValidHtml de HtmlValidator solo debe utilizar métodos en la interfaz Queue, aunque la Queue esté implementada usando un LinkedList.

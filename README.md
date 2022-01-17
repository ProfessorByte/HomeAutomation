# HomeAutomation

Este es un proyecto de NetBeans, para su funcionamiento, primero se debe importar las librerias que se encuentran en la carpeta Libraries se debe establecer un CLASSPATH para los archivos DLL de la librería lwjgl (-Djava.library.path="AQUI VA LA RUTA DE LOS DLLS"), eso se le copia al apartado de propiedades del proyecto, en el apartado de Run, luego para importar la librería GraphicatorPro (esta es la librería que hicimos para el primer parcial, pero para usarla en este proyecto se le incorporó la librería que creamos para el parsing de texto, ya que lo necesitabamos para importar los archivos OBJ) se debe copiar el contenido de la carpeta "src" de la carpeta /.../Libraries/GraphicataorPro/src a la carpeta "src" del proyecto, en este caso ya están copiados los archivos en el nuevo proyecto, por tanto no será necesario copiarlos nuevamente.

Se deben importar GraphicatorPro.jar, slick-util.jar, PNGDecoder.jar, lwjgl.jar, lwjgl_util.jar todos estos archivos están en la carpeta Libraries, además para la parte de estadísticas se debe agregar el jfreechart que tambien se encuentra en la misma carpeta.

Al hacerlo correr solo se debe mover el cursor alrededor del terreno, y presionar E para dejar un objeto, Q para rotarlo,
Z y X para escalarlo, C y V para mover la artura, se puede cambiar de objeto con las teclas N y M, además de que para dejar salir del modo de edición se presiona la tecla P.

Para el módulo gráfico se debe presionar la tecla L.

Tambien se deja el código de la librería GraphicatorPro por si quiere revisar las mejoras.
Se pueden crear un máximo de 10 luces, pero si se modifica el código de GraphicatorPro se pueden crear más, sin embargo no es recomendable crear muchas luces porque su procesamiento consume mucha RAM.

Para todo se utiliza el JDK 11

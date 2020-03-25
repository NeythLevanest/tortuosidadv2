# Tortuosidad Geométrica, algoritmo busqueda en anchura

Este proyecto consiste en un experimiento para calcular la tortuosidad geométrica de celdas de combustible sólido a través del algoritmo de busqueda en anchura aplicado a matrices binarias que representan (1) obstáculos, (0) espacios por donde puede circular el fluido simulado.

### Notas:
1. En el archivo ruta.java dentro de la carpeta metodología en el src, en la línea 40 donde se instancia la matriz se debe especificar el número de filas y el número de columnas del archivo en cuestión.

2. En la linea 42 se debe establecer el nombre del archivo como primer parámetro (los archivos se encuentran en la carpeta assets).

3. Si deseas modifica el tamaño de las celdas del fluido debes cambiar el valor de la variable |tamanoCelda|.

4. En el archivo MatrizPrueba.java de la carpeta estructuras, dentro del método pain(), cuando se llama a la función getReadMatrizFile("nombre",filas, columnas) también debes especificar como primer parámetro el mismo nombre del archivo que especificaste en ruta.java, así como también el número de filas y columnas, para efector de visualizar gráficamente la matriz en pantalla.

5. Al igual que antes dentro del metodo paint existe una vairable |tamanoCelda| que debe tener el mismo tamaño especificado en el archivo ruta.java para una correcta visualizacion 

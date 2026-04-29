package EL2aArbolesBinarios;

import java.util.ArrayList;
import java.util.Collections;

public class Main{

    public static int sumarLista(ListaSimplementeEnlazada<Integer> lista) {

        int suma = 0;

        Iterador<Integer> it = lista.getIterador();

        while (it.hasNext()) {
            suma += it.next();
        }

        return suma;
    }


    public static void main(String[] args) {
        System.out.println("PROGRAMA DE PRUEBA 1 (Inserción Ordenada)");
        ArbolBinarioDeBusquedaEnteros arbol1 = new ArbolBinarioDeBusquedaEnteros();
        
        // 3.1. Añadir los números de 0 a 128 en orden.
        for (int i = 0; i <= 128; i++) {
            arbol1.add(i);
        }
        
        // 3.2. Calcular la suma
        int suma1 = arbol1.getSuma();
        System.out.println("Suma total: " + suma1);
        
        // 3.3. Verifica que la suma es la misma accediendo en los 3 tipos de recorridos posibles.
        int sumaPre1 = sumarLista(arbol1.getListaPreOrden());
        int sumaPost1 = sumarLista(arbol1.getListaPostOrden());
        int sumaCentral1 = sumarLista(arbol1.getListaOrdenCentral());
        
        System.out.println("Suma PreOrden: " + sumaPre1);
        System.out.println("Suma PostOrden: " + sumaPost1);
        System.out.println("Suma OrdenCentral: " + sumaCentral1);
        
        // 3.4. Verifica que la suma es la misma cuando se suman los elementos de los subárboles izquierdo y derecho.
        int sumaSubIzquierda1 = arbol1.getSubArbolIzquierda().getSuma();
        int sumaSubDerecha1 = arbol1.getSubArbolDerecha().getSuma();
        // Raíz es 0, por lo que el subárbol izquierdo estará vacío.
        System.out.println("Suma Subárbol Izq + Subárbol Der + Raíz: " + (sumaSubIzquierda1 + sumaSubDerecha1 + 0));
        System.out.println("¿Por qué? Porque todos los elementos del árbol se reparten entre la raíz y sus subárboles.");
        
        // 3.5. ¿Cuál es la altura del árbol?
        System.out.println("Altura del árbol 1: " + arbol1.getAltura());
        
        // 3.6. ¿Cuál es el camino para llegar al valor 110? ¿Cuál es su longitud de camino?
        ListaSimplementeEnlazada<Integer> camino1 = arbol1.getCamino(110);
        System.out.println("Camino a 110: ");
        camino1.imprimirLista();
        System.out.println("Longitud de camino a 110: " + (camino1.getTamaño() > 0 ? camino1.getTamaño() - 1 : 0));
        
        System.out.println("\nPROGRAMA DE PRUEBA 2 (Inserción Aleatoria)");
        ArbolBinarioDeBusquedaEnteros arbol2 = new ArbolBinarioDeBusquedaEnteros();
        
        // 4.1. Añade los números de 0 a 128 PERO DE MANERA ALEATORIA y sin repetir.
        ListaSimplementeEnlazada<Integer> numeros = new ListaSimplementeEnlazada<>();

        for (int i = 0; i <= 128; i++) {
            numeros.add(i);
        }

        while (!numeros.estaVacia()) {

            int pos = (int)(Math.random() * numeros.getTamaño());

            Integer num = numeros.busqPos(pos);

            arbol2.add(num);

            numeros.eliminar(num);
        }
        
        // 4.2. Calcula la suma
        int suma2 = arbol2.getSuma();
        System.out.println("Suma total: " + suma2);
        
        // 4.3. Verifica que la suma es la misma accediendo en los 3 tipos de recorridos posibles.
        int sumaPre2 = sumarLista(arbol2.getListaPreOrden());
        int sumaPost2 = sumarLista(arbol2.getListaPostOrden());
        int sumaCentral2 = sumarLista(arbol2.getListaOrdenCentral());
        
        System.out.println("Suma PreOrden: " + sumaPre2);
        System.out.println("Suma PostOrden: " + sumaPost2);
        System.out.println("Suma OrdenCentral: " + sumaCentral2);
        
        // 4.4. Verifica que la suma es la misma cuando se suman los elementos de los subárboles izquierdo y derecho.
        int raizDato2 = arbol2.getListaPreOrden().busqPos(0);
        int sumaSubIzquierda2 = arbol2.getSubArbolIzquierda().getSuma();
        int sumaSubDerecha2 = arbol2.getSubArbolDerecha().getSuma();
        System.out.println("Suma Subárbol Izq + Subárbol Der + Raíz: " + (sumaSubIzquierda2 + sumaSubDerecha2 + raizDato2));
        System.out.println("¿Por qué? Porque al igual que en el árbol anterior, todo elemento pertenece a la raíz o a alguno de sus subárboles.");
        
        // 4.5. ¿Cuál es la altura del árbol? ¿Por qué?
        System.out.println("Altura del árbol 2: " + arbol2.getAltura());
        System.out.println("¿Por qué? Al insertar de manera aleatoria, el árbol está más equilibrado que al insertar ordenadamente, lo que reduce la altura significativamente.");
        
        // 4.6. ¿Cuál es el camino para llegar al valor 110? ¿Cuál es su longitud de camino?
        ListaSimplementeEnlazada<Integer> camino2 = arbol2.getCamino(110);
        System.out.println("Camino a 110: ");
        camino2.imprimirLista();
        System.out.println("Longitud de camino a 110: " + (camino2.getTamaño() > 0 ? camino2.getTamaño() - 1 : 0));
        
        System.out.println("\nPREGUNTAS FINALES");
        System.out.println("Diferencias entre los resultados obtenidos: El árbol con inserción ordenada (Programa 1) es un árbol degenerado que se comporta como una lista enlazada, resultando en una altura máxima igual al número de elementos menos uno (128). En cambio, el árbol con inserción aleatoria (Programa 2) se distribuye mucho mejor (más equilibrado), reduciendo drásticamente la altura.");
        System.out.println("¿Qué sucede al ejecutar varias veces?: Los resultados del Programa 1 son idénticos en cada ejecución porque los datos se insertan de la misma manera ordenada. Sin embargo, en el Programa 2, la estructura del árbol y por ende la altura y la ruta de búsqueda cambian en cada ejecución debido al orden de inserción aleatorio, aunque las sumas de sus elementos se mantienen inalterables.");
    }
}
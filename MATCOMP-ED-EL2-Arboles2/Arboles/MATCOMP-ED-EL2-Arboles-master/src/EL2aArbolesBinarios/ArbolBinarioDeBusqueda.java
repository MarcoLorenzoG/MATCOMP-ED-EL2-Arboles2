package EL2aArbolesBinarios;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ArbolBinarioDeBusqueda<T extends Comparable<T>> {

    // Clase Nodo para el árbol binario
    public class Nodo {
        public T dato;
        public Nodo izq;
        public Nodo der;

        public Nodo(T dato) {
            this.dato = dato;
        }
    }



// Crear raiz
    protected ArbolBinarioDeBusqueda<T>.Nodo raiz;

// Constructor por defecto
    public ArbolBinarioDeBusqueda() {
        this.raiz = null;
    }

// Constructor con raiz
    protected ArbolBinarioDeBusqueda(Nodo raiz) {
        this.raiz = raiz;
    }



// Para saber donde añadir el elemento (nodo) de forma recursiva
    protected Nodo addRecursiva(Nodo nodo, T dato) {
        if (nodo == null) {
            return new Nodo(dato);
        }
        int comparacion = dato.compareTo(nodo.dato);
        if (comparacion < 0) {
            nodo.izq = addRecursiva(nodo.izq, dato);
        } else if (comparacion > 0) {
            nodo.der = addRecursiva(nodo.der, dato);
        }
        return nodo; 
    }



// Obtener el grado del árbol
    public int getGrado() {
        return getGradoRec(raiz);
    }

    protected int getGradoRec(Nodo nodo) {
        if (nodo == null) return 0;
        int grado = 0;
        if (nodo.izq != null) grado++;
        if (nodo.der != null) grado++;
        
        int gradoIzq = getGradoRec(nodo.izq);
        int gradoDer = getGradoRec(nodo.der);
        
        return Math.max(grado, Math.max(gradoIzq, gradoDer));
    }

// Obtener la altura del árbol
    public int getAltura() {
        return getAlturaRec(raiz);
    }

    protected int getAlturaRec(Nodo nodo) {
        if (nodo == null) return 0;
        if (nodo.izq == null && nodo.der == null) return 0;
        int altIzq = (nodo.izq != null) ? getAlturaRec(nodo.izq) + 1 : 0;
        int altDer = (nodo.der != null) ? getAlturaRec(nodo.der) + 1 : 0;
        return Math.max(altIzq, altDer);
    }

// Crear lista de datos de un nivel dado
    public List<T> getListaDatosNivel(int nivel) {
        List<T> lista = new ArrayList<>();
        getListaDatosNivelRec(raiz, 0, nivel, lista);
        return lista;
    }

    protected void getListaDatosNivelRec(Nodo nodo, int actual, int objetivo, List<T> lista) {
        if (nodo == null) return;
        if (actual == objetivo) {
            lista.add(nodo.dato);
        } else {
            getListaDatosNivelRec(nodo.izq, actual + 1, objetivo, lista);
            getListaDatosNivelRec(nodo.der, actual + 1, objetivo, lista);
        }
    }

// Verificar si el árbol es homogéneo
    public boolean isArbolHomogeneo() {
        if (raiz == null) return true;
        int gradoMax = getGrado();
        return isArbolHomogeneoRec(raiz, gradoMax);
    }

    protected boolean isArbolHomogeneoRec(Nodo nodo, int gradoMax) {
        if (nodo == null) return true;
        int grado = 0;
        if (nodo.izq != null) grado++;
        if (nodo.der != null) grado++;
        
        if (grado != 0 && grado != gradoMax) return false;
        return isArbolHomogeneoRec(nodo.izq, gradoMax) && isArbolHomogeneoRec(nodo.der, gradoMax);
    }

// Verificar si el árbol es completo
    public boolean isArbolCompleto() {
        if (raiz == null) return true;
        int h = getAltura();
        return isArbolCompletoRec(raiz, 0, h);
    }

    protected boolean isArbolCompletoRec(Nodo nodo, int profundidad, int h) {
        if (nodo == null) return true;
        if (profundidad == h) {
            return nodo.izq == null && nodo.der == null;
        }
        if (nodo.izq == null || nodo.der == null) return false;
        return isArbolCompletoRec(nodo.izq, profundidad + 1, h) &&
               isArbolCompletoRec(nodo.der, profundidad + 1, h);
    }

// Verificar si el árbol es casi completo
    public boolean isArbolCasiCompleto() {
        if (raiz == null) return true;
        int h = getAltura();
        if (h == 0) return true;
        
        Queue<Nodo> q = new LinkedList<>();
        q.add(raiz);
        boolean foundNull = false;
        
        while (!q.isEmpty()) {
            Nodo curr = q.poll();
            if (curr == null) {
                foundNull = true;
            } else {
                if (foundNull) return false;
                q.add(curr.izq);
                q.add(curr.der);
            }
        }
        return true;
    }

// Obtener camino para llegar a un dato dado
    public List<T> getCamino(T dato) {
        List<T> camino = new ArrayList<>();
        Nodo actual = raiz;
        while (actual != null) {
            camino.add(actual.dato);
            int cmp = dato.compareTo(actual.dato);
            if (cmp < 0) {
                actual = actual.izq;
            } else if (cmp > 0) {
                actual = actual.der;
            } else {
                return camino;
            }
        }
        return new ArrayList<>(); 
    }

    // Añadir elementos al árbol
    public void add(T dato) {
        raiz = addRecursiva(raiz, dato);
    }
    // Obtener el subárbol izquierdo
    public ArbolBinarioDeBusqueda<T> getSubArbolIzquierda() {
        if (raiz == null) return new ArbolBinarioDeBusqueda<>();
        return new ArbolBinarioDeBusqueda<>(raiz.izq);
    }

    // Obtener el subárbol derecho
    public ArbolBinarioDeBusqueda<T> getSubArbolDerecha() {
        if (raiz == null) return new ArbolBinarioDeBusqueda<>();
        return new ArbolBinarioDeBusqueda<>(raiz.der);
    }

// Crear lista con orden de primero el padre y luego los hijos, primero la rama izquierda
    public List<T> getListaPreOrden() {
        List<T> lista = new ArrayList<>();
        preOrdenRec(raiz, lista);
        return lista;
    }

    protected void preOrdenRec(Nodo nodo, List<T> lista) {
        if (nodo != null) {
            lista.add(nodo.dato);
            preOrdenRec(nodo.izq, lista);
            preOrdenRec(nodo.der, lista);
        }
    }

// Crear lista con orden de primero los hijos y al final el padre, pasando por ambas ramas
    public List<T> getListaPostOrden() {
        List<T> lista = new ArrayList<>();
        postOrdenRec(raiz, lista);
        return lista;
    }

    protected void postOrdenRec(Nodo nodo, List<T> lista) {
        if (nodo != null) {
            postOrdenRec(nodo.izq, lista);
            postOrdenRec(nodo.der, lista);
            lista.add(nodo.dato);
        }
    }

// Crear lista ordenada de menor a mayor
    public List<T> getListaOrdenCentral() {
        List<T> lista = new ArrayList<>();
        ordenCentralRec(raiz, lista);
        return lista;
    }

    protected void ordenCentralRec(Nodo nodo, List<T> lista) {
        if (nodo != null) {
            ordenCentralRec(nodo.izq, lista);
            lista.add(nodo.dato);
            ordenCentralRec(nodo.der, lista);
        }
    }
}
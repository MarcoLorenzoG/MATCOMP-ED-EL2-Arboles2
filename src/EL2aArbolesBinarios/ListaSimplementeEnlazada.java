package EL2aArbolesBinarios;

public class ListaSimplementeEnlazada<T extends Comparable<T>> implements Lista<T> {
    protected NodoLSE<T> cabeza;
    protected int tamaño = 0;

    @Override
    public void add(T dato) {
        NodoLSE<T> nuevo = new NodoLSE<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoLSE<T> act = cabeza;
            while (act.siguiente != null) act = act.siguiente;
            act.siguiente = nuevo;
        }
        tamaño++;
    }

    @Override
    public void addPrimero(T dato) {
        // Paso 1: Creamos el nuevo nodo con el dato
        NodoLSE<T> nuevoNodo = new NodoLSE<>(dato);

        // Paso 2: El "siguiente" del nuevo nodo apunta a la cabeza actual.
        // (Nota: Si la lista estaba vacía, cabeza es null, así que el nuevo nodo apuntará a null. ¡Eso es correcto!)
        nuevoNodo.siguiente = cabeza;

        // Paso 3: La cabeza de la lista pasa a ser este nuevo nodo
        cabeza = nuevoNodo;

        // Paso 4: Aumentamos el contador de elementos
        tamaño++;
    }
    @Override
    public T get(T dato) {
        NodoLSE<T> act = cabeza;
        while (act != null) {
            if (act.dato.compareTo(dato) == 0) return act.dato;
            act = act.siguiente;
        }
        return null;
    }

    @Override
    public T eliminar(T dato) {
        NodoLSE<T> act = cabeza, ant = null;
        while (act != null) {
            if (act.dato.compareTo(dato) == 0) {
                if (ant == null) cabeza = act.siguiente;
                else ant.siguiente = act.siguiente;
                tamaño--;
                return act.dato;
            }
            ant = act;
            act = act.siguiente;
        }
        return null;
    }

    @Override public boolean estaVacia() { return cabeza == null; }
    @Override public int getTamaño() { return tamaño; }

    @Override
    public void vaciar() {
        cabeza = null;
        tamaño = 0;
    }

    @Override
    public boolean contiene(T dato) {
        return get(dato) != null;
    }

    @Override
    public int getPosicion(T dato) {
        NodoLSE<T> act = cabeza;
        int posicion = 0;

        while (act != null) {
            if (act.dato.compareTo(dato) == 0) {
                return posicion; // encontrado
            }
            act = act.siguiente;
            posicion++;
        }
        return -1; // no encontrado
    }

    @Override
    public T busqPos(int posicion) {

        if (posicion < 0 && posicion >= tamaño){
            return null;
        }

        NodoLSE<T> act = cabeza;
        int ind = 0;
        while (ind < posicion) {
            act = act.siguiente;
            ind++;
        }
        return act.dato;
    }

    @Override
    public T getPrimero() {
        if (cabeza != null) {
            return cabeza.dato;
        } else {
            return null;
        }
    }

    @Override
    public T getUltimo() {
        if (cabeza == null) return null;
        NodoLSE<T> act = cabeza;
        while (act.siguiente != null) act = act.siguiente;
        return act.dato;
    }

    @Override
    public void imprimirLista() {
        NodoLSE<T> act = cabeza;
        System.out.print("[");
        while (act != null) {
            if (act.siguiente != null) {
                System.out.print(act.dato + ", ");
            }
            else{
            System.out.print(act.dato);
            }
            act = act.siguiente;
        }
        System.out.println("]");
    }
    @Override
    public Iterador<T> getIterador() {
        return new IteradorLSE<>(this.cabeza);
    }
}
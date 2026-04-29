package EL2aArbolesBinarios;

// Nodo para listas simples
public class NodoLSE<T> {
    T dato;
    NodoLSE<T> siguiente;

    public NodoLSE(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
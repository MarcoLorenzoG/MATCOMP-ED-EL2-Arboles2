package EL2aArbolesBinarios;

public class IteradorLSE<T> implements Iterador<T> {
    private NodoLSE<T> actual;

    public IteradorLSE(NodoLSE<T> comienzo) {
        this.actual = comienzo;
    }

    @Override
    public boolean hasNext() {
        return actual != null;
    }

    @Override
    public T next() {
        if (!hasNext()) return null;
        T dato = actual.dato;
        actual = actual.siguiente;
        return dato;
    }
}
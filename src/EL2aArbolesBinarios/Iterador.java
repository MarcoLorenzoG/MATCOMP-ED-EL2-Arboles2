package EL2aArbolesBinarios;

// Interfaz para el iterador personalizado
public interface Iterador<T> {
    boolean hasNext();
    T next();
}
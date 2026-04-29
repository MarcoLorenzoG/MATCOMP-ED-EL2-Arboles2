package EL2aArbolesBinarios;

// Comparable obliga a que los objetos de datos puedan ser comparables entre sí para establecer orden
public interface Lista<T extends Comparable<T>> {
    void add(T dato);
    void addPrimero(T dato);
    T get(T dato);
    T eliminar(T dato);
    boolean estaVacia();
    int getTamaño();
    void vaciar();
    boolean contiene(T dato);
    int getPosicion(T dato);
    T busqPos(int indice);
    T getPrimero();
    T getUltimo();
    void imprimirLista();
    Iterador<T> getIterador();
}

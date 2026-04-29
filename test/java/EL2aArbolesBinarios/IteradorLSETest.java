package EL2aArbolesBinarios;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IteradorLSETest {

    @Test
    void hasNext() {
        IteradorLSE<Integer> itVacio = new IteradorLSE<>(null);
        assertFalse(itVacio.hasNext());

        NodoLSE<Integer> nodo = new NodoLSE<>(10);
        IteradorLSE<Integer> it = new IteradorLSE<>(nodo);
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    void next() {
        NodoLSE<Integer> nodo2 = new NodoLSE<>(20);
        NodoLSE<Integer> nodo1 = new NodoLSE<>(10);
        nodo1.siguiente = nodo2;

        IteradorLSE<Integer> it = new IteradorLSE<>(nodo1);

        assertEquals(10, it.next());
        assertEquals(20, it.next());
        assertNull(it.next());
    }
}

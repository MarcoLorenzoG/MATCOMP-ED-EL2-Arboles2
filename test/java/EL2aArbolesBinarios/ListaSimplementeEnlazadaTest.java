package EL2aArbolesBinarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListaSimplementeEnlazadaTest {

    private ListaSimplementeEnlazada<Integer> lista;

    @BeforeEach
    void setUp() {
        lista = new ListaSimplementeEnlazada<>();
    }

    @Test
    void add() {
        lista.add(10);
        lista.add(20);
        assertEquals(2, lista.getTamaño());
        assertEquals(10, lista.getPrimero());
        assertEquals(20, lista.getUltimo());
    }

    @Test
    void addPrimero() {
        lista.add(10);
        lista.addPrimero(5);
        assertEquals(2, lista.getTamaño());
        assertEquals(5, lista.getPrimero());
    }

    @Test
    void get() {
        lista.add(10);
        lista.add(20);
        assertEquals(10, lista.get(10));
        assertNull(lista.get(30));
    }

    @Test
    void eliminar() {
        assertNull(lista.eliminar(10));

        lista.add(10);
        lista.add(20);
        lista.add(30);

        assertNull(lista.eliminar(40));

        assertEquals(10, lista.eliminar(10));
        assertEquals(2, lista.getTamaño());
        assertEquals(20, lista.getPrimero());

        assertEquals(30, lista.eliminar(30));
        assertEquals(1, lista.getTamaño());
        assertEquals(20, lista.getUltimo());
    }

    @Test
    void estaVacia() {
        assertTrue(lista.estaVacia());
        lista.add(1);
        assertFalse(lista.estaVacia());
    }

    @Test
    void getTamaño() {
        assertEquals(0, lista.getTamaño());
        lista.add(1);
        lista.add(2);
        assertEquals(2, lista.getTamaño());
    }

    @Test
    void vaciar() {
        lista.add(1);
        lista.add(2);
        lista.vaciar();
        assertTrue(lista.estaVacia());
        assertEquals(0, lista.getTamaño());
    }

    @Test
    void contiene() {
        lista.add(50);
        assertTrue(lista.contiene(50));
        assertFalse(lista.contiene(100));
    }

    @Test
    void getPosicion() {
        lista.add(10);
        lista.add(20);
        lista.add(30);
        assertEquals(0, lista.getPosicion(10));
        assertEquals(1, lista.getPosicion(20));
        assertEquals(2, lista.getPosicion(30));
        assertEquals(-1, lista.getPosicion(40));
    }

    @Test
    void busqPos() {
        assertNull(lista.busqPos(0));

        lista.add(10);
        lista.add(20);
        lista.add(30);

        assertNull(lista.busqPos(-1));

        assertNull(lista.busqPos(3));

        assertEquals(10, lista.busqPos(0));

        assertEquals(30, lista.busqPos(2));
    }

    @Test
    void getPrimero() {
        assertNull(lista.getPrimero());
        lista.add(100);
        assertEquals(100, lista.getPrimero());
    }

    @Test
    void getUltimo() {
        assertNull(lista.getUltimo());
        lista.add(100);
        lista.add(200);
        assertEquals(200, lista.getUltimo());
    }

    @Test
    void imprimirLista() {
        // Method prints to console, we verify it doesn't crash
        lista.add(1);
        lista.add(2);
        assertDoesNotThrow(() -> lista.imprimirLista());
    }

    @Test
    void getIterador() {
        lista.add(1);
        lista.add(2);
        Iterador<Integer> it = lista.getIterador();
        assertTrue(it.hasNext());
        assertEquals(1, it.next());
        assertEquals(2, it.next());
        assertFalse(it.hasNext());
    }
}

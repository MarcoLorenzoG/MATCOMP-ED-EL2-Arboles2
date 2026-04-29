package EL2aArbolesBinarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArbolBinarioDeBusquedaTest {

    private ArbolBinarioDeBusqueda<Integer> arbol;

    @BeforeEach
    void setUp() {
        arbol = new ArbolBinarioDeBusqueda<>();
    }

    @Test
    void addRecursiva() {
        arbol.add(10);

        arbol.add(5);

        arbol.add(15);

        arbol.add(10);

        assertEquals(3, arbol.getListaOrdenCentral().getTamaño());
        assertEquals(5, arbol.getListaOrdenCentral().busqPos(0));
        assertEquals(10, arbol.getListaOrdenCentral().busqPos(1));
        assertEquals(15, arbol.getListaOrdenCentral().busqPos(2));
    }

    @Test
    void getGrado() {
        assertEquals(0, arbol.getGrado());
        arbol.add(50);
        assertEquals(0, arbol.getGrado());
        arbol.add(30);
        assertEquals(1, arbol.getGrado());
        arbol.add(70);
        assertEquals(2, arbol.getGrado());
    }

    @Test
    void getGradoRec() {
        arbol.add(50);
        arbol.add(30);
        arbol.add(70);
        assertEquals(2, arbol.getGradoRec(arbol.raiz));
    }

    @Test
    void getAltura() {
        assertEquals(0, arbol.getAltura());
        arbol.add(50);
        assertEquals(0, arbol.getAltura());
        arbol.add(30);
        assertEquals(1, arbol.getAltura());
        arbol.add(70);
        arbol.add(20);
        assertEquals(2, arbol.getAltura());
    }

    @Test
    void getAlturaRec() {
        assertEquals(0, arbol.getAltura());

        arbol.add(10);
        assertEquals(0, arbol.getAltura());

        arbol.add(5);
        arbol.add(15);
        assertEquals(1, arbol.getAltura());

        arbol.add(3);
        arbol.add(20);
        assertEquals(2, arbol.getAltura());
    }

    @Test
    void getListaDatosNivel() {
        arbol.add(50);
        arbol.add(30);
        arbol.add(70);
        ListaSimplementeEnlazada<Integer> nivel1 = arbol.getListaDatosNivel(1);
        assertEquals(2, nivel1.getTamaño());
    }

    @Test
    void getListaDatosNivelRec() {
        assertEquals(0, arbol.getListaDatosNivel(0).getTamaño());

        arbol.add(10);
        arbol.add(5);
        arbol.add(15);
        arbol.add(3);
        arbol.add(7);

        ListaSimplementeEnlazada<Integer> nivel0 = arbol.getListaDatosNivel(0);
        assertEquals(1, nivel0.getTamaño());
        assertEquals(10, nivel0.busqPos(0));

        ListaSimplementeEnlazada<Integer> nivel1 = arbol.getListaDatosNivel(1);
        assertEquals(2, nivel1.getTamaño());
        assertEquals(5, nivel1.busqPos(0));
        assertEquals(15, nivel1.busqPos(1));

        ListaSimplementeEnlazada<Integer> nivel2 = arbol.getListaDatosNivel(2);
        assertEquals(2, nivel2.getTamaño());
        assertEquals(3, nivel2.busqPos(0));
        assertEquals(7, nivel2.busqPos(1));

        ListaSimplementeEnlazada<Integer> nivel3 = arbol.getListaDatosNivel(3);
        assertEquals(0, nivel3.getTamaño());
    }

    @Test
    void isArbolHomogeneo() {
        assertTrue(arbol.isArbolHomogeneo());

        arbol.add(10);
        assertTrue(arbol.isArbolHomogeneo());

        arbol.add(15);
        assertTrue(arbol.isArbolHomogeneo());

        arbol.add(5);
        arbol.add(3);
        assertFalse(arbol.isArbolHomogeneo());

        arbol.add(7);
        assertTrue(arbol.isArbolHomogeneo());

        arbol.add(20);
        assertFalse(arbol.isArbolHomogeneo());
    }

    @Test
    void isArbolHomogeneoRec() {
        arbol.add(50);
        arbol.add(30);
        arbol.add(70);
        assertTrue(arbol.isArbolHomogeneoRec(arbol.raiz, 2));
    }

    @Test
    void isArbolCompleto() {
        assertTrue(arbol.isArbolCompleto());
        arbol.add(10);
        assertTrue(arbol.isArbolCompleto());
        arbol.add(5);
        assertFalse(arbol.isArbolCompleto());
        arbol.add(15);
        assertTrue(arbol.isArbolCompleto());
    }

    @Test
    void isArbolCompletoRec() {
        assertTrue(arbol.isArbolCompleto());

        assertTrue(arbol.isArbolCompletoRec(null, 0, 0));

        arbol.add(10);
        assertTrue(arbol.isArbolCompleto());

        arbol.add(15);
        assertFalse(arbol.isArbolCompleto());

        arbol.add(5);
        assertTrue(arbol.isArbolCompleto());

        assertFalse(arbol.isArbolCompletoRec(arbol.raiz, 0, 0));

        ArbolBinarioDeBusquedaEnteros arbolDer = new ArbolBinarioDeBusquedaEnteros();
        arbolDer.add(10);
        arbolDer.add(15);
        assertFalse(arbolDer.isArbolCompletoRec(arbolDer.raiz, 0, 0));

        ArbolBinarioDeBusquedaEnteros arbolIzq = new ArbolBinarioDeBusquedaEnteros();
        arbolIzq.add(10);
        arbolIzq.add(5);
        assertFalse(arbolIzq.isArbolCompleto());

        arbol.add(3);
        assertFalse(arbol.isArbolCompleto());

        arbol.add(7);
        assertFalse(arbol.isArbolCompleto());

        arbol.add(12);
        assertFalse(arbol.isArbolCompleto());

        arbol.add(20);
        assertTrue(arbol.isArbolCompleto());

        arbol.add(25);
        assertFalse(arbol.isArbolCompleto());

        arbol.add(1);
        assertFalse(arbol.isArbolCompleto());
    }

    @Test
    void isArbolCasiCompleto() {
        assertTrue(arbol.isArbolCasiCompleto());

        arbol.add(10);
        assertTrue(arbol.isArbolCasiCompleto());

        arbol.add(5);
        assertTrue(arbol.isArbolCasiCompleto());

        arbol.add(15);
        assertTrue(arbol.isArbolCasiCompleto());

        arbol.add(3);
        assertTrue(arbol.isArbolCasiCompleto());

        arbol.add(20);
        assertFalse(arbol.isArbolCasiCompleto());

        ArbolBinarioDeBusquedaEnteros arbol2 = new ArbolBinarioDeBusquedaEnteros();
        arbol2.add(10);
        arbol2.add(15);
        assertFalse(arbol2.isArbolCasiCompleto());
    }

    @Test
    void getCamino() {
        assertEquals(0, arbol.getCamino(10).getTamaño());

        arbol.add(10);

        ListaSimplementeEnlazada<Integer> caminoRaiz = arbol.getCamino(10);
        assertEquals(1, caminoRaiz.getTamaño());
        assertEquals(10, caminoRaiz.busqPos(0));

        arbol.add(5);
        arbol.add(15);
        arbol.add(3);

        ListaSimplementeEnlazada<Integer> caminoIzq = arbol.getCamino(3);
        assertEquals(3, caminoIzq.getTamaño());
        assertEquals(10, caminoIzq.busqPos(0));
        assertEquals(5, caminoIzq.busqPos(1));
        assertEquals(3, caminoIzq.busqPos(2));

        ListaSimplementeEnlazada<Integer> caminoDer = arbol.getCamino(15);
        assertEquals(2, caminoDer.getTamaño());
        assertEquals(10, caminoDer.busqPos(0));
        assertEquals(15, caminoDer.busqPos(1));

        ListaSimplementeEnlazada<Integer> caminoNoEncontradoDer = arbol.getCamino(20);
        assertEquals(2, caminoNoEncontradoDer.getTamaño());
        assertEquals(10, caminoNoEncontradoDer.busqPos(0));
        assertEquals(15, caminoNoEncontradoDer.busqPos(1));

        ListaSimplementeEnlazada<Integer> caminoNoEncontradoIzq = arbol.getCamino(1);
        assertEquals(3, caminoNoEncontradoIzq.getTamaño());
        assertEquals(10, caminoNoEncontradoIzq.busqPos(0));
        assertEquals(5, caminoNoEncontradoIzq.busqPos(1));
        assertEquals(3, caminoNoEncontradoIzq.busqPos(2));
    }

    @Test
    void add() {
        arbol.add(10);
        assertFalse(arbol.getListaPreOrden().estaVacia());
        assertEquals(10, arbol.getListaPreOrden().busqPos(0));
    }

    @Test
    void getSubArbolIzquierda() {
        assertEquals(0, arbol.getSubArbolIzquierda().getListaOrdenCentral().getTamaño());

        arbol.add(10);
        arbol.add(5);

        assertEquals(1, arbol.getSubArbolIzquierda().getListaOrdenCentral().getTamaño());
        assertEquals(5, arbol.getSubArbolIzquierda().getListaOrdenCentral().busqPos(0));
    }

    @Test
    void getSubArbolDerecha() {
        assertEquals(0, arbol.getSubArbolDerecha().getListaOrdenCentral().getTamaño());
        arbol.add(10);
        arbol.add(15);
        ArbolBinarioDeBusqueda<Integer> sub = arbol.getSubArbolDerecha();
        assertEquals(15, sub.raiz.dato);
    }

    @Test
    void getListaPreOrden() {
        arbol.add(50);
        arbol.add(30);
        arbol.add(70);
        ListaSimplementeEnlazada<Integer> lista = arbol.getListaPreOrden();
        assertEquals(50, lista.busqPos(0));
        assertEquals(30, lista.busqPos(1));
        assertEquals(70, lista.busqPos(2));
    }

    @Test
    void preOrdenRec() {
        arbol.add(10);
        ListaSimplementeEnlazada<Integer> lista = new ListaSimplementeEnlazada<>();
        arbol.preOrdenRec(arbol.raiz, lista);
        assertEquals(1, lista.getTamaño());
    }

    @Test
    void getListaPostOrden() {
        arbol.add(50);
        arbol.add(30);
        arbol.add(70);
        ListaSimplementeEnlazada<Integer> lista = arbol.getListaPostOrden();
        assertEquals(30, lista.busqPos(0));
        assertEquals(70, lista.busqPos(1));
        assertEquals(50, lista.busqPos(2));
    }

    @Test
    void postOrdenRec() {
        arbol.add(10);
        ListaSimplementeEnlazada<Integer> lista = new ListaSimplementeEnlazada<>();
        arbol.postOrdenRec(arbol.raiz, lista);
        assertEquals(1, lista.getTamaño());
    }

    @Test
    void getListaOrdenCentral() {
        arbol.add(50);
        arbol.add(30);
        arbol.add(70);
        ListaSimplementeEnlazada<Integer> lista = arbol.getListaOrdenCentral();
        assertEquals(30, lista.busqPos(0));
        assertEquals(50, lista.busqPos(1));
        assertEquals(70, lista.busqPos(2));
    }

    @Test
    void ordenCentralRec() {
        arbol.add(10);
        ListaSimplementeEnlazada<Integer> lista = new ListaSimplementeEnlazada<>();
        arbol.ordenCentralRec(arbol.raiz, lista);
        assertEquals(1, lista.getTamaño());
    }
}

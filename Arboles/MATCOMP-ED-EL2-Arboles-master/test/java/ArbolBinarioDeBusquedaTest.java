import EL2aArbolesBinarios.ArbolBinarioDeBusqueda;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArbolBinarioDeBusquedaTest {

    @Test
    void addRecursiva() {
        // Probado a través de add()
    }

    @Test
    void getGrado() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        assertEquals(0, arbol.getGrado());
        arbol.add(10);
        assertEquals(0, arbol.getGrado());
        arbol.add(5);
        assertEquals(1, arbol.getGrado());
        arbol.add(15);
        assertEquals(2, arbol.getGrado());
    }

    @Test
    void getGradoRec() {
        // Probado indirectamente a través de getGrado
    }

    @Test
    void getAltura() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        assertEquals(0, arbol.getAltura());
        arbol.add(10);
        assertEquals(0, arbol.getAltura());
        arbol.add(5);
        arbol.add(15);
        arbol.add(2);
        assertEquals(2, arbol.getAltura());
    }

    @Test
    void getAlturaRec() {
        // Probado indirectamente a través de getAltura
    }

    @Test
    void getListaDatosNivel() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(10);
        arbol.add(5);
        arbol.add(15);
        arbol.add(2);
        assertEquals(List.of(10), arbol.getListaDatosNivel(0));
        assertEquals(List.of(5, 15), arbol.getListaDatosNivel(1));
        assertEquals(List.of(2), arbol.getListaDatosNivel(2));
    }

    @Test
    void getListaDatosNivelRec() {
        // Probado indirectamente
    }

    @Test
    void isArbolHomogeneo() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        assertTrue(arbol.isArbolHomogeneo());
        arbol.add(10);
        arbol.add(5);
        arbol.add(15);
        assertTrue(arbol.isArbolHomogeneo()); // Grado max 2, raíz tiene 2.
        arbol.add(2);
        assertFalse(arbol.isArbolHomogeneo()); // Nodo 5 tiene 1 hijo, gradoMax es 2.
    }

    @Test
    void isArbolHomogeneoRec() {
        // Probado indirectamente
    }

    @Test
    void isArbolCompleto() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
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
        // Probado indirectamente
    }

    @Test
    void isArbolCasiCompleto() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(10);
        arbol.add(5);
        assertTrue(arbol.isArbolCasiCompleto());
        arbol.add(15);
        assertTrue(arbol.isArbolCasiCompleto());
        
        ArbolBinarioDeBusqueda<Integer> arbolIncompleto = new ArbolBinarioDeBusqueda<>();
        arbolIncompleto.add(10);
        arbolIncompleto.add(15); // Falta el hijo izquierdo del 10
        assertFalse(arbolIncompleto.isArbolCasiCompleto());
    }

    @Test
    void getCamino() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(10);
        arbol.add(5);
        arbol.add(15);
        arbol.add(12);
        assertEquals(List.of(10, 15, 12), arbol.getCamino(12));
        assertTrue(arbol.getCamino(100).isEmpty());
    }

    @Test
    void add() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(50);
        arbol.add(30);
        arbol.add(70);
        assertEquals(List.of(30, 50, 70), arbol.getListaOrdenCentral());
    }

    @Test
    void getSubArbolIzquierda() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(10);
        arbol.add(5);
        arbol.add(2);
        ArbolBinarioDeBusqueda<Integer> sub = arbol.getSubArbolIzquierda();
        assertEquals(List.of(2, 5), sub.getListaOrdenCentral());
    }

    @Test
    void getSubArbolDerecha() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(10);
        arbol.add(15);
        arbol.add(20);
        ArbolBinarioDeBusqueda<Integer> sub = arbol.getSubArbolDerecha();
        assertEquals(List.of(15, 20), sub.getListaOrdenCentral());
    }

    @Test
    void getListaPreOrden() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(10);
        arbol.add(5);
        arbol.add(15);
        assertEquals(List.of(10, 5, 15), arbol.getListaPreOrden());
    }

    @Test
    void preOrdenRec() {
        // Probado indirectamente
    }

    @Test
    void getListaPostOrden() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(10);
        arbol.add(5);
        arbol.add(15);
        assertEquals(List.of(5, 15, 10), arbol.getListaPostOrden());
    }

    @Test
    void postOrdenRec() {
        // Probado indirectamente
    }

    @Test
    void getListaOrdenCentral() {
        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(10);
        arbol.add(5);
        arbol.add(15);
        assertEquals(List.of(5, 10, 15), arbol.getListaOrdenCentral());
    }

    @Test
    void ordenCentralRec() {
        // Probado indirectamente
    }
}

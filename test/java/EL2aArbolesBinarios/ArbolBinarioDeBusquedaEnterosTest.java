package EL2aArbolesBinarios;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArbolBinarioDeBusquedaEnterosTest {

    @Test
    void getSuma() {
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();
        assertEquals(0, arbol.getSuma());

        arbol.add(10);
        arbol.add(5);
        arbol.add(15);
        
        assertEquals(30, arbol.getSuma());
    }

    @Test
    void getSubArbolIzquierda() {
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();

        ArbolBinarioDeBusquedaEnteros izqVacio = arbol.getSubArbolIzquierda();
        assertNotNull(izqVacio);
        assertNull(izqVacio.raiz);

        arbol.add(10);
        arbol.add(5);
        arbol.add(15);

        ArbolBinarioDeBusquedaEnteros izq = arbol.getSubArbolIzquierda();
        assertNotNull(izq);
        assertEquals(5, izq.getSuma());
    }

    @Test
    void getSubArbolDerecha() {
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();

        ArbolBinarioDeBusquedaEnteros derVacio = arbol.getSubArbolDerecha();
        assertNotNull(derVacio);
        assertNull(derVacio.raiz);

        arbol.add(10);
        arbol.add(5);
        arbol.add(15);

        ArbolBinarioDeBusquedaEnteros der = arbol.getSubArbolDerecha();
        assertNotNull(der);
        assertEquals(15, der.getSuma());
    }
}

import EL2aArbolesBinarios.ArbolBinarioDeBusquedaEnteros;
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
        arbol.add(10);
        arbol.add(5);
        ArbolBinarioDeBusquedaEnteros sub = arbol.getSubArbolIzquierda();
        assertNotNull(sub);
        assertEquals(5, sub.getSuma());
    }

    @Test
    void getSubArbolDerecha() {
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();
        arbol.add(10);
        arbol.add(15);
        ArbolBinarioDeBusquedaEnteros sub = arbol.getSubArbolDerecha();
        assertNotNull(sub);
        assertEquals(15, sub.getSuma());
    }
}

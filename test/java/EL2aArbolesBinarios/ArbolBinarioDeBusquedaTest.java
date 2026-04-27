package EL2aArbolesBinarios;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

class ArbolBinarioDeBusquedaTest {

    private ArbolBinarioDeBusqueda<Integer> arbol;

    @BeforeEach
    void setUp() {
        arbol = new ArbolBinarioDeBusqueda<>();
    }

    @Test
    void add() {
        arbol.add(50);
        arbol.add(30);
        arbol.add(70);
        assertEquals(List.of(30, 50, 70), arbol.getListaOrdenCentral());
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
    void getListaDatosNivel() {
        arbol.add(50);
        arbol.add(30);
        arbol.add(70);
        arbol.add(20);
        arbol.add(40);

        assertEquals(List.of(50), arbol.getListaDatosNivel(0));
        assertEquals(List.of(30, 70), arbol.getListaDatosNivel(1));
        assertEquals(List.of(20, 40), arbol.getListaDatosNivel(2));
        assertTrue(arbol.getListaDatosNivel(3).isEmpty());
    }

    @Test
    void isArbolHomogeneo() {
        arbol.add(50);
        assertTrue(arbol.isArbolHomogeneo(), "Un solo nodo es homogéneo");
        
        arbol.add(30);
        arbol.add(70);
        assertTrue(arbol.isArbolHomogeneo(), "Grado max 2, todos tienen 0 o 2");

        arbol.add(20);
        assertFalse(arbol.isArbolHomogeneo(), "Nodo 30 tiene grado 1, no es homogéneo");
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

        arbol.add(3);
        assertFalse(arbol.isArbolCompleto());
    }

    @Test
    void isArbolCasiCompleto() {
        assertTrue(arbol.isArbolCasiCompleto());

        arbol.add(10);
        assertTrue(arbol.isArbolCasiCompleto());

        arbol.add(5);
        assertTrue(arbol.isArbolCasiCompleto());

        arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(10);
        arbol.add(15);
        assertFalse(arbol.isArbolCasiCompleto());

        arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(10);
        arbol.add(5);
        arbol.add(15);
        assertTrue(arbol.isArbolCasiCompleto());

        arbol.add(3);
        arbol.add(20);
        assertFalse(arbol.isArbolCasiCompleto());
    }
    @Test
    void getCamino() {
        arbol.add(50);
        arbol.add(30);
        arbol.add(70);
        arbol.add(40);

        assertEquals(List.of(50, 30, 40), arbol.getCamino(40));
        assertEquals(List.of(), arbol.getCamino(100));
    }

    @Test
    void getSubArbolIzquierda() {
        ArbolBinarioDeBusqueda<Integer> subArbolVacio = arbol.getSubArbolIzquierda();
        assertNull(subArbolVacio.raiz);

        arbol.add(10);
        arbol.add(5);
        arbol.add(15);

        ArbolBinarioDeBusqueda<Integer> subArbol = arbol.getSubArbolIzquierda();
        assertNotNull(subArbol.raiz);
        assertEquals(5, subArbol.raiz.dato);
    }

    @Test
    void getSubArbolDerecha() {
        ArbolBinarioDeBusqueda<Integer> subArbolVacio = arbol.getSubArbolDerecha();
        assertNull(subArbolVacio.raiz);

        arbol.add(10);
        arbol.add(5);
        arbol.add(15);

        ArbolBinarioDeBusqueda<Integer> subArbol = arbol.getSubArbolDerecha();
        assertNotNull(subArbol.raiz);
        assertEquals(15, subArbol.raiz.dato);
    }

    @Test
    void getListaPreOrden() {
        arbol.add(50);
        arbol.add(30);
        arbol.add(70);
        assertEquals(List.of(50, 30, 70), arbol.getListaPreOrden());
    }

    @Test
    void getListaPostOrden() {
        arbol.add(50);
        arbol.add(30);
        arbol.add(70);
        assertEquals(List.of(30, 70, 50), arbol.getListaPostOrden());
    }

    @Test
    void getListaOrdenCentral() {
        arbol.add(50);
        arbol.add(30);
        arbol.add(70);
        arbol.add(20);
        assertEquals(List.of(20, 30, 50, 70), arbol.getListaOrdenCentral());
    }

    // Los métodos recursivos protegidos se prueban a través de los métodos públicos anteriores.
    @Test
    void addRecursiva() {
        arbol.add(10);
        assertEquals(10, arbol.raiz.dato, "La raíz debería ser 10");

        arbol.add(5);
        assertEquals(5, arbol.raiz.izq.dato, "El hijo izquierdo debería ser 5");

        arbol.add(15);
        assertEquals(15, arbol.raiz.der.dato, "El hijo derecho debería ser 15");

        arbol.add(10);

        List<Integer> orden = arbol.getListaOrdenCentral();
        assertEquals(3, orden.size(), "El tamaño debería seguir siendo 3 (el duplicado no se añade)");
        assertIterableEquals(List.of(5, 10, 15), orden);
    }


    @Test
    void getGradoRec() {
        // Probado vía getGrado()
    }

    @Test
    void getAlturaRec() {
            assertEquals(0, arbol.getAltura(), "Un árbol vacío debe tener altura 0");

            arbol.add(10);
            assertEquals(0, arbol.getAltura(), "Un nodo hoja debe tener altura 0");

            arbol.add(5);
            assertEquals(1, arbol.getAltura(), "Árbol con un hijo izquierdo debe tener altura 1");

            arbol = new ArbolBinarioDeBusqueda<>();
            arbol.add(10);

            arbol.add(15);
            assertEquals(1, arbol.getAltura(), "Árbol con un hijo derecho debe tener altura 1");

            arbol.add(5);
            arbol.add(20);

            assertEquals(2, arbol.getAltura(), "La altura debe ser 2 (camino 10-15-20)");
        }

    @Test
    void getListaDatosNivelRec() {
        // Probado vía getListaDatosNivel()
    }

    @Test
    void isArbolHomogeneoRec() {
        assertTrue(arbol.isArbolHomogeneo());

        arbol.add(10);
        assertTrue(arbol.isArbolHomogeneo());

        arbol.add(15);
        arbol.add(20);
        assertTrue(arbol.isArbolHomogeneo());

        arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(10);
        arbol.add(5);
        arbol.add(15);
        assertTrue(arbol.isArbolHomogeneo());

        arbol.add(3);
        assertFalse(arbol.isArbolHomogeneo());

        arbol.add(7);
        arbol.add(20);
        assertFalse(arbol.isArbolHomogeneo());
    }


    @Test
    void isArbolCompletoRec() {
        assertTrue(arbol.isArbolCompletoRec(null, 0, 0));

        arbol.add(10);
        assertTrue(arbol.isArbolCompletoRec(arbol.raiz, 0, 0));

        arbol.add(5);
        assertFalse(arbol.isArbolCompletoRec(arbol.raiz, 0, 0));

        arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(10);
        arbol.add(15);
        assertFalse(arbol.isArbolCompletoRec(arbol.raiz, 0, 0));

        assertFalse(arbol.isArbolCompletoRec(arbol.raiz, 0, 1));

        arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(10);
        arbol.add(5);
        assertFalse(arbol.isArbolCompletoRec(arbol.raiz, 0, 1));

        arbol.add(15);
        assertTrue(arbol.isArbolCompletoRec(arbol.raiz, 0, 1));

        arbol.add(3);
        assertFalse(arbol.isArbolCompletoRec(arbol.raiz, 0, 2));

        arbol = new ArbolBinarioDeBusqueda<>();
        arbol.add(10);
        arbol.add(5);
        arbol.add(15);
        arbol.add(3);
        arbol.add(7);
        arbol.add(20);
        assertFalse(arbol.isArbolCompletoRec(arbol.raiz, 0, 2));
    }

    @Test
    void preOrdenRec() {
        // Probado vía getListaPreOrden()
    }

    @Test
    void postOrdenRec() {
        // Probado vía getListaPostOrden()
    }

    @Test
    void ordenCentralRec() {
        // Probado vía getListaOrdenCentral()
    }
}

package EL2bGrafos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TripletaTest {

    @Test
    void getS() {
        Tripleta f = new Tripleta();
        Tripleta t = new Tripleta("sujeto", "predicado", "objeto");
        assertEquals("sujeto", t.getS());
    }

    @Test
    void getP() {
        Tripleta t = new Tripleta("sujeto", "predicado", "objeto");
        assertEquals("predicado", t.getP());
    }

    @Test
    void getO() {
        Tripleta t = new Tripleta("sujeto", "predicado", "objeto");
        assertEquals("objeto", t.getO());
    }

    @Test
    void testToString() {
        Tripleta t = new Tripleta("s", "p", "o");
        assertEquals("<\"s\", \"p\", \"o\">", t.toString());
    }
}

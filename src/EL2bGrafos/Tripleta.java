package EL2bGrafos;

public class Tripleta {
    private String s;
    private String p;
    private String o;

    // Constructor por defecto
    public Tripleta() {
    }

    // Constructor con parámetros
    public Tripleta(String s, String p, String o) {
        this.s = s;
        this.p = p;
        this.o = o;
    }

    // Getters para los atributos
    public String getS() {
        return s;
    }

    public String getP() {
        return p;
    }

    public String getO() {
        return o;
    }

    @Override
    // Representación en cadena de la tripleta
    public String toString() {
        return "<\"" + s + "\", \"" + p + "\", \"" + o + "\">";
    }
}

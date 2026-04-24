package EL2bGrafos;

public class Tripleta {
    private String s;
    private String p;
    private String o;

    public Tripleta() {
    }

    public Tripleta(String s, String p, String o) {
        this.s = s;
        this.p = p;
        this.o = o;
    }

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
    public String toString() {
        return "<\"" + s + "\", \"" + p + "\", \"" + o + "\">";
    }
}

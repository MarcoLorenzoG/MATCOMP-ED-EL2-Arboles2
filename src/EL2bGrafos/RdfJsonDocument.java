package EL2bGrafos;

import java.util.List;

public class RdfJsonDocument {
    private List<String> tipos;
    private List<Tripleta> tripletas;

    // Obtener la lista de tipos declarados en el documento JSON
    public List<String> getTipos() {
        return tipos;
    }

    // Obtener la lista de todas las tripletas contenidas en el documento JSON
    public List<Tripleta> getTripletas() {
        return tripletas;
    }
}

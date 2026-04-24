package EL2bGrafos;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class PracticaRdfApp {
    public static void main(String[] args) throws IOException {

        KnowledgeGraph grafoConexo = KnowledgeGraph.loadFromJson(Path.of("grafo_conexo.json"));
        KnowledgeGraph grafoDisjunto = KnowledgeGraph.loadFromJson(Path.of("grafo_disjunto.json"));
        KnowledgeGraph grafoNobel = KnowledgeGraph.loadFromJson(Path.of("grafo_nobel.json"));

        System.out.println("===== 1) Camino minimo =====");
        List<String> camino = grafoConexo.shortestPath("persona:Albert Einstein", "lugar:Copenhague");
        printPath(camino);

        System.out.println();
        System.out.println("===== 2) Comprobacion de disjuncion =====");
        System.out.println("grafo_conexo.json es disjunto? " + grafoConexo.isDisjointGraph());
        System.out.println("grafo_disjunto.json es disjunto? " + grafoDisjunto.isDisjointGraph());

        System.out.println();
        System.out.println("===== 3) Fisico famoso en la misma ciudad que Einstein =====");
        Optional<String> fisico = grafoNobel.famousPhysicistBornInSameCityAsEinstein();
        System.out.println("Respuesta: " + fisico.orElse("No encontrado"));

        System.out.println();
        System.out.println("===== 4) Insertar tripleta de Antonio y listar lugares Nobel =====");
        grafoNobel.addTripleta(new Tripleta(
                "persona:Antonio",
                "nace_en",
                "lugar:Villarrubia de los Caballeros"
        ));
        Set<String> lugaresNobel = grafoNobel.birthPlacesOfNobelLaureates();
        System.out.println("Lugares de nacimiento de personas con Nobel: " + lugaresNobel);
        System.out.println("Caminos que se recorren para responder: " + grafoNobel.requiredTraversalPathsForNobelBirthPlaces());

        System.out.println();
        System.out.println("===== 5) Tipos de nodos =====");
        System.out.println(grafoNobel.nodeTypes());
    }

    private static void printPath(List<String> path) {
        if (path.isEmpty()) {
            System.out.println("No existe camino.");
            return;
        }
        System.out.println(String.join(" -> ", path));
        System.out.println("Longitud (aristas): " + (path.size() - 1));
    }
}

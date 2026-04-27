package EL2bGrafos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class KnowledgeGraphTest {

    private KnowledgeGraph graph;

    @BeforeEach
    void setUp() {
        graph = new KnowledgeGraph();
    }

    @Test
    void testLoadFromJson(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("full.json");
        Files.writeString(file, "{\"tipos\":[\"persona\", \"lugar\"], \"tripletas\":[{\"s\":\"A\",\"p\":\"B\",\"o\":\"C\"}]}");
        KnowledgeGraph g1 = KnowledgeGraph.loadFromJson(file);
        assertEquals(1, g1.getTripletas().size());

        Path fileEmpty = tempDir.resolve("empty.json");
        Files.writeString(fileEmpty, "{}");
        KnowledgeGraph g2 = KnowledgeGraph.loadFromJson(fileEmpty);
        assertTrue(g2.getTripletas().isEmpty());
    }

    @Test
    void testAddTripletaAndGetTripletas() {
        graph.addTripleta(null);
        assertTrue(graph.getTripletas().isEmpty());

        graph.addTripleta(new Tripleta(null, "p", "o"));
        graph.addTripleta(new Tripleta("s", "p", null));
        graph.addTripleta(new Tripleta("", "p", "o"));
        graph.addTripleta(new Tripleta("s", "p", "  "));

        Tripleta t = new Tripleta("A", "B", "C");
        graph.addTripleta(t);
        assertEquals(5, graph.getTripletas().size());

        assertThrows(UnsupportedOperationException.class, () -> graph.getTripletas().add(new Tripleta()));
    }

    @Test
    void testConnectedComponentsAndIsDisjointGraph() {
        assertEquals(0, graph.connectedComponents());
        assertFalse(graph.isDisjointGraph());

        graph.addTripleta(new Tripleta("A", "p", "B"));
        assertEquals(1, graph.connectedComponents());
        assertFalse(graph.isDisjointGraph());

        graph.addTripleta(new Tripleta("C", "p", "D"));
        assertEquals(2, graph.connectedComponents());
        assertTrue(graph.isDisjointGraph());

        graph.addTripleta(new Tripleta("B", "p", "C"));
        graph.addTripleta(new Tripleta("A", "p", "C"));
        assertEquals(1, graph.connectedComponents());
        assertFalse(graph.isDisjointGraph());
    }

    @Test
    void testShortestPath() {
        assertTrue(graph.shortestPath(null, "B").isEmpty());
        assertTrue(graph.shortestPath("A", null).isEmpty());
        assertTrue(graph.shortestPath("A", "B").isEmpty());

        graph.addTripleta(new Tripleta("A", "p", "B"));
        graph.addTripleta(new Tripleta("B", "p", "C"));
        graph.addTripleta(new Tripleta("A", "p", "C"));
        graph.addTripleta(new Tripleta("D", "p", "E"));

        assertTrue(graph.shortestPath("A", "Z").isEmpty());
        assertEquals(List.of("A"), graph.shortestPath("A", "A"));

        List<String> pathAC = graph.shortestPath("A", "C");
        assertEquals(2, pathAC.size());
        assertEquals("A", pathAC.get(0));
        assertEquals("C", pathAC.get(1));

        assertTrue(graph.shortestPath("A", "D").isEmpty());
    }

    @Test
    void testBirthPlaceOfFullConditionCoverage() {
        assertTrue(graph.birthPlaceOf("persona:1").isEmpty());

        graph.addTripleta(new Tripleta("persona:2", "nace_en", "Madrid"));
        graph.addTripleta(new Tripleta("persona:1", "vive_en", "Madrid"));
        assertTrue(graph.birthPlaceOf("persona:1").isEmpty());

        graph.addTripleta(new Tripleta("persona:3", "nace_en", null));
        assertTrue(graph.birthPlaceOf("persona:3").isEmpty());

        graph.addTripleta(new Tripleta("persona:1", "nace_en", "Barcelona"));
        assertEquals("Barcelona", graph.birthPlaceOf("persona:1").get());
    }

    @Test
    void testBirthPlacesOfNobelLaureates() {
        assertTrue(graph.birthPlacesOfNobelLaureates().isEmpty());

        graph.addTripleta(new Tripleta("persona:A", null, "X"));
        graph.addTripleta(new Tripleta("persona:A", "premio:NobelFisica", "1921"));
        graph.addTripleta(new Tripleta("persona:A", "nace_en", "Ulm"));

        graph.addTripleta(new Tripleta("persona:B", "gana_premio", "premio:Nobel"));
        graph.addTripleta(new Tripleta("persona:B", "nace_en", "Paris"));

        graph.addTripleta(new Tripleta("persona:C", "premio:Oscar", "2000"));
        graph.addTripleta(new Tripleta("persona:C", "nace_en", "Madrid"));

        Set<String> places = graph.birthPlacesOfNobelLaureates();
        assertEquals(2, places.size());
        assertTrue(places.contains("Ulm"));
        assertTrue(places.contains("Paris"));
    }

    @Test
    void testFamousPhysicistBornInSameCityAsEinstein() {
        assertTrue(graph.famousPhysicistBornInSameCityAsEinstein().isEmpty());

        graph.addTripleta(new Tripleta("persona:Albert Einstein", "nace_en", "Ulm"));
        assertTrue(graph.famousPhysicistBornInSameCityAsEinstein().isEmpty());

        graph.addTripleta(new Tripleta("persona:A", "vive_en", "Ulm"));
        assertTrue(graph.famousPhysicistBornInSameCityAsEinstein().isEmpty());

        graph.addTripleta(new Tripleta("persona:B", "nace_en", "Madrid"));
        assertTrue(graph.famousPhysicistBornInSameCityAsEinstein().isEmpty());

        graph.addTripleta(new Tripleta("persona:C", "nace_en", "Ulm"));
        graph.addTripleta(new Tripleta("persona:C", "profesion", "quimico"));
        assertTrue(graph.famousPhysicistBornInSameCityAsEinstein().isEmpty());

        graph.addTripleta(new Tripleta("persona:D", "nace_en", "Ulm"));
        graph.addTripleta(new Tripleta("persona:D", "profesion", "fisico"));
        graph.addTripleta(new Tripleta("persona:D", "famoso", "false"));
        assertTrue(graph.famousPhysicistBornInSameCityAsEinstein().isEmpty());

        graph.addTripleta(new Tripleta("persona:E", "nace_en", "Ulm"));
        graph.addTripleta(new Tripleta("persona:E", "profesion", "fisico"));
        graph.addTripleta(new Tripleta("persona:E", "famoso", "true"));

        assertEquals("persona:E", graph.famousPhysicistBornInSameCityAsEinstein().get());
    }

    @Test
    void testNodeTypes(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("types.json");
        Files.writeString(file, "{\"tipos\":[\"tipo1\", \"\", null, \"  tipo2  \"]}");
        KnowledgeGraph g = KnowledgeGraph.loadFromJson(file);

        g.addTripleta(new Tripleta("persona:A", "premio:B", "literal"));
        g.addTripleta(new Tripleta(null, "", "  "));

        Set<String> types = g.nodeTypes();
        assertTrue(types.contains("tipo1"));
        assertTrue(types.contains("tipo2"));
        assertTrue(types.contains("persona"));
        assertTrue(types.contains("premio"));
        assertTrue(types.contains("literal"));
    }

    @Test
    void testRequiredTraversalPathsForNobelBirthPlaces() {
        List<String> paths = graph.requiredTraversalPathsForNobelBirthPlaces();
        assertEquals(2, paths.size());
        assertEquals("persona --(premio:Nobel)--> anyo", paths.get(0));
        assertEquals("persona --(nace_en)--> lugar", paths.get(1));
    }
    @Test
    void testBuildPath() throws Exception {
        java.lang.reflect.Method method = KnowledgeGraph.class.getDeclaredMethod(
                "buildPath", String.class, String.class, java.util.Map.class
        );
        method.setAccessible(true);

        java.util.Map<String, String> mapValido = java.util.Map.of("C", "B", "B", "A");
        List<?> result1 = (List<?>) method.invoke(graph, "A", "C", mapValido);
        assertEquals(List.of("A", "B", "C"), result1);

        List<?> result2 = (List<?>) method.invoke(graph, "A", null, java.util.Map.of());
        assertTrue(result2.isEmpty());

        List<?> result3 = (List<?>) method.invoke(graph, "A", "A", java.util.Map.of());
        assertEquals(List.of("A"), result3);

        java.util.Map<String, String> mapRoto = java.util.Map.of("C", "B");
        List<?> result4 = (List<?>) method.invoke(graph, "A", "C", mapRoto);
        assertTrue(result4.isEmpty());
    }
}
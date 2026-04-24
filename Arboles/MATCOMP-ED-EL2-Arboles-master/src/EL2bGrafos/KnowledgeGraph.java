package EL2bGrafos;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class KnowledgeGraph {
    private final Set<String> declaredTypes = new LinkedHashSet<>();
    private final List<Tripleta> tripletas = new ArrayList<>();
    private final Map<String, Set<String>> adjacency = new HashMap<>();

    public static KnowledgeGraph loadFromJson(Path file) throws IOException {
        Gson gson = new Gson();
        try (Reader reader = Files.newBufferedReader(file)) {
            RdfJsonDocument document = gson.fromJson(reader, RdfJsonDocument.class);
            KnowledgeGraph graph = new KnowledgeGraph();
            if (document.getTipos() != null) {
                graph.declaredTypes.addAll(document.getTipos());
            }
            if (document.getTripletas() != null) {
                for (Tripleta t : document.getTripletas()) {
                    graph.addTripleta(t);
                }
            }
            return graph;
        }
    }

    public void addTripleta(Tripleta tripleta) {
        if (tripleta == null) {
            return;
        }
        tripletas.add(tripleta);
        connectUndirected(tripleta.getS(), tripleta.getO());
    }

    public List<Tripleta> getTripletas() {
        return Collections.unmodifiableList(tripletas);
    }

    public boolean isDisjointGraph() {
        return connectedComponents() > 1;
    }

    public int connectedComponents() {
        if (adjacency.isEmpty()) {
            return 0;
        }

        Set<String> visited = new HashSet<>();
        int components = 0;

        for (String start : adjacency.keySet()) {
            if (visited.contains(start)) {
                continue;
            }
            components++;
            ArrayDeque<String> queue = new ArrayDeque<>();
            queue.add(start);
            visited.add(start);

            while (!queue.isEmpty()) {
                String node = queue.poll();
                for (String next : adjacency.getOrDefault(node, Set.of())) {
                    if (visited.add(next)) {
                        queue.add(next);
                    }
                }
            }
        }
        return components;
    }

    public List<String> shortestPath(String source, String target) {
        if (source == null || target == null || !adjacency.containsKey(source) || !adjacency.containsKey(target)) {
            return List.of();
        }
        if (source.equals(target)) {
            return List.of(source);
        }

        ArrayDeque<String> queue = new ArrayDeque<>();
        Map<String, String> previous = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.add(source);
        visited.add(source);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            for (String next : adjacency.getOrDefault(current, Set.of())) {
                if (visited.contains(next)) {
                    continue;
                }
                visited.add(next);
                previous.put(next, current);
                if (next.equals(target)) {
                    return buildPath(source, target, previous);
                }
                queue.add(next);
            }
        }
        return List.of();
    }

    public Optional<String> birthPlaceOf(String personId) {
        for (Tripleta t : tripletas) {
            if (personId.equals(t.getS()) && "nace_en".equals(t.getP())) {
                return Optional.ofNullable(t.getO());
            }
        }
        return Optional.empty();
    }

    public Set<String> birthPlacesOfNobelLaureates() {
        Set<String> nobelPeople = new HashSet<>();
        for (Tripleta t : tripletas) {
            if (isNobelRelation(t)) {
                nobelPeople.add(t.getS());
            }
        }

        Set<String> birthPlaces = new LinkedHashSet<>();
        for (Tripleta t : tripletas) {
            if ("nace_en".equals(t.getP()) && nobelPeople.contains(t.getS())) {
                birthPlaces.add(t.getO());
            }
        }
        return birthPlaces;
    }

    public Optional<String> famousPhysicistBornInSameCityAsEinstein() {
        String einstein = "persona:Albert Einstein";
        Optional<String> einsteinCity = birthPlaceOf(einstein);
        if (einsteinCity.isEmpty()) {
            return Optional.empty();
        }

        String city = einsteinCity.get();
        Set<String> candidates = new LinkedHashSet<>();
        for (Tripleta t : tripletas) {
            if ("nace_en".equals(t.getP()) && city.equals(t.getO()) && !einstein.equals(t.getS())) {
                candidates.add(t.getS());
            }
        }

        for (String candidate : candidates) {
            if (hasFact(candidate, "profesion", "fisico") && hasFact(candidate, "famoso", "true")) {
                return Optional.of(candidate);
            }
        }
        return Optional.empty();
    }

    public Set<String> nodeTypes() {
        Set<String> types = new LinkedHashSet<>();
        for (String declaredType : declaredTypes) {
            if (declaredType != null && !declaredType.isBlank()) {
                types.add(declaredType.trim());
            }
        }

        for (Tripleta t : tripletas) {
            addType(types, t.getS());
            addType(types, t.getP());
            addType(types, t.getO());
        }
        return types;
    }

    public List<String> requiredTraversalPathsForNobelBirthPlaces() {
        return List.of(
                "persona --(premio:Nobel)--> anyo",
                "persona --(nace_en)--> lugar"
        );
    }

    private boolean hasFact(String subject, String predicate, String object) {
        for (Tripleta t : tripletas) {
            if (subject.equals(t.getS()) && predicate.equals(t.getP()) && object.equalsIgnoreCase(t.getO())) {
                return true;
            }
        }
        return false;
    }

    private boolean isNobelRelation(Tripleta t) {
        if (t.getP() == null) {
            return false;
        }
        return t.getP().startsWith("premio:Nobel")
                || ("gana_premio".equals(t.getP()) && "premio:Nobel".equals(t.getO()));
    }

    private void addType(Set<String> types, String nodeValue) {
        if (nodeValue == null || nodeValue.isBlank()) {
            return;
        }
        int idx = nodeValue.indexOf(':');
        if (idx > 0) {
            types.add(nodeValue.substring(0, idx));
        } else {
            types.add("literal");
        }
    }

    private void connectUndirected(String a, String b) {
        if (a == null || b == null || a.isBlank() || b.isBlank()) {
            return;
        }
        adjacency.computeIfAbsent(a, ignored -> new LinkedHashSet<>()).add(b);
        adjacency.computeIfAbsent(b, ignored -> new LinkedHashSet<>()).add(a);
    }

    private List<String> buildPath(String source, String target, Map<String, String> previous) {
        List<String> path = new ArrayList<>();
        String current = target;
        while (current != null) {
            path.add(current);
            if (current.equals(source)) {
                break;
            }
            current = previous.get(current);
        }
        Collections.reverse(path);
        if (path.isEmpty() || !path.get(0).equals(source)) {
            return List.of();
        }
        return path;
    }
}

RESPUESTAS

1) ¿Qué tipos de nodos tiene el grafo?
En esta practica usamos sobre todo nodos de tipo `persona`, `lugar`, `premio` y `literal` (por ejemplo, años como 1921). Tambien aparece `tipo` en el JSON para marcar categorias del modelo.

2) ¿Qué es una ontologia?
Una ontologia es como un mapa de reglas del dominio. Define que cosas existen, como se relacionan y que limites hay, para que todos entendamos los datos igual.

3) ¿Qué relación tiene con los grafos?
El grafo guarda hechos concretos (tripletas sujeto-predicado-objeto). La ontologia seria la guia que dice que tipos de nodos y relaciones tienen sentido y como interpretarlos.

4) ¿Podríamos crear una ontologia para nuestro problema?
Si, sin problema. Por ejemplo, con clases como `Persona`, `Lugar` y `Premio`, y relaciones como `nace_en`, `gana_premio`, `profesion` o `famoso`.

5) ¿Qué haríamos con ella?
Nos serviria para: validar mejor los datos, usar siempre el mismo vocabulario, hacer consultas mas claras y facilitar inferencias (sacar conclusiones automaticamente en algunos casos).



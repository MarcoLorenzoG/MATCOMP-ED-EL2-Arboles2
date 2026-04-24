 RESPUESTAS

1) Que tipos de nodos tiene el grafo?
En esta practica usamos sobre todo nodos de tipo `persona`, `lugar`, `premio` y `literal` (por ejemplo, anos como `1921`). Tambien aparece `tipo` en el JSON para marcar categorias del modelo.

2) Que es una ontologia?
Una ontologia es como un mapa de reglas del dominio. Define que cosas existen, como se relacionan y que limites hay, para que todos entendamos los datos igual.

3) Que relacion tiene con los grafos?
El grafo guarda hechos concretos (tripletas sujeto-predicado-objeto). La ontologia seria la guia que dice que tipos de nodos y relaciones tienen sentido y como interpretarlos.

4) Podriamos crear una ontologia para nuestro problema?
Si, sin problema. Por ejemplo, con clases como `Persona`, `Lugar` y `Premio`, y relaciones como `nace_en`, `gana_premio`, `profesion` o `famoso`.

5) Que hariamos con ella?
Nos serviria para:
- validar mejor los datos,
- usar siempre el mismo vocabulario,
- hacer consultas mas claras,
- y facilitar inferencias (sacar conclusiones automaticamente en algunos casos).

6) Consultas funcionales solicitadas
Todo lo operativo que pide el enunciado ya esta cubierto en el programa:
- camino minimo entre dos entidades,
- comprobacion de grafo disjunto y no disjunto con dos archivos,
- consulta del fisico famoso nacido en la misma ciudad que Einstein,
- insercion de la tripleta de Antonio y listado de lugares de nacimiento de premios Nobel,
- y obtencion de tipos de nodos.

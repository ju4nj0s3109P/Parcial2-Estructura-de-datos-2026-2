# ejercicio 2 - obtener un segmento del historial

## requerimientos

implementar una funcion `obtenerSegmento` que reciba una pila propia de acciones y dos posiciones enteras: `inicio` y `fin`.

la funcion debe retornar una nueva pila con las acciones comprendidas entre ambas posiciones, incluidos los extremos. las posiciones se cuentan desde la cima, empezando en 1. la accion ubicada en `inicio` debe quedar en la cima de la pila resultado.

### condiciones

- la pila original debe conservar todos sus elementos y el mismo orden.
- si `inicio < 1`, `inicio > fin` o `fin` supera la cantidad de elementos, retornar una pila vacia.
- si `inicio` y `fin` son iguales y la posicion es valida, el resultado contiene solamente esa accion.
- trabajar con las operaciones de la pila propia.
- se permiten pilas propias auxiliares.

### ejemplo del enunciado

pila original, desde la cima hasta el fondo:

`a6 -> a5 -> a4 -> a3 -> a2 -> a1`

para `inicio = 2` y `fin = 4`, el resultado desde la cima hasta el fondo es:

`a5 -> a4 -> a3`

## implementacion

- usar la pila propia del curso; no reemplazarla por una estructura distinta.
- utilizar auxiliares propios cuando sean necesarios para conservar el orden y restaurar la pila original.
- retornar una pila nueva; no modificar el contenido observable de la pila recibida.
- validar los indices con las condiciones especificadas.
- resolver de forma directa usando operaciones de pila vistas en clase.

## reglas de documentacion

- escribir comentarios y documentacion en minusculas y sin emojis.
- documentar de forma clara, descriptiva y breve.
- evitar comentarios que narren operaciones obvias o textos extensos con estilo artificial.
- explicar el proposito de los metodos y las operaciones relevantes. ejemplo: "recorre la pila y devuelve la cantidad de elementos que cumplen la condicion".
- no documentar de mas; mantener un estilo natural y parecido al de un estudiante.

## pruebas a contemplar

- segmento valido con varias acciones.
- inicio y fin iguales en una posicion valida.
- inicio menor que 1.
- inicio mayor que fin.
- fin mayor que el tamano de la pila.
- pila vacia.
- verificar que la pila original queda intacta en todos los casos.

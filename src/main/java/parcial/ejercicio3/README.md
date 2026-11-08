# ejercicio 3 - seleccion de solicitudes para una jornada

## requerimientos

implementar una funcion `seleccionarPaquetes` que reciba una cola propia de paquetes pendientes y la capacidad disponible del vehiculo. debe retornar una nueva cola con los paquetes seleccionados y retirar esos paquetes de la cola de pendientes.

cada paquete tiene codigo unico, tipo de entrega (urgente o normal) y peso entero positivo en kilogramos.

### condiciones de seleccion

- procesar primero los paquetes urgentes, respetando su orden de registro.
- despues procesar los paquetes normales, respetando su orden de registro.
- seleccionar un paquete si su peso es menor o igual a la capacidad restante y descontar su peso.
- si un paquete no cabe, dejarlo pendiente y continuar evaluando los siguientes.
- la cola resultado conserva el orden de seleccion: urgentes y luego normales, respetando el orden de llegada dentro de cada tipo.
- los paquetes pendientes deben conservar entre si el orden de registro original.
- si la capacidad es menor o igual a cero, retornar una cola vacia y dejar la cola de pendientes sin cambios.

### condicion de implementacion

- el procesamiento y la reorganizacion deben realizarse mediante recursividad.
- no utilizar ciclos `for`, `while` ni `do-while`.
- trabajar con las operaciones de la cola propia.
- se permiten colas propias auxiliares y funciones recursivas adicionales.
- puede utilizarse la cantidad de elementos de la cola para controlar el numero de elementos de cada recorrido.

### datos de prueba

agregar a una cola vacia, en este orden:

| orden | codigo | tipo | peso |
|---|---|---|---|
| 1 | p1 | normal | 4 kg |
| 2 | p2 | urgente | 7 kg |
| 3 | p3 | normal | 3 kg |
| 4 | p4 | urgente | 5 kg |
| 5 | p5 | normal | 6 kg |
| 6 | p6 | urgente | 4 kg |
| 7 | p7 | normal | 2 kg |

ejecutar la funcion con una capacidad disponible de 14 kg.

mostrar la cola inicial desde el frente hasta el final, cada paquete evaluado y si fue seleccionado o quedo pendiente, la cola retornada, la cola de pendientes y la capacidad restante.

## implementacion

- utilizar la cola propia del curso, sin reemplazarla por una coleccion avanzada.
- realizar el procesamiento mediante metodos recursivos; no incluir ciclos en la seleccion ni en la reorganizacion.
- conservar el orden relativo de paquetes urgentes, normales y pendientes segun lo indicado.
- mantener una forma sencilla de actualizar y compartir la capacidad restante durante el procesamiento.
- evitar estructuras o abstracciones no solicitadas por el enunciado.

## reglas de documentacion

- escribir comentarios y documentacion en minusculas y sin emojis.
- documentar de forma clara, descriptiva y breve.
- evitar comentarios que narren operaciones obvias o textos extensos con estilo artificial.
- explicar el proposito de los metodos y las operaciones relevantes. ejemplo: "recorre la cola y devuelve los paquetes que caben en la capacidad disponible".
- no documentar de mas; mantener un estilo natural y parecido al de un estudiante.

## pruebas a contemplar

- capacidad menor o igual a cero y verificar que la cola no cambia.
- capacidad suficiente para seleccionar todos los paquetes.
- paquetes que no caben seguidos de otros que si caben.
- verificar prioridad de urgentes sobre normales.
- verificar orden de seleccion y orden de pendientes.
- comprobar la capacidad restante al finalizar.

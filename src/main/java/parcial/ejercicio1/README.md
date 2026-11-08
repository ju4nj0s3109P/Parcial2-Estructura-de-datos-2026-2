# ejercicio 1 - retiro de cajas y evaluacion de expresiones

## requerimientos

implementar una funcion `retirarCaja` que reciba una pila propia de cajas y el codigo de la caja a retirar.

cada caja tiene un codigo unico y un valor entero.

### retiro y reorganizacion

- buscar la caja solicitada utilizando las operaciones de la pila propia.
- retirar la caja encontrada y reorganizar las cajas restantes segun la paridad del valor de la caja retirada.
- las posiciones se cuentan desde la cima, empezando en 1, luego de retirar la caja y antes de reorganizar.
- si el valor retirado es par, las cajas de posiciones impares quedan arriba y las de posiciones pares abajo.
- si el valor retirado es impar, las cajas de posiciones pares quedan arriba y las de posiciones impares abajo.
- cada grupo debe conservar su orden relativo original.
- retornar `true` si se retiro la caja; si el codigo no existe o la pila esta vacia, retornar `false`.
- cuando el codigo no exista, la pila debe quedar exactamente en el mismo orden.
- utilizar las operaciones de la pila propia. se permiten pilas propias auxiliares.

### datos de prueba

agregar a una pila vacia, en este orden:

- c1: 2
- c2: 4
- c3: 6
- c4: 8
- c5: 3
- c6: 9
- c7: 5

ejecutar sobre la misma pila y en secuencia: retirar c3, retirar c4 y retirar c7. cada llamada utiliza el resultado de la anterior.

mostrar la pila inicial desde la cima hasta el fondo, el valor retornado y la pila despues de cada llamada, y el contenido final.

### evaluacion de expresion

con los valores de las cuatro cajas que quedan en la pila final:

- a: valor de la primera caja.
- b: valor de la segunda caja dividido entre a, conservando decimales.
- c: valor de la tercera caja mas a.
- d: la suma del valor de la cuarta caja y c, multiplicada por a.

mostrar los valores asignados, la conversion de la expresion a la notacion requerida, la sustitucion de variables y la evaluacion mediante una pila. incluir una tabla con elemento procesado, operacion realizada y contenido de la pila en cada paso, y el resultado final.

## implementacion

- utilizar la pila propia solicitada en el curso y sus operaciones; no sustituirla por una coleccion avanzada de java.
- pueden crearse pilas auxiliares propias para preservar el orden.
- implementar la busqueda, retiro y reorganizacion sin perder elementos ni alterar el orden dentro de cada grupo.
- para la division de b, utilizar un tipo numerico que permita decimales.
- la conversion y evaluacion de la expresion debe usar una pila, de acuerdo con el procedimiento visto en clase.
- resolver con metodos directos y estructuras vistas en clase, sin complejidad innecesaria.

## reglas de documentacion

- escribir comentarios y documentacion en minusculas y sin emojis.
- documentar de forma clara, descriptiva y breve.
- evitar comentarios que narren operaciones obvias o textos extensos con estilo artificial.
- explicar el proposito de los metodos y las operaciones relevantes. ejemplo: "recorre la pila y devuelve la cantidad de elementos que cumplen la condicion".
- no documentar de mas; mantener un estilo natural y parecido al de un estudiante.

## pruebas a contemplar

- pila vacia.
- codigo inexistente y verificar que el orden original se conserva.
- retirar una caja con valor par y otra con valor impar.
- ejecutar las tres solicitudes de retiro en secuencia.
- comprobar la conversion y evaluacion de la expresion con la pila final.

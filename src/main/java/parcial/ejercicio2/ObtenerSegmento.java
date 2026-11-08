package parcial.ejercicio2;

import parcial.ejercicio1.Pila;

/**
 * Obtiene un segmento del historial sin modificar la pila original.
 */
public class ObtenerSegmento {

    public Pila<String> obtenerSegmento(Pila<String> historial, int inicio, int fin) {
        Pila<String> resultado = new Pila<>();
        Pila<String> auxiliar = new Pila<>();

        if (inicio < 1 || inicio > fin || fin > historial.tamanio()) {
            return resultado;
        }

        int posicion = 1;
        while (!historial.estaVacia()) {
            String accion = historial.desapilar();

            if (posicion >= inicio && posicion <= fin) {
                auxiliar.apilar(accion);
            }

            resultado.apilar(accion);
            posicion++;
        }

        // Se reconstruye la pila original con el mismo orden.
        while (!resultado.estaVacia()) {
            historial.apilar(resultado.desapilar());
        }

        // Se conserva el orden del segmento para que inicio quede en la cima.
        while (!auxiliar.estaVacia()) {
            resultado.apilar(auxiliar.desapilar());
        }

        return resultado;
    }
}

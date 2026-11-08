package parcial.ejercicio1;

/**
 * Pila propia implementada con nodos enlazados.
 */
public class Pila<T> {
    private Nodo<T> tope;
    private int cantidad;

    private static class Nodo<T> {
        private final T dato;
        private Nodo<T> siguiente;

        private Nodo(T dato, Nodo<T> siguiente) {
            this.dato = dato;
            this.siguiente = siguiente;
        }
    }

    public void apilar(T dato) {
        tope = new Nodo<>(dato, tope);
        cantidad++;
    }

    public T desapilar() {
        if (estaVacia()) {
            throw new IllegalStateException("la pila esta vacia");
        }
        T dato = tope.dato;
        tope = tope.siguiente;
        cantidad--;
        return dato;
    }

    public T consultarTope() {
        if (estaVacia()) {
            throw new IllegalStateException("la pila esta vacia");
        }
        return tope.dato;
    }

    public boolean estaVacia() {
        return tope == null;
    }

    public int tamanio() {
        return cantidad;
    }
}

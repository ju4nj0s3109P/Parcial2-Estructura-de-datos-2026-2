package parcial.ejercicio3;

/**
 * Cola propia implementada con nodos enlazados.
 */
public class Cola<T> {
    private Nodo<T> frente;
    private Nodo<T> fin;
    private int cantidad;

    private static class Nodo<T> {
        private final T dato;
        private Nodo<T> siguiente;

        private Nodo(T dato) {
            this.dato = dato;
        }
    }

    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (estaVacia()) {
            frente = nuevo;
        } else {
            fin.siguiente = nuevo;
        }
        fin = nuevo;
        cantidad++;
    }

    public T desencolar() {
        if (estaVacia()) {
            throw new IllegalStateException("la cola esta vacia");
        }
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        cantidad--;
        return dato;
    }

    public T consultarFrente() {
        if (estaVacia()) {
            throw new IllegalStateException("la cola esta vacia");
        }
        return frente.dato;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int tamanio() {
        return cantidad;
    }
}

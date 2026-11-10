package parcial.ejercicio4;

/**
 * Nodo de un arbol binario de busqueda. La clave es el codigo del equipo.
 */
public class NodoABB {
    private final int clave;
    private NodoABB izquierdo;
    private NodoABB derecho;

    public NodoABB(int clave) {
        this.clave = clave;
    }

    public int getClave() {
        return clave;
    }

    public NodoABB getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoABB izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoABB getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoABB derecho) {
        this.derecho = derecho;
    }
}

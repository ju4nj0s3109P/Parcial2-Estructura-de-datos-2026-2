package parcial.ejercicio4;

/**
 * Arbol binario de busqueda con los codigos de los equipos del laboratorio.
 */
public class ArbolBinarioBusqueda {

    private NodoABB raiz;

    /**
     * Inserta un codigo en el arbol. Los codigos repetidos se ignoran porque cada
     * equipo tiene un codigo unico.
     */
    public void insertar(int clave) {
        raiz = insertar(raiz, clave);
    }

    private NodoABB insertar(NodoABB nodo, int clave) {
        if (nodo == null) {
            return new NodoABB(clave);
        }
        if (clave < nodo.getClave()) {
            nodo.setIzquierdo(insertar(nodo.getIzquierdo(), clave));
        } else if (clave > nodo.getClave()) {
            nodo.setDerecho(insertar(nodo.getDerecho(), clave));
        }
        return nodo;
    }

    public NodoABB getRaiz() {
        return raiz;
    }

    /**
     * Cuenta los equipos cuyo codigo esta dentro del rango, incluidos los extremos.
     * Aprovecha el orden del arbol: si la clave del nodo queda fuera del rango, solo
     * se visita el subarbol que todavia puede aportar resultados.
     */
    public static int contarEquiposEnRango(NodoABB raiz, int inferior, int superior) {
        if (raiz == null || inferior > superior) {
            return 0;
        }
        if (raiz.getClave() < inferior) {
            return contarEquiposEnRango(raiz.getDerecho(), inferior, superior);
        }
        if (raiz.getClave() > superior) {
            return contarEquiposEnRango(raiz.getIzquierdo(), inferior, superior);
        }
        return 1
                + contarEquiposEnRango(raiz.getIzquierdo(), inferior, superior)
                + contarEquiposEnRango(raiz.getDerecho(), inferior, superior);
    }
}

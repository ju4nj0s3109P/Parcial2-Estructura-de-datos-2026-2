package parcial.ejercicio4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArbolBinarioBusquedaTest {

    private ArbolBinarioBusqueda crearArbolPrueba() {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        int[] codigos = { 41, 20, 52, 16, 6, 13, 12, 60, 22, 47, 50, 60, 43, 44 };
        for (int codigo : codigos) {
            arbol.insertar(codigo);
        }
        return arbol;
    }

    @Test
    void cuentaLosEquiposDentroDelRango() {
        ArbolBinarioBusqueda arbol = crearArbolPrueba();

        assertEquals(7, ArbolBinarioBusqueda.contarEquiposEnRango(arbol.getRaiz(), 20, 50));
        assertEquals(13, ArbolBinarioBusqueda.contarEquiposEnRango(arbol.getRaiz(), 0, 100));
        assertEquals(1, ArbolBinarioBusqueda.contarEquiposEnRango(arbol.getRaiz(), 41, 41));
        assertEquals(0, ArbolBinarioBusqueda.contarEquiposEnRango(arbol.getRaiz(), 61, 80));
    }

    @Test
    void arbolVacioOLimitesInvertidosRetornanCero() {
        ArbolBinarioBusqueda arbol = crearArbolPrueba();

        assertEquals(0, ArbolBinarioBusqueda.contarEquiposEnRango(null, 1, 100));
        assertEquals(0, ArbolBinarioBusqueda.contarEquiposEnRango(arbol.getRaiz(), 50, 20));
    }
}

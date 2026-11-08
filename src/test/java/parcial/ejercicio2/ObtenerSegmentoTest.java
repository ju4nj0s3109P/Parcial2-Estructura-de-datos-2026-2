package parcial.ejercicio2;

import parcial.ejercicio1.Pila;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObtenerSegmentoTest {

    private Pila<String> crearHistorial() {
        Pila<String> historial = new Pila<>();
        historial.apilar("A1");
        historial.apilar("A2");
        historial.apilar("A3");
        historial.apilar("A4");
        historial.apilar("A5");
        historial.apilar("A6");
        return historial;
    }

    private String contenido(Pila<String> pila) {
        Pila<String> auxiliar = new Pila<>();
        StringBuilder resultado = new StringBuilder();

        while (!pila.estaVacia()) {
            String accion = pila.desapilar();
            if (resultado.length() > 0) {
                resultado.append(" -> ");
            }
            resultado.append(accion);
            auxiliar.apilar(accion);
        }

        while (!auxiliar.estaVacia()) {
            pila.apilar(auxiliar.desapilar());
        }
        return resultado.toString();
    }

    @Test
    void obtieneSegmentoYConservaHistorialOriginal() {
        Pila<String> historial = crearHistorial();

        Pila<String> segmento = new ObtenerSegmento().obtenerSegmento(historial, 2, 4);

        assertEquals("A5 -> A4 -> A3", contenido(segmento));
        assertEquals("A6 -> A5 -> A4 -> A3 -> A2 -> A1", contenido(historial));
    }

    @Test
    void inicioYFinIgualesRetornanUnaAccion() {
        Pila<String> historial = crearHistorial();

        Pila<String> segmento = new ObtenerSegmento().obtenerSegmento(historial, 3, 3);

        assertEquals("A4", contenido(segmento));
        assertEquals("A6 -> A5 -> A4 -> A3 -> A2 -> A1", contenido(historial));
    }

    @Test
    void indicesInvalidosRetornanPilaVaciaYNoAlteranHistorial() {
        Pila<String> historial = crearHistorial();
        ObtenerSegmento servicio = new ObtenerSegmento();

        assertTrue(servicio.obtenerSegmento(historial, 0, 3).estaVacia());
        assertTrue(servicio.obtenerSegmento(historial, 4, 2).estaVacia());
        assertTrue(servicio.obtenerSegmento(historial, 2, 7).estaVacia());
        assertEquals("A6 -> A5 -> A4 -> A3 -> A2 -> A1", contenido(historial));
    }

    @Test
    void historialVacioRetornaSegmentoVacio() {
        Pila<String> historial = new Pila<>();

        assertTrue(new ObtenerSegmento().obtenerSegmento(historial, 1, 1).estaVacia());
        assertTrue(historial.estaVacia());
    }
}

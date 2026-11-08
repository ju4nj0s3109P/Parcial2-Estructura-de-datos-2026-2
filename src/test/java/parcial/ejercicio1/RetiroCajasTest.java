package parcial.ejercicio1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RetiroCajasTest {

    private Pila<Caja> crearPilaPrueba() {
        Pila<Caja> pila = new Pila<>();
        pila.apilar(new Caja("C1", 2));
        pila.apilar(new Caja("C2", 4));
        pila.apilar(new Caja("C3", 6));
        pila.apilar(new Caja("C4", 8));
        pila.apilar(new Caja("C5", 3));
        pila.apilar(new Caja("C6", 9));
        pila.apilar(new Caja("C7", 5));
        return pila;
    }

    private String contenido(Pila<Caja> pila) {
        Pila<Caja> auxiliar = new Pila<>();
        StringBuilder resultado = new StringBuilder();

        while (!pila.estaVacia()) {
            Caja caja = pila.desapilar();
            if (resultado.length() > 0) {
                resultado.append(" -> ");
            }
            resultado.append(caja);
            auxiliar.apilar(caja);
        }

        while (!auxiliar.estaVacia()) {
            pila.apilar(auxiliar.desapilar());
        }
        return resultado.toString();
    }

    @Test
    void retiraCajasEnElOrdenSolicitado() {
        Pila<Caja> pila = crearPilaPrueba();
        RetiroCajas servicio = new RetiroCajas();

        assertTrue(servicio.retirarCaja(pila, "C3"));
        assertEquals("C7:5 -> C5:3 -> C2:4 -> C6:9 -> C4:8 -> C1:2", contenido(pila));

        assertTrue(servicio.retirarCaja(pila, "C4"));
        assertEquals("C7:5 -> C2:4 -> C1:2 -> C5:3 -> C6:9", contenido(pila));

        assertTrue(servicio.retirarCaja(pila, "C7"));
        assertEquals("C1:2 -> C6:9 -> C2:4 -> C5:3", contenido(pila));
    }

    @Test
    void cajaInexistenteNoCambiaElOrden() {
        Pila<Caja> pila = crearPilaPrueba();
        RetiroCajas servicio = new RetiroCajas();
        String antes = contenido(pila);

        assertFalse(servicio.retirarCaja(pila, "C99"));
        assertEquals(antes, contenido(pila));
    }

    @Test
    void pilaVaciaRetornaFalse() {
        assertFalse(new RetiroCajas().retirarCaja(new Pila<>(), "C1"));
    }

    @Test
    void retiroImparIntercambiaLosGruposDePosiciones() {
        Pila<Caja> pila = new Pila<>();
        pila.apilar(new Caja("A", 10));
        pila.apilar(new Caja("B", 20));
        pila.apilar(new Caja("C", 30));
        pila.apilar(new Caja("D", 40));
        pila.apilar(new Caja("E", 7));

        assertTrue(new RetiroCajas().retirarCaja(pila, "E"));
        assertEquals("C:30 -> A:10 -> D:40 -> B:20", contenido(pila));
    }
}

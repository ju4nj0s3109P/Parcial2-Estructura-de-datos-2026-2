package parcial.ejercicio3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeleccionarPaquetesTest {

    private Cola<Paquete> crearColaPrueba() {
        Cola<Paquete> pendientes = new Cola<>();
        pendientes.encolar(new Paquete("P1", TipoEntrega.NORMAL, 4));
        pendientes.encolar(new Paquete("P2", TipoEntrega.URGENTE, 7));
        pendientes.encolar(new Paquete("P3", TipoEntrega.NORMAL, 3));
        pendientes.encolar(new Paquete("P4", TipoEntrega.URGENTE, 5));
        pendientes.encolar(new Paquete("P5", TipoEntrega.NORMAL, 6));
        pendientes.encolar(new Paquete("P6", TipoEntrega.URGENTE, 4));
        pendientes.encolar(new Paquete("P7", TipoEntrega.NORMAL, 2));
        return pendientes;
    }

    private String codigos(Cola<Paquete> cola) {
        StringBuilder texto = new StringBuilder();
        int cantidad = cola.tamanio();

        for (int i = 0; i < cantidad; i++) {
            Paquete paquete = cola.desencolar();
            if (texto.length() > 0) {
                texto.append(" -> ");
            }
            texto.append(paquete.getCodigo());
            cola.encolar(paquete);
        }
        return texto.toString();
    }

    private String textos(Cola<String> cola) {
        StringBuilder texto = new StringBuilder();
        int cantidad = cola.tamanio();

        for (int i = 0; i < cantidad; i++) {
            String dato = cola.desencolar();
            if (texto.length() > 0) {
                texto.append(" | ");
            }
            texto.append(dato);
            cola.encolar(dato);
        }
        return texto.toString();
    }

    @Test
    void casoDelEnunciadoConCapacidadDeCatorce() {
        Cola<Paquete> pendientes = crearColaPrueba();
        SeleccionarPaquetes servicio = new SeleccionarPaquetes();

        Cola<Paquete> seleccionados = servicio.seleccionarPaquetes(pendientes, 14);

        assertEquals("P2 -> P4 -> P7", codigos(seleccionados));
        assertEquals("P1 -> P3 -> P5 -> P6", codigos(pendientes));
        assertEquals(0, servicio.getCapacidadRestante());
    }

    @Test
    void registraCadaPaqueteEvaluadoEnOrden() {
        Cola<Paquete> pendientes = crearColaPrueba();
        SeleccionarPaquetes servicio = new SeleccionarPaquetes();

        servicio.seleccionarPaquetes(pendientes, 14);

        assertEquals("P2: seleccionado | P4: seleccionado | P6: pendiente | P1: pendiente"
                        + " | P3: pendiente | P5: pendiente | P7: seleccionado",
                textos(servicio.getEvaluaciones()));
    }

    @Test
    void capacidadMenorOIgualACeroNoCambiaLaCola() {
        Cola<Paquete> pendientes = crearColaPrueba();
        SeleccionarPaquetes servicio = new SeleccionarPaquetes();

        assertTrue(servicio.seleccionarPaquetes(pendientes, 0).estaVacia());
        assertEquals("P1 -> P2 -> P3 -> P4 -> P5 -> P6 -> P7", codigos(pendientes));

        assertTrue(servicio.seleccionarPaquetes(pendientes, -5).estaVacia());
        assertEquals("P1 -> P2 -> P3 -> P4 -> P5 -> P6 -> P7", codigos(pendientes));
    }

    @Test
    void capacidadSuficienteSeleccionaTodosPrimeroLosUrgentes() {
        Cola<Paquete> pendientes = crearColaPrueba();
        SeleccionarPaquetes servicio = new SeleccionarPaquetes();

        Cola<Paquete> seleccionados = servicio.seleccionarPaquetes(pendientes, 31);

        assertEquals("P2 -> P4 -> P6 -> P1 -> P3 -> P5 -> P7", codigos(seleccionados));
        assertTrue(pendientes.estaVacia());
        assertEquals(0, servicio.getCapacidadRestante());
    }
}

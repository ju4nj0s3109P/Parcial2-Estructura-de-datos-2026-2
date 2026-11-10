package parcial.ejercicio3;

/**
 * Selecciona los paquetes de una jornada segun el tipo de entrega, el orden de
 * registro y la capacidad disponible del vehiculo. Todo el procesamiento se hace
 * de forma recursiva, sin ciclos.
 */
public class SeleccionarPaquetes {

    private int capacidadRestante;
    private Cola<String> evaluaciones = new Cola<>();

    /**
     * Retira de la cola de pendientes los paquetes que caben en la capacidad
     * disponible y los retorna en el orden en que fueron seleccionados: primero los
     * urgentes y despues los normales.
     */
    public Cola<Paquete> seleccionarPaquetes(Cola<Paquete> pendientes, int capacidad) {
        capacidadRestante = capacidad;
        evaluaciones = new Cola<>();
        Cola<Paquete> seleccionados = new Cola<>();

        if (pendientes == null || capacidad <= 0) {
            return seleccionados;
        }

        procesar(pendientes, seleccionados, TipoEntrega.URGENTE, pendientes.tamanio());
        procesar(pendientes, seleccionados, TipoEntrega.NORMAL, pendientes.tamanio());
        return seleccionados;
    }

    /**
     * Recorre una vez la cola de pendientes y selecciona los paquetes del tipo
     * indicado que caben en la capacidad restante. Los demas se vuelven a encolar,
     * por lo que conservan su orden de registro original.
     */
    private void procesar(Cola<Paquete> pendientes, Cola<Paquete> seleccionados,
                          TipoEntrega tipo, int porRevisar) {
        if (porRevisar == 0) {
            return;
        }

        Paquete paquete = pendientes.desencolar();
        boolean cabe = paquete.getTipo() == tipo && paquete.getPeso() <= capacidadRestante;

        if (cabe) {
            capacidadRestante -= paquete.getPeso();
            seleccionados.encolar(paquete);
            evaluaciones.encolar(paquete.getCodigo() + ": seleccionado");
        } else {
            pendientes.encolar(paquete);
            if (paquete.getTipo() == tipo) {
                evaluaciones.encolar(paquete.getCodigo() + ": pendiente");
            }
        }

        procesar(pendientes, seleccionados, tipo, porRevisar - 1);
    }

    public int getCapacidadRestante() {
        return capacidadRestante;
    }

    /**
     * Resultado de cada paquete evaluado, en el orden en que fue revisado.
     */
    public Cola<String> getEvaluaciones() {
        return evaluaciones;
    }
}

package parcial.ejercicio1;

/**
 * Contiene la operacion de retiro y reorganizacion de cajas.
 */
public class RetiroCajas {

    /**
     * Retira la caja indicada y reorganiza las restantes por paridad de posiciones.
     *
     * @param pila pila de cajas
     * @param codigo codigo de la caja a retirar
     * @return true si se retiro la caja
     */
    public boolean retirarCaja(Pila<Caja> pila, String codigo) {
        if (pila == null || pila.estaVacia() || codigo == null) {
            return false;
        }

        Pila<Caja> superiores = new Pila<>();
        boolean encontrada = false;
        Caja retirada = null;

        while (!pila.estaVacia()) {
            Caja actual = pila.desapilar();
            if (actual.getCodigo().equals(codigo)) {
                retirada = actual;
                encontrada = true;
                break;
            }
            superiores.apilar(actual);
        }

        if (!encontrada) {
            while (!superiores.estaVacia()) {
                pila.apilar(superiores.desapilar());
            }
            return false;
        }

        while (!superiores.estaVacia()) {
            pila.apilar(superiores.desapilar());
        }

        reorganizar(pila, retirada.getValor());
        return true;
    }

    private void reorganizar(Pila<Caja> pila, int valorRetirado) {
        Pila<Caja> impares = new Pila<>();
        Pila<Caja> pares = new Pila<>();
        int posicion = 1;

        while (!pila.estaVacia()) {
            Caja caja = pila.desapilar();
            if (posicion % 2 == 0) {
                pares.apilar(caja);
            } else {
                impares.apilar(caja);
            }
            posicion++;
        }

        if (valorRetirado % 2 == 0) {
            transferir(pares, pila);
            transferir(impares, pila);
        } else {
            transferir(impares, pila);
            transferir(pares, pila);
        }
    }

    private void transferir(Pila<Caja> origen, Pila<Caja> destino) {
        while (!origen.estaVacia()) {
            destino.apilar(origen.desapilar());
        }
    }
}

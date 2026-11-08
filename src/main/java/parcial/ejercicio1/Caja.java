package parcial.ejercicio1;

/**
 * Representa una caja del deposito.
 */
public class Caja {
    private final String codigo;
    private final int valor;

    public Caja(String codigo, int valor) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("el codigo no puede estar vacio");
        }
        this.codigo = codigo;
        this.valor = valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return codigo + ":" + valor;
    }
}

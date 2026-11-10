package parcial.ejercicio3;

/**
 * Representa un paquete pendiente de entrega.
 */
public class Paquete {
    private final String codigo;
    private final TipoEntrega tipo;
    private final int peso;

    public Paquete(String codigo, TipoEntrega tipo, int peso) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("el codigo no puede estar vacio");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("el tipo de entrega es obligatorio");
        }
        if (peso <= 0) {
            throw new IllegalArgumentException("el peso debe ser positivo");
        }
        this.codigo = codigo;
        this.tipo = tipo;
        this.peso = peso;
    }

    public String getCodigo() {
        return codigo;
    }

    public TipoEntrega getTipo() {
        return tipo;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return codigo + " (" + tipo + ", " + peso + " kg)";
    }
}

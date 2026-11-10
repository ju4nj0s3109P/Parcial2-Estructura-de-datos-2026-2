package parcial.ejercicio1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EvaluadorExpresionTest {

    private final EvaluadorExpresion evaluador = new EvaluadorExpresion();

    @Test
    void convierteLaExpresionInfijaAPostfija() {
        assertEquals("A B C * +", evaluador.convertirAPostfija("A + B * C"));
        assertEquals("A B + C *", evaluador.convertirAPostfija("(A + B) * C"));
        assertEquals("A B C D - + /", evaluador.convertirAPostfija("A / (B + (C - D))"));
    }

    @Test
    void reemplazaLasVariablesYEvaluaConLaPila() {
        String postfija = evaluador.convertirAPostfija("(A + B) * C");
        String sustituida = evaluador.reemplazarVariables(postfija, 2, 4.5, 6, 18);

        assertEquals("2.0 4.5 + 6.0 *", sustituida);
        assertEquals(39.0, evaluador.evaluarPostfija(sustituida));
    }

    @Test
    void evaluaConValoresNegativos() {
        String postfija = evaluador.convertirAPostfija("(A + B) * C - D");
        String sustituida = evaluador.reemplazarVariables(postfija, 2, -4.5, 6, -18);

        assertEquals(3.0, evaluador.evaluarPostfija(sustituida));
    }

    @Test
    void expresionInvalidaLanzaError() {
        assertThrows(IllegalArgumentException.class, () -> evaluador.convertirAPostfija("(A + B"));
        assertThrows(IllegalArgumentException.class, () -> evaluador.convertirAPostfija("A + B)"));
        assertThrows(IllegalArgumentException.class, () -> evaluador.convertirAPostfija(" "));
        assertThrows(IllegalArgumentException.class, () -> evaluador.evaluarPostfija("2 0 /"));
    }
}

package parcial.ejercicio1;

/**
 * Convierte una expresion infija a notacion postfija y la evalua usando la pila
 * propia, mostrando la tabla de pasos solicitada.
 */
public class EvaluadorExpresion {

    /**
     * Convierte la expresion infija a postfija apilando los operadores y sacandolos
     * cuando llega uno de menor o igual prioridad.
     */
    public String convertirAPostfija(String infija) {
        if (infija == null || infija.isBlank()) {
            throw new IllegalArgumentException("la expresion no puede estar vacia");
        }

        Pila<String> operadores = new Pila<>();
        StringBuilder salida = new StringBuilder();

        for (String token : separar(infija)) {
            if (token.equals("(")) {
                operadores.apilar(token);
            } else if (token.equals(")")) {
                while (!operadores.estaVacia() && !operadores.consultarTope().equals("(")) {
                    agregar(salida, operadores.desapilar());
                }
                if (operadores.estaVacia()) {
                    throw new IllegalArgumentException("parentesis desbalanceados");
                }
                operadores.desapilar();
            } else if (esOperando(token)) {
                agregar(salida, token);
            } else {
                while (!operadores.estaVacia()
                        && !operadores.consultarTope().equals("(")
                        && prioridad(operadores.consultarTope()) >= prioridad(token)) {
                    agregar(salida, operadores.desapilar());
                }
                operadores.apilar(token);
            }
        }

        while (!operadores.estaVacia()) {
            String operador = operadores.desapilar();
            if (operador.equals("(")) {
                throw new IllegalArgumentException("parentesis desbalanceados");
            }
            agregar(salida, operador);
        }

        return salida.toString();
    }

    /**
     * Reemplaza las variables A, B, C y D por sus valores antes de evaluar.
     */
    public String reemplazarVariables(String expresion, double a, double b, double c, double d) {
        return expresion
                .replace("A", String.valueOf(a))
                .replace("B", String.valueOf(b))
                .replace("C", String.valueOf(c))
                .replace("D", String.valueOf(d));
    }

    /**
     * Evalua la expresion postfija con una pila e imprime la tabla con el elemento
     * procesado, la operacion realizada y el contenido de la pila en cada paso.
     */
    public double evaluarPostfija(String postfija) {
        if (postfija == null || postfija.isBlank()) {
            throw new IllegalArgumentException("la expresion no puede estar vacia");
        }

        Pila<Double> pila = new Pila<>();
        System.out.printf("%-14s %-30s %s%n", "ELEMENTO", "OPERACION", "PILA (tope a fondo)");

        // la postfija ya viene separada por espacios; asi un valor negativo conserva su signo
        for (String token : postfija.trim().split("\\s+")) {
            String operacion;

            if (esOperando(token)) {
                pila.apilar(Double.parseDouble(token));
                operacion = "apilar " + token;
            } else {
                if (pila.tamanio() < 2) {
                    throw new IllegalArgumentException("la expresion no es valida");
                }
                double derecho = pila.desapilar();
                double izquierdo = pila.desapilar();
                double resultado = operar(izquierdo, derecho, token);
                pila.apilar(resultado);
                operacion = izquierdo + " " + token + " " + derecho + " = " + resultado;
            }

            System.out.printf("%-14s %-30s %s%n", token, operacion, contenido(pila));
        }

        double resultado = pila.desapilar();
        if (!pila.estaVacia()) {
            throw new IllegalArgumentException("la expresion no es valida");
        }
        return resultado;
    }

    /**
     * Separa la expresion en tokens dejando espacios alrededor de operadores y parentesis.
     */
    private String[] separar(String expresion) {
        return expresion.replaceAll("([+\\-*/()])", " $1 ").trim().split("\\s+");
    }

    private boolean esOperando(String token) {
        return !token.equals("(") && !token.equals(")") && prioridad(token) == 0;
    }

    private int prioridad(String token) {
        if (token.equals("+") || token.equals("-")) {
            return 1;
        }
        if (token.equals("*") || token.equals("/")) {
            return 2;
        }
        return 0;
    }

    private double operar(double izquierdo, double derecho, String operador) {
        switch (operador) {
            case "+":
                return izquierdo + derecho;
            case "-":
                return izquierdo - derecho;
            case "*":
                return izquierdo * derecho;
            case "/":
                if (derecho == 0) {
                    throw new IllegalArgumentException("division por cero");
                }
                return izquierdo / derecho;
            default:
                throw new IllegalArgumentException("operador no valido: " + operador);
        }
    }

    private void agregar(StringBuilder salida, String token) {
        if (salida.length() > 0) {
            salida.append(" ");
        }
        salida.append(token);
    }

    /**
     * Devuelve el contenido de la pila desde el tope hasta el fondo sin alterarla.
     */
    private String contenido(Pila<Double> pila) {
        Pila<Double> auxiliar = new Pila<>();
        StringBuilder texto = new StringBuilder();

        while (!pila.estaVacia()) {
            double valor = pila.desapilar();
            if (texto.length() > 0) {
                texto.append(" ");
            }
            texto.append(valor);
            auxiliar.apilar(valor);
        }

        while (!auxiliar.estaVacia()) {
            pila.apilar(auxiliar.desapilar());
        }
        return texto.toString();
    }
}

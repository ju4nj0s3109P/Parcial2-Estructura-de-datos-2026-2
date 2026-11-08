package parcial;

import parcial.ejercicio1.Caja;
import parcial.ejercicio1.Pila;
import parcial.ejercicio1.RetiroCajas;

/**
 * Ejecuta la demostracion del ejercicio 1.
 */
public class Main {

    public static void main(String[] args) {
        Pila<Caja> cajas = crearPilaInicial();
        RetiroCajas retiro = new RetiroCajas();

        System.out.println("parcial 2 - estructura de datos");
        System.out.println("\nejercicio 1: retiro de cajas");
        System.out.println("pila inicial (tope a fondo):");
        mostrarPila(cajas);

        ejecutarRetiro(retiro, cajas, "C3");
        ejecutarRetiro(retiro, cajas, "C4");
        ejecutarRetiro(retiro, cajas, "C7");

        System.out.println("\npila final (tope a fondo):");
        mostrarPila(cajas);
        mostrarVariables(cajas);
    }

    private static Pila<Caja> crearPilaInicial() {
        Pila<Caja> cajas = new Pila<>();
        cajas.apilar(new Caja("C1", 2));
        cajas.apilar(new Caja("C2", 4));
        cajas.apilar(new Caja("C3", 6));
        cajas.apilar(new Caja("C4", 8));
        cajas.apilar(new Caja("C5", 3));
        cajas.apilar(new Caja("C6", 9));
        cajas.apilar(new Caja("C7", 5));
        return cajas;
    }

    private static void ejecutarRetiro(RetiroCajas retiro, Pila<Caja> cajas, String codigo) {
        boolean resultado = retiro.retirarCaja(cajas, codigo);
        System.out.println("\nretirar " + codigo + " -> " + resultado);
        System.out.println("pila despues del retiro (tope a fondo):");
        mostrarPila(cajas);
    }

    private static void mostrarPila(Pila<Caja> cajas) {
        Pila<Caja> auxiliar = new Pila<>();

        while (!cajas.estaVacia()) {
            Caja caja = cajas.desapilar();
            System.out.println(caja);
            auxiliar.apilar(caja);
        }

        while (!auxiliar.estaVacia()) {
            cajas.apilar(auxiliar.desapilar());
        }
    }

    private static void mostrarVariables(Pila<Caja> cajas) {
        Pila<Caja> auxiliar = new Pila<>();
        Caja primera = cajas.desapilar();
        Caja segunda = cajas.desapilar();
        Caja tercera = cajas.desapilar();
        Caja cuarta = cajas.desapilar();

        auxiliar.apilar(cuarta);
        auxiliar.apilar(tercera);
        auxiliar.apilar(segunda);
        auxiliar.apilar(primera);

        while (!auxiliar.estaVacia()) {
            cajas.apilar(auxiliar.desapilar());
        }

        double a = primera.getValor();
        double b = segunda.getValor() / a;
        double c = tercera.getValor() + a;
        double d = (cuarta.getValor() + c) * a;

        System.out.println("\nvalores asignados:");
        System.out.println("A = " + a);
        System.out.println("B = " + b);
        System.out.println("C = " + c);
        System.out.println("D = " + d);
        System.out.println("\nla expresion de conversion y evaluacion no se incluye porque el enunciado compartido no especifica una expresion.");
    }
}

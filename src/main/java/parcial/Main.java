package parcial;

import java.util.Scanner;

import parcial.ejercicio1.Caja;
import parcial.ejercicio1.Pila;
import parcial.ejercicio1.RetiroCajas;

/**
 * Menu interactivo para probar los ejercicios del parcial.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n===================================");
            System.out.println(" PARCIAL 2 - ESTRUCTURA DE DATOS");
            System.out.println("===================================");
            System.out.println("1. Ejercicio 1 - Retiro de cajas");
            System.out.println("2. Ejercicio 2");
            System.out.println("3. Ejercicio 3");
            System.out.println("0. Salir");

            int opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    menuEjercicio1();
                    break;
                case 2:
                    mostrarEjercicioPendiente(2);
                    break;
                case 3:
                    mostrarEjercicioPendiente(3);
                    break;
                case 0:
                    continuar = false;
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }

        scanner.close();
    }

    private static void menuEjercicio1() {
        Pila<Caja> cajas = new Pila<>();
        RetiroCajas retiro = new RetiroCajas();
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n===================================");
            System.out.println(" EJERCICIO 1 - RETIRO DE CAJAS");
            System.out.println("===================================");
            System.out.println("1. Registrar caja");
            System.out.println("2. Retirar caja");
            System.out.println("3. Mostrar pila");
            System.out.println("4. Consultar caja por codigo");
            System.out.println("5. Mostrar variables A, B, C y D");
            System.out.println("0. Volver al menu principal");

            int opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    registrarCaja(cajas);
                    break;
                case 2:
                    retirarCaja(cajas, retiro);
                    break;
                case 3:
                    System.out.println("\nPila actual (tope a fondo):");
                    mostrarPila(cajas);
                    break;
                case 4:
                    consultarCaja(cajas);
                    break;
                case 5:
                    mostrarVariables(cajas);
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
    }

    private static void registrarCaja(Pila<Caja> cajas) {
        System.out.println("\n--- Registrar caja ---");
        String codigo = leerTexto("Ingrese el codigo: ");

        if (buscarCaja(cajas, codigo) != null) {
            System.out.println("Ya existe una caja con ese codigo.");
            return;
        }

        int valor = leerEntero("Ingrese el valor: ");
        cajas.apilar(new Caja(codigo, valor));
        System.out.println("Caja " + codigo + " registrada correctamente.");
    }

    private static void retirarCaja(Pila<Caja> cajas, RetiroCajas retiro) {
        if (cajas.estaVacia()) {
            System.out.println("No hay cajas registradas.");
            return;
        }

        String codigo = leerTexto("Ingrese el codigo de la caja a retirar: ");
        boolean resultado = retiro.retirarCaja(cajas, codigo);

        if (resultado) {
            System.out.println("Caja retirada y pila reorganizada correctamente.");
        } else {
            System.out.println("No se encontro una caja con ese codigo. La pila permanece igual.");
        }

        System.out.println("\nPila actual (tope a fondo):");
        mostrarPila(cajas);
    }

    private static void consultarCaja(Pila<Caja> cajas) {
        String codigo = leerTexto("Ingrese el codigo de la caja a consultar: ");
        Caja caja = buscarCaja(cajas, codigo);

        if (caja == null) {
            System.out.println("No se encontro una caja con ese codigo.");
        } else {
            System.out.println("Caja encontrada: " + caja);
        }
    }

    private static Caja buscarCaja(Pila<Caja> cajas, String codigo) {
        Pila<Caja> auxiliar = new Pila<>();
        Caja encontrada = null;

        while (!cajas.estaVacia()) {
            Caja actual = cajas.desapilar();
            if (actual.getCodigo().equalsIgnoreCase(codigo)) {
                encontrada = actual;
            }
            auxiliar.apilar(actual);
        }

        restaurarPila(cajas, auxiliar);
        return encontrada;
    }

    private static void mostrarPila(Pila<Caja> cajas) {
        if (cajas.estaVacia()) {
            System.out.println("La pila esta vacia.");
            return;
        }

        Pila<Caja> auxiliar = new Pila<>();
        while (!cajas.estaVacia()) {
            Caja caja = cajas.desapilar();
            System.out.println(caja);
            auxiliar.apilar(caja);
        }

        restaurarPila(cajas, auxiliar);
    }

    private static void mostrarVariables(Pila<Caja> cajas) {
        if (cajas.tamanio() < 4) {
            System.out.println("Se necesitan al menos cuatro cajas para calcular A, B, C y D.");
            return;
        }

        Pila<Caja> auxiliar = new Pila<>();
        Caja primera = cajas.desapilar();
        Caja segunda = cajas.desapilar();
        Caja tercera = cajas.desapilar();
        Caja cuarta = cajas.desapilar();

        // Apilar en orden de extraccion permite restaurar la pila sin alterar su orden.
        auxiliar.apilar(primera);
        auxiliar.apilar(segunda);
        auxiliar.apilar(tercera);
        auxiliar.apilar(cuarta);
        restaurarPila(cajas, auxiliar);

        double a = primera.getValor();
        if (a == 0) {
            System.out.println("No se puede calcular B porque A es cero.");
            return;
        }

        double b = segunda.getValor() / a;
        double c = tercera.getValor() + a;
        double d = (cuarta.getValor() + c) * a;

        System.out.println("\nValores calculados con las cuatro cajas del tope:");
        System.out.println("A = " + a);
        System.out.println("B = " + b);
        System.out.println("C = " + c);
        System.out.println("D = " + d);
        System.out.println("La conversion/evaluacion de una expresion no se agrega porque el enunciado compartido no especifica la expresion.");
    }

    private static void restaurarPila(Pila<Caja> destino, Pila<Caja> auxiliar) {
        while (!auxiliar.estaVacia()) {
            destino.apilar(auxiliar.desapilar());
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }

    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();

            if (!entrada.isEmpty()) {
                return entrada;
            }

            System.out.println("El dato no puede estar vacio.");
        }
    }

    private static void mostrarEjercicioPendiente(int numero) {
        System.out.println("\nEjercicio " + numero + ":");
        System.out.println("El menu esta preparado, pero las operaciones de este ejercicio aun no se han conectado.");
    }
}

package parcial;

import java.util.Scanner;

import parcial.ejercicio1.Caja;
import parcial.ejercicio1.EvaluadorExpresion;
import parcial.ejercicio1.Pila;
import parcial.ejercicio1.RetiroCajas;
import parcial.ejercicio2.ObtenerSegmento;
import parcial.ejercicio3.Cola;
import parcial.ejercicio3.Paquete;
import parcial.ejercicio3.SeleccionarPaquetes;
import parcial.ejercicio3.TipoEntrega;
import parcial.ejercicio4.ArbolBinarioBusqueda;

/**
 * Menu interactivo para probar los ejercicios del parcial.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n PARCIAL 2 - ESTRUCTURA DE DATOS");
            System.out.println("1. Ejercicio 1 - Retiro de cajas");
            System.out.println("2. Ejercicio 2 - Segmento del historial");
            System.out.println("3. Ejercicio 3 - Seleccion de solicitudes");
            System.out.println("4. Ejercicio 4 - Consulta de equipos por rango");
            System.out.println("0. Salir");

            int opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    menuEjercicio1();
                    break;
                case 2:
                    menuEjercicio2();
                    break;
                case 3:
                    menuEjercicio3();
                    break;
                case 4:
                    menuEjercicio4();
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
            System.out.println("\n EJERCICIO 1 - RETIRO DE CAJAS");
            System.out.println("1. Registrar caja");
            System.out.println("2. Retirar caja");
            System.out.println("3. Mostrar pila");
            System.out.println("4. Consultar caja por codigo");
            System.out.println("5. Mostrar variables A, B, C y D");
            System.out.println("6. Evaluar expresion");
            System.out.println("7. Ejecutar datos del enunciado");
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
                case 6:
                    evaluarExpresion(cajas);
                    break;
                case 7:
                    ejecutarDatosEjercicio1();
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

    /**
     * Ejecuta los datos de prueba del enunciado: arma la pila, retira C3, C4 y C7 en
     * secuencia y muestra el resultado de cada llamada.
     */
    private static void ejecutarDatosEjercicio1() {
        Pila<Caja> cajas = new Pila<>();
        cajas.apilar(new Caja("C1", 2));
        cajas.apilar(new Caja("C2", 4));
        cajas.apilar(new Caja("C3", 6));
        cajas.apilar(new Caja("C4", 8));
        cajas.apilar(new Caja("C5", 3));
        cajas.apilar(new Caja("C6", 9));
        cajas.apilar(new Caja("C7", 5));

        RetiroCajas retiro = new RetiroCajas();

        System.out.println("\nPila inicial (tope a fondo):");
        mostrarPila(cajas);

        retirarYMostrar(cajas, retiro, "C3");
        retirarYMostrar(cajas, retiro, "C4");
        retirarYMostrar(cajas, retiro, "C7");

        System.out.println("\nPila final (cima a fondo):");
        mostrarPila(cajas);

        evaluarExpresion(cajas);
    }

    private static void retirarYMostrar(Pila<Caja> cajas, RetiroCajas retiro, String codigo) {
        boolean resultado = retiro.retirarCaja(cajas, codigo);
        System.out.println("\nretirarCaja(" + codigo + ") retorno " + resultado);
        System.out.println("Pila resultante (tope a fondo):");
        mostrarPila(cajas);
    }

    /**
     * Calcula A, B, C y D con las cuatro cajas del tope. Retorna null si no hay
     * cajas suficientes o si A es cero.
     */
    private static double[] calcularVariables(Pila<Caja> cajas) {
        if (cajas.tamanio() < 4) {
            System.out.println("Se necesitan al menos cuatro cajas para calcular A, B, C y D.");
            return null;
        }

        Caja primera = cajas.desapilar();
        Caja segunda = cajas.desapilar();
        Caja tercera = cajas.desapilar();
        Caja cuarta = cajas.desapilar();

        Pila<Caja> auxiliar = new Pila<>();
        auxiliar.apilar(primera);
        auxiliar.apilar(segunda);
        auxiliar.apilar(tercera);
        auxiliar.apilar(cuarta);
        restaurarPila(cajas, auxiliar);

        double a = primera.getValor();
        if (a == 0) {
            System.out.println("No se puede calcular B porque A es cero.");
            return null;
        }

        double b = (double) segunda.getValor() / a;
        double c = tercera.getValor() + a;
        double d = (cuarta.getValor() + c) * a;
        return new double[] { a, b, c, d };
    }

    private static void mostrarVariables(Pila<Caja> cajas) {
        double[] variables = calcularVariables(cajas);
        if (variables == null) {
            return;
        }

        System.out.println("\nValores calculados con las cuatro cajas del tope:");
        imprimirVariables(variables);
    }

    private static void imprimirVariables(double[] variables) {
        System.out.println("A = " + variables[0]);
        System.out.println("B = " + variables[1]);
        System.out.println("C = " + variables[2]);
        System.out.println("D = " + variables[3]);
    }

    /**
     * Convierte la expresion a notacion postfija, reemplaza las variables por sus
     * valores y la evalua con una pila mostrando la tabla de pasos.
     */
    private static void evaluarExpresion(Pila<Caja> cajas) {
        double[] variables = calcularVariables(cajas);
        if (variables == null) {
            return;
        }

        System.out.println("\nValores asignados:");
        imprimirVariables(variables);

        String infija = leerTexto("\nIngrese la expresion en notacion infija (use A, B, C y D): ");
        EvaluadorExpresion evaluador = new EvaluadorExpresion();

        try {
            String postfija = evaluador.convertirAPostfija(infija);
            System.out.println("\nExpresion en notacion postfija: " + postfija);

            String sustituida = evaluador.reemplazarVariables(
                    postfija, variables[0], variables[1], variables[2], variables[3]);
            System.out.println("Expresion con los valores reemplazados: " + sustituida);

            System.out.println("\nEvaluacion paso a paso:");
            double resultado = evaluador.evaluarPostfija(sustituida);
            System.out.println("\nResultado final de la expresion: " + resultado);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("No se pudo evaluar la expresion: " + e.getMessage());
        }
    }

    private static void menuEjercicio2() {
        Pila<String> historial = new Pila<>();
        ObtenerSegmento servicio = new ObtenerSegmento();
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n EJERCICIO 2 - SEGMENTO DEL HISTORIAL");
            System.out.println("1. Registrar accion");
            System.out.println("2. Obtener segmento");
            System.out.println("3. Mostrar historial");
            System.out.println("0. Volver al menu principal");

            int opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    registrarAccion(historial);
                    break;
                case 2:
                    obtenerSegmento(historial, servicio);
                    break;
                case 3:
                    System.out.println("\nHistorial (cima a fondo):");
                    mostrarPila(historial);
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
    }

    private static void registrarAccion(Pila<String> historial) {
        String accion = leerTexto("Ingrese el identificador de la accion: ");
        historial.apilar(accion);
        System.out.println("Accion " + accion + " registrada correctamente.");
    }

    private static void obtenerSegmento(Pila<String> historial, ObtenerSegmento servicio) {
        int inicio = leerEntero("Ingrese la posicion inicial (desde la cima): ");
        int fin = leerEntero("Ingrese la posicion final (desde la cima): ");

        Pila<String> segmento = servicio.obtenerSegmento(historial, inicio, fin);

        if (segmento.estaVacia()) {
            System.out.println("Los indices no son validos. Se retorna una pila vacia.");
        } else {
            System.out.println("\nSegmento obtenido (cima a fondo):");
            mostrarPila(segmento);
        }

        System.out.println("\nHistorial original (cima a fondo), sin cambios:");
        mostrarPila(historial);
    }

    private static void menuEjercicio3() {
        Cola<Paquete> pendientes = new Cola<>();
        SeleccionarPaquetes servicio = new SeleccionarPaquetes();
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n EJERCICIO 3 - SELECCION DE SOLICITUDES");
            System.out.println("1. Registrar paquete");
            System.out.println("2. Seleccionar paquetes");
            System.out.println("3. Mostrar cola de pendientes");
            System.out.println("4. Ejecutar datos del enunciado");
            System.out.println("0. Volver al menu principal");

            int opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    registrarPaquete(pendientes);
                    break;
                case 2:
                    seleccionarPaquetes(pendientes, servicio);
                    break;
                case 3:
                    System.out.println("\nCola de pendientes (frente a final):");
                    mostrarCola(pendientes);
                    break;
                case 4:
                    ejecutarDatosEjercicio3();
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
    }

    private static void registrarPaquete(Cola<Paquete> pendientes) {
        System.out.println("\n--- Registrar paquete ---");
        String codigo = leerTexto("Ingrese el codigo: ");

        int tipo = leerEntero("Tipo de entrega (1 urgente, 2 normal): ");
        while (tipo != 1 && tipo != 2) {
            tipo = leerEntero("Opcion no valida. Tipo de entrega (1 urgente, 2 normal): ");
        }

        int peso = leerEntero("Ingrese el peso en kilogramos: ");

        try {
            pendientes.encolar(new Paquete(codigo,
                    tipo == 1 ? TipoEntrega.URGENTE : TipoEntrega.NORMAL, peso));
            System.out.println("Paquete " + codigo + " registrado correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("No se pudo registrar el paquete: " + e.getMessage());
        }
    }

    private static void seleccionarPaquetes(Cola<Paquete> pendientes, SeleccionarPaquetes servicio) {
        if (pendientes.estaVacia()) {
            System.out.println("No hay paquetes pendientes.");
            return;
        }

        int capacidad = leerEntero("Ingrese la capacidad disponible en kilogramos: ");
        mostrarSeleccion(pendientes, servicio, capacidad);
    }

    /**
     * Ejecuta la seleccion y muestra lo solicitado en el enunciado.
     */
    private static void mostrarSeleccion(Cola<Paquete> pendientes, SeleccionarPaquetes servicio,
                                         int capacidad) {
        System.out.println("\nCola inicial (frente a final):");
        mostrarCola(pendientes);

        Cola<Paquete> seleccionados = servicio.seleccionarPaquetes(pendientes, capacidad);

        System.out.println("\nPaquetes evaluados:");
        mostrarCola(servicio.getEvaluaciones());

        System.out.println("\nCola retornada por la funcion (frente a final):");
        mostrarCola(seleccionados);

        System.out.println("\nCola de pendientes despues de la seleccion (frente a final):");
        mostrarCola(pendientes);

        System.out.println("\nCapacidad disponible restante: " + servicio.getCapacidadRestante() + " kg");
    }

    /**
     * Ejecuta los datos de prueba del enunciado con una capacidad de 14 kg.
     */
    private static void ejecutarDatosEjercicio3() {
        Cola<Paquete> pendientes = new Cola<>();
        pendientes.encolar(new Paquete("P1", TipoEntrega.NORMAL, 4));
        pendientes.encolar(new Paquete("P2", TipoEntrega.URGENTE, 7));
        pendientes.encolar(new Paquete("P3", TipoEntrega.NORMAL, 3));
        pendientes.encolar(new Paquete("P4", TipoEntrega.URGENTE, 5));
        pendientes.encolar(new Paquete("P5", TipoEntrega.NORMAL, 6));
        pendientes.encolar(new Paquete("P6", TipoEntrega.URGENTE, 4));
        pendientes.encolar(new Paquete("P7", TipoEntrega.NORMAL, 2));

        mostrarSeleccion(pendientes, new SeleccionarPaquetes(), 14);
    }

    private static void menuEjercicio4() {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n EJERCICIO 4 - CONSULTA DE EQUIPOS POR RANGO");
            System.out.println("1. Registrar codigo de equipo");
            System.out.println("2. Contar equipos en rango");
            System.out.println("3. Cargar los codigos del enunciado");
            System.out.println("0. Volver al menu principal");

            int opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    arbol.insertar(leerEntero("Ingrese el codigo del equipo: "));
                    System.out.println("Codigo registrado correctamente.");
                    break;
                case 2:
                    contarEnRango(arbol);
                    break;
                case 3:
                    cargarCodigosEjercicio4(arbol);
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
    }

    private static void contarEnRango(ArbolBinarioBusqueda arbol) {
        int inferior = leerEntero("Ingrese el limite inferior: ");
        int superior = leerEntero("Ingrese el limite superior: ");

        int cantidad = ArbolBinarioBusqueda.contarEquiposEnRango(arbol.getRaiz(), inferior, superior);
        System.out.println("Equipos con codigo entre " + inferior + " y " + superior + ": " + cantidad);
    }

    /**
     * Inserta los codigos del enunciado en el orden indicado.
     */
    private static void cargarCodigosEjercicio4(ArbolBinarioBusqueda arbol) {
        int[] codigos = { 41, 20, 52, 16, 6, 13, 12, 60, 22, 47, 50, 60, 43, 44 };
        for (int codigo : codigos) {
            arbol.insertar(codigo);
        }
        System.out.println("Codigos cargados. El 60 repetido se ignora porque los codigos son unicos.");
    }

    /**
     * Muestra el contenido de la pila desde el tope hasta el fondo sin alterarla.
     */
    private static <T> void mostrarPila(Pila<T> pila) {
        if (pila.estaVacia()) {
            System.out.println("La pila esta vacia.");
            return;
        }

        Pila<T> auxiliar = new Pila<>();
        StringBuilder texto = new StringBuilder();

        while (!pila.estaVacia()) {
            T dato = pila.desapilar();
            if (texto.length() > 0) {
                texto.append(" -> ");
            }
            texto.append(dato);
            auxiliar.apilar(dato);
        }

        restaurarPila(pila, auxiliar);
        System.out.println(texto);
    }

    /**
     * Muestra el contenido de la cola desde el frente hasta el final sin alterarla.
     */
    private static <T> void mostrarCola(Cola<T> cola) {
        if (cola.estaVacia()) {
            System.out.println("La cola esta vacia.");
            return;
        }

        StringBuilder texto = new StringBuilder();
        recorrerCola(cola, cola.tamanio(), texto);
        System.out.println(texto);
    }

    private static <T> void recorrerCola(Cola<T> cola, int porRecorrer, StringBuilder texto) {
        if (porRecorrer == 0) {
            return;
        }

        T dato = cola.desencolar();
        if (texto.length() > 0) {
            texto.append(" -> ");
        }
        texto.append(dato);
        cola.encolar(dato);

        recorrerCola(cola, porRecorrer - 1, texto);
    }

    private static <T> void restaurarPila(Pila<T> destino, Pila<T> auxiliar) {
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
}

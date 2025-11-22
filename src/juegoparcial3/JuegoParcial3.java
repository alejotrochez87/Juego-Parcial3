package juegoparcial3;

import java.util.Scanner;

/**
 *
 * @author Alejandro
 */
public class JuegoParcial3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(Colores.AMARILLO + " Bienvenido a Batalla de Poderes " + Colores.RESET);
        System.out.print("Ingrese su nombre: ");
        String nombreJugador = sc.nextLine();

        // Crear estructuras
        ListaEnlazada baraja = new ListaEnlazada();
        Cola mazoJugador = new Cola();
        Cola mazoCPU = new Cola();
        Pila cementerioJugador = new Pila();
        Pila cementerioCPU = new Pila();
        ArbolBinario estrategiaCPU = new ArbolBinario();
        TablaHash tabla = new TablaHash(10);

        // Generar baraja
        for (int i = 1; i <= 40; i++) {
            String tipo = switch (i % 4) {
                case 0 -> "Fuego";
                case 1 -> "Agua";
                case 2 -> "Tierra";
                default -> "Aire";
            };
            baraja.agregar(new Carta("Carta" + i, tipo, (int)(Math.random()*100)+1));
        }

        // Repartir cartas
        for (int i = 0; i < 10; i++) {
            mazoJugador.enqueue(baraja.obtenerAleatoria());
            mazoCPU.enqueue(baraja.obtenerAleatoria());
        }

        System.out.println("Tu mazo tiene 10 cartas");
        System.out.println("El oponente CPU también tiene 10 cartas");

        int rondasJugador = 0, rondasCPU = 0;

        for (int ronda = 1; ronda <= 10; ronda++) {
            System.out.println("\n--- Ronda " + ronda + " ---");

            // Mostrar opciones al jugador
            Carta opcion1 = mazoJugador.dequeue();
            Carta opcion2 = mazoJugador.dequeue();
            System.out.println("Tus proximas cartas:");
            System.out.println("1. " + Colores.ROJO + opcion1 + Colores.RESET);
            System.out.println("2. " + Colores.AZUL + opcion2 + Colores.RESET);

            System.out.print("Elige la carta a jugar (1 o 2): ");
            int eleccion = sc.nextInt();
            Carta cartaJ = (eleccion == 1) ? opcion1 : opcion2;

            // La carta no elegida vuelve al final del mazo
            if (eleccion == 1) mazoJugador.enqueue(opcion2);
            else mazoJugador.enqueue(opcion1);

            // CPU decide con arbol binario
            String decisionCPU = estrategiaCPU.decidir(cartaJ.getPoder(), cartaJ.getTipo());
            Carta cartaC = mazoCPU.dequeue();
            System.out.println("CPU decide: " + decisionCPU);
            System.out.println("CPU juega: " + cartaC);

            // Comparar cartas
            if (cartaJ.getPoder() >= cartaC.getPoder()) {
                System.out.println(Colores.VERDE + "Ganador de la ronda: " + nombreJugador + " 🎉" + Colores.RESET);
                rondasJugador++;
                cartaJ.setPoder(cartaJ.getPoder() + 5);
                mazoJugador.enqueue(cartaJ);
                cementerioCPU.push(cartaC);
                tabla.agregar(nombreJugador, cartaJ, true);
                tabla.agregar("CPU", cartaC, false);
            } else {
                System.out.println(Colores.ROJO + "Ganador de la ronda: CPU " + Colores.RESET);
                rondasCPU++;
                cartaC.setPoder(cartaC.getPoder() + 5);
                mazoCPU.enqueue(cartaC);
                cementerioJugador.push(cartaJ);
                tabla.agregar("CPU", cartaC, true);
                tabla.agregar(nombreJugador, cartaJ, false);
            }
        }

        // Mostrar ganador final
        System.out.println("\n--- Estadisticas Finales ---");
        tabla.mostrar(nombreJugador);
        tabla.mostrar("CPU");

        if (rondasJugador > rondasCPU) {
            System.out.println("¡" + nombreJugador + " gana la partida¡");
        } else if (rondasCPU > rondasJugador) {
            System.out.println(" ¡CPU gana la partida! ");
        } else {
            System.out.println(" ¡La partida termina en empate!");
        }

        // Mostrar cementerio del jugador
        System.out.println("\n--- Cartas derrotadas de " + nombreJugador + " ---");
        while (!cementerioJugador.estaVacia()) {
            System.out.println("Top: " + cementerioJugador.pop());
        }

        // Mostrar cementerio del CPU
        System.out.println("\n--- Cartas derrotadas del CPU ---");
        while (!cementerioCPU.estaVacia()) {
            System.out.println("Top: " + cementerioCPU.pop());
        }

        System.out.println("\nGracias por jugar Batalla de Poderes ");
        sc.close();
    }
}


package juegoparcial3;

import java.util.ArrayList;

/**
 *
 * @author Alejandro
 */
public class TablaHash {
  private class Registro {
        String jugador;
        ArrayList<Carta> cartasJugadas = new ArrayList<>();
        int rondasGanadas = 0;
        int poderTotal = 0;
    }

    private Registro[] tabla;

    public TablaHash(int size) {
        tabla = new Registro[size];
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % tabla.length;
    }

    public void agregar(String jugador, Carta carta, boolean gano) {
        int index = hash(jugador);
        if (tabla[index] == null) {
            tabla[index] = new Registro();
            tabla[index].jugador = jugador;
        }
        tabla[index].cartasJugadas.add(carta);
        tabla[index].poderTotal += carta.getPoder();
        if (gano) tabla[index].rondasGanadas++;
    }

    public void mostrar(String jugador) {
        int index = hash(jugador);
        Registro r = tabla[index];
        if (r != null) {
            System.out.println(jugador + ": " + r.rondasGanadas + " rondas ganadas, Poder acumulado: " + r.poderTotal);
        }
    }
}

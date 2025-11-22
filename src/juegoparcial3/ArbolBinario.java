package juegoparcial3;


public class ArbolBinario {

    // Nodo interno del arbol
    private class Nodo {
        String decision;
        Nodo izquierda, derecha;

        Nodo(String decision) {
            this.decision = decision;
        }
    }

    private Nodo raiz;

    public ArbolBinario() {
        raiz = new Nodo("¿Carta jugador > 80?");
        raiz.izquierda = new Nodo("¿Tipo debil?");
        raiz.derecha = new Nodo("Jugar carta fuerte ");

        raiz.izquierda.izquierda = new Nodo("Jugar carta media ");
        raiz.izquierda.derecha = new Nodo("Jugar carta fuerte ");
    }

    public String decidir(int poderJugador, String tipoJugador) {
        // Nodo raiz: ¿Carta jugador > 80?
        if (poderJugador > 80) {
            // Rama izquierda: ¿Tipo debil?
            if (tipoJugador.equalsIgnoreCase("Fuego")) {
                return raiz.izquierda.izquierda.decision; // Jugar carta media
            } else {
                return raiz.izquierda.derecha.decision; // Jugar carta fuerte
            }
        } else {
            // Rama derecha: Jugar carta fuerte
            return raiz.derecha.decision;
        }
    }
}

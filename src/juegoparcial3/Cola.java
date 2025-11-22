package juegoparcial3;

/**
 *
 * @author Alejandro
 */
public class Cola {
    private Nodo frente, fin;

    private class Nodo {
        Carta carta;
        Nodo siguiente;
        Nodo(Carta carta) { this.carta = carta; }
    }

    public void enqueue(Carta carta) {
        Nodo nuevo = new Nodo(carta);
        if (fin != null) fin.siguiente = nuevo;
        fin = nuevo;
        if (frente == null) frente = nuevo;
    }

    public Carta dequeue() {
        if (frente == null) return null;
        Carta carta = frente.carta;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        return carta;
    }

    public boolean estaVacia() { return frente == null; }
}


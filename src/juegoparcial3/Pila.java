package juegoparcial3;

/**
 *
 * @author Alejandro
 */
public class Pila {
      private Nodo tope;

    private class Nodo {
        Carta carta;
        Nodo siguiente;
        Nodo(Carta carta) { this.carta = carta; }
    }

    public void push(Carta carta) {
        Nodo nuevo = new Nodo(carta);
        nuevo.siguiente = tope;
        tope = nuevo;
    }

    public Carta pop() {
        if (tope == null) return null;
        Carta carta = tope.carta;
        tope = tope.siguiente;
        return carta;
    }

    public Carta peek() {
        return (tope != null) ? tope.carta : null;
    }

    public boolean estaVacia() { return tope == null; }
}


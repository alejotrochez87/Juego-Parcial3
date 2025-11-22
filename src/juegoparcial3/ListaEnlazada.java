package juegoparcial3;

/**
 *
 * @author Alejandro
 */
public class ListaEnlazada {

    private Nodo cabeza;

    private class Nodo {

        Carta carta;
        Nodo siguiente;

        Nodo(Carta carta) {
            this.carta = carta;
        }
    }

    public void agregar(Carta carta) {
        Nodo nuevo = new Nodo(carta);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
        }
    }

    public Carta obtenerAleatoria() {
        int size = contar();
        int index = (int) (Math.random() * size);
        Nodo temp = cabeza;
        for (int i = 0; i < index; i++) {
            temp = temp.siguiente;
        }
        return temp.carta;
    }

    public int contar() {
        int count = 0;
        Nodo temp = cabeza;
        while (temp != null) {
            count++;
            temp = temp.siguiente;
        }
        return count;
    }
}

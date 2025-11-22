package juegoparcial3;

/**
 *
 * @author Alejandro
 */
public class Carta {
        private String nombre;
    private String tipo; // Fuego, Agua, Tierra, Aire
    private int poder;

    public Carta(String nombre, String tipo, int poder) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.poder = poder;
    }

    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public int getPoder() { return poder; }
    public void setPoder(int poder) { this.poder = poder; }

    @Override
    public String toString() {
        return nombre + " (" + tipo + ") - Poder: " + poder;
    }
}

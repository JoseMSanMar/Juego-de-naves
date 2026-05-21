package juego;

import edu.epromero.util.Lienzo;

/**
 *
 * @author ernes
 */
public class Entrada {

    private Lienzo miCanvas;
    private int tecla;
    private Heroe bueno;

    /**
     * @return the miCanvas
     */
    public Lienzo getMiCanvas() {
        return miCanvas;
    }

    /**
     * @param miCanvas the miCanvas to set
     */
    public void setMiCanvas(Lienzo miCanvas) {
        this.miCanvas = miCanvas;
    }

    /**
     * @return the tecla
     */
    public int getTecla() {
        return tecla;
    }

    /**
     * @param tecla the tecla to set
     */
    public void setTecla(int tecla) {
        this.tecla = tecla;
    }

    /**
     * @return the bueno
     */
    public Heroe getBueno() {
        return bueno;
    }

    /**
     * @param bueno the bueno to set
     */
    public void setBueno(Heroe bueno) {
        this.bueno = bueno;
    }

}

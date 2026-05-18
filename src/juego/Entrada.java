package juego;

import edu.epromero.util.Lienzo;

/**
 *
 * @author ernes
 */
public class Entrada {

    protected Lienzo miCanvas;
    protected int tecla;
    protected Heroe bueno;

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

}

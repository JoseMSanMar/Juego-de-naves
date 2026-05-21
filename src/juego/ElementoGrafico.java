package juego;

import edu.epromero.util.Lienzo;
import edu.epromero.util.Imagen;

/**
 *
 * @author user
 */
public abstract class ElementoGrafico {

    private double columna; //Columna del elemento
    private double renglon; //Renglon del elemento
    private String nomSprite; //El nombre del archivo que contiene el sprite
    private Imagen miImagen;
    private boolean visible;
    private Lienzo canvas;

    public static final int DERECHA = 1;
    public static final int IZQUIERDA = -1;
    public static final int ARRIBA = 1;
    public static final int ABAJO = -1;

    /**
     * Este es el constructor
     */
    public ElementoGrafico() {
        setColumna(0);
        setRenglon(0);
        setNomSprite("");
    }

    /**
     * Este es el constructor
     *
     * @param col la columna en la que se establece la imagen
     * @param ren el renglon en la que se establece la imagen
     */
    public ElementoGrafico(double col, double ren) {
        setColumna(col);
        setRenglon(ren);
        setNomSprite("");

    }

    public boolean isVisible() {
        return visible;
    }

    public void aparecer() {
        setVisible(true);
    }

    public void inicia() {
        this.miImagen = new Imagen(getNomSprite());
    }

    public abstract void Mueve(Entrada e);

    /**
     * @return the columna
     */
    public double getColumna() {
        return columna;
    }

    /**
     * @param columna the columna to set
     */
    public void setColumna(double columna) {
        this.columna = columna;
    }

    /**
     * @return the renglon
     */
    public double getRenglon() {
        return renglon;
    }

    /**
     * @param renglon the renglon to set
     */
    public void setRenglon(double renglon) {
        this.renglon = renglon;
    }

    /**
     * @return the nomSprite
     */
    public String getNomSprite() {
        return nomSprite;
    }

    /**
     * @param nomSprite the nomSprite to set
     */
    public void setNomSprite(String nomSprite) {
        this.nomSprite = nomSprite;
    }

    /**
     * @return the miImagen
     */
    public Imagen getMiImagen() {
        return miImagen;
    }

    /**
     * @param miImagen the miImagen to set
     */
    public void setMiImagen(Imagen miImagen) {
        this.miImagen = miImagen;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @return the canvas
     */
    public Lienzo getCanvas() {
        return canvas;
    }

    /**
     * @param canvas the canvas to set
     */
    public void setCanvas(Lienzo canvas) {
        this.canvas = canvas;
    }
}

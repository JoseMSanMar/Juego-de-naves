/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;

import edu.epromero.util.LienzoStd;
import edu.epromero.util.Lienzo;
import edu.epromero.util.Imagen;

/**
 *
 * @author user
 */
abstract public class ElementoGrafico {

    protected double x; //Columna del elemento
    protected double y; //Renglon del elemento
    protected String nomSprite; //El nombre del archivo que contiene el sprite
    protected Imagen miImagen;
    protected boolean visible;
    protected Lienzo canvas;

    public static final int DERECHA = 1;
    public static final int IZQUIERDA = -1;
    public static final int ARRIBA = 1;
    public static final int ABAJO = -1;

    /**
     * Este es el constructor
     */
    public ElementoGrafico() {
        setX(0);
        setY(0);
        setNomSprite("");
    }

    /**
     * Este es el constructor
     *
     * @param col la columna en la que se establece la imagen
     * @param ren el renglon en la que se establece la imagen
     */
    public ElementoGrafico(double col, double ren) {
        setX(col);
        setY(ren);
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

    public abstract void mueve(Entrada e);

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
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
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juego;

import edu.epromero.util.Lienzo;

/**
 *
 * @author user
 */
public class Bala extends ElementoGrafico {

    public static final int INACTIVA = 0;
    public static final int VUELO = 1;
    public static final int EXPLOTANDO = 2;
    private int estado;
    private int ciclosExplosion;
    private Lienzo lienzo;

    public Bala() {
        super();
        setNomSprite(".\\src\\bala.png");
        inicia();
        this.miImagen.ponColorTransparente(Lienzo.BLANCO);
        setEstado(INACTIVA);

    }

    public void iniciarPosicion(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void pinta(Lienzo canvas) {
        this.canvas = canvas;
        canvas.dibujo(getX(), getY(), this.miImagen);
    }

    @Override
    public void mueve(Entrada e) {
        if (getEstado() == VUELO) {
            this.y += 30;
            if (this.y >= e.getMiCanvas().pideLimiteYMax()) {
                setEstado(INACTIVA);
            }
        }
        if (getEstado() == EXPLOTANDO) {
            setCiclosExplosion(getCiclosExplosion() + 1);
            if (getCiclosExplosion() >= 3) {
                setEstado(INACTIVA);
            }

        }
    }

    public void setEstado(int estado) {
        if (estado < 0 || estado > 2) {
            throw new IllegalArgumentException("Error de estado.");
        } else {
            this.estado = estado;
            if (estado == EXPLOTANDO || estado == VUELO) {
                setCiclosExplosion(0);
            }
        }
    }

    public int getEstado() {
        return estado;
    }

    /**
     * @return the posicion
     */
    /**
     * @return the lienzo
     */
    public Lienzo getLienzo() {
        return lienzo;
    }

    /**
     * @param lienzo the lienzo to set
     */
    public void setLienzo(Lienzo lienzo) {
        this.lienzo = lienzo;
    }

    /**
     * @return the ciclosExplosion
     */
    public int getCiclosExplosion() {
        return ciclosExplosion;
    }

    /**
     * @param ciclosExplosion the ciclosExplosion to set
     */
    public void setCiclosExplosion(int ciclosExplosion) {
        this.ciclosExplosion = ciclosExplosion;
    }

}

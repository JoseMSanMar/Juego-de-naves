package juego;

import edu.epromero.util.Imagen;
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
    private Imagen miImagen;
    private double direccionY = 30;

    public Bala() {
        super();
        setNomSprite("./resources/bala.png");
        inicia();
        getMiImagen().ponColorTransparente(Lienzo.BLANCO);
        setEstado(INACTIVA);

    }

    public void iniciarPosicion(double x, double y, double direccionY) {
        setColumna(x);
        setRenglon(y);
        this.direccionY = direccionY;
    }

    public void pinta(Lienzo canvas) {
        setCanvas(canvas);
        canvas.dibujo(getColumna(), getRenglon(), getMiImagen());
    }

    @Override
    public void Mueve(Entrada e) {
        if (getEstado() == VUELO) {
            setRenglon(getRenglon() + direccionY);
            if (getRenglon() >= e.getMiCanvas().pideLimiteYMax()
                    || getRenglon() <= e.getMiCanvas().pideLimiteYMin()) {
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

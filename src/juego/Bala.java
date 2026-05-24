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
    //Reciben data de la nave
    private int estado;
    private int ciclosExplosion;
    //Direccion default
    private double direccionY = 0;
    private double direccionX = 0;

    public Bala() {
        super();
        setNomSprite("/resources/bala.png");
        inicia();
        getMiImagen().ponColorTransparente(Lienzo.BLANCO);
        setEstado(INACTIVA);

    }

    //inicia la pocision de la bala
    public void iniciarPosicion(double x, double y,
            double direccionX, double direccionY) {
        setColumna(x);
        setRenglon(y);
        this.direccionX = direccionX;
        this.direccionY = direccionY;
    }

    //pinta la img de la bala
    public void pinta(Lienzo canvas) {
        setCanvas(canvas);
        canvas.dibujo(getColumna(), getRenglon(), getMiImagen());
    }

    @Override
    public void Mueve(Entrada e) {
        //Si la bala esta en vuelo se mueve
        if (getEstado() == VUELO) {
            setRenglon(getRenglon() + direccionY);
            setColumna(getColumna() + direccionX);
            //Si ya esta fuera de la pantalla se apaga
            if (getRenglon() >= e.getMiCanvas().pideLimiteYMax()
                    || getRenglon() <= e.getMiCanvas().pideLimiteYMin()) {
                setEstado(INACTIVA);
            }
        }
        //Inicia animacion de explosion
        if (getEstado() == EXPLOTANDO) {
            setCiclosExplosion(getCiclosExplosion() + 1);
            if (getCiclosExplosion() >= 3) {
                setEstado(INACTIVA);
            }

        }
    }

    //Recibe el estado de la bala
    public void setEstado(int estado) {
        //revisa que el estado sea valido
        if (estado < 0 || estado > 2) {
            throw new IllegalArgumentException("Error de estado.");
        } else {
            this.estado = estado;
            if (estado == EXPLOTANDO || estado == VUELO) {
                setCiclosExplosion(0);
            }
        }
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @return the posicion
     */
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

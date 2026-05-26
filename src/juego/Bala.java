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

        if (getEstado() == VUELO) {
            // DIBUJO NORMAL: Pinta el láser/proyectil en su posición
            canvas.dibujo(getColumna(), getRenglon(), getMiImagen());
        } else {
            // Si no está en vuelo, abrimos el bloque else y preguntamos por la explosión
            if (getEstado() == EXPLOTANDO) {
                // DIBUJO DE EXPLOSIÓN: Guardamos el nombre del sprite de la bala
                String spriteOriginal = getNomSprite();

                // Cambiamos temporalmente al sprite de explosión
                setNomSprite("/resources/explosion.png");
                inicia();
                getMiImagen().ponColorTransparente(Lienzo.BLANCO);

                // Dibujamos el estallido centrado
                canvas.dibujo(getColumna() - 20, getRenglon() - 20, getMiImagen());

                // RESTAURAMOS EL SPRITE ORIGINAL
                setNomSprite(spriteOriginal);
                inicia();
                getMiImagen().ponColorTransparente(Lienzo.BLANCO);
            }
        }
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

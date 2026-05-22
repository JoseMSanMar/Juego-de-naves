package juego;

import edu.epromero.util.Lienzo;
import edu.epromero.util.ComportamientoEnemigo;

/**
 *
 * @author user
 */
@ComportamientoEnemigo(tipo = "Destructor", resistencia = 2, puntos = 50)
public class Destructor extends NaveEnemiga {

    private int componenteX;
    private int componenteY;

    public Destructor() {

        super();
        // Pon la ruta de tu imagen enemiga
        setNomSprite("/resources/enemigo.png");
        inicia();
        getMiImagen().ponColorTransparente(Lienzo.BLANCO);
        //Se inicia la direccion de la nave
        this.componenteX = DERECHA;
        setPuntos(50);
        setVidas(2);
    }

    @Override
    public void Mueve(Entrada e) {
        //Averiguar si se acerca a la derecha
        if (getColumna() > (e.getMiCanvas().pideLimiteXMax()) - 120) {
            setComponenteX(IZQUIERDA);
        }
        //Averiguar si se acerca a la izquierda
        if (getColumna() < (e.getMiCanvas().pideLimiteXMin()) + 120) {
            setComponenteX(DERECHA);
        }
        //actualiza la pocision de la nave
        setColumna(getColumna() + (componenteX * 30));
        //Lee si la nave disparó
        dispara();

        // ACTUALIZACIÓN DE LA BALA: Si la bala está activa, avanza UN paso
        if (getBala().getEstado() == Bala.VUELO) {
            getBala().Mueve(e);
        }
        // FASE DE ENTRADA: Si la nave está por encima del límite visible de juego
        if (getRenglon() > (e.getMiCanvas().pideLimiteYMax() - 150)) {
            // La hacemos bajar verticalmente de 15 en 15 píxeles hasta su zona de patrullaje
            setRenglon(getRenglon() - 15);
        }
    }

    public boolean hayColision(Bala bala) {
        boolean siHayColision;
        siHayColision = false;
        if (getRenglon() < bala.getRenglon() + 80
                && getRenglon() > bala.getRenglon() - 80) {
            if (getColumna() < bala.getColumna() + 80
                    && getColumna() > bala.getColumna() - 80) {
                siHayColision = true;
            }
        }

        return siHayColision;
    }

    public void dispara() {
        // IA de disparo por probabilidad (20% de probabilidad por frame)
        if (Math.random() < 0.1) {
            if (getBala().getEstado() == Bala.INACTIVA) {
                getBala().iniciarPosicion(getColumna(), getRenglon(), -60);
                getBala().setEstado(Bala.VUELO);
            }
        }
    }

    /**
     * @return the bala
     */
    /**
     * @return the componenteX
     */
    public int getComponenteX() {
        return componenteX;
    }

    /**
     * @param componenteX the componenteX to set
     */
    public void setComponenteX(int componenteX) {
        this.componenteX = componenteX;
    }

    /**
     * @return the componenteY
     */
    public int getComponenteY() {
        return componenteY;
    }

    /**
     * @param componenteY the componenteY to set
     */
    public void setComponenteY(int componenteY) {
        this.componenteY = componenteY;
    }
}

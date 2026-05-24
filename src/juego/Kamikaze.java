package juego;

import edu.epromero.util.ComportamientoEnemigo;
import edu.epromero.util.Lienzo;

/**
 *
 * @author user
 */
@ComportamientoEnemigo(tipo = "Generico", resistencia = 1, puntos = 10)
public class Kamikaze extends NaveEnemiga {

    private int componenteX;
    private int componenteY;

    public Kamikaze() {
        super();
        // Pon la ruta de tu imagen enemiga
        setNomSprite("/resources/enemigo.png");
        inicia();
        getMiImagen().ponColorTransparente(Lienzo.BLANCO);
        //Se inicia la direccion de la nave
        this.componenteX = DERECHA;
        this.componenteY = ARRIBA;
        setPuntos(10);
        setVidas(1);
    }

    @Override
    public void Mueve(Entrada e) {
        //**********CONTROL DEL MOVIMIENTO Y DIRECCION*********
        //Averiguar si se acerca a la derecha
        if (getColumna() > (e.getMiCanvas().pideLimiteXMax()) - 120) {
            setComponenteX(IZQUIERDA);
        }
        //Averiguar si se acerca a la izquierda
        if (getColumna() < (e.getMiCanvas().pideLimiteXMin()) + 120) {
            setComponenteX(DERECHA);
        }
        //Averiguar si se acerca al techo
        if (getRenglon() > (e.getMiCanvas().pideLimiteYMax()) - 120) {
            setComponenteY(ABAJO);
        }
        //Averiguar si se acerca al lim inferior
        if (getRenglon() < (e.getMiCanvas().pideLimiteYMin()) + 150) {
            setComponenteY(ARRIBA);
        }
        //actualiza la pocision de la nave
        setRenglon(getRenglon() + (getComponenteY() * 30));
        setColumna(getColumna() + (getComponenteX() * 30));
        //**********CONTROL DE REAPARICION DE LA NAVE**********
        // FASE DE ENTRADA: Si la nave está por encima del límite
        //visible de juego
        if (getRenglon() > getPosRanY()) {
            // La hacemos avanzar horizontalmentee de 15 en 15 píxeles
            //hasta su zona de patrullaje
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

package juego;

import edu.epromero.util.Lienzo;
import static juego.ElementoGrafico.DERECHA;
import edu.epromero.util.ComportamientoEnemigo;

/**
 *
 * @author user
 */
@ComportamientoEnemigo(tipo = "LanzaMinas", resistencia = 4, puntos = 150)
public class LanzaMinas extends NaveEnemiga {

    //direccion del vector de movimiento
    private int componenteX;
    private int componenteY;

    public LanzaMinas() {
        super();
        // Pon la ruta de tu imagen enemiga
        setNomSprite("/resources/LanzaMinas.png");
        inicia();
        getMiImagen().ponColorTransparente(Lienzo.BLANCO);
        //Se inicia la direccion de la nave a la DERECHA
        this.componenteX = DERECHA;
        setPuntos(150);
        setVidas(4);
    }

    @Override
    public void iniciarPosicion(Lienzo canvas) {
        setCanvas(canvas);
        Random();
        setColumna(getPosRanX());
        setRenglon(getCanvas().pideLimiteYMax() - 60);
    }

    @Override
    public void Mueve(Entrada e) {
        //Averiguar si se sale por la derecha
        //cambiar el sentido
        if (getColumna() > (e.getMiCanvas().pideLimiteXMax()) - 120) {
            setComponenteX(IZQUIERDA);
        }
        //Averiguar si se sale por la izq
        if (getColumna() < (e.getMiCanvas().pideLimiteXMin()) + 120) {
            setComponenteX(DERECHA);
        }
        setColumna(getColumna() + (componenteX * 10));

        dispara();

        // ACTUALIZACIÓN DE LA BALA: Si la bala está activa, avanza UN paso
        if (getBala().getEstado() == Bala.VUELO) {
            getBala().Mueve(e);
        }

        if (getRenglon() > (e.getMiCanvas().pideLimiteYMax()) - 150) {
            setRenglon(getRenglon() - 15);
        }
    }

    public void dispara() {
        // IA de disparo por probabilidad (20% de probabilidad por frame)
        if (Math.random() < 0.01) {
            if (getBala().getEstado() == Bala.INACTIVA) {
                getBala().iniciarPosicion(getColumna(), getRenglon(), -60);
                getBala().setEstado(Bala.VUELO);
            }
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

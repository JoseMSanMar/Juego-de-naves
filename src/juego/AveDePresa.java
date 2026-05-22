package juego;

import edu.epromero.util.Lienzo;
import edu.epromero.util.ComportamientoEnemigo;

/**
 *
 * @author user
 */
@ComportamientoEnemigo(tipo = "AveDePresa", resistencia = 2, puntos = 100)
public class AveDePresa extends NaveEnemiga {

    private int componenteY;

    public AveDePresa() {

        super();
        // Pon la ruta de tu imagen enemiga
        setNomSprite("/resources/avepresa.png");
        inicia();
        getMiImagen().ponColorTransparente(Lienzo.BLANCO);
        //Se inicia la direccion de la nave
        this.componenteY = ABAJO;
        setPuntos(100);
        setVidas(2);
    }

    @Override
    public void Mueve(Entrada e) {
        //Averiguar si se sale por la derecha
        //cambiar el sentido
        if (getRenglon() > (e.getMiCanvas().pideLimiteYMax()) - 150) {
            setComponenteY(ABAJO);
        }
        //Averiguar si se sale por la izq
        if (getRenglon() < (e.getMiCanvas().pideLimiteYMin()) + 150) {
            setComponenteY(ARRIBA);
        }
        setRenglon(getRenglon() + (getComponenteY() * 20));

        dispara();

        // ACTUALIZACIÓN DE LA BALA:
        //Si la bala está activa, avanza UN paso en este frame
        if (getBala().getEstado() == Bala.VUELO) {
            getBala().Mueve(e);

            if (getRenglon() > (e.getMiCanvas().pideLimiteYMax() - 150)) {
                setRenglon(getRenglon() - 15);
            }
        }
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

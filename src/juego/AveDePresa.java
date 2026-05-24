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
        //**********CONTROL DEL MOVIMIENTO Y DIRECCION*********
        //Averiguar si se acerca al techo
        if (getRenglon() > (e.getMiCanvas().pideLimiteYMax()) - 150) {
            setComponenteY(ABAJO);
        }
        //Averiguar si se acerca al lim inferior
        if (getRenglon() < (e.getMiCanvas().pideLimiteYMin()) + 150) {
            setComponenteY(ARRIBA);
        }
        setRenglon(getRenglon() + (getComponenteY() * 20));
        //**********CONTROL DE LOS DISPAROS Y LAS BALAS**********
        Bala[] cartucho = getCargador();
        //Lee si la nave disparo
        dispara();
        // ACTUALIZACIÓN DE LA BALA:
        //Si la bala está activa, avanza UN paso en este frame
        if (cartucho[0].getEstado() == Bala.VUELO) {
            cartucho[0].Mueve(e);
        }
        //**********CONTROL DE REAPARICIONDE LA NAVE**********
        // FASE DE ENTRADA: Si la nave está por encima del límite
        //visible de juego
        if (getColumna() > getPosRanX()) {
            setColumna(getColumna() - 15);
        }
        if (getColumna() < getPosRanX()) {
            setColumna(getColumna() + 15);
        }
    }

    public void dispara() {
        // IA de disparo por probabilidad (8% de probabilidad por frame)
        if (Math.random() < 0.08) {
            //INICIAMOS el arreglo porque hereda de NaveEnemiga aunque
            //sea solo uno
            Bala[] cartucho = getCargador();
            // Verificamos si no hay balas ya activas
            boolean cargadorListo = true;
            if (cartucho[0].getEstado() != Bala.INACTIVA) {
                cargadorListo = false;
            }
            if (cargadorListo) {
                cartucho[0].iniciarPosicion(getColumna(), getRenglon(), 0, -40);
                cartucho[0].setEstado(Bala.VUELO);
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

    @Override
    public void reaparecer() {
        //Se vuelve a iniciar la nave
        Random();

        //Posición inicial: Y aleatoria, pero X a la derecha del limite
        setRenglon(getPosRanY());
        //Si su getPosRanX es a la derecha, entra desde la  derecha
        if (getPosRanX() > getCanvas().pideLimiteXMax() / 2) {
            setColumna(getCanvas().pideLimiteXMax() + 100);
        }
        //Si su getPosRanX es a la iaquierda, entra desde la izquierda
        if (getPosRanX() < getCanvas().pideLimiteXMax() / 2) {
            setColumna(getCanvas().pideLimiteXMin() - 100);
        }
        //Restauramos la salud y visibilidad de la nave según su tipo
        setVisible(true);
        setDanioFatal(false);
        // Aquí le devolvemos la vida base.
        setVidas(2);  // AveDePresa
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

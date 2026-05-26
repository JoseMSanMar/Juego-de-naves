package juego;

import edu.epromero.util.Lienzo;
import edu.epromero.util.ComportamientoEnemigo;
import edu.epromero.util.FabricaAudio;

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
        setNomSprite("/resources/Destructor.png");
        inicia();
        //Se inicia la direccion de la nave
        this.componenteX = DERECHA;
        setPuntos(50);
        setVidas(2);
    }

    @Override
    public void Mueve(Entrada e) {
        //**********CONTROL DEL MOVIMIENTO Y DIRECCION*********
        //Averiguar si se acerca a la derecha
        if (getColumna() > (e.getMiCanvas().pideLimiteXMax()) - 180) {
            setComponenteX(IZQUIERDA);
        }
        //Averiguar si se acerca a la izquierda
        if (getColumna() < (e.getMiCanvas().pideLimiteXMin()) + 180) {
            setComponenteX(DERECHA);
        }
        //actualiza la pocision de la nave
        setColumna(getColumna() + (componenteX * 30));
        //**********CONTROL DE LOS DISPAROS Y LAS BALAS**********
        Bala[] cartucho = getCargador();
        //Lee si la nave disparó
        dispara();

        // ACTUALIZACIÓN DE LA BALA: Si la bala está activa, avanza UN paso
        if (cartucho[0].getEstado() == Bala.VUELO) {
            cartucho[0].Mueve(e);
        }
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
                FabricaAudio miAudio = new FabricaAudio();
                miAudio.reproducir(".//src//disparo.wav");
                //Colocamos la probabilidad de disparo por lado del  50%
                if (Math.random() < 0.5) {
                    cartucho[0].iniciarPosicion(getColumna(),
                            getRenglon(), -15, -30);
                    cartucho[0].setEstado(Bala.VUELO);
                }
                if (Math.random() > 0.5) {
                    cartucho[0].iniciarPosicion(getColumna(),
                            getRenglon(), 15, -30);
                    cartucho[0].setEstado(Bala.VUELO);
                }
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

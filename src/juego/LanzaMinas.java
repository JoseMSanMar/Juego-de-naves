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
        //Ruta de tu imagen enemiga
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
        aparecer();
    }

    @Override
    public void Mueve(Entrada e) {
        //Patrullaje horizontal
        if (getColumna() > (e.getMiCanvas().pideLimiteXMax()) - 120) {
            setComponenteX(IZQUIERDA);
        }
        if (getColumna() < (e.getMiCanvas().pideLimiteXMin()) + 120) {
            setComponenteX(DERECHA);
        }
        setColumna(getColumna() + (getComponenteX() * 10));

        dispara();

        // Ciclo para mover las balas que estén volando
        Bala[] listaBalas = getCargador();
        for (int i = 0; i < listaBalas.length; i++) {
            if (listaBalas[i].getEstado() == Bala.VUELO) {
                listaBalas[i].Mueve(e);
            }
        }

        // Fase de reaparición / entrada suave
        if (getRenglon() > (e.getMiCanvas().pideLimiteYMax()) - 150) {
            setRenglon(getRenglon() - 15);
        }
    }

    public void dispara() {
        if (Math.random() < 0.08) { // 8% de probabilidad por frame
            Bala[] lista = getCargador();

            // Verificamos si todo el cargador está despejado
            boolean cargadorListo = true;
            for (int i = 0; i < lista.length; i++) {
                if (lista[i].getEstado() != Bala.INACTIVA) {
                    cargadorListo = false;
                }
            }

            // Si las 3 casillas están libres, desatamos el ataque triple
            if (cargadorListo) {
                // Bala 0 Se abre en diagonal hacia la izquierda (-20)
                lista[0].iniciarPosicion(getColumna(), getRenglon(), -15, -40);
                lista[0].setEstado(Bala.VUELO);

                // Bala 1 Cae recta en vertical (0)
                lista[1].iniciarPosicion(getColumna(), getRenglon(), 0, -40);
                lista[1].setEstado(Bala.VUELO);

                // Bala 2 Se abre en diagonal hacia la derecha (+20)
                lista[2].iniciarPosicion(getColumna(), getRenglon(), 15, -40);
                lista[2].setEstado(Bala.VUELO);
            }
        }
    }

    public boolean hayColision(Bala bala) {
        //Declaramos por defecto la colision en false
        boolean siHayColision;
        siHayColision = false;
        //Evalua el area de colision de la nave
        if (getRenglon() < bala.getRenglon() + 80
                && getRenglon() > bala.getRenglon() - 80) {
            if (getColumna() < bala.getColumna() + 80
                    && getColumna() > bala.getColumna() - 80) {
                siHayColision = true;
            }
        }
        //Regresa el resultado de la evaluacion
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

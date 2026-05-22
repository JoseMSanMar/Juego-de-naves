package juego;

import edu.epromero.util.Destruible;
import edu.epromero.util.Lienzo;
import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_SPACE;

/**
 *
 * @author Alumno
 */
public class Heroe extends ElementoGrafico implements Destruible {

    private Bala bala;
    private boolean danioFatal;
    private int vidas;
    private int puntosObtenidos;

    public Heroe() {
        setNomSprite("/resources/Heroe.png");
        inicia();
        getMiImagen().ponColorTransparente(Lienzo.BLANCO);
        bala = new Bala();
        setVidas(10);
        //public void ponPuntos(int puntos)
    }

    public void pinta(Lienzo canvas) {
        setCanvas(getCanvas());
        getCanvas().dibujo(getColumna(), getRenglon(), getMiImagen());
        if (getBala().getEstado() != Bala.INACTIVA) {
            getBala().pinta(getCanvas());
        }
    }

    @Override
    public boolean recibirDanio() {
        if (getVidasActuales() <= 0) {
            setDanioFatal(true);
        }
        return isDanioFatal();
    }

    public void perderVida() {
        setVidas(getVidasActuales() - 1);
    }

    public void destruir(boolean danio) {
        if (danio) {
            setVisible(false);
        }
    }

    public void iniciarPosicion(Lienzo canvas) {
        setCanvas(canvas);
        setColumna(getCanvas().pideLimiteXMax() / 2);
        setRenglon(getCanvas().pideLimiteYMin() + 200);
        aparecer();

    }

    @Override
    public void Mueve(Entrada e) {
        if (e.getMiCanvas().fuePulsadaTecla(VK_LEFT)
                || e.getMiCanvas().fuePulsadaTecla(VK_A)) {
            if (getColumna() >= getCanvas().pideLimiteXMin() + 60) {
                //System.out.println("Tecla arriba pulsada antes: " + columna);
                setColumna(getColumna() - 20);
            }
            //System.out.println("Tecla arriba pulsada despues: " + columna);
        }
        if (e.getMiCanvas().fuePulsadaTecla(VK_RIGHT)
                || e.getMiCanvas().fuePulsadaTecla(VK_D)) {
            if (getColumna() <= getCanvas().pideLimiteXMax() - 60) {
                //System.out.println("Tecla abajo pulsada: " + columna);
                setColumna(getColumna() + 20);
            }
        }
        // Lógica del disparo: Revisamos el gatillo
        dispara(e);

        // ACTUALIZACIÓN DE LA BALA: Si la bala está activa, avanza UN paso
        if (getBala().getEstado() == Bala.VUELO) {
            getBala().Mueve(e);
        }
    }

    public boolean hayColision(Bala bala) {
        boolean siHayColision;
        siHayColision = false;
        if (isVisible()) {
            if (getRenglon() < bala.getRenglon() + 80
                    && getRenglon() > bala.getRenglon() - 80) {
                if (getColumna() < bala.getColumna() + 80
                        && getColumna() > bala.getColumna() - 80) {
                    siHayColision = true;
                }
            }
        }

        return siHayColision;
    }

    public void dispara(Entrada e) {
        if (e.getMiCanvas().fuePulsadaTecla(VK_SPACE)) {
            // Regla fundamental:
            //Solo puedes disparar si no hay otra bala tuya en vuelo
            if (getBala().getEstado() == Bala.INACTIVA) {
                // La bala nace JUSTO en la posición actual de la nave
                getBala().iniciarPosicion(getColumna(), getRenglon(), 80);
                // Le damos luz verde para que empiece a volar
                getBala().setEstado(Bala.VUELO);
            }
        }
    }

    public void ponPuntos(int puntos) {
        setPuntosObtenidos(getPuntosObtenidos() + puntos);
    }

    /**
     * @return the bala
     */
    public Bala getBala() {
        return bala;
    }

    /**
     * @param bala the bala to set
     */
    public void setBala(Bala bala) {
        this.bala = bala;
    }

    @Override
    public int getVidasActuales() {
        return vidas;
    }

    /**
     * @param vidas the vidas to set
     */
    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    /**
     * @return the danioFatal
     */
    public boolean isDanioFatal() {
        return danioFatal;
    }

    /**
     * @param danioFatal the danioFatal to set
     */
    public void setDanioFatal(boolean danioFatal) {
        this.danioFatal = danioFatal;
    }

    /**
     * @return the puntosObtenidos
     */
    public int getPuntosObtenidos() {
        return puntosObtenidos;
    }

    /**
     * @param puntosObtenidos the puntosObtenidos to set
     */
    public void setPuntosObtenidos(int puntosObtenidos) {
        this.puntosObtenidos = puntosObtenidos;
    }
}
